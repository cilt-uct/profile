 <%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %> 
 
<h:form>
	<h:panelGrid border="0" >
		<h:outputText value="Name or Network ID:" style="font-weight: bold;" /> 
	  	<h:outputText value="Search for a User Profile by entering the user's name (e.g. last name) or Network ID (e.g. jsmith)."/> 
		<h:outputText value="      "/>
	</h:panelGrid>	
	<h:panelGrid columns="2" border="0" >
		<h:inputText value="#{SearchTool.searchKeyword}" onclick="this.value=''"/>
	  	<h:commandButton action="#{SearchTool.processActionSearch}" value="#{msgs.bar_search}" />
	</h:panelGrid>  
	
	<h:panelGrid columns="1" >
		<h:outputText value="     " />
		<h:outputText value="     " />
		<h:outputText value="     " />
	</h:panelGrid> 
	<h:panelGrid  rendered="#{SearchTool.showSearchResults}">  
		<jsp:include page="searchResults.jsp"/>	
	</h:panelGrid>
	<h:panelGroup rendered="#{SearchTool.showNoMatchFound}">
		<jsp:include page="noMatchFound.jsp"/>	
	</h:panelGroup>
</h:form>