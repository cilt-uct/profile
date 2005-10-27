<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<f:loadBundle basename="org.sakaiproject.tool.profile.bundle.Messages" var="msgs"/>
<link href='/sakai-profile-tool/css/profile.css' rel='stylesheet' type='text/css' />
<f:view>
<h:form id="editProfileForm"> 
	<sakai:view title="My Profile" rendered="#{ProfileTool.showTool}">
		<div class="navIntraTool">
		 	<h:outputText id="editprofile" value="#{msgs.profile_edit}" styleClass="currentView" />
			<h:outputText id="seperaror" value=" | "/>
			<h:commandLink id="cancel" immediate="true" action="#{SearchTool.processCancel}"  value="#{msgs.profile_show}" />
		</div>
		<sakai:tool_bar_message value="#{msgs.profile_edit_title}" />
 			 <div class="instruction">
  			    <h:outputText id="er1"  value="Complete form and then choose 'Save' at the bottom. #{msgs.info_A}"/>
			 	<h:outputText id="er2" style="color: red" value="'#{msgs.info_required_sign}'"/>
			    <h:outputText id="er3" value="#{msgs.info_required}"/>

  			 </div> <h:selectBooleanCheckbox id="checkhide" title= "Hide my Profile" value="#{ProfileTool.profile.hidePublicInfo}" /> <h:outputText   value="Hide my
				entire Profile" />


  			<h4><h:outputText  value="#{msgs.title_public_info}"/></h4>

			<%--http://www.thoughtsabout.net/blog/archives/000031.html--%>
			<p class="shorttext">
				<h:panelGrid columns="3">
					<h:panelGroup><h:outputText id="inputid"  value="#{msgs.info_required_sign}" style="color: red"/>	</h:panelGroup>
					<h:panelGroup><h:outputLabel id="outputLabel" for="first_name"  value="#{msgs.profile_first_name}"/>	</h:panelGroup>
					<h:panelGroup><h:inputText size="50" id="first_name"  value="#{ProfileTool.profile.firstName}"/></h:panelGroup>


					<h:panelGroup/>
					<h:panelGroup/>
					<h:panelGroup><h:outputText id="er4" value="#{msgs.error_empty_first_name}" style="color: red" rendered="#{ProfileTool.displayEmptyFirstNameMsg}"/>
					</h:panelGroup>


					<h:panelGroup><h:outputText id="er5" value="#{msgs.info_required_sign}" style="color: red"/>	</h:panelGroup>
					<h:panelGroup><h:outputLabel id="outputLabel111" for="lname"  value="#{msgs.profile_last_name}"/></h:panelGroup>
					<h:panelGroup><h:inputText id="lname" size="50"  value="#{ProfileTool.profile.lastName}"/></h:panelGroup>

					<h:panelGroup/>
					<h:panelGroup/>
					<h:panelGroup>
						<h:outputText id="er6" value="#{msgs.error_empty_last_name}" style="color: red"
								rendered="#{ProfileTool.displayEmptyLastNameMsg}"/>
					</h:panelGroup>

					<h:panelGroup/>
					<h:panelGroup><h:outputLabel id="outputLabel2" for="nickname"  value="#{msgs.profile_nick_name}"/></h:panelGroup>
					<h:panelGroup><h:inputText id="nickname" size="50"  value="#{ProfileTool.profile.nickName}"/></h:panelGroup>

					<h:panelGroup/>
					<h:panelGroup><h:outputLabel id="outputLabel3"  for="position"  value="#{msgs.profile_position}"/></h:panelGroup>
					<h:panelGroup><h:inputText id="position" size="50"  value="#{ProfileTool.profile.position}"/></h:panelGroup>

					<h:panelGroup/>
					<h:panelGroup><h:outputLabel id="outputLabel4" for="department"  value="#{msgs.profile_department}"/></h:panelGroup>
					<h:panelGroup><h:inputText id="department" size="50"  value="#{ProfileTool.profile.department}"/></h:panelGroup>

					<h:panelGroup/>
					<h:panelGroup><h:outputLabel id="outputLabel5" for="school"  value="#{msgs.profile_school}"/></h:panelGroup>
					<h:panelGroup><h:inputText id="school" size="50"  value="#{ProfileTool.profile.school}" /></h:panelGroup>

					<h:panelGroup/>
					<h:panelGroup><h:outputLabel id="outputLabel6" for="room"  value="#{msgs.profile_room}"/></h:panelGroup>

					<h:panelGroup><h:inputText id="room" size="50"  value="#{ProfileTool.profile.room}"/></h:panelGroup>
				</h:panelGrid>
			</p>

		    <h4><h:outputText id="personal" value="#{msgs.title_personal_info}" /></h4>
			<h:selectBooleanCheckbox  id="hideMyPersonalInfo" title= "Hide my Personal Information"   value="#{ProfileTool.profile.hidePrivateInfo}" />
			<h:outputText id="id5"  value="Hide only my Personal Information" />

			<p class="shorttext">
				<h:panelGrid columns="2" columnClasses="editProfile">
					<h:outputLabel id="outputLabel7" for="picture"  value="#{msgs.profile_picture}" />
					<h:panelGroup>
						<h:selectOneRadio id="picture" styleClass="shorttext" title="Choose picture id preference"  value="#{ProfileTool.pictureIdPreference}" layout="pageDirection">
							<f:selectItem itemLabel="None" itemValue="none"/>
							<f:selectItem itemLabel="Use University Id Picture" itemValue="universityId"/>
							<f:selectItem itemLabel="Use Picture URL :" itemValue="pictureUrl"/>
						</h:selectOneRadio>
		  				<h:inputText size="50" id="inputPictureUrl" value="#{ProfileTool.profile.pictureUrl}"/>
					</h:panelGroup>

					<h:panelGroup/>
					<h:panelGroup>
						 <h:outputText id="er7" value="#{msgs.error_msg} #{ProfileTool.malformedUrlError}" style="color: red"  rendered="#{ProfileTool.displayMalformedUrlError}"/>
					</h:panelGroup>

					<h:panelGroup><h:outputLabel id="outputLabel17" for="email"  value="#{msgs.profile_email}"/></h:panelGroup>
					<h:panelGroup><h:inputText size="50" id="email"  value="#{ProfileTool.profile.email}"/></h:panelGroup>

					<h:panelGroup><h:outputLabel id="outputLabel8" for="homepage"  value="#{msgs.profile_homepage}"/></h:panelGroup>
					<h:panelGroup><h:inputText id="homepage" size="50"  value="#{ProfileTool.profile.homepage}"/></h:panelGroup>

					<h:panelGroup><h:outputLabel id="outputLabel9" for="workphone"  value="#{msgs.profile_work_phone}"/></h:panelGroup>
					<h:panelGroup><h:inputText size="50" id="workphone" value="#{ProfileTool.profile.workPhone}"/></h:panelGroup>

					<h:panelGroup><h:outputLabel id="outputLabel10" for="homephone"  value="#{msgs.profile_home_phone}"/></h:panelGroup>
					<h:panelGroup><h:inputText size="50" id="homephone" value="#{ProfileTool.profile.homePhone}"/></h:panelGroup>

				</h:panelGrid>
			</p>

			<p class="shorttext">
				<h:panelGrid >
					<h:panelGroup><h:outputLabel id="outputLabel11" for="otherInformation" value="#{msgs.profile_other_information}"/></h:panelGroup>
					<h:panelGroup id="otherInformation"><sakai:rich_text_area value="#{ProfileTool.profile.otherInformation}" rows="17" columns="70"/></h:panelGroup>
					<h:outputText value="#{msgs.error_msg} #{ProfileTool.evilTagMsg}" style="color: red"  rendered="#{ProfileTool.displayEvilTagMsg}"/>
				</h:panelGrid>
			</p>

			<p class="act">
				<h:commandButton id="editSaveButton" action="#{ProfileTool.processActionEditSave}" onkeypress="document.forms[0].submit;" value="#{msgs.bar_save}" />
				<h:commandButton id="editCancelButton" action="#{ProfileTool.processCancel}" onkeypress="document.forms[0].cancel;"	immediate="true" value="#{msgs.bar_cancel}" />
			<p> 
	</sakai:view>
	</h:form>
</f:view>
