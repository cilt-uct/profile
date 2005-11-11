<h4><h:outputText id="name" value="#{msgs.name_or_network}"/> </h4>
<h:outputText id="search" value="#{msgs.search_msg}"/> 
<div>
     <div class="layer-search">
	  <h:inputText id="inputSearchBox" value="#{SearchTool.searchKeyword}" onclick="this.value=''"/>
     </div>
     <div class="layer2">
        <h:commandButton id="searchButton" title ="#{msgs.search}" action="#{SearchTool.processActionSearch}" onkeypress="document.forms[0].submit;" value="#{msgs.bar_search}" />
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
