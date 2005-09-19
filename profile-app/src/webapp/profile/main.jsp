<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %> 
<%@ taglib uri="http://sakaiproject.org/jsf/profile" prefix="profile" %> 
 
<% response.setContentType("text/html; charset=UTF-8"); %>
<f:loadBundle basename="org.sakaiproject.tool.profile.bundle.Messages" var="msgs"/>
<f:view>
	<sakai:view_container title="Profile">
	<sakai:view_content>
		<h:panelGrid rendered="#{ProfileTool.showTool}" > 
	 		<jsp:include page="profileCommonToolBar.jsp"/>
	 		<h:outputText value="#{msgs.no_profile_msg}" rendered="#{ProfileTool.displayNoProfileMsg}" style="color:red;"/>
	 	 	<h:panelGrid  columns="2"  border ="1" style="valign:top;">
		  		<h:outputText  value="Profile" style="font-weight: bold;"/> 
		  		<h:outputText   value="Search for Profile" style="font-weight: bold;"/>  
		 		<h:panelGrid columns="2" border="0" > 			 
					<h:panelGrid columns="1" border="0" style="valign:top;" rendered="#{ProfileTool.displayPicture}">
					 <h:graphicImage value="ProfileImageServlet.prf?photo=#{ProfileTool.profile.userId}" height="75" width="75"  rendered="#{ProfileTool.displayUniversityPhoto}"/> 
					 <h:graphicImage value="#{ProfileTool.profile.pictureUrl}" height="75" width="75"  rendered="#{ProfileTool.displayPictureURL}"/>
					 <h:graphicImage id="image"  alt="No Official ID photo is Available" url="/images/officialPhotoUnavailable.jpg" width="75"  rendered="#{ProfileTool.displayUniversityPhotoUnavailable}" />	
					</h:panelGrid>	 
						<h:panelGrid  width="150"  style="valign:top;">
							<h:outputText  value="#{ProfileTool.profile.firstName} #{ProfileTool.profile.lastName}" style="font-weight: bold; text-align: right"/>
							<h:outputText value="#{ProfileTool.profile.position}"/> 
							<h:outputText value="#{ProfileTool.profile.department}"/> 
							<h:outputText value="#{ProfileTool.profile.school}"/> 
							<h:outputText value="#{ProfileTool.profile.room}"/>							
							<h:outputText value="#{ProfileTool.profile.email}" />
							<h:outputText value="#{ProfileTool.profile.homepage}" />
							<h:outputText value="#{ProfileTool.profile.workPhone}"/>
							<h:outputText value="#{ProfileTool.profile.homePhone}"/>
							<profile:profile_display_HTML value="#{ProfileTool.profile.otherInformation}"/>
					 	</h:panelGrid>	 			
				</h:panelGrid>	
				<jsp:include page="searchModule.jsp"/>
		 </h:panelGrid>	
	  </h:panelGrid>	
	</sakai:view_content>	
	</sakai:view_container>
</f:view> 