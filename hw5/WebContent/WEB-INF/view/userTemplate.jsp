<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- JSTL -->
<c:if test="${!(empty users)}">
	<c:forEach var="usersIter" varStatus="loop" items="${users}">
		<li class="nav-item">
			<form method="POST"
				action=<c:choose>
					<c:when test="${ (empty user) }">
						visitor.do
					</c:when>
					<c:when test="${usersIter.email != user.email}">
						visitor.do
					</c:when>
					<c:otherwise>
						home.do
					</c:otherwise>
				</c:choose>>
				<input type="hidden" name="userEmail" value="${ usersIter.email }" />
				<button class="nav-link text-white-50"
					style="background-color: transparent; border: transparent;"
					type="submit">${usersIter.firstName} ${usersIter.lastName}</button>
			</form>
		</li>
	</c:forEach>
</c:if>


