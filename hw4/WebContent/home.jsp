
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
						<p class="text-white">Welcome, Lucas Liu</p>
						<li class="nav-item"><a class="nav-link text-white-50"
							href="#">Home</a></li>
						<li class="nav-item">
							<form method="POST"
								action="Logout">
								<button class="nav-link text-white-50" style="background-color: transparent; border: transparent;"  type="submit">Logout</button>
							</form>
						</li>
						<hr>
						<jsp:include page="userTemplateLoginStatus.html" />
					</ul>
				</nav>
			</div>
			<div class="col-9" style="padding-left: 0px;">
				<h1 class="text-white-50 bg-dark display-1 w-100 h-25 text-center"
					style="margin-bottom: 0px;">Power Blog</h1>
				<h2 class="text-center" style="margin-top: 15px;">Lucas Liu's
					Home Page</h2>
				<div class="row" style="margin-left: 20px; margin-bottom: 5px;">
					<button type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					J2EE is a good course! -- 9/15/2018 11:12am
				</div>
				<div class="row" style="margin-left: 40px; margin-bottom: 10px;">
					<button type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					Comment by Donald Trump - We need more money to build the wall.
					9/15/2018 12:12am
				</div>
				<div class="row" style="margin-left: 20px; margin-bottom: 5px;">
					<button type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					Hello world! 9/15/2018 13:11am
				</div>
				<div class="row" style="margin-left: 40px; margin-bottom: 10px;">
					<button type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					Comment by Lucas Liu - Let's get it started! 9/15/2018 14:01am
				</div>

				<div style="margin-left: 40px; margin-top: 30px;">
					<label for="comment">New Post:</label>
					<textarea class="form-control w-50" rows="5" id="comment"></textarea>
					<a class="btn btn-secondary text-white" style="margin-top: 20px;">Submit</a>
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