<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %> 

<f:loadBundle basename="org.sakaiproject.tool.profile.bundle.Messages" var="msgs"/>
<f:view>
 <sakai:view_container title="User not logged in"> 
 <sakai:view_content>
		<table width = "700"> 
 			<tr>
 			 	<td  width = "350" valign = "Top"> 
 			 		<h:outputText style="font-weight: bold; color:red;" value="Please login to view your profile" />	 	
 			 	 </td>   				 
  			</tr>
		</table>
</sakai:view_content>	 
</sakai:view_container>  
</f:view>