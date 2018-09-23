<%@page import="java.util.List"%>
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
						<li class="nav-item"><a class="nav-link text-white-50"
							href="login.jsp">Login</a></li>
						<li class="nav-item"><a class="nav-link text-white-50"
							href="register.jsp">Register</a></li>
						<hr>
						<jsp:include page="userTemplate.html" />
					</ul>
				</nav>
			</div>
			<div class="col-9" style="padding-left: 0px;">
				<h1 class="text-white-50 bg-dark display-1 w-100 h-25 text-center"
					style="margin-bottom: 0px;">Power Blog</h1>
				<div class="pre-scrollable" style="max-height: 500px;">
					<h2 style="margin-left: 100px; margin-top: 20px;">Register</h2>
					<form class="w-75 h-75" style="margin-left: 100px;">
						<div class="form-inline" style="margin-bottom: 30px;">
							<label for="usr" style="margin-right: 10px;">First Name:
							</label> <input type="text" class="form-control" id="usr"
								style="margin-right: 30px;" value="${form.firstName}"> <label
								for="usr" style="margin-right: 10px;">Last Name: </label> <input
								type="text" class="form-control" id="usr"
								style="margin-right: 30px;" value="${form.lastName}">
						</div>

						<div class="form-group">
							<label for="usr">E-mail:</label> <input type="text"
								class="form-control" name="email" value="${form.email}">
						</div>
						<div class="form-group">
							<label for="pwd">Password:</label> <input type="password"
								class="form-control" name="password">
						</div>
						<div class="form-group">
							<label for="pwd">Confirm Password:</label> <input type="password"
								class="form-control" name="passwordConfirmed">
						</div>
						<input class="btn btn-secondary" type="submit" name="button"
							value="Register">
					</form>
					<%
						List<String> errors = (List<String>) request.getAttribute("errors");
						if (errors != null) {
							for (String error : errors) {
					%>
					<h3 style="color: red; margin-left: 100px;">
						<%=error%>
					</h3>
					<%
						}
						}
					%>
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