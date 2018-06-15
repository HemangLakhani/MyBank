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
					<li class="current"><a href="ViewBalance.jsp">View Balance</a></li>
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
			<h1 style="float:none;"> Account Summary.</h1>
	<form style="float:left;" action="" method="POST">
	
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
		        
		        for (int i=0;i<NumOfCTokens;i++) {
		        	int CAcc_no = (Integer)ckAccounts.get(i);
		        	
		        	//Getting Checking Account Object
			      	final CheckingAccount ck_Acc; 
		        	ck_Acc = new CheckingAccount(CAcc_no);
		        	
		        	double balance = ck_Acc.ViewCheckingBalance();
				%>
		        	<TABLE cellPadding='3' id="ViewChecking">
		        		<tr>
		        			
		        	     	<td>Checking Account : <%=i+1%></td>
		        	    </tr>
		        	    <tr>
		        	    	<td>Account Number : <%=CAcc_no%></td>
		        	    </tr>
		        	    <tr> 
		        	    	 <td>Balance : <%=balance %></td>
		        	    </tr>
		        	 </TABLE>
		        	<% }
		        	
		        	//Getting number of Savings Account of User
		        	Vector svAccounts = (Vector)session.getAttribute("svAccounts");
		        	int NumOfSTokens = svAccounts.size();
		        	
	    	    	for (int j=0;j<NumOfSTokens;j++) {
	    	    		int SAcc_no = (Integer)svAccounts.get(j);
	    	         	
	    	    		//Getting Checking Account Object
				      	final SavingsAccount sv_Acc; 
				        sv_Acc = new SavingsAccount(SAcc_no);
	    	    		
	    	    		double balance = sv_Acc.ViewSavingsBalance();
					%>
			        	<TABLE cellPadding='3' id="ViewSavings">
			        		<tr>
			        			
			        	     	<td>Saving Account : <%=j+1%></td>
			        	    </tr>
			        	    <tr>
			        	    	<td>Account Number : <%=SAcc_no%></td>
			        	    </tr>
			        	    <tr> 
			        	    	 <td>Balance : <%=balance %></td>
			        	    </tr>
			        	 </TABLE>
			        	<% }
	}%>
			<div id="logout">
				<button id='LogoutButton' style="margin-top:20px;" class = "button_1"><span>Logout</span></button>
			</div>	
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
<script>
var logout = document.getElementById("LogoutButton");
logout.addEventListener("click",function(){
	document.forms[0].action = "LogoutServlet.do";
	document.forms[0].submit();
},false);

</script>
</html>