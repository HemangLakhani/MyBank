package com.servlets;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lakhani.CheckingAccount;
import com.lakhani.SavingsAccount;
import com.lakhani.Transactions;


/**
 * Servlet implementation class TrransferServlet
 */
public class TrransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CheckingAccount SckAcc, DckAcc;
	private SavingsAccount SsvAcc, DsvAcc;
	private Transactions tr;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrransferServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    		String sourceSelection = request.getParameter("src");
    		String destinationSelection = request.getParameter("des");
    		
    		String amount = request.getParameter("Amount");
    		double amt = Double.parseDouble(amount);
    		
    		
    		//Getting UserName
    		HttpSession session = request.getSession();
    		String userName = (String)session.getAttribute("UName");
    		
    		if(sourceSelection != "" && amount != "" && destinationSelection != "" ) {
    			int SAcc_no = Integer.parseInt(sourceSelection);
    			int DAcc_no = Integer.parseInt(destinationSelection);
    			
    			SckAcc = new CheckingAccount(SAcc_no);
    			SsvAcc = new SavingsAccount(SAcc_no);
    			
    			DckAcc = new CheckingAccount(DAcc_no);
    			DsvAcc = new SavingsAccount(DAcc_no);
    			
    			if(SckAcc.is_CheckingAccount_exist() && DckAcc.is_CheckingAccount_exist()) {
    				
    				double Sbal = SckAcc.withdraw_from_CheckingAccount(amt);
    				double Dbal = DckAcc.deposit_to_CheckingAccount(amt);
    				
    				tr = new Transactions("Transfer", amt, "Checking", "Checking",userName);
				    tr.Record_Transactions();
				    
				    response.sendRedirect("Transfer.jsp?Success=1");
				    
				    String msg = "Transfer Successful!!";
	    			request.getSession().setAttribute("Message", msg);
				    /*
				    request.setAttribute("CBalance", bal);
				    RequestDispatcher rd = request.getRequestDispatcher("/Deposit.jsp");
				    rd.forward(request, response);
				    */
    				
    			}
    			else if(SsvAcc.is_SavingsAccount_exist() && DsvAcc.is_SavingsAccount_exist()) {
    				
    				double Sbal = SsvAcc.withdraw_from_SavingsAccount(amt);
    				double Dbal = DsvAcc.deposit_to_SavingsAccount(amt);
    				
    				tr = new Transactions("Transfer", amt, "Savings", "Savings",userName);
				    tr.Record_Transactions();
				    
				    response.sendRedirect("Transfer.jsp?Success=1");
				    /*
				    request.setAttribute("CBalance", bal);
				    RequestDispatcher rd = request.getRequestDispatcher("/Deposit.jsp");
				    rd.forward(request, response);
				    */
    				
    			}else if(SckAcc.is_CheckingAccount_exist() && DsvAcc.is_SavingsAccount_exist()) {
    				
    				double Sbal = SckAcc.withdraw_from_CheckingAccount(amt);
    				double Dbal = DsvAcc.deposit_to_SavingsAccount(amt);
    				
    				tr = new Transactions("Transfer", amt, "Checking", "Savings",userName);
				    tr.Record_Transactions();
				    
				    response.sendRedirect("Transfer.jsp?Success=1");
				    /*
				    request.setAttribute("CBalance", bal);
				    RequestDispatcher rd = request.getRequestDispatcher("/Deposit.jsp");
				    rd.forward(request, response);
				    */
    			}else if(SsvAcc.is_SavingsAccount_exist() && DckAcc.is_CheckingAccount_exist()) {
    				
    				double Sbal = SsvAcc.withdraw_from_SavingsAccount(amt);
    				double Dbal = DckAcc.deposit_to_CheckingAccount(amt);
    				
    				tr = new Transactions("Transfer", amt, "Savings", "Checking",userName);
				    tr.Record_Transactions();
				    
				    response.sendRedirect("Transfer.jsp?Success=1");
				    /*
				    request.setAttribute("CBalance", bal);
				    RequestDispatcher rd = request.getRequestDispatcher("/Deposit.jsp");
				    rd.forward(request, response);
				    */
    			} 
    			
    		}
    		else {
    			//String msg = "Either your Account Selection or Amount is wrong. Please Refresh Page and Try Again!!";
    			//request.getSession().setAttribute("Message", msg);
    			response.sendRedirect("Transfer.jsp?Success=-1");
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

}
