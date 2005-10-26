<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %> 
<f:loadBundle basename="org.sakaiproject.tool.profile.bundle.Messages" var="msgs"/>
<f:view>
 <sakai:view title="User not logged in"> 
  		<h:outputText id="error" style="font-weight: bold; color:red;" value="Please login to view your profile" />	 	
 </sakai:view>  
</f:view>