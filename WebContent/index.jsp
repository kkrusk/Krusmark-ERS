
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	session = request.getSession(false);
	if (session.getAttribute("usr") != null)
		response.sendRedirect("main.jsp");
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<link rel="icon" type="image/png" href="./img/favicon.ico" />

<title>Krusmark | Expense Reporting Management</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	id="bootstrap-css">

<!-- Importing fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Merriweather|Open+Sans"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="./css/style.css" rel="stylesheet">
<link href="./css/video.css" rel="stylesheet">

</head>
<body>
	<div class="video-background">
		<div class="video-foreground">
			<iframe
				src="https://www.youtube.com/embed/dYAQw22cW84?controls=0&showinfo=0&rel=0&autoplay=1&loop=1&playlist=dYAQw22cW84"
				frameborder="0" allowfullscreen></iframe>
		</div>
	</div>

	<div id="vidtop-content">
		<form class="form-signin" action="login" method="post">
			<div class="text-center mb-4">
				<a href="#">
					<div class="logo mb-2">KRUSMARK</div>
				</a>
				<h1 class="h4 mb-3 font-weight-normal">Expense Reimbursement
					System</h1>
			</div>

			<div class="form-label-group">
				<input type="text" id="inputEmail" class="form-control"
					placeholder="Email address" name="un" required autofocus> <label
					for="inputEmail">Email address or Username</label>
			</div>

			<div class="form-label-group">
				<input type="password" id="inputPassword" class="form-control"
					placeholder="Password" name="pw" required> <label
					for="inputPassword">Password</label>
			</div>

			<%
				String error_msg = (String) request.getAttribute("error");
				if (error_msg != null) {
					out.println("<div class='alert alert-danger text-center' role='alert'>" + error_msg + "</div>");
				}

				String success_msg = (String) request.getAttribute("success");
				if (success_msg != null) {
					out.println("<div class='alert alert-success text-center' role='alert'>" + success_msg + "</div>");
				}
			%>

			<div class="checkbox mb-3">
				<label> <input type="checkbox" value="remember-me">
					Remember me
				</label>
			</div>

			<div class="text-center mb-4">
				<small><a href="register.jsp">Not a user? Create an
						account here</a></small>
			</div>

			<button class="btn btn-md btn-primary btn-block" type="submit">Sign
				in</button>
			<p class="mt-5 mb-3 text-muted text-center small">&copy; Krusmark
				2017-2018</p>
		</form>
	</div>
</body>
</html>