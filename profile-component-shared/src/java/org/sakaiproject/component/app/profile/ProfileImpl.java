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
package org.sakaiproject.component.app.profile;

import org.sakaiproject.api.app.profile.Profile;
import org.sakaiproject.api.common.edu.person.SakaiPerson;

/**
 * Preferences - Java - Code Style - Code Templates
 */
public class ProfileImpl implements Profile
{
  private SakaiPerson sakaiPerson;

  public ProfileImpl()
  {
  }

  /**
   * @param eduPerson
   */
  public ProfileImpl(SakaiPerson sakaiPerson)
  {
    this.sakaiPerson = sakaiPerson;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getDepartment()
   */
  public String getDepartment()
  {
    return sakaiPerson.getOrganizationalUnit();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setDepartment(java.lang.String)
   */
  public void setDepartment(String department)
  {
    sakaiPerson.setOrganizationalUnit(department);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getEmail()
   */
  public String getEmail()
  {
    return sakaiPerson.getMail();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setEmail(java.lang.String)
   */
  public void setEmail(String email)
  {
    sakaiPerson.setMail(email);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getFirstName()
   */
  public String getFirstName()
  {
    return sakaiPerson.getGivenName();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setFirstName(java.lang.String)
   */
  public void setFirstName(String firstName)
  {
    sakaiPerson.setGivenName(firstName);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getNickName()
   */
  public String getNickName()
  {
    return sakaiPerson.getNickname();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setNickName(java.lang.String)
   */
  public void setNickName(String nickName)
  {
    sakaiPerson.setNickname(nickName);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getHomePhone()
   */
  public String getHomePhone()
  {
    return sakaiPerson.getHomePhone();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setHomePhone(java.lang.String)
   */
  public void setHomePhone(String homePhone)
  {
    sakaiPerson.setHomePhone(homePhone);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getHomepage()
   */
  public String getHomepage()
  {
    return sakaiPerson.getLabeledURI();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setHomepage(java.lang.String)
   */
  public void setHomepage(String homepage)
  {
    sakaiPerson.setLabeledURI(homepage);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getLastName()
   */
  public String getLastName()
  {
    return sakaiPerson.getSurname();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setLastName(java.lang.String)
   */
  public void setLastName(String lastName)
  {
    sakaiPerson.setSurname(lastName);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getOtherInformation()
   */
  public String getOtherInformation()
  {
    return sakaiPerson.getNotes();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setOtherInformation(java.lang.String)
   */
  public void setOtherInformation(String otherInformation)
  {
    sakaiPerson.setNotes(otherInformation);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getPictureURL()
   */
  public String getPictureUrl()
  {
    return sakaiPerson.getPictureUrl();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setPictureURL(java.lang.String)
   */
  public void setPictureUrl(String pictureUrl)
  {
    sakaiPerson.setPictureUrl(pictureUrl);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getPosition()
   */
  public String getPosition()
  {
    return sakaiPerson.getTitle();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setPosition(java.lang.String)
   */
  public void setPosition(String position)
  {
    sakaiPerson.setTitle(position);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getRoom()
   */
  public String getRoom()
  {
    return sakaiPerson.getRoomNumber();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setRoom(java.lang.String)
   */
  public void setRoom(String room)
  {
    sakaiPerson.setRoomNumber(room);
  }

  public String getSchool()
  {
    return sakaiPerson.getCampus();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setSchool(java.lang.String)
   */
  public void setSchool(String school)
  {
    sakaiPerson.setCampus(school);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getWorkPhone()
   */
  public String getWorkPhone()
  {
    return sakaiPerson.getTelephoneNumber();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setWorkPhone(java.lang.String)
   */
  public void setWorkPhone(String workPhone)
  {
    sakaiPerson.setTelephoneNumber(workPhone);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getNetworkID()
   */
  public String getUserId()
  {
    return sakaiPerson.getUid();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setNetworkID(java.lang.String)
   */
  public void setUserID(String userID)
  {
    sakaiPerson.setUid(userID);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#isInstitutionalPictureIDSelected()
   */
  public Boolean isInstitutionalPictureIdPreferred()
  {
    return sakaiPerson.isSystemPicturePreferred();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setInstitutionalPictureIDSelected(boolean)
   */
  public void setInstitutionalPictureIdPreferred(
      Boolean institutionalPictureIdPreferred)
  {
    sakaiPerson.setSystemPicturePreferred(institutionalPictureIdPreferred);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#isPrivateInfoViewable()
   */
  public Boolean getHidePrivateInfo()
  {
    return sakaiPerson.getHidePrivateInfo();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setPrivateInfoViewable(java.lang.Boolean)
   */
  public void setHidePrivateInfo(Boolean hidePrivateInfo)
  {
    sakaiPerson.setHidePrivateInfo(hidePrivateInfo);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#isPublicInfoViewable()
   */
  public Boolean getHidePublicInfo()
  {
    return sakaiPerson.getHidePublicInfo();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setPublicInfoViewable(java.lang.Boolean)
   */
  public void setHidePublicInfo(Boolean hidePublicInfo)
  {
    sakaiPerson.setHidePublicInfo(hidePublicInfo);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#getSakaiPerson()
   */
  public SakaiPerson getSakaiPerson()
  {
    return sakaiPerson;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.sakaiproject.api.app.profile.Profile#setSakaiPerson(org.sakaiproject.api.app.profile.SakaiPerson)
   */
  public void setSakaiPerson(SakaiPerson sakaiPerson)
  {
    this.sakaiPerson = sakaiPerson;
  }
 
  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("{id=");

    sb.append(",  First Name=");
    sb.append(getFirstName());
    sb.append(", Nick Name=");
    sb.append(getNickName());
    sb.append(", Last Name=");
    sb.append(getLastName());
    sb.append(", NetworkID=");
    sb.append(getUserId());
    sb.append(", Position=");
    sb.append(getPosition());
    sb.append(", Department=");
    sb.append(getDepartment());
    sb.append(", School=");
    sb.append(getSchool());
    sb.append(", Room=");
    sb.append(getRoom());
    sb.append(", Show Institutional PictureID =");
    sb.append(isInstitutionalPictureIdPreferred());
    sb.append(", Picture URL=");
    sb.append(getPictureUrl());
    sb.append(", Home Page=");
    sb.append(getHomepage());
    sb.append(", Email=");
    sb.append(getEmail());
    sb.append(", Work Phone=");
    sb.append(getWorkPhone());
    sb.append(", Home Phone=");
    sb.append(getHomePhone());
    sb.append(", Other Information=");
    sb.append(getOtherInformation());
    sb.append(", Show public information = ");
    sb.append(getHidePublicInfo());
    sb.append(",Show private information = , ");
    sb.append(getHidePublicInfo());

    return sb.toString();
  }
 
}


