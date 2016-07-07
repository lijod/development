<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Handling Form Submission</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
	<body>
		<h1>Form</h1>
	
		<form:form action="welcome.htm"  modelAttribute="user" method="POST">
			<form:input path="username" />
			<form:password path="password" />
			<input type="submit" value="Login" />
		</form:form>
	
	</body>
</html>