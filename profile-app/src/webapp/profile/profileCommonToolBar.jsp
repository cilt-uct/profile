<div class="navIntraTool">
	<h:commandLink id="editProfile" title ="#{msgs.profile_edit}" action="#{ProfileTool.processActionEdit}" immediate="true"   value="#{msgs.profile_edit}" />
	<h:outputText id="seperator" value=" | "/>
	<h:commandLink  id="searchProfile" title ="#{msgs.profile_show}" immediate="true" action="#{SearchTool.processCancel}"  value="#{msgs.profile_show}" />
</div>

 