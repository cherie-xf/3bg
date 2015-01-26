<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<body>
<sec:authorize access="isAnonymous()">
  please login
</sec:authorize>
<sec:authorize access="isAuthenticated()">
   <h4>
	Hi <sec:authentication property="principal.lastName" /> <sec:authentication property="principal.firstName" />,
	</h4>
	<p>
	Congratulations! You have login 3qnow.com with <sec:authentication property="principal.username" /> successfully.
	<a href="<c:url value="j_spring_security_logout" />" > Logout</a>
	</p>
	<p>${applicationScope.globalnav[0].category_name}</p>
</sec:authorize>
</body>
</html>
