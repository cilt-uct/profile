<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %> 
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
					<h:panelGrid columns="1" border="0" rendered="#{ProfileTool.pictureDisplayed}">
					 	 <%--<h:graphicImage rendered="#{ProfileTool.pictureDisplayed}" alt="My Photo" id="newID"   value="/sakai-profile-tool/image/photo_not_available.gif" width="150"   style="margin-top: 0px;margin-right: 10px;margin-bottom: 0 px;margin-left: 0px;float: left;" />--%>					 	 
					 	 <h:outputText   rendered="#{ProfileTool.pictureDisplayed}" value="Photo" style="font-style: italic;align:center"/>
					 	 <h:outputText   rendered="#{ProfileTool.pictureDisplayed}" value="Not Available" style="font-style: italic;align:center"/>  
					</h:panelGrid>	 
					<h:panelGrid> 
						<h:panelGrid  width="150"  style="valign:top;">
							<h:outputText  value="#{ProfileTool.profile.firstName} #{ProfileTool.profile.lastName}" style="font-weight: bold; text-align: right"/>
							<h:outputText value="#{ProfileTool.profile.position}"/> 
							<h:outputText value="#{ProfileTool.profile.department}"/> 
							<h:outputText value="#{ProfileTool.profile.school}"/> 
							<h:outputText value="#{ProfileTool.profile.room}"/>
					 	</h:panelGrid>
	 				</h:panelGrid>	 
				</h:panelGrid>	
				<jsp:include page="searchModule.jsp"/>
		 </h:panelGrid>	
	  </h:panelGrid>	
	</sakai:view_content>	
	</sakai:view_container>
</f:view> 