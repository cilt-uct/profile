<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %> 
<%@ taglib uri="http://sakaiproject.org/jsf/profile" prefix="profile" %> 
 
<% response.setContentType("text/html; charset=UTF-8"); %>
<link href='/sakai-profile-tool/css/profile.css' rel='stylesheet' type='text/css' /> 
<f:loadBundle basename="org.sakaiproject.tool.profile.bundle.Messages" var="msgs"/>
<f:view>
<h:form id="displayProfileForm">	 	
<sakai:view title="Profile" rendered="#{ProfileTool.showTool}">
	 <%@include file="profileCommonToolBar.jsp"%>
		<div class="base-div">
		        <div class="left-section">
		       
				<sakai:view_title  value="Profile" /> 
		   		<h4><h:outputText id="id0" value="#{SearchTool.profile.profile.firstName} #{SearchTool.profile.profile.lastName}"/></h4>
		   	 <div class="layer1">	
			 	<h:graphicImage id="image1" value="ProfileImageServlet.prf?photo=#{SearchTool.profile.profile.userId}" alt="Official ID Photo" height="75" width="75"  rendered="#{SearchTool.profile.displayUniversityPhoto}"/> 
		   		<h:graphicImage id="image2" value="#{SearchTool.profile.profile.pictureUrl}" height="75" width="75"  alt="User selected picture url" rendered="#{SearchTool.profile.displayPictureURL}"/>
		   		<h:graphicImage id="image3" url="/images/pictureUnavailable.jpg" width="75"  alt="No photo available" rendered="#{SearchTool.profile.displayNoPicture}"/>
		   		<h:graphicImage id="image4" alt="No Official ID photo is Available" url="/images/officialPhotoUnavailable.jpg" width="75"  rendered="#{SearchTool.profile.displayUniversityPhotoUnavailable}" />			
			 </div>
    			 <div class="layer3">
				<h:outputText id="id1" value="#{SearchTool.profile.profile.position}"/> <br/>
				<h:outputText id="id2" value="#{SearchTool.profile.profile.department}"/> <br/>
				<h:outputText id="id3" value="#{SearchTool.profile.profile.school}"/><br/>
				<h:outputText id="id4" value="#{SearchTool.profile.profile.room}"/><br/> 
				<h:outputText id="id5" value="#{SearchTool.profile.profile.email} " rendered="#{SearchTool.profile.displayCompleteProfile}"/><br/>
				<h:outputText id="id6" value="#{SearchTool.profile.profile.homepage}" rendered="#{SearchTool.profile.displayCompleteProfile}"/><br/>
				<h:outputText id="id7" value="#{SearchTool.profile.profile.workPhone}" rendered="#{SearchTool.profile.displayCompleteProfile}"/><br/>
				<h:outputText id="id8" value="#{SearchTool.profile.profile.homePhone}" rendered="#{SearchTool.profile.displayCompleteProfile}"/><br/>
				<profile:profile_display_HTML value="#{SearchTool.profile.profile.otherInformation}"  rendered="#{SearchTool.profile.displayCompleteProfile}"/>
						 	
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