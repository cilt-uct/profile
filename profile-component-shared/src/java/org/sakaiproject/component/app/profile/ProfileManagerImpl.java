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
   * @see org.sakaiproject.api.app.profile.ProfileManager#getProfileById(java.lang.String)
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
   * @see org.sakaiproject.api.app.profile.ProfileManager#getProfile()
   */
  public Profile getProfile()
  {
    LOG.debug("getProfile()");

    //TODO Find a better displayName
    final String displayName = SessionManager.getCurrentSession().getUserEid();

    Profile profile = getProfileById(displayName, SessionManager
        .getCurrentSession().getUserEid(), SessionManager.getCurrentSession()
        .getUserId());

    return profile;
  }

  /**
   * @see org.sakaiproject.api.app.profile.ProfileManager#findProfiles(java.lang.String)
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

        //Select the profile for display on if the public information is viewable.
        if ((profile != null) && (profile.getHidePublicInfo() != null)
            && (profile.getHidePublicInfo().booleanValue() == false))
        {
          searchResults.add(profile);
        }
      }
    }

    return searchResults;
  }

  /**
   * @see org.sakaiproject.api.app.profile.ProfileManager#getDisplayPictureUrl(org.sakaiproject.api.app.profile.Profile)
   */
  public String getDisplayPictureUrl(Profile profile)
  {
    if (LOG.isDebugEnabled())
    {
      LOG.debug("getDisplayPictureUrl(" + profile + ")");
    }
    if (profile == null)
      throw new IllegalArgumentException("Illegal profile argument passed!");

    SakaiPerson sakaiPerson = null;

    if (profile != null)
    {
      if ((profile.isInstitutionalPictureIdPreferred() != null)
          && (profile.isInstitutionalPictureIdPreferred().booleanValue() == true))
      {
        List sakaiPersons = sakaiPersonManager
            .findSakaiPersonByUid(SessionManager.getCurrentSession()
                .getUserEid());

        if ((sakaiPersons != null) && (sakaiPersons.size() > 0))
        {
          sakaiPerson = (SakaiPerson) sakaiPersons.get(0);
        }

        if ((sakaiPersons != null) && (sakaiPersons.size() > 1))
        {
          LOG
              .info("Profile Tool is not designed to handle more than one record for uuid");
        }
        //TODO: implement this
        return " ";
      }
      else
      {
        return profile.getPictureUrl();
      }
    }
    else
    {
      return null;
    }
  }
}



