<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import ="com.lakhani.Account" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width">
<meta name="author" content="Hemang Lakhani">
<title>TDN Banking | Welcome.</title>
<link rel = "stylesheet" href ="./css/stylesheet.css">
<!-- <script src="./script/jquery-3.3.1.min.js"></script>
<script src="./script/main.js"></script> -->
</head>
<body>
	<header>
		<div class="container">
			<div id ="branding">
				<h1><span class="highlight">TDN</span> Banking Inc.</h1>
			</div>
			<nav>
				<ul>
					<li><a href="index.jsp">Home</a></li>
					<li><a href="ViewBalance.jsp">View Balance</a></li>
					<li><a href="Deposit.jsp">Deposit</a></li>
					<li><a href="Withdraw.jsp">Withdraw</a></li>
					<li><a href="Transfer.jsp">Transfer</a></li>
					<li><a href="OpenAccount.jsp">Open Account</a></li>
				</ul>
				<!-- 
				<select> 
   					 <option value="" selected="selected">Select</option> 
    				 <option value="index.jsp">Home</option> 
    				 <option value="ViewBalance.jsp">View Balance</option> 
    				 <option value="Deposit.jsp">Deposit</option> 
    				 <option value="Transfer.jsp">Transfer</option> 
    				 <option value="OpenAccount.jsp">Open Account</option> 
  				</select> 	
  				 -->
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
			<h1 style="float:none;">Thank You for Choosing TDN Internet Banking. You can create Account below.</h1>
				<FORM style="float:left;" NAME="SignUpPage" ACTION="SignUpServlet.do" METHOD ="POST">
		<TABLE cellPadding='3' ALIGN='center'>
			<TR>
				<TD>USERNAME:</TD>
				<TD>
				<INPUT TYPE='text' NAME='UsernameField' SIZE='15' placeholder="Please Type User Name..">
				</TD>
			</TR>
			<TR>
				<TD>PASSWORD:</TD>
				<TD>
				<INPUT TYPE='password' NAME='PasswordField' Value='' SIZE='15' placeholder="Enter Your Password.." >
				</TD>
			</TR>
			<TR>
				<TD>Re-ENTER PASSWORD:</TD>
				<TD>
				<INPUT TYPE='password' NAME='RePasswordField' Value='' SIZE='15' placeholder="Renter Your Password.." onkeyup='check();'>
				</TD>
				<TD><span id ="message"></span></TD>
			</TR>
			<TR>
				<TD>FULL NAME:</TD>
				<TD>
				<INPUT TYPE='text' NAME='NameField' SIZE='15' placeholder="Please Type Your Full Name..">
				</TD>
			</TR>
		</TABLE>
		<div id="formButton">
			<button NAME='submitBTN' onClick="checkInputs()" class = "button_1"><span>SignUp</span></button>
		</div>
		</FORM>
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
<SCRIPT LANGUAGE="JavaScript"> 
		function checkInputs()
		{
		var Prompts = "";
		var Username = window.document.SignUpPage.UsernameField.value;
		var Password = window.document.SignUpPage.PasswordField.value;
		if (Username == "" || Password == "") {
		if (Username == "")
		Prompts +="Please enter your username!\n";
		if (Password == "")
		Prompts +="Please enter your password!\n";
		if (Prompts != "")
		window.alert(Prompts);
		} else {
		document.SignUpPage.submit();
		}
		}
		function check(){
			var Password = window.document.SignUpPage.PasswordField.value;
			var rePassword = window.document.SignUpPage.RePasswordField.value;
			if(Password == rePassword){
				document.getElementById('message').style.color = 'green';
				document.getElementById('message').style.font = '16px bold';
			    document.getElementById('message').innerHTML = 'Matching..';
			}
			else{
				document.getElementById('message').style.color = 'red';
				document.getElementById('message').style.font = '16px bold';
			    document.getElementById('message').innerHTML = 'Not Matching..';
			}
		}
		
		</SCRIPT>
</html>