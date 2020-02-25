<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- required for the spring-security-taglibs support -->
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<%--  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 --%>
<html>

<head>

<title>spring security-app demo project.</title>



</head>

<body>

	<h1>This is spring security-app home</h1>

	<!-- displaying users role and name -->
	<p>
		User :
		<security:authentication property="principal.username" />
	</p>
	<br />
	<p>
		User Roles :
		<security:authentication property="principal.authorities" />
	</p>
	<br />
	<hr />


	<!--  adding role based restrictions -->
   <security:authorize access="hasRole('MANAGER')">
    <!-- show this link only if user is manager -->
	 
	 <!-- add links for the managers -->
	 <a href="${pageContext.request.contextPath }/leaders">Managers
		Meeting [for managers]</a>
		<br><hr />
	</security:authorize>	
	
	 <security:authorize access="hasRole('ADMIN')">
	
      	<!-- add links for the admins [systems ] -->
	    <a href="${pageContext.request.contextPath }/systems"> system [ admin ] </a>
	    <br><hr />	

    </security:authorize>

	<!-- logout button -->
	<form:form action="${pageContext.request.contextPath }/logout"
		method="POST">

		<input type="submit" value="logout" />

	</form:form>

</body>

</html>