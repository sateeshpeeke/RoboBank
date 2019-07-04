<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty responseCode}">
		<h1>${responseCode}:</h1>
	</c:if>

	<c:if test="${not empty responseMessage}">
		<h2>${responseMessage}</h2>
	</c:if>

</body>
</html>