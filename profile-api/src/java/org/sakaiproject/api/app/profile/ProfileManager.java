/**********************************************************************************
 * $URL$
 * $Id$
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

import java.util.List;

/**
 * @author rshastri 
 */
public interface ProfileManager
{
  public void init();

  public void destroy();

  /**
   * Save or Update an existing profile
   *
   * @param profile
   */
  public void save(Profile profile);

  /**
   * Returns user mutable profile of currently logged in user
   *
   * @return
   */
  public Profile getProfile();

  /**
   * Searches the list of user profiles matching given search criteria
   *
   * @param searchString
   * @return
   */
  public List findProfiles(String searchString);

  /**
   * Searches the university photo for given username
   * 
   * @param uid
   * @return
   */
  public byte[] getInstitutionalPhotoByUserId(String uid);

  /**
   * Allow user with update access to site view id photo of member users 
   * 
   * @param uid
   * @param siteMaintainer
   * @return
   */
  public byte[] getInstitutionalPhotoByUserId(String uid, boolean siteMaintainer);

  // Helper  methods
  /**
   *  
   * @param profile
   * @return
   */
  public boolean displayCompleteProfile(Profile profile);

  /**
   * @param profile
   * @return
   */
  public boolean isCurrentUserProfile(Profile profile);

  /**
   * @param profile
   * @return
   */
  public boolean isDisplayPictureURL(Profile profile);

  /**
   * @param profile
   * @return
   */
  public boolean isDisplayUniversityPhoto(Profile profile);

  /**
   * @param profile
   * @return
   */
  public boolean isDisplayUniversityPhotoUnavailable(Profile profile);

  /**
   * @param profile
   * @return
   */
  public boolean isDisplayAnyPhoto(Profile profile);

  /**
   * @param profile
   * @return
   */
  public boolean isShowTool();

  /**
   * @param id
   * @return
   */
  public Profile getUserProfileById(String id);
}
