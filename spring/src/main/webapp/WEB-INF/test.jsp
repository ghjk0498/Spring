<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
</head>
<body>
test
${ restVO.id }
${ restVO.title }
${ restVO.text }
${ restVO.imageUrl }
<form:form  method="POST" action="postClient" modelAttribute="restVO">
	<form:hidden path="title" value="postClient-title"/>
	<form:hidden path="text" value="postClient-text"/>
	<form:hidden path="imageUrl" value="postClient-imageUrl"/>
	<button class="btn btn-lg btn-primary btn-block" type="submit">post</button>
</form:form>
</body>
</html>
