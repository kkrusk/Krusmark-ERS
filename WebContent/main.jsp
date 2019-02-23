<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page import="com.krusmark.models.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<%
	User u = null;
	session = request.getSession(false);
	if (session.getAttribute("usr") == null)
		response.sendRedirect("index.jsp");
	else
		u = (User) session.getAttribute("usr");
%>
<title>Krusmark | Dashboard</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="desription" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" href="./img/favicon.ico" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/floating-labels.css">
<link rel="stylesheet" href="./css/dash.css">
<link rel="stylesheet" href="./css/footer.css">

<!-- Importing fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Merriweather|Open+Sans"
	rel="stylesheet">
<!-- Importing JS files -->
<!-- Bootstrap related JS files -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>

<!-- Include Date Range Picker -->
<script type="text/javascript"
	src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />

<!-- Extra external JS -->
<script src="./js/statistics.js"></script>
<script src="./js/datetime.js"></script>
<script src="./js/datatable.js"></script>
<script src="./js/userimages.js"></script>

</head>
<body id="page-top" onload="datetime('curdt');">
	<!-- document.getElementById('dbd').submit(); -->
	<nav class="navbar navbar-expand-lg bg-dark navbar-dark fixed-top"
		id="mainNav">
	<div class="container background-container">
		<!-- Brand -->
		<a class="navbar-brand logo" href="#page-top">KRUSMARK</a>
		<!-- Toggler/collapsibe Button -->
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<!-- Navbar links -->
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <img id="profilepicture1"
						src="" height=30px
						style="border-radius: 50%; clip: rect(-50px, 50px, 50px, -50px);" />
						<code>
							<%
								if (u != null) {
									String type = "";
									if (u.getRole() == 0)
										type = "{e}";
									if (u.getRole() == 1)
										type = "{fm}";
									out.print(u.getFirstname() + " " + u.getLastname() + " <code>" + type + "</code>");
								}
							%>
						</code>
				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" data-toggle='modal'
							data-target="#userInfo" href="#userInfo">Account Information</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="logout">Log out</a>
					</div></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="jumbotron" style="margin-bottom: 0;">
		<div class="container extrapadding">
			<h1 class="display-4">
				WELCOME &lt;<%
				if (u != null) {
					out.print(u.getFirstname());
				}
			%>&gt;
			</h1>
			<p class="h5">
				The current
				<code>date</code>
				and
				<code>time</code>
				is:
				<samp>
					<span id="curdt"></span>
				</samp>
			</p>
		</div>
	</div>
	<div class="container-fluid" style="background-color: #e9ecef;">
		<div class="container">
			<ul class="nav nav-tabs colormod">
				<li class="nav-item"><a class="nav-link active"
					data-toggle="tab" href="#status">Reimbursement(s)</a></li>

				<%
					String s = "<li class='nav-item'><a class='nav-link' data-toggle='tab' href='#submit'>Submit</a></li>";
					if (u != null) {
						if (u.getRole() == 0)
							out.print(s);
					} else
						out.print(s);
				%>
			</ul>
		</div>
	</div>
	<div class="container background-container">
		<div class="tab-content">
			<!-- TABLE TAB -->
			<div id="status" class="tab-pane active container">
				<div class="container extrapadding">

					<%
						String graphs = "<div class='container'><div class='row'><div class='col-md-auto'><canvas id='webchart' class='doughnutstuffing' height='400'></canvas></div><div class='col'><canvas id='barchart'></canvas></div></div></div>";
						String bardiv = "<img src='img/diamond.png' width=100% style='border-radius: 10px 10px 0 0;' />";
						String graphsjs = "<script type='text/javascript'>(function() { getChartData(); })();</script>";
						if (u != null) {
							if (u.getRole() == 1) {
								out.print(graphs);
								out.print(graphsjs);
							} else
								out.print(bardiv);
						}
					%>

					<div class="table-responsive" id="tableToExport">
						<!-- <img src="img/diamond.png" width=100% height=30px
							style="border-radius: 10px 10px 0 0;" /> -->
						<table id="datatable"
							class="table table-hover table-expandable table-striped table-bordered"
							style="overflow: hidden; margin: 0;">
							<thead id='stopper'>
								<tr>
									<th colspan="3"><input class="form-control" type="text"
										name="datefilter" placeholder="Date range" style="width: 100%"
										id="datefilter" /> <script type="text/javascript"
											src="js/daterangepicker.js"></script></th>
									<th colspan="3"><input class="form-control" type="text"
										id="filterTableInput" onkeyup="fltrTbl()"
										placeholder="Filter by author..." title="Table filters"
										style="width: 100%;"></th>
									<th><select class="form-control" id="type"
										onchange="fltrTbl()">
											<option selected>-</option>
											<option>Pending</option>
											<option>Approved</option>
											<option>Denied</option>
									</select></th>
								</tr>
								<tr>
									<th onclick="sortTable(0)">Amount</th>
									<th onclick="sortTable(1)">Submitted</th>
									<th>Resolved</th>
									<th onclick="sortTable(3)">Author</th>
									<th>Resolver</th>
									<th onclick="sortTable(5)">Type</th>
									<th onclick="sortTable(6)">Status</th>
								</tr>
							</thead>
							<tbody id="dataTableBuffer">
							</tbody>
						</table>
						<div class="container"
							style="background-color: #e9ecef; margin: 0; border-radius: 0 0 10px 10px; height: 2rem;">&nbsp;</div>
					</div>
				</div>
			</div>
			<!-- SUBMISSION FORM -->
			<div id="submit" class="tab-pane container">
				<div class="container extrapadding">
					<h1 class>Reimbursement Submission</h1>
					<div class="extrapadding">
						<form action="insert" method="post" enctype='multipart/form-data'>
							<div class="form-group row">
								<div class="col">
									<label for="name">Name:</label> <input type="text"
										class="form-control-plaintext" readonly name="fname"
										id="fname"
										placeholder="<%out.print((u != null) ? (u.getFirstname() + " " + u.getLastname()) : "John Doe");%>">
									<input type="hidden" name="aid"
										value="<%out.print((u != null) ? u.getId() : "");%>">
								</div>
								<div class="col">
									<label for="email">Email:</label> <input type="email"
										class="form-control-plaintext" readonly name="email"
										id="email"
										placeholder="<%out.print((u != null) ? u.getEmail() : "JohnDoe@email.ext");%>">
								</div>
							</div>
							<div class="form-group row">
								<div class="col">
									<label for="name">Amount:</label> <input type="number"
										value="0.00" min="0.01" step="0.01" data-number-to-fixed="2"
										data-number-stepfactor="100" class="form-control currency"
										id="amount" name="amount" required>
								</div>
								<div class="col">
									<label for="sel1">Type:</label> <select class="form-control"
										id="type" name="type" required>
										<option disabled selected value>-</option>
										<option value="1">Lodging</option>
										<option value="2">Travel</option>
										<option value="3">Food</option>
										<option value="4">Other</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="message">Description:</label>
								<textarea class="form-control" rows="6" name="description"
									id="description" placeholder="Description"></textarea>
							</div>
							<div class="form-group">
								<label for="uploadfile">Reciept (Optional):</label> <input
									type="file" class="form-control" name="receipt" id="reciept">
							</div>
							<div class="form-group extrapadding">
								<button type="submit" class="btn btn-default" name="submit"
									id="submit">Send</button>
							</div>
						</form>
					</div>
				</div>
			</div>


		</div>
	</div>

	<!-- Edit User Information Modal -->
	<div class="modal fade" id="userInfo" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						<strong>Account</strong> Information
					</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form class="form-signin" action="userUpdate" method="post"
						enctype='multipart/form-data'>
						<div class="text-center mb-4">
							<img id="profilepicture2"
								src=""
								class="mb-3" width="300px"
								style="border-radius: 50%; border-style: solid; border-width: 1px; clip: rect(-300px, 300px, 300px, -300px);" />


							<p class="mb-3 font-weight-normal">
								Password <strong>required</strong> to update Account Information
							</p>
						</div>

						<div class="form-label-group">
							<input type="file" class="form-control" name="userImage"
								id="userImage"> <label for="userImage">Profile Picture</label>
						</div>

						<div class="form-row">
							<div class="col">
								<div class="form-label-group">
									<input type="text" id="inputFirstName" class="form-control"
										placeholder="First name" name="fn" required
										value="<%out.print((u != null) ? u.getFirstname() : "");%>">
									<label for="inputFirstName">First name</label>
								</div>
							</div>
							<div class="col">
								<div class="form-label-group">
									<input type="text" id="inputLastName" class="form-control"
										placeholder="Last name" name="ln" required
										value="<%out.print((u != null) ? u.getLastname() : "");%>">
									<label for="inputLastName">Last name</label>
								</div>
							</div>
						</div>

						<div class="form-label-group">
							<input type="text" id="inputUsername" class="form-control"
								placeholder="Username" name="un" required
								value="<%out.print((u != null) ? u.getUsername() : "");%>">
							<label for="inputUsername">Username</label>
						</div>

						<div class="form-label-group">
							<input type="text" id="inputEmail" class="form-control"
								placeholder="Email address" name="em" required
								value="<%out.print((u != null) ? u.getEmail() : "");%>">
							<label for="inputEmail">Email address</label>
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
						%>

						<button class="btn btn-md btn-primary btn-block" type="submit">Update</button>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Receipt</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>

				</div>
				<div class="modal-body">
					<p>
						<!-- <img src="./img/gandalf.gif" width=100%> -->
					<div class="container" id="hiddenReceipt"></div>
					</p>
					<div class="card" id="hiddenDesc"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<!-- About Us Modal -->
	<div class="modal fade" id="devs" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="row" style="margin: 0">
					<div class="col"
						style="padding: 0; background-image: url('img/devsatwork.gif'); background-size: cover; background-position: center center; height: 300px;"></div>
				</div>
				<div class="row" style="margin: 0">
					<div class="col" style="padding: 0">
						<div class="modal-header undershadowed">
							<!-- <h5 class="modal-title logo" id="exampleModalLabel">Krusmark</h5> -->
							<h3>Development Team</h3>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body p-5">
							<div class="row">
								<div class="col-sm-4 p-3 text-right">
									<img
										src="https://media.licdn.com/dms/image/C4D03AQFy3xSitA47yw/profile-displayphoto-shrink_200_200/0?e=1528866000&v=beta&t=2VVmJC1kki2gg_rV6DXUtt1UlvY-SKxdQc0Mg8Qw3ec"
										width=100% style="border-radius: 50%;">
								</div>
								<div class="col-sm-8 p-3 text-left">
									<h3>Vince Mark</h3>
									<p class="text-muted">
										BS Computer & Network Security from Wilmington University<br />
										Front End / Java Developer
									</p>
									<blockquote class="blockquote">
										<p class="mb-0">The secret of getting ahead is getting started.</p>
										<footer class="blockquote-footer">Mark Twain</footer>
									</blockquote>
								</div>
							</div>
							<div class="dropdown-divider"></div>
							<div class="row">
								<div class="col-sm-8 p-3 text-right">
									<h3>Kyle Kruskamp</h3>
									<p class="text-muted">
										BS Computer Information Systems from California State University<br />
										Back End / Database Developer
									</p>
									<blockquote class="blockquote">
										<p class="mb-0">It always seems impossible until it's done</p>
										<footer class="blockquote-footer">Nelson Mandela</footer>
									</blockquote>
								</div>
								<div class="col-sm-4 p-3 text-left">
									<img
										src="https://media.licdn.com/dms/image/C5603AQFy-T3doBVgww/profile-displayphoto-shrink_800_800/0?e=1529089200&v=beta&t=nKVH5xir711xPqAbIju2RvbxpnbH_sto6n4Fk0DImLQ"
										width=100% style="border-radius: 50%;">
								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--Footer-->
	<footer class="container-fluid redfooter page-footer font-small">
	<div class="container text-center text-md-left">
		<div class="row">
			<div class="col-md-6">
				<h5 class="text-uppercase">Krusmark Expense Reimbursement
					Software</h5>
				<p>Krusmark is the easiest way for you to automate how you
					reimburse your team, reconcile corporate credit cards, and
					implement your expense policy.</p>
				<p>
					<a data-target="#devs" data-toggle="modal" class="MainNavText"
						id="MainNavHelp" href="#devs">Developers</a>
				</p>
			</div>
			<div class="col-md-6">
				<h5 class="text-uppercase">Environments Used</h5>
				<ul class="list-unstyled">
					<li>Front End: <strong>HTML5/JSP/CSS3</strong></li>
					<li>JS: <strong>Bootstrap 4, Charts.js, DataPicker.js</strong></li>
					<li>Servlets: <strong>Java</strong></li>
					<li>Database: <strong>MySQL</strong></li>
				</ul>
			</div>
		</div>
	</div>
	</footer>

	<script type="text/javascript">
		encryptUserId(
	<%out.print((u != null) ? u.getId() : 0);%>
		);
		$(document)
				.ready(
						function() {
							console.log("ready!");

							readJSON(
	<%out.print((u != null) ? u.getId() + "," + u.getRole() : 0);%>
		);
						});
	</script>
</body>
</html>