package org.sakaiproject.tool.profile;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sakaiproject.api.app.profile.ProfileManager;
import org.sakaiproject.api.kernel.component.cover.ComponentManager;

public class ProfileImageServlet extends HttpServlet
{
  private static final String PHOTO = "photo";
  private static final String CONTENT_TYPE = "image/jpeg";
  private ProfileManager profileManager;

  /**
   * The doGet method of the servlet. <br>
   *
   * This method is called when a form has its tag value method equals to get.
   * 
   * @param request the request send by the client to the server
   * @param response the response send by the server to the client
   * @throws ServletException if an error occurred
   * @throws IOException if an error occurred
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException
  {
    response.setContentType(CONTENT_TYPE);
    String userId = null;
    byte[] institutionalPhoto;
    userId = (String) request.getParameter(PHOTO);
    if (userId != null && userId.trim().length() > 0)
    {
      institutionalPhoto = getProfileManager().getInstitutionalPhotoByUserId(
          userId);
      if (institutionalPhoto != null && institutionalPhoto.length > 0)
      {
        response.setContentLength(institutionalPhoto.length);
        OutputStream stream = response.getOutputStream();
        stream.write(institutionalPhoto);
        stream.flush();
      }
    }
  }

  /**
   * get the component manager
   * @return profile manager
   */
  public ProfileManager getProfileManager()
  {
    if (profileManager == null)
    {
      return (ProfileManager) ComponentManager.get(ProfileManager.class
          .getName());
    }
    return profileManager;
  }

}
