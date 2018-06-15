<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width">
<meta name="author" content="Hemang Lakhani">
<title>TDN Banking | Welcome.</title>
<link rel = "stylesheet" href ="./css/stylesheet.css">
</head>
<body>
	<header>
		<div class="container">
			<div id ="branding">
				<h1><span class="highlight">TDN</span> Banking Inc.</h1>
			</div>
			<nav>
				<ul>
					<li class="current"><a href="index.jsp">Home</a></li>
					<li><a href="ViewBalance.jsp">View Balance</a></li>
					<li><a href="Deposit.jsp">Deposit</a></li>
					<li><a href="Withdraw.jsp">Withdraw</a></li>
					<li><a href="Transfer.jsp">Transfer</a></li>
					<li><a href="OpenAccount.jsp">Open Account</a></li>
				</ul> 	
			</nav>
		</div>
	</header>
	<section id ="showcase">
		<div class="container">
		<h1>America's Most Affordable Banking.</h1>
		<p>TDN banking is proud to all Americans and we are also proud to serve the nation.</p>
		</div>
	</section>
	<section id ="options">
		<div class="container">
			<h1> Welcome to TDN Internet Banking. Please choose an option to proceed.</h1>
				<form action="" method = "POST">
					<button type="submit" id="SignUp" class="button_1"><span>SignUp</span></button>
					<button type="submit" id="Login" class="button_1"><span>Login</span></button>
				</form>
		</div>
	</section>
	<section id ="boxes">
		<div class= "container">
			<div class="box">
				<img alt="" src="./img/HomeLoan.jpg">
				<h3> Home Loan</h3>
				<p>Make your dream come true. Apply for Home Loan Today.</p>
			</div>
			<div class="box">
				<img alt="" src="./img/CarLoan.jpg">
				<h3> Car Loan</h3>
				<p>Make your dream come true. Apply for Car Loan Today.</p>
			</div>
			<div class="box">
				<img alt="" src="./img/BusinessLoan.jpg">
				<h3> Business Loan</h3>
				<p>Make your efforts worthy. Apply for Business Loans Today.</p>
			</div>
		</div>
	</section>
	<footer>
		<p>TDN Banking Inc. copyright &copy; 2018</p>
	
	</footer>
</body>
<script language = "javascript">
var signup = document.getElementById("SignUp");
signup.addEventListener("click", function() {
	document.forms[0].action = "SignUp.jsp";
    document.forms[0].submit(); //submit the form or save it etc
}, false); 
var login = document.getElementById("Login");
login.addEventListener("click", function(){
	document.forms[0].action = "Login.jsp";
	document.forms[0].submit();
},false);
</script>
</html>