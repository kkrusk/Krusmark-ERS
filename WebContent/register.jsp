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

<title>Krusmark | Sign Up</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	id="bootstrap-css">

<!-- Custom styles for this template -->
<link href="./css/style.css" rel="stylesheet">

</head>
<body>
	<form class="form-signin" action="register" method="post">
		<div class="text-center mb-4">
			<a href="index.jsp">
				<div class="logo mb-2">KRUSMARK</div>
			</a>
			<h1 class="h4 mb-3 font-weight-normal">Create your account</h1>
		</div>

		<div class="form-row">
			<div class="col">
				<div class="form-label-group">
					<input type="text" id="inputFirstName" class="form-control"
						placeholder="First name" name="fn" autofocus required> <label
						for="inputFirstName">First name</label>
				</div>
			</div>
			<div class="col">
				<div class="form-label-group">
					<input type="text" id="inputLastName" class="form-control"
						placeholder="Last name" name="ln" required> <label
						for="inputLastName">Last name</label>
				</div>
			</div>
		</div>

		<div class="form-label-group">
			<input type="text" id="inputUsername" class="form-control"
				placeholder="Username" name="un" required> <label
				for="inputUsername">Username</label>
		</div>

		<div class="form-label-group">
			<input type="text" id="inputEmail" class="form-control"
				placeholder="Email address" name="em" required> <label
				for="inputEmail">Email address</label>
		</div>

		<div class="form-label-group">
			<input type="password" id="inputPassword" class="form-control"
				placeholder="Password" name="pw" required> <label
				for="inputPassword">Password</label>
		</div>

		<button class="btn btn-md btn-primary btn-block" type="submit">Sign
			up</button>
		<p class="mt-5 mb-3 text-muted text-center small">&copy; Krusmark
			2017-2018</p>
	</form>

</body>
</html>