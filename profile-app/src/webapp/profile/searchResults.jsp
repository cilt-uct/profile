<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %> 
  <h:panelGrid columns="9" style="align:center" >
	<h:commandLink action="#{SearchTool.processActionDisplayFirst}"  style="font-weight:bold" value="<<" rendered="#{SearchTool.showPrevious}"/> 		 
	<h:outputText rendered="#{SearchTool.showPrevious}" value="          "/>
	<h:commandLink action="#{SearchTool.processActionDisplayPrevious}"  style="font-weight:bold" value="<" rendered="#{SearchTool.showPrevious}"/> 		 
	<h:outputText rendered="#{SearchTool.showPrevious}" value="          "/>
	<h:selectOneMenu  rendered="#{SearchTool.showSearchResults}" onchange="this.form.submit();"  valueChangeListener="#{SearchTool.processValueChangeForDisplayNSearchResult}" value="#{SearchTool.displayNoOfRec}">  
		<f:selectItem itemValue="10" itemLabel="Select 10 items" />
		<f:selectItem itemValue="20" itemLabel="Select 20 items" />
		<f:selectItem itemValue="30" itemLabel="Select 30 items"/>
		<f:selectItem itemValue="40" itemLabel="Select 40 items" />
		<f:selectItem itemValue="50" itemLabel="Select 50 items" />
	</h:selectOneMenu>  
	 <h:outputText rendered="#{SearchTool.showPrevious}" value="          "/>
	 <h:commandLink    action="#{SearchTool.processActionDisplayNext}" style="font-weight:bold"  value=">" rendered="#{SearchTool.showNext}"/>  
	 <h:outputText rendered="#{SearchTool.showPrevious}" value="          "/>
	 <h:commandLink    action="#{SearchTool.processActionDisplayLast}" style="font-weight:bold"  value=">>" rendered="#{SearchTool.showNext}"/>  
</h:panelGrid>  

<h:panelGrid columns="1" style="align:center" >
	<h:outputText value="     " />
	<h:outputText value="     " />
	<h:outputText value="     " />
</h:panelGrid> 
<h:dataTable value="#{SearchTool.currentSearchResults}" var="searchResult">
	<h:column>	
		<h:commandLink action="#{searchResult.processActionDisplayProfile}">
		<h:outputText value="     " />
		<h:outputText value="#{searchResult.profile.lastName}" /> 
		<h:outputText value=", " /> 
		<h:outputText value="#{searchResult.profile.firstName}" />
		<h:outputText value="                          " />
		</h:commandLink >
	</h:column>
</h:dataTable> 
  			  	 