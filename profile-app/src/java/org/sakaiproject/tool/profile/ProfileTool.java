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
  private static final String UNIVERSITY_ID = "universityId";
  private Logger logger;
  private ProfileManager profileService;
  private Profile profile;
  private boolean loadingFirstTime = true;
  private String displayPicture = null;
  private boolean pictureDisplayed = false;
  private boolean showTool = false;
  private String title;
  private String pictureIdPreference = UNIVERSITY_ID;
  private boolean displayNoProfileMsg = false;
  private boolean displayEvilTagMsg=false;
  private String evilTagMsg=null;
  private boolean displayEmptyFirstNameMsg=false;
  private boolean displayEmptyLastNameMsg=false;

  /**
   * Process data for save action on edit page.
   * 
   * @return navigation outcome: return to main page or if no user is present throw permission exception
   */
  public String processActionEditSave()
  {
    logger.debug(this + "processActionEditSave()");
    displayEvilTagMsg=false;
    displayEmptyFirstNameMsg=false;
    displayEmptyLastNameMsg=false;
    if ((profile != null) && (profile.getUserId() == null))
    {
      logger.error(this + "processActionEditSave :" + "No User Found");

      return "permissionException";
    }
    if(profile.getFirstName() == null || profile.getFirstName().trim().length()< 1)
    {
      displayEmptyFirstNameMsg = true;
      return "edit";          
    }
    if(profile.getLastName() == null || profile.getLastName().trim().length()< 1)
    {
      displayEmptyLastNameMsg = true;
      return "edit";          
    }
    if(profile.getOtherInformation()!=null)
    {
    	StringBuffer alertMsg = new StringBuffer();
    	String errorMsg= null;
    	try
		{
			errorMsg =  FormattedText.processFormattedText(profile.getOtherInformation(), alertMsg);
			if (alertMsg.length() > 0)
			{
				evilTagMsg =alertMsg.toString();
				displayEvilTagMsg=true;
				return "edit";
			}
		 }
		catch (Exception e)
		{
			logger.warn(this + " " + errorMsg,e);
		}
    }

    if ((getPictureIdPreference() != null)
        && getPictureIdPreference().equals(UNIVERSITY_ID))
    {
      profile.setInstitutionalPictureIdPreferred(new Boolean(true));
    }
    else
    {
      profile.setInstitutionalPictureIdPreferred(new Boolean(false));
    }
    

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

      if ((profile != null)
          && (profile.isInstitutionalPictureIdPreferred() != null)
          && profile.isInstitutionalPictureIdPreferred().booleanValue())
      {
        setPictureIdPreference(UNIVERSITY_ID);
      }
      else
      {
        setPictureIdPreference(NONE);
      }

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
    this.displayPicture = profileService.getDisplayPictureUrl(profile);

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
      loadingFirstTime = false;
    }
    else
    {
      if(profile == null)
      {
        displayNoProfileMsg = true;
      }
      else
      {
        if((profile.getFirstName() == null) || (profile.getLastName() == null))
        {
          displayNoProfileMsg = true;
        }
        else
        {
          if(profile.getFirstName().equalsIgnoreCase("") || profile.getLastName().equalsIgnoreCase(""))
            displayNoProfileMsg = true;
          else
            displayNoProfileMsg = false;
        }
      }
    }

    this.displayPicture = profileService.getDisplayPictureUrl(profile);

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
  
  public void setDisplayNoProfileMsg(boolean displayNoProfileMsg)
  {
    this.displayNoProfileMsg = displayNoProfileMsg;
  }
  
  public boolean getDisplayNoProfileMsg()
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
   * Setter for property
   * 
   * @param loadingFirstTime
   */
  public void setLoadingFirstTime(boolean loadingFirstTime)
  {
    logger.debug(this + "setLoadingFirstTime(" + loadingFirstTime + ")");
    this.loadingFirstTime = loadingFirstTime;
  }

  /**
   * Returns the url for display picture
   * 
   * @return String
   */
  public String getDisplayPicture()
  {
    logger.debug(this + "getProfileService()");

    return profileService.getDisplayPictureUrl(profile);
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

  /**
   * Examine if the url to display picture is null or no length
   * 
   * @return boolean value
   */
  public boolean isPictureDisplayed()
  {
    logger.debug(this + "isPictureDisplayed()");

    return (this.displayPicture != null);// && (this.displayPicture.trim().length() > 0));
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

  public String getTitle()
  {
    return SiteService.findTool(PortalService.getCurrentToolId()).getTitle();
  }
  
  public String getEvilTagMsg() 
  {
	return evilTagMsg;
  }

  public void setEvilTagMsg(String evilTagMsg) 
  {
	this.evilTagMsg = evilTagMsg;
  }
  
  public boolean getDisplayEvilTagMsg()
  {
	return displayEvilTagMsg;
  }

  public void setDisplayEvilTagMsg(boolean displayEvilTagMsg) 
  {
	this.displayEvilTagMsg = displayEvilTagMsg;
  }

  public boolean isDisplayEmptyFirstNameMsg()
  {
	return displayEmptyFirstNameMsg;
  }

  public void setDisplayEmptyFirstNameMsg(boolean displayEmptyFirstNameMsg) 
  {
	this.displayEmptyFirstNameMsg = displayEmptyFirstNameMsg;
  }

  public boolean isDisplayEmptyLastNameMsg() 
  {
	return displayEmptyLastNameMsg;
  }

  public void setDisplayEmptyLastNameMsg(boolean displayEmptyLastNameMsg) 
  {
	this.displayEmptyLastNameMsg = displayEmptyLastNameMsg;
  }

 
}

