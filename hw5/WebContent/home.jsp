<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

<title>Power Blog</title>
</head>
<body>

	<div class="container">
		<!-- <div class="row h-25 fixed-top">
				<h1 class="text-white-50 bg-dark display-1 w-100"
					style="margin-bottom: 0px;">
					 Power Blog
				</h1>
			</div> -->
		<div class="row h-100 fixed-top">
			<div class="col-3" style="padding-right: 0px;">
				<img class="img-fluid" src="blog-home.gif" alt="Power Blog"
					width="400" height="100">
				<nav class="navbar bg-dark h-75">
					<ul class="navbar-nav">
						<p class="text-white">
							<c:choose>
								<c:when test="${ (empty user) }">
									???
								</c:when>
								<c:otherwise>
									Welcome, ${user.firstName} ${user.lastName}
								</c:otherwise>
							</c:choose>
						</p>
						<li class="nav-item">
							<form method="POST" action="home.do">
								<button class="nav-link text-white-50"
									style="background-color: transparent; border: transparent;"
									type="submit">Home</button>
							</form>
						</li>
						<li class="nav-item">
							<form method="POST" action="logout.do">
								<button class="nav-link text-white-50"
									style="background-color: transparent; border: transparent;"
									type="submit">Logout</button>
							</form>
						</li>
						<hr>
						<jsp:include page="userTemplate.jsp" />
					</ul>
				</nav>
			</div>
			<div class="col-9" style="padding-left: 0px;">
				<h1 class="text-white-50 bg-dark display-1 w-100 h-25 text-center"
					style="margin-bottom: 0px;">Power Blog</h1>
				<!-- <div style="overflow:auto; width:1000px;height:500px;"> -->
				<div style="position: fixed; overflow: auto; top: 25%; bottom: 10px; width: 75%;">
					<h2 class="text-center" style="margin-top: 15px;">
						<c:choose>
							<c:when test="${ (empty user) }">
							???
						</c:when>
							<c:otherwise>
							${user.firstName} ${user.lastName} 's Home Page
						</c:otherwise>
						</c:choose>

					</h2>

					<!-- JSTL -->
					<jsp:include page="postAndCommentTemplate.jsp" />

					<!-- new post -->
					<div style="margin-left: 40px; margin-top: 30px;">
						<label>New Post:</label>
						<form method="POST" action="home.do">
							<textarea class="form-control w-50" rows="5" name="post"
								value="${postForm.postContent}"></textarea>
							<input class="btn btn-secondary" type="submit" name="button"
								style="margin-top: 20px;" value="Submit">
						</form>
					</div>

					<!-- errors -->
					<c:if test="${!(empty errors)}">
						<c:forEach var="error" items="${errors}">
							<h3 style="color: red; margin-left: 40px;">${error}</h3>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>



	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>