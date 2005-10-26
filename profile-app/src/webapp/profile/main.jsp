<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %> 
<%@ taglib uri="http://sakaiproject.org/jsf/profile" prefix="profile" %> 
 
<% response.setContentType("text/html; charset=UTF-8"); %>
<link href='/sakai-profile-tool/css/profile.css' rel='stylesheet' type='text/css' /> 
<f:loadBundle basename="org.sakaiproject.tool.profile.bundle.Messages" var="msgs"/>
<f:view>
<h:form>	 	
<sakai:view title="Profile" rendered="#{ProfileTool.showTool}">
	 <%@include file="profileCommonToolBar.jsp"%>		 
		<div class="base-div">
		        <div class="left-section">
		     		<sakai:view_title value="Profile" /> 
		   		<h:outputText id="warning" value="#{msgs.no_profile_msg}" rendered="#{ProfileTool.displayNoProfileMsg}" style="color:red;"/>
	 			<h4><h:outputText  id="Name" value="#{ProfileTool.profile.firstName} #{ProfileTool.profile.lastName}"/></h4>
		   	 <div class="layer1">	
				<h:graphicImage id="image1" value="ProfileImageServlet.prf?photo=#{ProfileTool.profile.userId}" alt="Official ID Photo" height="75" width="75"  rendered="#{ProfileTool.displayUniversityPhoto}"/> 
		   		<h:graphicImage id="image2" value="#{ProfileTool.profile.pictureUrl}" height="75" width="75"  alt="User selected picture" rendered="#{ProfileTool.displayPictureURL}"/>
		   		<h:graphicImage id="image3" url="/images/pictureUnavailable.jpg" width="75"  alt="No photo available" rendered="#{ProfileTool.displayNoPicture}"/>
		   		<h:graphicImage id="image4" alt="No Official ID photo is Available" url="/images/officialPhotoUnavailable.jpg" width="75"  rendered="#{ProfileTool.displayUniversityPhotoUnavailable}" />	
			 </div>
    			 <div class="layer3">
				<h:outputText id="position" value="#{ProfileTool.profile.position}"/> <br/>
				<h:outputText id="department" value="#{ProfileTool.profile.department}"/> <br/>
				<h:outputText id="school" value="#{ProfileTool.profile.school}"/> <br/>
				<h:outputText id="room" value="#{ProfileTool.profile.room}"/><br/>							
				<h:outputText id="email" value="#{ProfileTool.profile.email}" /><br/>
				<h:outputText id="homepage" value="#{ProfileTool.profile.homepage}" /><br/>
				<h:outputText id="workphone" value="#{ProfileTool.profile.workPhone}"/><br/>
				<h:outputText id="homephone" value="#{ProfileTool.profile.homePhone}"/><br/>
				<profile:profile_display_HTML value="#{ProfileTool.profile.otherInformation}"/>	
		   	</div>
		   	</div>
		        <div class="right-section">
				<sakai:view_title value="Search for Profile"/> 
		   		<%@include file="searchModule.jsp"%>
		        </div>
		</div>  
	</sakai:view>
     </h:form>
</f:view> 