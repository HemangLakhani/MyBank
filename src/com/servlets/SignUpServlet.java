package com.servlets;

import java.io.IOException;
import java.io.*;
import java.util.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lakhani.Account;

/**
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	 private String Username, Password, Re_enterPassword, CustomerName;
	 private PrintWriter output;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
  //a method called automatically to initialize the servlet
    public void init( ServletConfig config )
       throws ServletException
    {
       super.init( config );
       Username = new String("");
       Password = new String("");
       Re_enterPassword = new String("");
       CustomerName = new String("");

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//obtains a character-based output stream that enables
	      //text data to be sent to the client
	      output = response.getWriter();

	      //specifies the MIME type of the response to the browser
	      response.setContentType( "text/html" );

	      //returns the value associated with a parameter sent to
	      //the servlet as part of a post request
	      Username = request.getParameter( "UsernameField" );
	      Password = request.getParameter( "PasswordField" );
	      Re_enterPassword = request.getParameter( "RePasswordField" );
	      CustomerName = request.getParameter( "NameField" );

	      Account Acct = new Account(Username, Password, Re_enterPassword, CustomerName);
	      if (Acct.signUp()) {
	    	  request.getSession(true).setAttribute("UName", Username);
	    	  response.sendRedirect("Login.jsp");
	          // showSuccess();
	      }
	      else
	           output.println("Account creation failed because of existing username or invalid username. Please try again!");
	}
	
	   //this "cleanup" method is called when a servlet is terminated by the server
	   public void destroy() {
	       output.close();
	   }
	
}
