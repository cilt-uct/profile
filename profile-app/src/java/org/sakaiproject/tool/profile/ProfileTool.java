/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005 The Regents of the University of Michigan, Trustees of Indiana University,
 *                  Board of Trustees of the Leland Stanford, Jr., University, and The MIT Corporation
 *
 * Licensed under the Educational Community License Version 1.0 (the "License");
 * By obtaining, using and/or copying this Original Work, you agree that you have read,
 * understand, and will comply with the terms and conditions of the Educational Community License.
 * You may obtain a copy of the License at:
 *
 *      http://cvs.sakaiproject.org/licenses/license_1_0.html
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 **********************************************************************************/
package org.sakaiproject.tool.profile;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.api.app.profile.Profile;
import org.sakaiproject.api.app.profile.ProfileManager;
import org.sakaiproject.service.framework.portal.cover.PortalService;
import org.sakaiproject.service.legacy.site.cover.SiteService;
import org.sakaiproject.util.text.FormattedText;

/**
 * @author rshastri 
 * 
 * */
public class ProfileTool
{
  private static final Log LOG = LogFactory.getLog(ProfileTool.class);
  private static final String NONE = "none";
  private static final String UNIVERSITY_PHOTO = "universityId";
  private static final String PICTURE_URL = "pictureUrl";
  
  private ProfileManager profileService;
  private Profile profile;
 
  private boolean loadingFirstTime = true;

  private String pictureIdPreference = NONE;
  private boolean displayPicture = false;
  private boolean displayNoProfileMsg = false;
  private boolean displayEvilTagMsg = false;
  private boolean displayEmptyFirstNameMsg = false;
  private boolean displayEmptyLastNameMsg = false;
  private boolean displayMalformedUrlError = false;
  
  private String malformedUrlError = null;
  private String evilTagMsg = null;

  /**
   * Process data for save action on edit page.
   *
   * @return navigation outcome: return to main page or if no user is present throw permission exception
   */
  public String processActionEditSave()
  {
    LOG.debug("processActionEditSave()");
    displayEvilTagMsg = false;
    displayEmptyFirstNameMsg = false;
    displayEmptyLastNameMsg = false;
    displayMalformedUrlError = false;
    if ((profile != null) && (profile.getUserId() == null))
    {
      LOG.error("processActionEditSave :" + "No User Found");

      return "permissionException";
    }
    if (profile.getFirstName() == null
        || profile.getFirstName().trim().length() < 1)
    {
      displayEmptyFirstNameMsg = true;
      return "edit";
    }
    if (profile.getLastName() == null
        || profile.getLastName().trim().length() < 1)
    {
      displayEmptyLastNameMsg = true;
      return "edit";
    }
    if (profile.getOtherInformation() != null)
    {
      StringBuffer alertMsg = new StringBuffer();
      String errorMsg = null;
      try
      {
        errorMsg = FormattedText.processFormattedText(profile
            .getOtherInformation(), alertMsg);
        if (alertMsg.length() > 0)
        {
          evilTagMsg = alertMsg.toString();
          displayEvilTagMsg = true;
          return "edit";
        }
      }
      catch (Exception e)
      {
        LOG.error(" " + errorMsg, e);
      }
    }

    if ((getPictureIdPreference() != null)
        && getPictureIdPreference().equals(UNIVERSITY_PHOTO))
    {
      profile.setInstitutionalPictureIdPreferred(new Boolean(true));
      profile.setPictureUrl(null);
      displayPicture = true;
      this.pictureIdPreference = UNIVERSITY_PHOTO;
    }
    else
      if ((getPictureIdPreference() != null)
          && (getPictureIdPreference().equals(PICTURE_URL)))
      {
        profile.setInstitutionalPictureIdPreferred(new Boolean(false));
        displayPicture = true;
        this.pictureIdPreference = PICTURE_URL;
        if (profile.getPictureUrl() != null
            && profile.getPictureUrl().trim().length() > 0)
        {
          try
          {
            URL pictureUrl = new URL(profile.getPictureUrl());
          }
          catch (MalformedURLException e)
          {
            this.displayMalformedUrlError = true;
            this.malformedUrlError = "Please check picture URL again ("
                + e.getMessage() + ")";
            return "edit";
          }
        }
      }
      else
      {
        //    returns null or none
        profile.setInstitutionalPictureIdPreferred(new Boolean(false));
        profile.setPictureUrl(null);
        displayPicture = false;
        this.pictureIdPreference = NONE;
      }

    //   Catch a bad url passed in homepage.  
    //    if(profile.getHomepage()!=null && profile.getHomepage().trim().length()>0)
    //    {
    //      try
    //      {
    //      URL pictureUrl = new URL(profile.getPictureUrl());
    //      }
    //      catch (MalformedURLException e)
    //      {
    //        this.displayMalformedHomePage = true;
    //        this.malformedHomePageUrlError= "Please check HomePage URL again (" + e.getMessage()+")";
    //        return "edit";
    //      }
    //    }
    try
    {
      profileService.save(profile);
      LOG.debug("User record updated for Id :-" + profile.getUserId());

      return "main";
    }

    catch (Exception e)
    {
      LOG.debug(e.getMessage(), e);
    }
    return "main";
  }

  /**
   * Setup before navigating to edit page
   *
   * @return navigation outcome: return to edit page or if no user is present throw permission exception
   */
  public String processActionEdit()
  {
    LOG.debug("processActionEdit()");
    try
    {
      if ((profile != null) && (profile.getUserId() == null))
      {
        LOG.equals("processActionEdit : " + "No User Found");
        return "PermissionException";
      }
      setPictureIdPreference(profile);
      return "edit";
    }
    catch (Exception e)
    {
      LOG.error(e.getMessage(), e);
      return null;
    }
  }

  /**
   * @return
   */
  public String processCancel()
  {
    LOG.debug("processCancel()");
    profile = profileService.getProfile();
    return "main";
  }

  /**
   * Setup to fetch a profile
   *
   * @return Profile for user logged in or empty profile
   */
  public Profile getProfile()
  {
    LOG.debug("getProfile()");
    if (loadingFirstTime)
    {
      profile = profileService.getProfile();
      setPictureIdPreference(profile);
      loadingFirstTime = false;
    }
    else
    {
      if (profile == null)
      {
        displayNoProfileMsg = true;
      }
      else
      {
        if ((profile.getFirstName() == null) || (profile.getLastName() == null))
        {
          displayNoProfileMsg = true;
        }
        else
        {
          if (profile.getFirstName().equalsIgnoreCase("")
              || profile.getLastName().equalsIgnoreCase(""))
            displayNoProfileMsg = true;
          else
            displayNoProfileMsg = false;
        }
      }
    }
    return profile;
  }

  /**
   * Getter for ProfileManager service
   *
   * @return instance of ProfileManager
   */
  public ProfileManager getProfileService()
  {
    LOG.debug("getProfileService()");
    return profileService;
  }

  /**
   * Setter for ProfileManager service
   *
   * @param profileService
   */
  public void setProfileService(ProfileManager profileService)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("setProfileService(ProfileManager" + profileService + ")()");
    }
    this.profileService = profileService;

  }

  /**
   * @return
   */
  public boolean isDisplayNoProfileMsg()
  {
    LOG.debug("isDisplayNoProfileMsg()");
    return displayNoProfileMsg;
  }

  /**
   * Getter for property if the tool bean is loaded for first time
   *
   * @return boolean value
   */
  public boolean isLoadingFirstTime()
  {
    LOG.debug("isLoadingFirstTime()");
    return loadingFirstTime;
  }

  /**
   * Returns display picture preference
   *
   * @return String
   */
  public String getPictureIdPreference()
  {
    LOG.debug("getPictureIdPreference()");
    return pictureIdPreference;
  }

  /**
   * Set display picture preference
   *
   * @param pictureIDPreference
   */
  public void setPictureIdPreference(String pictureIdPreference)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("setPictureIDPreference(String" + pictureIdPreference + ")");
    }
    this.pictureIdPreference = pictureIdPreference;
  }

  public boolean isShowTool()
  {
    LOG.debug("isShowTool()");
    return profileService.isShowTool();
  }

  /**
   * @return
   */
  public String getTitle()
  {
    LOG.debug("getTitle()");
    return SiteService.findTool(PortalService.getCurrentToolId()).getTitle();
  }

  /**
   * @return
   */
  public String getEvilTagMsg()
  {
    LOG.debug("getEvilTagMsg()");
    return evilTagMsg;
  }

  /**
   * @return
   */
  public boolean isDisplayEvilTagMsg()
  {
    LOG.debug("isDisplayEvilTagMsg()");
    return displayEvilTagMsg;
  }

  /**
   * @return
   */
  public boolean isDisplayEmptyFirstNameMsg()
  {
    LOG.debug("isDisplayEmptyFirstNameMsg()");
    return displayEmptyFirstNameMsg;
  }

  /**
   * @return
   */
  public boolean isDisplayEmptyLastNameMsg()
  {
    LOG.debug("isDisplayEmptyLastNameMsg()");
    return displayEmptyLastNameMsg;
  }

  /**
   * @return
   */
  public boolean isDisplayNoPicture()
  {
    LOG.debug("isDisplayPicture()");
    return profileService.isDisplayNoPhoto(profile);
  }

  /**
   * @param profile
   */
  public void setProfile(Profile profile)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("setProfile(Profile" + profile + ")");
    }
    this.profile = profile;
  }

  /**
   * @return
   */
  public boolean isDisplayPictureURL()
  {
    LOG.debug("isDisplayPictureURL()");
    return profileService.isDisplayPictureURL(profile);
  }

  /**
   * @return
   */
  public boolean isDisplayUniversityPhoto()
  {
    LOG.debug("isDisplayUniversityPhoto()");
    return profileService.isDisplayUniversityPhoto(profile);
  }

  /**
   * @return
   */
  public boolean isDisplayUniversityPhotoUnavailable()
  {
    LOG.debug("isDisplayUniversityPhotoUnavailable()");
    return profileService.isDisplayUniversityPhotoUnavailable(profile);
  }

  /**
   * @param profile
   */
  private void setPictureIdPreference(Profile profile)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("setPictureIdPreference(Profile" + profile + ")");
    }
    if (profile.isInstitutionalPictureIdPreferred() != null
        && profile.isInstitutionalPictureIdPreferred().booleanValue() == true)
    {
      this.pictureIdPreference = UNIVERSITY_PHOTO;
      this.displayPicture = true;
    }
    else
      if (profile.getPictureUrl() != null
          && profile.getPictureUrl().length() > 0)
      {
        this.pictureIdPreference = PICTURE_URL;
        this.displayPicture = true;
      }
      else
      {
        this.pictureIdPreference = NONE;
        this.displayPicture = false;
      }

  }

  /**
   * @return
   */
  public boolean isDisplayMalformedUrlError()
  {
    LOG.debug("isDisplayMalformedUrlError()");
    return displayMalformedUrlError;
  }

  /**
   * @return
   */
  public String getMalformedUrlError()
  {
    LOG.debug("getMalformedUrlError()");
    return malformedUrlError;
  }

}
