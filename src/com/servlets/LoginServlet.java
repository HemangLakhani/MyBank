package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lakhani.*;
import java.util.*;

/**
 * Servlet implementation class LoginServlet
 * LoginServlet implements Filter
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private CheckingAccount ckAcc;
	private SavingsAccount svAcc;
	private Vector ckAccounts, svAccounts;
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String UName = request.getParameter("UserName");
            String Password = request.getParameter("Password");
            Account acc = new Account(UName,Password) ;
            String customerName = acc.signIn();
            if(customerName!= "") {	
            	
            	//Getting Total Accounts for this User.
            	ckAcc = new CheckingAccount(UName);
            	ckAccounts = ckAcc.getCAccounts();
            	
            	//Getting Total Accounts for this User.
   			 	svAcc = new SavingsAccount(UName);
   			 	svAccounts = svAcc.getSAccounts();
   			 	
   			 	// set session scoped attribute
   		        HttpSession session = request.getSession();
   		        
   		        //Passing Number of Checking Accounts and Saving Accounts of this User. 
   		        session.setAttribute("ckAccounts", ckAccounts);
   		        session.setAttribute("svAccounts", svAccounts);
   		  
   		        //Passing User Name and Customer Name in session
   		        session.setAttribute("UName", UName);
   		        session.setAttribute("customerName", customerName);
   		        
   		        //Redirecting to Account Overview Page 
            	response.sendRedirect("ViewBalance.jsp");
            }else {
            	response.sendRedirect("index.jsp");
            }
            
        }
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	/*public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException{
	
	}*/
	
}
