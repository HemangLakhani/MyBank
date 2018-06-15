<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="com.lakhani.*" %>


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
					<li><a href="index.jsp">Home</a></li>
					<li><a href="ViewBalance.jsp">View Balance</a></li>
					<li class="current"><a href="DepositServlet.do">Deposit</a></li>
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
			<h1 style="float:none;"> Enter Your Details to Deposit.</h1>
			
				<form style="float:left;" name="DepositPage" action="DepositServlet.do" method = "POST" onsubmit="return validation()">
				
	<%
	/*This is secure page. So, after logout the page should not go back. For that we have two ways.
	1. Tell browser to not store cache and revalidate each page after revisiting.
	2. Disable browser back button. 
	
	However, Disabling browser back button is bad practice. It would create bad user experience. So, we have to use way no 1.
	For that we have to tell browser to not set cache and store as well as revalidate each page.
	So, we have to set "Browser Header".
	*/
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1 protocol version
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0 protocol version
	response.setDateHeader("Expires", 0); // Proxies
	
	
	// This is secure page and you can perform transaction after Login only. So, if not Login then go to Login Page.
	if(session.getAttribute("UName") == null){
		response.sendRedirect("Login.jsp");
	}else{
				
				// get session scoped attribute
		        // Getting Number of Checking Accounts of User
				Vector ckAccounts = (Vector)session.getAttribute("ckAccounts");
		        int NumOfCTokens = ckAccounts.size();
		        
		        //Getting number of Savings Account of User
	        	Vector svAccounts = (Vector)session.getAttribute("svAccounts");
	        	int NumOfSTokens = svAccounts.size();
	        	
				%>
				<TABLE class="table" cellPadding='3' ALIGN='center'>
					<TR>
					<TD>Choose Your Account :</TD>
					<td>
					<select name ="Deposit" required class="drpdwn">
						<!-- <option value="">--Select--</option> --> 
						<%
						for (int i=0;i<NumOfCTokens;i++) {
		  
		        		//Getting Checking Account Number
		        		int CAcc_no = (Integer)ckAccounts.get(i);
		   				%>
  						<option value="<%=CAcc_no%>">Checking Account <%=i+1 %></option>
						<% } 
	    	    		for (int j=0;j<NumOfSTokens;j++) {
	    	    		
	    	    		//Getting Saving Account Number
	    	    		int SAcc_no = (Integer)svAccounts.get(j);
	    	        	%>
	    	        	<option value="<%=SAcc_no%>">Savings Account <%=j+1 %></option>
						<% }
	}	%>
					</select>
					</td>
					</TR>
					<TR>
						<TD>Enter Amount :</TD>
						<TD>
						<INPUT TYPE='number' NAME='Amount' SIZE='15' placeholder="Enter Amount" onKeyUp ="checkAmount()">
						</TD>
					</TR>
					</TABLE>
					<div id="formButton">
						<button id='DepositButton' class = "button_1"><span>Deposit</span></button>
					</div>
					<div id="show">
						<h3>Your Current Balance is :<b>${CBalance}</b><b>${SBalance}</b></h3>
					</div>
					
				</form>
				<div id="logout">
					<button id='LogoutButton' style="float:right;margin-top:100px;" class = "button_1"><span>Logout</span></button>
				</div>		
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
function checkAmount(){
	var amount = window.document.DepositPage.Amount.value;
	
	var prompt ="";
	if(amount < 0){
		prompt +="Amount should be Positive!!";
		if (prompt != ""){
			window.document.DepositPage.reset();
			window.alert(prompt);
		}
	}
}
function validation(){
	var amount = window.document.DepositPage.Amount.value;
	
	if(amount === ""){
		window.alert("Please Enter Amount.");
		return false;
	}
}
var logout = document.getElementById("LogoutButton");
logout.addEventListener("click",function(){
	document.forms[0].action = "LogoutServlet.do";
	document.forms[0].submit();
},false);
/*
function showBalance(){
	
	var x = document.getElementById("show");
	if(x.style.display === "none"){
		x.style.display = "block";
	}
	else{
		x.style.display = "none";
	}
}
*/

</script>
</html>