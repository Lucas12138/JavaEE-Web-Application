<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${!(empty posts)}">
	<!-- posts -->
	<c:forEach var="post" items="${posts}">
		<div class="row" style="margin-left: 20px; margin-bottom: 5px;">
			<!-- only show delete post for home pages -->
			<c:if test="${fn: contains(pageContext.request.requestURI, 'home')}">
				<form class="delete-form" method="POST" action="delete.do">
					<input type="hidden" name="postId" value="${ post.postId }" /> <input
						type="hidden" name="requestURIFromPostCommentTemplate"
						value="${pageContext.request.requestURI}" />
					<c:set var="userEmail" value="${ userSelected.email }"
						scope="session" />
					<button type="submit" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</form>
			</c:if>
			<!-- sanitize the content -->
			<c:out value="${post.content}"></c:out>
			--
			<fmt:formatDate value="${post.postDatetime}" type="both"
				pattern="MMM-dd-yyyy  h:mm aa" />
		</div>

		<!-- comments -->
		<c:if test="${!(empty postIdToCommentsMap)}">
			<c:forEach var="comment" items="${postIdToCommentsMap[post.postId]}">
				<div class="row" style="margin-left: 40px; margin-bottom: 10px;">
					<!-- user has to login and is the comment owner -->
					<c:if test="${!(empty user) && comment.email == user.email}">
						<form class="delete-form" method="POST" action="delete.do">
							<input type="hidden" name="commentId"
								value="${ comment.commentId }" /> <input type="hidden"
								name="requestURIFromPostCommentTemplate"
								value="${pageContext.request.requestURI}" />
							<c:set var="userEmail" value="${ userSelected.email }"
								scope="session" />
							<button type="submit" class="close" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</form>
					</c:if>
					Comment by ${emailToFullNameMap[comment.email]} -
					<!-- sanitize the content -->
					<c:out value="${comment.content}"></c:out>
					--
					<fmt:formatDate value="${comment.commentDatetime}" type="both"
						pattern="MMM-dd-yyyy  h:mm aa" />
				</div>
			</c:forEach>

		</c:if>
		<!-- new comment -->



		<div class="form-inline"
			style="margin-left: 40px; margin-bottom: 10px;">
			<form
				method=<c:choose>
							<c:when test="${empty user}">
							GET
							</c:when>
							<c:otherwise>
							POST
							</c:otherwise>
						</c:choose>
				action=<c:choose>
							<c:when test="${empty user}">
								login.do
							</c:when>
							<c:when test="${fn: contains(pageContext.request.requestURI, 'home')}">
								home.do
							</c:when>
							<c:otherwise>
								visitor.do
							</c:otherwise>
						</c:choose>>
				<c:set var="userEmail" value="${ userSelected.email }"
					scope="session" />
				<input type="hidden" name="postId" value="${ post.postId }" /> <input
					type="text" class="form-control w-50" name="comment" value="">
				<input class="btn btn-secondary" type="submit" name="button"
					style="margin-left: 10px;" value="Comment">
			</form>
		</div>

	</c:forEach>
</c:if>