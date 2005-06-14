<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %> 
<% response.setContentType("text/html; charset=UTF-8"); %>
<f:loadBundle basename="org.sakaiproject.tool.profile.bundle.Messages" var="msgs"/>
<f:view>
	<sakai:view_container>
  		<sakai:view_content>
  			<jsp:include page="profileCommonToolBar.jsp"/>
  			<h:form> 	 	
  				<table width="100%">
			 		<tr>
			 			<td colspan="2">
			 				<h:outputText style="font-weight: bold; font-size: 14;" value="#{msgs.profile_edit_title}" />
			 			</td>
			 		</tr>
			 		<tr>
			 			<td colspan="2"><br/>
			 			</td>
					</tr>
			 		<tr>
			 			<td colspan="2">
					 		<h:selectBooleanCheckbox  title= "Hide my Profile" value="#{ProfileTool.profile.hidePublicInfo}" />
			 				<h:outputText   value="Hide my entire Profile"  style="font-size: 11;"/>
			 			</td>
			 		</tr>
			 		<tr>
			 			<td colspan="2">
			 				<h:outputText style="font-weight: bold; font-size: 12;" value="#{msgs.title_public_info}" />
			 			</td>
			 		</tr>	
			 		<tr>
			 			<td>	
			 				<h:outputText style="font-weight: bold; font-size: 10;" value="#{msgs.profile_first_name}"/>
			 	 		</td>
			 	 		<td>
			 	 			<h:inputText id="first_name"  size="50"   value="#{ProfileTool.profile.firstName}"/> 
			   	   		</td>
			   	   	</tr>
			   	   	<tr>	
			   	   		<td>
			   	   			<h:outputText style="font-weight: bold; font-size: 11;" value="#{msgs.profile_last_name}"/>
				 		</td>
				 		<td>	
				 			<h:inputText size="50" value="#{ProfileTool.profile.lastName}"/> 
				 		</td>
				 	</tr>
				 	<tr>	
			   	   		<td>
			   	   			<h:outputText style="font-weight: bold; font-size: 11;" value="#{msgs.profile_nick_name}"/>
				 		</td>
				 		<td>	
				 			<h:inputText size="50" value="#{ProfileTool.profile.nickName}"/> 
				 		</td>
				 	</tr>
				 	<tr>
				 		<td>		
				  		  	<h:outputText style="font-weight: bold; font-size: 11;" value="#{msgs.profile_position}"/>
				  		</td>
				  		<td>
				  		 	<h:inputText size="50" value="#{ProfileTool.profile.position}"/> 
				  		</td>
				  	</tr>
				  	<tr>
				  		<td>
				  			<h:outputText style="font-weight: bold; font-size: 11;" value="#{msgs.profile_department}"/>
				  		</td>
				  		<td>	
				  			<h:inputText size="50" value="#{ProfileTool.profile.department}"/> 
				  		</td>
				  	</tr>				    
				  	<tr>
				  		<td>
				  		  	<h:outputText style="font-weight: bold; font-size: 11;" value="#{msgs.profile_school}"/>
				  		</td> 	
				 		<td>	
				 			<h:inputText  value="#{ProfileTool.profile.school}" /> 
				  	 	</td>
				  	 </tr>
				  	 <tr>
				  	 	<td>	 
				 			<h:outputText style="font-weight: bold; font-size: 11;" value="#{msgs.profile_room}"/>
				 		</td>
				 		<td>	
				 			<h:inputText size="50" value="#{ProfileTool.profile.room}"/>
				 		</td>
				 	</tr>		
					<tr>
			 			<td colspan="2"><br/>
			 			</td>
					</tr>	
					<tr>				
			 			<td colspan="2">
			 				<h:outputText style="font-weight: bold; font-size: 12" value="#{msgs.title_personal_info}" />
							<h:outputText  value="    "/>
		 				</td>
			 		</tr>
			 		<tr>				
			 			<td colspan="2">
			 				<h:selectBooleanCheckbox  title= "Hide my Personal Information"   value="#{ProfileTool.profile.hidePrivateInfo}" />
			 				<h:outputText   value="Hide only my Personal Information"  style="font-size: 11;"/>
			 			</td>
			 		</tr>
					<tr>
				  	 	<td>
			 				<h:outputText  value="#{msgs.profile_picture}" style="font-weight: bold; font-size: 11;"/>
			 			</td>
			 			<td> 
			 				<h:selectOneRadio  style="font-size: 11;"  value="#{ProfileTool.pictureIdPreference}" layout="pageDirection">
			 					<f:selectItem itemLabel="None" itemValue="none"/>
			 					<f:selectItem itemLabel="Use University ID Picture" itemValue="universityId" />			 						
			  				</h:selectOneRadio>
			  			</td>	
					</tr>
					<tr>
						<td>
							<h:outputText style="font-weight: bold; font-size: 11;" value="#{msgs.profile_email}"/>
						</td>
						<td>
						 	<h:inputText size="50" value="#{ProfileTool.profile.email}"/> 
			 			</td>
			 		</tr>	
			 		<tr>
			 			<td>	
			 				<h:outputText style="font-weight: bold; font-size: 11;" value="#{msgs.profile_homepage}"/>
			 			</td>
			 			<td>
			 				<h:inputText size="50" value="#{ProfileTool.profile.homepage}"/>  
			 			</td>
			 		</tr>		
					<tr>
						<td>
							<h:outputText style="font-weight: bold; font-size: 11;" value="#{msgs.profile_work_phone}"/>
						</td>
						<td>
							<h:inputText size="50" value="#{ProfileTool.profile.workPhone}"/> 
						</td>
					</tr>
					<tr>
						<td>
						 	<h:outputText style="font-weight: bold; font-size: 11;" value="#{msgs.profile_home_phone}"/>
						</td>
						<td>
							<h:inputText size="50" value="#{ProfileTool.profile.homePhone}"/> 
						</td>
					</tr>	
			 	      <tr>
			 			<td colspan="2">
			 				<h:outputText style="font-weight: bold; font-size: 11;" value="#{msgs.profile_other_information}"/> 
			 			<br/>	
			 				<sakai:rich_text_area value="#{ProfileTool.profile.otherInformation}" rows="10" columns="80" javascriptLibrary="/library/htmlarea"/> <br/>
			 	 		</td>
			 		</tr> 
				</table>	
  				<sakai:button_bar>
					<sakai:button_bar_item	action="#{ProfileTool.processActionEditSave}" value="#{msgs.bar_save}" />
					<sakai:button_bar_item	action="#{ProfileTool.processCancel}"	immediate="true" value="#{msgs.bar_cancel}" />  
				</sakai:button_bar>
			</h:form>
		</sakai:view_content>
	</sakai:view_container>
</f:view>
