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
		 			<h:panelGrid columns="2" border="0" >
						<h:panelGrid columns="1" border="0" width="150" rendered="#{SearchTool.profile.displayCompleteProfile}">
				<%-- 			<h:graphicImage alt="Photo"  rendered="#{SearchTool.profile.pictureDisplayed}" value="/sakai-profile-tool/image/photo_not_available.gif" width="150"   style="margin-top: 0px;margin-right: 10px;margin-bottom: 0 px;margin-left: 0px;float: left;" /> --%>
				 				 <h:outputText   rendered="#{SearchTool.profile.pictureDisplayed}" value="Photo" style="font-style: italic;align:center"/>
					 	 <h:outputText   rendered="#{SearchTool.profile.pictureDisplayed}" value="Not Available" style="font-style: italic;align:center"/>  
				
						</h:panelGrid>	 
						<h:panelGrid>			 
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
						 <h:outputText  value=" "/> 
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

	