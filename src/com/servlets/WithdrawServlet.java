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
 * Servlet implementation class Withdraw
 */
public class WithdrawServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CheckingAccount ckAcc;
	private SavingsAccount svAcc;
	private Transactions tr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    		String withdrawSelection = request.getParameter("Withdraw");
    		String amount = request.getParameter("Amount");
    		double amt = Double.parseDouble(amount);
    		
    		//Getting UserName
    		HttpSession session = request.getSession();
    		String userName = (String)session.getAttribute("UName");
    		
    		if(withdrawSelection != "" && amount != "") {
    			int Acc_no = Integer.parseInt(withdrawSelection);
    			ckAcc = new CheckingAccount(Acc_no);
    			svAcc = new SavingsAccount(Acc_no);
    			if(ckAcc.is_CheckingAccount_exist()) {
    				
    				double bal = ckAcc.withdraw_from_CheckingAccount(amt);
    				
    				tr = new Transactions("Withdraw", amt, "Checking", "Checking",userName);
				    tr.Record_Transactions();
				    
				    request.setAttribute("CBalance", bal);
				    RequestDispatcher rd = request.getRequestDispatcher("/Withdraw.jsp?Success=1");
				    rd.forward(request, response);
    				
    			}
    			else if(svAcc.is_SavingsAccount_exist()) {
    				double bal = svAcc.withdraw_from_SavingsAccount(amt);
    				
    				tr = new Transactions("Withdraw", amt, "Savings", "Savings",userName);
				    tr.Record_Transactions();
				    
    				request.setAttribute("SBalance", bal);
    				RequestDispatcher rd = request.getRequestDispatcher("/Withdraw.jsp?Success=1");
				    rd.forward(request, response);
    				
    			}
    		}
    		else {
    			//String msg = "Either your Account Selection or Amount is wrong. Please Refresh Page and Try Again!!";
    			//request.getSession().setAttribute("Message", msg);
    			response.sendRedirect("Deposit.jsp?Success=-1");
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
