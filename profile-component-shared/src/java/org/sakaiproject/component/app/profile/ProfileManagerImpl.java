/**********************************************************************************
 * $URL$
 * $Id$
 **********************************************************************************
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

package org.sakaiproject.component.app.profile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.api.app.profile.Profile;
import org.sakaiproject.api.app.profile.ProfileManager;
import org.sakaiproject.api.common.agent.Agent;
import org.sakaiproject.api.common.agent.AgentGroupManager;
import org.sakaiproject.api.common.edu.person.SakaiPerson;
import org.sakaiproject.api.common.edu.person.SakaiPersonManager;
import org.sakaiproject.api.kernel.session.cover.SessionManager;
import org.sakaiproject.api.kernel.tool.Placement;
import org.sakaiproject.api.kernel.tool.cover.ToolManager;
import org.sakaiproject.exception.IdUnusedException;
import org.sakaiproject.service.legacy.authzGroup.AuthzGroup;
import org.sakaiproject.service.legacy.authzGroup.cover.AuthzGroupService;
import org.sakaiproject.service.legacy.security.cover.SecurityService;
import org.sakaiproject.service.legacy.site.cover.SiteService;

/**
 * @author rshastri
 * @version $Id$
 */
public class ProfileManagerImpl implements ProfileManager
{
  private static final Log LOG = LogFactory.getLog(ProfileManagerImpl.class);

  /** Dependency: SakaiPersonManager */
  private SakaiPersonManager sakaiPersonManager;
  /** Dependency: AgentGroupManager */
  private AgentGroupManager agentGroupManager;

  private static final String ANONYMOUS = "Anonymous";

  public void init()
  {
    LOG.debug("init()");
    ; // do nothing (for now)
  }

  public void destroy()
  {
    LOG.debug("destroy()");
    ; // do nothing (for now)
  }

  /**
   * @see org.sakaiproject.api.app.profile.ProfileManager#getProfile()
   */
  public Profile getProfile()
  {
    LOG.debug("getProfile()");

    //TODO Find a better displayName
    final String displayName = getCurrentUser();

    return getProfileById(displayName, getCurrentUser(), SessionManager
        .getCurrentSession().getUserId());
  }

  /**
   * @see org.sakaiproject.api.app.profile.ProfileManager#findProfiles(java.lang.String)
   * Returns userMutable profiles only 
   */
  public List findProfiles(String searchString)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("findProfiles(" + searchString + ")");
    }
    if (searchString == null || searchString.length() < 1)
      throw new IllegalArgumentException(
          "Illegal searchString argument passed!");

    List profiles = sakaiPersonManager.findSakaiPerson(searchString);
    List searchResults = new ArrayList();
    Profile profile;

    if ((profiles != null) && (profiles.size() > 0))
    {
      Iterator profileIterator = profiles.iterator();

      while (profileIterator.hasNext())
      {
        profile = new ProfileImpl((SakaiPerson) profileIterator.next());

        //Select the user mutable profile for display on if the public information is viewable.
        if ((profile != null)
            && profile.getSakaiPerson().getTypeUuid().equals(
                sakaiPersonManager.getUserMutableType().getUuid()))
        {
          if ((getCurrentUser().equals(profile.getUserId()) || SecurityService
              .isSuperUser()))
          {
            //allow user  to search and view own profile and superuser to view all profiles
            searchResults.add(profile);
          }
          else
            if ((profile.getHidePublicInfo() != null)
                && (profile.getHidePublicInfo().booleanValue() != true))
            {
              if (profile.getHidePrivateInfo() != null
                  && profile.getHidePrivateInfo().booleanValue() != true)
              {
                searchResults.add(profile);
              }
              else
              {
                searchResults.add(getOnlyPublicProfile(profile));
              }

            }
        }

      }
    }

    return searchResults;
  }

  /**
   * @see org.sakaiproject.api.app.profile.ProfileManager#save(org.sakaiproject.api.app.profile.Profile)
   */
  public void save(Profile profile)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("save(" + profile + ")");
    }
    if (profile == null)
      throw new IllegalArgumentException("Illegal profile argument passed!");

    sakaiPersonManager.save(profile.getSakaiPerson());
  }

  /**
   * @param sakaiPersonManager
   */
  public void setSakaiPersonManager(SakaiPersonManager sakaiPersonManager)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("setSakaiPersonManager(SakaiPersonManager "
          + sakaiPersonManager + ")");
    }

    this.sakaiPersonManager = sakaiPersonManager;
  }

  /**
   * @param agentGroupManager
   *          The agentGroupManager to set.
   */
  public void setAgentGroupManager(AgentGroupManager agentGroupManager)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("setAgentGroupManager(AgentGroupManager " + agentGroupManager
          + ")");
    }

    this.agentGroupManager = agentGroupManager;
  }

  /* (non-Javadoc)
   * @see org.sakaiproject.api.app.profile.ProfileManager#getInstitutionalPhotoByUserId(java.lang.String)
   */
  public byte[] getInstitutionalPhotoByUserId(String uid)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("getInstitutionalPhotoByUserId(String " + uid + ")");
    }
    return getInstitutionalPhoto(uid, false);

  }

  public byte[] getInstitutionalPhotoByUserId(String uid, boolean siteMaintainer)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("getInstitutionalPhotoByUserId(String" + uid + ", boolean "
          + siteMaintainer + ")");
    }
    return getInstitutionalPhoto(uid, true);
  }

  /* (non-Javadoc)
   * @see org.sakaiproject.api.app.profile.ProfileManager#getUserProfileById(java.lang.String)
   */
public Profile getUserProfileById(String id)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("getUserProfileById(String" + id + ")");
    }
    SakaiPerson sakaiPerson=sakaiPersonManager.getSakaiPerson(getAgentUuidByEnterpriseId(id),sakaiPersonManager.getUserMutableType());
    if (sakaiPerson==null)
    {
      return null;
    }
    return new ProfileImpl(sakaiPerson);
  }

  public String getAgentUuidByEnterpriseId(String uid)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug(" getAgentUuidByEnterpriseId(String " + uid + ")");
    }
    if (uid == null || (uid != null && uid.trim().length() < 0))
    {
      return null;
    }

    Agent agent = agentGroupManager.getAgentByEnterpriseId(uid);
    if(agent==null)
    {
      return null;
    }
    //Do not try to create an agent.
    return agent.getUuid();
  }
  
  /**
   * @param agentUuid
   * @return
   */
  public String getEnterpriseIdByAgentUuid(String agentUuid)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug(" getEnterpriseIdByAgentUuid(String " + agentUuid + ")");
    }
    if (agentUuid == null
        || (agentUuid != null && agentUuid.trim().length() < 0))
    {
      return null;
    }

    Agent agent = agentGroupManager.getAgentByUuid(agentUuid);
    if (agent == null)
    {
      LOG.debug("No agent found for Uuid" + agentUuid);
      return null;
    }
    return agent.getEnterpriseId();
  }

  

  public boolean displayCompleteProfile(Profile profile)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("displayCompleteProfile(Profile" + profile + ")");
    }
    //  complete profile visble to Owner and superUser 
    if ((profile != null)
        && (isCurrentUserProfile(profile) || SecurityService.isSuperUser()))
    {
      return true;
    }
    else
      if ((profile != null)
          && (profile.getHidePrivateInfo().booleanValue() != true)
          && (profile.getHidePublicInfo().booleanValue() != true))
      {
        return true;
      }
      else
      {
        return false;
      }
  }

  /* (non-Javadoc)
   * @see org.sakaiproject.api.app.profile.ProfileManager#isCurrentUserProfile(org.sakaiproject.api.app.profile.Profile)
   */
  public boolean isCurrentUserProfile(Profile profile)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("isCurrentUserProfile(Profile" + profile + ")");
    }
    return ((profile != null) && profile.getUserId().equals(getCurrentUser()));
  }

  /* (non-Javadoc)
   * @see org.sakaiproject.api.app.profile.ProfileManager#isDisplayPictureURL(org.sakaiproject.api.app.profile.Profile)
   */
  public boolean isDisplayPictureURL(Profile profile)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("isDisplayPictureURL(Profile" + profile + ")");
    }
    return (profile != null
        && displayCompleteProfile(profile)
        && (profile.isInstitutionalPictureIdPreferred() == null || profile
            .isInstitutionalPictureIdPreferred().booleanValue() != true)
        && profile.getPictureUrl() != null && profile.getPictureUrl().trim()
        .length() > 0);
  }

  /* (non-Javadoc)
   * @see org.sakaiproject.api.app.profile.ProfileManager#isDisplayUniversityPhoto(org.sakaiproject.api.app.profile.Profile)
   */
  public boolean isDisplayUniversityPhoto(Profile profile)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("isDisplayUniversityPhoto(Profile" + profile + ")");
    }
    return (profile != null && displayCompleteProfile(profile)
        && profile.isInstitutionalPictureIdPreferred() != null
        && profile.isInstitutionalPictureIdPreferred().booleanValue() == true
        && getInstitutionalPhotoByUserId(profile.getUserId()) != null && getInstitutionalPhotoByUserId(profile
        .getUserId()).length > 0);
  }

  /* (non-Javadoc)
   * @see org.sakaiproject.api.app.profile.ProfileManager#isDisplayUniversityPhotoUnavailable(org.sakaiproject.api.app.profile.Profile)
   */
  public boolean isDisplayUniversityPhotoUnavailable(Profile profile)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("isDisplayUniversityPhotoUnavailable(Profile" + profile + ")");
    }
    return (profile != null && displayCompleteProfile(profile)
        && profile.isInstitutionalPictureIdPreferred() != null
        && profile.isInstitutionalPictureIdPreferred().booleanValue() == true
        && getInstitutionalPhotoByUserId(profile.getUserId()) == null && (getInstitutionalPhotoByUserId(profile
        .getUserId()) == null || getInstitutionalPhotoByUserId(profile
        .getUserId()).length < 1));
  }

  /* (non-Javadoc)
   * @see org.sakaiproject.api.app.profile.ProfileManager#isDisplayNoPhoto(org.sakaiproject.api.app.profile.Profile)
   */
  public boolean isDisplayNoPhoto(Profile profile)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("isDisplayNoPhoto(Profile" + profile + ")");
    }
    return (profile == null || !displayCompleteProfile(profile) ||(profile.isInstitutionalPictureIdPreferred()== null || (
        profile.isInstitutionalPictureIdPreferred().booleanValue() != true &&(profile.getPictureUrl()
        == null || profile.getPictureUrl().trim().length()<1))));
  }

  /* (non-Javadoc)
   * @see org.sakaiproject.api.app.profile.ProfileManager#isShowProfileTool(org.sakaiproject.api.app.profile.Profile)
   */
  public boolean isShowTool()
  {
    LOG.debug("isShowTool()");
    //  implement isAnonymous later on.
    return (getProfile().getUserId() != ANONYMOUS && isSiteMember(getProfile()
        .getUserId()));
  }

  private Profile getOnlyPublicProfile(Profile profile)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("getOnlyPublicProfile(Profile" + profile + ")");
    }
    profile.getSakaiPerson().setJpegPhoto(null);
    profile.setPictureUrl(null);
    profile.setEmail(null);
    profile.setHomepage(null);
    profile.setHomePhone(null);
    profile.setOtherInformation(null);
    return profile;
  }

  /**
   * Get the id photo if the profile member is site member and the requestor is either site maintainter or user or superuser.
   * @param uid
   * @param siteMaintainer
   * @return
   */
  private byte[] getInstitutionalPhoto(String uid, boolean siteMaintainer)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("getInstitutionalPhotoByUserId(" + uid + ")");
    }
    if (uid == null || uid.length() < 1)
      throw new IllegalArgumentException("Illegal userId argument passed!");

    SakaiPerson sakaiSystemPerson = sakaiPersonManager.getSakaiPerson(uid,
        sakaiPersonManager.getSystemMutableType());
    SakaiPerson sakaiPerson = sakaiPersonManager.getSakaiPerson(uid,
        sakaiPersonManager.getUserMutableType());
    Profile profile = null;

    if ((sakaiSystemPerson != null))
    {
      Profile systemProfile = new ProfileImpl(sakaiSystemPerson);
      // Fetch current users institutional photo for either the user or super user       
      if (getCurrentUser().equals(uid)
          || SecurityService.isSuperUser()
          || (siteMaintainer && doesCurrentUserHaveUpdateAccessToSite() && isSiteMember(uid)))
      {
        LOG.info("Official Photo fetched for userId " + uid);
        return systemProfile.getInstitutionalPicture();
      }

      //    if the public information && private information is viewable and user uses to display institutional picture id.
      if (sakaiPerson != null)
      {
        profile = new ProfileImpl(sakaiPerson);
        if (sakaiPerson != null
            && (profile.getHidePublicInfo() != null)
            && (profile.getHidePublicInfo().booleanValue() == false)
            && profile.getHidePrivateInfo() != null
            && profile.getHidePrivateInfo().booleanValue() == false
            && profile.isInstitutionalPictureIdPreferred().booleanValue() == true)
        {
          LOG.info("Official Photo fetched for userId " + uid);
          return systemProfile.getInstitutionalPicture();
        }

      }
    }
    return null;
  }

  /**
   * @param uid
   * @return
   */
  private boolean isSiteMember(String uid)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("isSiteMember(String" + uid + ")");
    }
    AuthzGroup realm;
    try
    {
      realm = AuthzGroupService.getAuthzGroup("/site/" + getCurrentSiteId());
      return realm.getUsers().contains(uid);
    }
    catch (IdUnusedException e)
    {
      LOG.error("IdUnusedException:", e);
    }
    return false;
  }

  /**
   * @return
   */
  private String getCurrentSiteId()
  {
    LOG.debug("getCurrentSiteId()");
    Placement placement = ToolManager.getCurrentPlacement();
    return placement.getContext();
  }

  /**
   * @return
   */
  private boolean doesCurrentUserHaveUpdateAccessToSite()
  {
    LOG.debug("doesCurrentUserHaveUpdateAccessToSite()");
    try
    {
      // If the current site is not my workspace of the user and has update access to the site  

      return (SiteService.allowUpdateSite(getCurrentSiteId()) && !SiteService
          .isUserSite(getCurrentSiteId()));
    }
    catch (Exception e)
    {
      LOG.error(e.getMessage(), e);
    }

    return false;
  }

  /**
   * @param displayName
   * @param uid
   * @param sessionManagerUserId
   * @return
   */
  private Profile getProfileById(String displayName, String uid,
      String sessionManagerUserId)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("getProfileById(" + displayName + "," + uid + ","
          + sessionManagerUserId + ")");
    }
    if (displayName == null || displayName.length() < 1)
      throw new IllegalArgumentException("Illegal displayName argument passed!");
    if (uid == null || uid.length() < 1)
      throw new IllegalArgumentException("Illegal uid argument passed!");
    if (sessionManagerUserId == null || sessionManagerUserId.length() < 1)
      throw new IllegalArgumentException(
          "Illegal sessionManagerUserId argument passed!");

    SakaiPerson sakaiPerson = null;

    if ((uid != null) && (uid.trim().length() > 0))
    {
      Agent agent = agentGroupManager
          .getAgentBySessionManagerUserId(sessionManagerUserId);

      if (agent != null)
      {
        sakaiPerson = sakaiPersonManager.getSakaiPerson(agent.getUuid(),
            sakaiPersonManager.getUserMutableType());

        if (sakaiPerson == null)
        {
          sakaiPerson = sakaiPersonManager.create(agent.getUuid(), uid,
              sakaiPersonManager.getUserMutableType());
        }
      }
      else
      {
        //FIXME how to handle this use case with UserDirectoryService?
        agent = agentGroupManager.createAgent(agentGroupManager
            .getDefaultContainer(), sessionManagerUserId, uid, displayName,
            agentGroupManager.getDefaultAgentType());
        sakaiPerson = sakaiPersonManager.create(agent.getUuid(), uid,
            sakaiPersonManager.getUserMutableType());
      }
    }
    return new ProfileImpl(sakaiPerson);
  }

  /**
   * @return
   */
  private String getCurrentUser()
  {
    LOG.debug("getCurrentUser()");
    return SessionManager.getCurrentSession().getUserEid();
  }
}
