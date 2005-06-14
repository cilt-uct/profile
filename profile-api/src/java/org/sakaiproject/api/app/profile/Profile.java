/**********************************************************************************
*
* $Header: /cvs/sakai2/profile/profile-api/src/java/org/sakaiproject/api/app/profile/Profile.java,v 1.1 2005/05/17 20:42:58 rshastri.iupui.edu Exp $
*
***********************************************************************************
*
* Copyright (c) 2005 The Regents of the University of Michigan, Trustees of Indiana University,
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
package org.sakaiproject.api.app.profile;

import org.sakaiproject.api.common.edu.person.SakaiPerson;


/**
 * @author rshastri <a href="mailto:rshastri@iupui.edu ">Rashmi Shastri</a>
 * @version $Id: Profile.java,v 1.1 2005/05/17 20:42:58 rshastri.iupui.edu Exp $
 *
 */
public interface Profile {
    /**
     * @return
     */

    //  public Long getId();

    /**
     * @return
     */
    public String getUserId();

    /**
     * @param userID
     */
    public void setUserID(String userID);

    /**
     * @return
     */

    // public Integer getVersion();

    /**
     * @return
     */
    public String getDepartment();

    /**
     * @param department
     */
    public void setDepartment(String department);

    /**
     * @return
     */
    public String getEmail();

    /**
     * @param email
     */
    public void setEmail(String email);

    /**
     * @return
     */
    public String getFirstName();

    /**
     * @param firstName
     */
    public void setFirstName(String firstName);

    /**
     * @return
     */
    public String getNickName();

    /**
     * @param firstName
     */
    public void setNickName(String nickName);

    /**
     * @return
     */
    public String getHomePhone();

    /**
     * @param homePhone
     */
    public void setHomePhone(String homePhone);

    /**
     * @return
     */
    public String getHomepage();

    /**
     * @param homepage
     */
    public void setHomepage(String homepage);

    /**
     * @return
     */
    public String getLastName();

    /**
     * @param lastName
     */
    public void setLastName(String lastName);

    /**
     * @return
     */
    public String getOtherInformation();

    /**
     * @param otherInformation
     */
    public void setOtherInformation(String otherInformation);

    /**
     * @return
     */
    public String getPictureUrl();

    /**
     * @param pictureUrl
     */
    public void setPictureUrl(String pictureUrl);

    /**
     * @return
     */
    public String getPosition();

    /**
     * @param position
     */
    public void setPosition(String position);

    /**
     * @return
     */
    public String getRoom();

    /**
     * @param room
     */
    public void setRoom(String room);

    /**
     * @return
     */
    public String getSchool();

    /**
     * @param school
     */
    public void setSchool(String school);

    /**
     * @return
     */
    public String getWorkPhone();

    /**
     * @param workPhone
     */
    public void setWorkPhone(String workPhone);

    /**
     * @return
     */
    public Boolean isInstitutionalPictureIdPreferred();

    /**
     * @param institutionalPictureIdPreferred
     */
    public void setInstitutionalPictureIdPreferred(
        Boolean institutionalPictureIdPreferred);

    /**
     * @return
     */
    public SakaiPerson getSakaiPerson();

    /**
     * @param sakaiPerson
     */
    public void setSakaiPerson(SakaiPerson sakaiPerson);

    /**
     * @return
     */
    public Boolean getHidePrivateInfo();

    /**
     * Person's preference to allow read access to private information
     *
     * @param hidePrivateInfo
     */
    public void setHidePrivateInfo(Boolean hidePrivateInfo);

    /**
     * Person's preference to allow read access to public information
     *
     * @return
     */
    public Boolean getHidePublicInfo();

    /**
     * Person's preference to allow read access to public information
     *
     * @param hidePublicInfo
     */
    public void setHidePublicInfo(Boolean hidePublicInfo);
}

/**********************************************************************************
*
* $Header: /cvs/sakai2/profile/profile-api/src/java/org/sakaiproject/api/app/profile/Profile.java,v 1.1 2005/05/17 20:42:58 rshastri.iupui.edu Exp $
*
***********************************************************************************/
