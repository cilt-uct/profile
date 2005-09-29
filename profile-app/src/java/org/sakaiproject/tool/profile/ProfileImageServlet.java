package org.sakaiproject.tool.profile;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.api.app.profile.ProfileManager;
import org.sakaiproject.api.kernel.component.cover.ComponentManager;

public class ProfileImageServlet extends HttpServlet
{
  private static final String PHOTO = "photo";
  private static final String CONTENT_TYPE = "image/jpeg";
  private static final String IMAGE_PATH = "/images/";
  private static final String UNAVAILABLE_IMAGE = "/officialPhotoUnavailable.jpg";
  private ProfileManager profileManager;
  private static final Log LOG = LogFactory
  .getLog(ProfileImageServlet.class);
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
    if (LOG.isDebugEnabled())
      LOG.debug("doGet(HttpServletRequest" + request + ", HttpServletResponse"
          + response + ")");
    response.setContentType(CONTENT_TYPE);
    String userId = null;
    byte[] institutionalPhoto;
    userId = (String) request.getParameter(PHOTO);
    if (userId != null && userId.trim().length() > 0)
    {
      institutionalPhoto = getProfileManager().getInstitutionalPhotoByUserId(
          userId);
      OutputStream stream = response.getOutputStream();
      if (institutionalPhoto != null && institutionalPhoto.length > 0)
      {
        LOG.debug("Display University ID photo for user:" + userId);
        response.setContentLength(institutionalPhoto.length);       
        stream.write(institutionalPhoto);
        stream.flush();
      }
      else
      {
        try
        {
          BufferedInputStream in = null;
          try
          {

            in = new BufferedInputStream(new FileInputStream(getServletContext()
                .getRealPath(IMAGE_PATH)
                + UNAVAILABLE_IMAGE));
            int ch;

            while ((ch = in.read()) != -1)
            {
              LOG.debug("Display University ID photo for user:" + userId
                  + " is unavailable");
              stream.write((char) ch);
            }
          }

          finally
          {
            if (in != null) in.close();
          }
        }
        catch (FileNotFoundException e)
        {
          LOG.error(e.getMessage(), e);
        }
        catch (IOException e)
        {
          LOG.error(e.getMessage(), e);
        }  
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
