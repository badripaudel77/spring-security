<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">
.loginError {
   color:red;
}

.logoutError {
 color : green;
}

</style>

</head>
<body>

<h2>custom login in spring security app</h2>

<form:form action="${pageContext.request.contextPath }/authenticateTheUser" method="POST">
      
      <!-- custom error handling for login-->
      <c:if test="${param.error != null }">
          <i class="loginError">sorry u entered invalid credentials</i>
      </c:if>
     
      <!-- custom error handling for logout-->
      <c:if test="${param.logout != null }">
          <i class="logoutError">You have been logged out ! </i>
      </c:if>
      
      <!-- use username and password as spring will look for it -->
      <p> User-name  <input type="text" name="username" />  </p>
      <p>  Password  <input type="password" name="password" /> </p>
      
      <input type="submit" value="submit" />
       
      
</form:form>

</body>
</html>