<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8" />
<title>Session Error</title>
</head>

<body>

	<h2>Abstract Action Class Error Message</h2>


	<c:if test="${!(empty errors)}">
		<c:forEach var="error" items="${errors}">
			<h3 style="color: red; margin-left: 100px;">${error}</h3>
		</c:forEach>
	</c:if>

	<p>Normally, in deployment, we would probably send back an error
		code in the HTTP response (like 404 -- Not Found) but to facilitate
		debugging, since you've probably made a mistake during development,
		we're providing the error message, above.</p>

</body>
</html>