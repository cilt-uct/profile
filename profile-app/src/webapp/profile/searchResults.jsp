  <h:panelGrid id="t1" summary="table to display search links" columns="9" style="align:center" >
	<h:commandLink id="first" title="Display first set of results" action="#{SearchTool.processActionDisplayFirst}"  style="font-weight:bold" value="<<" rendered="#{SearchTool.showPrevious}"/> 		 
	<h:outputText id="blankspace" rendered="#{SearchTool.showPrevious}" value="          "/>
	<h:commandLink id="previous" title ="Display previous set of results" action="#{SearchTool.processActionDisplayPrevious}"  style="font-weight:bold" value="<" rendered="#{SearchTool.showPrevious}"/> 		 
	<h:outputText id= "spaces" rendered="#{SearchTool.showPrevious}" value="          "/>
	<h:selectOneMenu  id="selectNoOfRec" title ="Select number of seach result displayed per page "  rendered="#{SearchTool.showSearchResults}" onchange="this.form.submit();"  valueChangeListener="#{SearchTool.processValueChangeForDisplayNSearchResult}" value="#{SearchTool.displayNoOfRec}">  
		<f:selectItem id="ten" itemValue="10" itemLabel="Select 10 items" />
		<f:selectItem id="twenty"  itemValue="20" itemLabel="Select 20 items" />
		<f:selectItem id="thirty"  itemValue="30" itemLabel="Select 30 items"/>
		<f:selectItem id="forty" itemValue="40" itemLabel="Select 40 items" />
		<f:selectItem id="fifty"  itemValue="50" itemLabel="Select 50 items" />
	</h:selectOneMenu>  
	 <h:outputText id="blankspace2" rendered="#{SearchTool.showPrevious}" value="          "/>
	 <h:commandLink id="next" title ="Display next set of results" action="#{SearchTool.processActionDisplayNext}" style="font-weight:bold"  value=">" rendered="#{SearchTool.showNext}"/>  
	 <h:outputText id="blankspace3" rendered="#{SearchTool.showPrevious}" value="          "/>
	 <h:commandLink id="last" title ="Display last set of results"   action="#{SearchTool.processActionDisplayLast}" style="font-weight:bold"  value=">>" rendered="#{SearchTool.showNext}"/>  
</h:panelGrid>  

<h:dataTable id="searchTable" summary="tabular representation of searched profiles by lastname or username" value="#{SearchTool.currentSearchResults}" var="searchResult">
	<h:column>	
		<h:commandLink id="profile" title ="Display Profile" action="#{searchResult.processActionDisplayProfile}">
		<h:outputText id="blankspace4" value="     " />
		<h:outputText id="profileLastName" value="#{searchResult.profile.lastName}" /> 
		<h:outputText id="comma" value=", " /> 
		<h:outputText id="profileFirstName" value="#{searchResult.profile.firstName}" />
		<h:outputText id="blankspace5" value="                          " />
		</h:commandLink >
	</h:column>
</h:dataTable> 