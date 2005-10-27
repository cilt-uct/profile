<div class="navIntraTool">
	<h:commandLink id="editProfile" title ="Edit my Profile" action="#{ProfileTool.processActionEdit}" immediate="true"   value="#{msgs.profile_edit}" />
	<h:outputText id="seperator" value=" | "/>
	<h:commandLink  id="searchProfile" title ="Search my Profile" immediate="true" action="#{SearchTool.processCancel}"  value="#{msgs.profile_show}" />
</div>

 