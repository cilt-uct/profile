<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>

<f:loadBundle basename="org.sakaiproject.tool.profile.bundle.Messages" var="msgs"/>
 
<f:view>

<%--<sakai:view_container title="#{msgs.profile_title}">
	<h:commandLink value="#{ProfileTool.processCancel}"> 	<img src="/tunnel/sakai-chef-tool/image/toolhome.gif" border="0"/>	 </h:commandLink>	<h:outputText value="#{msgs.profile_title}"/>
 </sakai:view_container>--%>

<sakai:view_container title="Profile">

	<sakai:title_bar value="#{ProfileTool.title}"helpDocId="profile_overview"/>

</sakai:view_container>
 
</f:view>
