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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

/**
 * @author rshastri <a href="mailto:rshastri@iupui.edu ">Rashmi Shastri</a>
 * @version $Id$
 *  
 */
public class SearchTool
{
  private static final String SEARCH_KEYWORD = "Search Keyword";
  private DecoratedProfile profile;
  private String searchKeyword;
  private List searchResults;
  private List currentSearchResults;
  private int noOfRecDisplayedFrom = 0;
  private int noOfRecDisplayedTo = 0;
  private int numberOfSearchedRecordsDisplayedPerPage = 10;
  private String displayNoOfRec = "10";
  private boolean showPrevious = false;
  private boolean showNext = false;
  private boolean showSearchResults = false;
  private boolean showNoMatchFound = false;
  private boolean redirectToSearchedProfile = false;
  protected Logger logger;
  protected ProfileManager profileService;

  public SearchTool()
  {
    this.reset("Search Keyword");
  }

  public String getDisplayPage()
  {
    if (redirectToSearchedProfile)
    {
      return "displaySearchedProfile";
    }
    else
    {
      return "main";
    }
  }

  public void processValueChangeForDisplayNSearchResult(ValueChangeEvent vce)
  {
    setDisplayNoOfRec(((String) vce.getNewValue()));
    logger.debug("Show these many rec :" + (String) vce.getNewValue());
    processActionDisplayFirst();
  }

  public String processActionDisplayFirst()
  {
    try
    {
      if ((searchResults != null) && (searchResults.size() > 1)
          && (searchResults.size() <= numberOfSearchedRecordsDisplayedPerPage))
      {
        showPrevious = false;
        showNext = false;
        showSearchResults = true;
        currentSearchResults = searchResults;
        noOfRecDisplayedFrom = 1;
        noOfRecDisplayedTo = searchResults.size();

        return getDisplayPage();
      }
      else
        if ((searchResults != null)
            && (searchResults.size() > numberOfSearchedRecordsDisplayedPerPage))
        {
          currentSearchResults = searchResults.subList(0,
              (numberOfSearchedRecordsDisplayedPerPage));
          noOfRecDisplayedFrom = 1;
          noOfRecDisplayedTo = numberOfSearchedRecordsDisplayedPerPage;
          showPrevious = false;
          showNext = true;
          showSearchResults = true;

          return getDisplayPage();
        }
        else
        {
          return profile.processActionDisplayProfile();
        }
    }
    catch (Exception e)
    {
      logger.error(e.getMessage(), e);

      return null;
    }
  }

  public String processActionDisplayNext()
  {
    try
    {
      if ((searchResults != null)
          && (searchResults.size() > (noOfRecDisplayedTo)))
      {
        this.showNoMatchFound = false;
        this.showSearchResults = true;
        this.showPrevious = true;

        if (searchResults.size() > (noOfRecDisplayedTo + numberOfSearchedRecordsDisplayedPerPage))
        {
          currentSearchResults = searchResults.subList((noOfRecDisplayedTo),
              (noOfRecDisplayedTo + numberOfSearchedRecordsDisplayedPerPage));
          noOfRecDisplayedFrom = noOfRecDisplayedTo + 1;
          noOfRecDisplayedTo = noOfRecDisplayedTo
              + numberOfSearchedRecordsDisplayedPerPage;
          showNext = true;

          return getDisplayPage();
        }

        if (searchResults.size() == (noOfRecDisplayedTo + numberOfSearchedRecordsDisplayedPerPage))
        {
          currentSearchResults = searchResults.subList((noOfRecDisplayedTo),
              (noOfRecDisplayedTo + numberOfSearchedRecordsDisplayedPerPage));
          noOfRecDisplayedFrom = noOfRecDisplayedTo + 1;
          noOfRecDisplayedTo = noOfRecDisplayedTo
              + numberOfSearchedRecordsDisplayedPerPage;
          showNext = false;

          return getDisplayPage();
        }

        if (searchResults.size() < (noOfRecDisplayedTo + numberOfSearchedRecordsDisplayedPerPage))
        {
          currentSearchResults = searchResults.subList((noOfRecDisplayedTo),
              (searchResults.size()));
          noOfRecDisplayedFrom = noOfRecDisplayedTo + 1;
          noOfRecDisplayedTo = searchResults.size();
          showNext = false;

          return getDisplayPage();
        }
      }

      return getDisplayPage();
    }
    catch (Exception e)
    {
      logger.error(e.getMessage(), e);

      return null;
    }
  }

  public String processActionDisplayPrevious()
  {
    try
    {
      if ((searchResults != null) && ((noOfRecDisplayedFrom) > 1)
          && (noOfRecDisplayedFrom >= numberOfSearchedRecordsDisplayedPerPage))
      {
        this.showNext = true;
        this.showNoMatchFound = false;
        this.showSearchResults = true;

        if ((noOfRecDisplayedFrom - numberOfSearchedRecordsDisplayedPerPage) == 1)
        {
          showPrevious = false;
          currentSearchResults = searchResults.subList(0,
              (numberOfSearchedRecordsDisplayedPerPage));
          noOfRecDisplayedFrom = 1;
          noOfRecDisplayedTo = numberOfSearchedRecordsDisplayedPerPage;
        }
        else
        {
          showPrevious = true;
          currentSearchResults = searchResults.subList((noOfRecDisplayedFrom
              - numberOfSearchedRecordsDisplayedPerPage - 1),
              (noOfRecDisplayedFrom - 1));
          noOfRecDisplayedTo = noOfRecDisplayedFrom - 1;
          noOfRecDisplayedFrom = noOfRecDisplayedFrom
              - numberOfSearchedRecordsDisplayedPerPage;
        }
      }
      else
      {
        showPrevious = false;
      }

      return getDisplayPage();
    }
    catch (Exception e)
    {
      logger.error(e.getMessage(), e);

      return null;
    }
  }

  public String processActionDisplayLast()
  {
    try
    {
      //Single page display
      if ((searchResults != null) && (searchResults.size() > 1)
          && (searchResults.size() <= numberOfSearchedRecordsDisplayedPerPage))
      {
        showPrevious = false;
        showNext = false;
        currentSearchResults = searchResults;

        return this.getDisplayPage();
      }
      else
        if ((searchResults != null)
            && (searchResults.size() > numberOfSearchedRecordsDisplayedPerPage))
        {
          int displayRecForLastPage = searchResults.size()
              % numberOfSearchedRecordsDisplayedPerPage;

          if (displayRecForLastPage == 0)
          {
            currentSearchResults = searchResults
                .subList(
                    (searchResults.size() - numberOfSearchedRecordsDisplayedPerPage),
                    (searchResults.size()));
            noOfRecDisplayedFrom = searchResults.size()
                - numberOfSearchedRecordsDisplayedPerPage + 1;
          }
          else
          {
            //there is a remainder
            currentSearchResults = searchResults.subList(
                (searchResults.size() - displayRecForLastPage), (searchResults
                    .size()));
            noOfRecDisplayedFrom = searchResults.size() - displayRecForLastPage
                + 1;
          }

          noOfRecDisplayedTo = searchResults.size();
          showPrevious = true;
          showNext = false;

          return this.getDisplayPage();
        }
        else
        // search result is exactly 1
        {
          return profile.processActionDisplayProfile();
        }
    }
    catch (Exception e)
    {
      logger.error(e.getMessage(), e);

      return null;
    }
  }

  public String processActionSearch()
  {
    try
    {
      this.reset(searchKeyword);

      List profiles = profileService.findProfiles(searchKeyword);
      searchResults = new ArrayList();

      if ((profiles != null) && (profiles.size() > 0))
      {
        Iterator profileIterator = profiles.iterator();

        while (profileIterator.hasNext())
        {
          profile = new DecoratedProfile((Profile) profileIterator.next());
          searchResults.add(profile);
        }

        return processActionDisplayFirst();
      }
      else
      {
        this.showNoMatchFound = true;

        return "main";
      }
    }
    catch (Exception e)
    {
      logger.error(e.getMessage(), e);

      return null;
    }
  }

  public boolean isShowNext()
  {
    return showNext;
  }

  public boolean isShowPrevious()
  {
    return showPrevious;
  }

  public List getCurrentSearchResults()
  {
    return currentSearchResults;
  }

  public void setCurrentSearchResults(List currentSearchResults)
  {
    this.currentSearchResults = currentSearchResults;
  }

  public boolean isShowNoMatchFound()
  {
    return showNoMatchFound;
  }

  public boolean isShowSearchResults()
  {
    return (showSearchResults);
  }

  public void reset(String searchKeyword)
  {
    this.searchKeyword = searchKeyword;
    this.profile = null;
    this.searchResults = null;
    this.currentSearchResults = null;
    this.noOfRecDisplayedFrom = 0;
    this.noOfRecDisplayedTo = 0;
    this.showPrevious = false;
    this.showNext = false;
    this.showSearchResults = false;
    this.showNoMatchFound = false;
    this.redirectToSearchedProfile = false;
  }

  public String processCancel()
  {
    this.reset("Search Keyword");

    return "main";
  }

  public String getDisplayNoOfRec()
  {
    try
    {
      if ((this.displayNoOfRec != null)
          && (Integer.parseInt(displayNoOfRec) != 0))
      {
        numberOfSearchedRecordsDisplayedPerPage = Integer
            .parseInt(displayNoOfRec);
      }
    }
    catch (NumberFormatException e)
    {
      logger.error(e.getMessage(), e);
    }

    return displayNoOfRec;
  }

  public void setDisplayNoOfRec(String no_of_searched_rec_per_page)
  {
    displayNoOfRec = no_of_searched_rec_per_page;

    try
    {
      if ((this.displayNoOfRec != null)
          && (Integer.parseInt(displayNoOfRec) != 0))
      {
        numberOfSearchedRecordsDisplayedPerPage = Integer
            .parseInt(displayNoOfRec);
      }
    }
    catch (NumberFormatException e)
    {
      logger.error(e.getMessage(), e);
    }
  }

  public DecoratedProfile getProfile()
  {
    return profile;
  }

  /**
   * @return
   */
  public Logger getLogger()
  {
    return logger;
  }

  /**
   * @param logger
   */
  public void setLogger(Logger logger)
  {
    this.logger = logger;
  }

  /**
   * @return
   */
  public ProfileManager getProfileService()
  {
    return profileService;
  }

  /**
   * @return
   */
  public String getSearchKeyword()
  {
    return searchKeyword;
  }

  /**
   * @return
   */
  public List getSearchResults()
  {
    return searchResults;
  }

  /**
   * @param profileService
   */
  public void setProfileService(ProfileManager profileService)
  {
    this.profileService = profileService;
  }

  /**
   * @param profile
   */
  public void setProfile(DecoratedProfile profile)
  {
    this.profile = profile;
  }

  /**
   * @param searchKeyword
   */
  public void setSearchKeyword(String searchKeyword)
  {
    this.searchKeyword = searchKeyword;
  }

  /**
   * @param searchResults
   */
  public void setSearchResults(List searchResults)
  {
    this.searchResults = searchResults;
  }

  /**
   * @return
   */
  public String getDisplayPicture()
  {
    return profile.getDisplayPicture();
  }

  public class DecoratedProfile
  {
    protected Profile inProfile;
    private String displayPicture = null;
    private boolean displayCompleteProfile = false;

    /**
     * @param newProfile
     */
    public DecoratedProfile(Profile newProfile)
    {
      inProfile = newProfile;
    }

    /**
     * @return
     */
    public Profile getProfile()
    {
      return inProfile;
    }

    /**
     * @return
     */
    public String processActionDisplayProfile()
    {
      try
      {
        profile = this;
        displayPicture = profileService.getDisplayPictureUrl(profile
            .getProfile());

        if (displayPrivateInformation(profile.getProfile()))
        {
          displayCompleteProfile = true;
        }
        else
        {
          displayCompleteProfile = false;
        }

        redirectToSearchedProfile = true;

        return "displaySearchedProfile";
      }
      catch (Exception e)
      {
        logger.error(e.getMessage(), e);

        return null;
      }
    }

    private boolean displayPrivateInformation(Profile profile)
    {
      if ((profile != null)
          && ((profile.getHidePrivateInfo().booleanValue() == false) && (profile
              .getHidePublicInfo().booleanValue() == false)))
      {
        return true;
      }
      else
      {
        return false;
      }
    }

    /**
     * @return
     */
    public String getDisplayPicture()
    {
      return profileService.getDisplayPictureUrl(inProfile);
    }

    /**
     * @return
     */
    public boolean isPictureDisplayed()
    {
      return ((this.displayPicture != null) && (this.displayPicture.trim()
          .length() > 0));
    }

    public boolean isDisplayCompleteProfile()
    {
      return displayCompleteProfile;
    }
  }
}


