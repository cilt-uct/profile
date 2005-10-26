<h4><h:outputText id="name" value="Name or Network ID:"/> </h4>
<h:outputText id="search" value="Search for a User Profile by entering the user's name (e.g. last name) or Network ID (e.g. jsmith)."/> 
<div>
     <div class="layer-search">
	  <h:inputText id="inputSearchBox" value="#{SearchTool.searchKeyword}" onclick="this.value=''"/>
     </div>
     <div class="layer2">
        <h:commandButton id="searchButton" title ="Search" action="#{SearchTool.processActionSearch}" onkeypress="document.forms[0].submit;" value="#{msgs.bar_search}" />
     </div>
</div> 
<div>
<div class="left-layer">	 
	<h:panelGroup id="showResult" rendered="#{SearchTool.showSearchResults}">  
 		<%@include file="searchResults.jsp"%>	
	 </h:panelGroup>
</div> 
 <div class="left-layer" >
	<h:panelGroup id="nomatchfound"  rendered="#{SearchTool.showNoMatchFound}"> 
	   <%@include file="noMatchFound.jsp"%>	
	 </h:panelGroup>
	</div> 
</div> 
