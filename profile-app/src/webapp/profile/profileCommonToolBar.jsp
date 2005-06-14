 <%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %> 
<h:panelGroup rendered="#{ProfileTool.showTool}">
 <h:form>
	<sakai:tool_bar>
		<sakai:tool_bar_item action="#{ProfileTool.processActionEdit}" value="#{msgs.profile_edit}"/>
		<sakai:tool_bar_item immediate="true" action="#{SearchTool.processCancel}" value="Show my Profile"/> 
	</sakai:tool_bar>
 </h:form>
 </h:panelGroup>