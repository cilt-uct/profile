<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://sakaiproject.org/jsf/profile" prefix="profile" %> 

<f:loadBundle basename="org.sakaiproject.tool.profile.bundle.Messages" var="msgs"/>
<% response.setContentType("text/html; charset=UTF-8"); %>
 <f:view>
	<sakai:view_container title="Profile">
	<sakai:view_content>
	 	<jsp:include page="profileCommonToolBar.jsp"/>
  		<table columns="2"  border ="1" style="valign:top">
	  		 <tr>
	  		 	<td>	
	  		 		<h:outputText  value="Profile" style="font-weight: bold;"/> 
				</td>
				<td>	
					<h:outputText   value="Search for Profile" style="font-weight: bold;"/>  
				</td>
	 		</tr>
	 		<tr>
	 			<td valign="Top">	
		 			<h:panelGrid columns="2" border="0" style="valign:top;" >
						<h:panelGrid columns="1" border="0" width="150" rendered="#{SearchTool.profile.displayPhoto}">
							 <h:graphicImage value="ProfileImageServlet.prf?photo=#{SearchTool.profile.profile.userId}" height="75" width="75" rendered="#{SearchTool.profile.displayUniversityPhoto}"/>
							 <h:graphicImage value="#{SearchTool.profile.profile.pictureUrl}" height="75" width="75"  rendered="#{SearchTool.profile.displayPictureURL}"/>
						</h:panelGrid>	 
						<h:panelGrid width="150">
								<h:outputText  value="#{SearchTool.profile.profile.firstName} #{SearchTool.profile.profile.lastName}" style="font-weight: bold;"/> 
								<h:outputText value="#{SearchTool.profile.profile.position}"/> 
								<h:outputText value="#{SearchTool.profile.profile.department}"/> 
								<h:outputText value="#{SearchTool.profile.profile.school}"/>
								<h:outputText value="#{SearchTool.profile.profile.room}"/> 
								<h:outputText value="#{SearchTool.profile.profile.email} " rendered="#{SearchTool.profile.displayCompleteProfile}"/>
								<h:outputText value="#{SearchTool.profile.profile.homepage}" rendered="#{SearchTool.profile.displayCompleteProfile}"/>
								<h:outputText value="#{SearchTool.profile.profile.workPhone}" rendered="#{SearchTool.profile.displayCompleteProfile}"/>
								<h:outputText value="#{SearchTool.profile.profile.homePhone}" rendered="#{SearchTool.profile.displayCompleteProfile}"/>
								<profile:profile_display_HTML value="#{SearchTool.profile.profile.otherInformation}"  rendered="#{SearchTool.profile.displayCompleteProfile}"/>
						 	</h:panelGrid>
					</h:panelGrid>	 
  				</td>
  				<td>
  					<jsp:include page="searchModule.jsp"/>
  				</td>
  			</tr>
  		</table>
  	</sakai:view_content>
	</sakai:view_container>
</f:view>

	