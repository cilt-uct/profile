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

import org.sakaiproject.api.app.profile.Profile;
import org.sakaiproject.api.app.profile.ProfileManager;
import org.sakaiproject.service.framework.log.Logger;
import org.sakaiproject.service.framework.portal.cover.PortalService;
import org.sakaiproject.service.legacy.site.cover.SiteService;
import org.sakaiproject.util.text.FormattedText;

/**
 * @author rshastri TODO To change the template for this generated type comment go to Window -
 *         Preferences - Java - Code Style - Code Templates
 */
public class ProfileTool
{
  private static final String ANONYMOUS = "Anonymous";
  private static final String NONE = "none";
  private static final String UNIVERSITY_PHOTO = "universityId";
  private static final String PICTURE_URL = "pictureUrl";

  private Logger logger;
  private ProfileManager profileService;
  private Profile profile;

  private boolean showTool = false;
  private String title;
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
    logger.debug(this + "processActionEditSave()");
    displayEvilTagMsg = false;
    displayEmptyFirstNameMsg = false;
    displayEmptyLastNameMsg = false;
    displayMalformedUrlError = false;
    if ((profile != null) && (profile.getUserId() == null))
    {
      logger.error(this + "processActionEditSave :" + "No User Found");

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
        logger.warn(this + " " + errorMsg, e);
      }
    }

    if ((getPictureIdPreference() != null)
        && getPictureIdPreference().equals(UNIVERSITY_PHOTO))
    {
      profile.setInstitutionalPictureIdPreferred(new Boolean(true));
      profile.setPictureUrl(null);
      this.displayPicture = true;
      this.pictureIdPreference = UNIVERSITY_PHOTO;
    }
    else
      if ((getPictureIdPreference() != null)
          && (getPictureIdPreference().equals(PICTURE_URL)))
      {
        profile.setInstitutionalPictureIdPreferred(new Boolean(false));
        this.displayPicture = true;
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
        this.displayPicture = false;
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
      logger
          .debug(this + "User record updated for Id :-" + profile.getUserId());

      return "main";
    }

    catch (Exception e)
    {
      logger.debug(e.getMessage(), e);
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
    logger.debug(this + "processActionEdit()");

    try
    {
      if ((profile != null) && (profile.getUserId() == null))
      {
        logger.equals(this + "processActionEdit : " + "No User Found");

        return "PermissionException";
      }

      setPictureIdPreference(profile);

      return "edit";
    }
    catch (Exception e)
    {
      logger.debug(e.getMessage(), e);

      return null;
    }
  }

  /**
   * @return
   */
  public String processCancel()
  {
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
    logger.debug("getProfile()");

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
    return profileService;
  }

  /**
   * Setter for ProfileManager service
   *
   * @param profileService
   */
  public void setProfileService(ProfileManager profileService)
  {
    this.profileService = profileService;
  }

  /**
   * Getter for Logger
   *
   * @return instance of Logger
   */
  public Logger getLogger()
  {
    return logger;
  }

  /**
   * Setter for Logger Service
   *
   * @param logger
   */
  public void setLogger(Logger logger)
  {
    this.logger = logger;
  }

  public boolean isDisplayNoProfileMsg()
  {
    return displayNoProfileMsg;
  }

  /**
   * Getter for property if the tool bean is loaded for first time
   *
   * @return boolean value
   */
  public boolean isLoadingFirstTime()
  {
    logger.debug(this + "isLoadingFirstTime()");

    return loadingFirstTime;
  }

  /**
   * Returns display picture preference
   *
   * @return String
   */
  public String getPictureIdPreference()
  {
    logger.debug(this + "getPictureIdPreference()");

    return pictureIdPreference;
  }

  /**
   * Set display picture preference
   *
   * @param pictureIDPreference
   */
  public void setPictureIdPreference(String pictureIdPreference)
  {
    logger.debug(this + "setPictureIDPreference(" + pictureIdPreference + ")");
    this.pictureIdPreference = pictureIdPreference;
  }

  public boolean isShowTool()
  {
    //implement isAnonymous later on.
    if (getProfile().getUserId() != ANONYMOUS)
    {
      this.showTool = true;
    }
    else
    {
      this.showTool = false;
    }

    return showTool;
  }

  /**
   * @return
   */
  public String getTitle()
  {
    return SiteService.findTool(PortalService.getCurrentToolId()).getTitle();
  }

  /**
   * @return
   */
  public String getEvilTagMsg()
  {
    return evilTagMsg;
  }

  /**
   * @return
   */
  public boolean isDisplayEvilTagMsg()
  {
    return displayEvilTagMsg;
  }

  /**
   * @return
   */
  public boolean isDisplayEmptyFirstNameMsg()
  {
    return displayEmptyFirstNameMsg;
  }

  /**
   * @return
   */
  public boolean isDisplayEmptyLastNameMsg()
  {
    return displayEmptyLastNameMsg;
  }

  /**
   * @return
   */
  public boolean isDisplayPicture()
  {
    return !(getPictureIdPreference() == NONE);
  }

  /**
   * @param profile
   */
  public void setProfile(Profile profile)
  {
    this.profile = profile;
  }

  /**
   * @return
   */
  public boolean isDisplayPictureURL()
  {
    if (getPictureIdPreference() == PICTURE_URL
        && profile.getPictureUrl() != null
        && profile.getPictureUrl().trim().length() > 0)
      return true;
    else
      return false;
  }

  /**
   * @return
   */
  public boolean isDisplayUniversityPhoto()
  {
    if (getPictureIdPreference() == UNIVERSITY_PHOTO
        && profile.isInstitutionalPictureIdPreferred() != null
        && profile.isInstitutionalPictureIdPreferred().booleanValue()
        && profileService.getInstitutionalPhotoByUserId(profile.getUserId()) != null)
      return true;
    else
      return false;
  }

  public boolean isDisplayUniversityPhotoUnavailable()
  {
    if (getPictureIdPreference() == UNIVERSITY_PHOTO
        && profile.isInstitutionalPictureIdPreferred() != null
        && profile.isInstitutionalPictureIdPreferred().booleanValue()
        && (profileService.getInstitutionalPhotoByUserId(profile.getUserId()) == null || profileService
            .getInstitutionalPhotoByUserId(profile.getUserId()).length < 1))
      return true;
    else
      return false;
  }

  /**
   * @param profile
   */
  private void setPictureIdPreference(Profile profile)
  {
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

  public boolean isDisplayMalformedUrlError()
  {
    return displayMalformedUrlError;
  }

  public String getMalformedUrlError()
  {
    return malformedUrlError;
  }

}
