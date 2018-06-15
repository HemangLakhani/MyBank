package com.lakhani;

import java.lang.*;
import java.util.*;
import java.sql.*;
import com.lakhani.*;

public class CheckingAccount{

private int CheckingAccountNumber;
private String CustomerName;
private double Balance;
private String CustomerID;

	public CheckingAccount(int CheckingAccountNumber, String cName, double bal, String UName){
		this.CheckingAccountNumber = CheckingAccountNumber;
		this.CustomerName = cName;
		this.Balance = bal;
		this.CustomerID = UName;
	}
	public CheckingAccount(int CheckingAccountNumber){
		this.CheckingAccountNumber = CheckingAccountNumber;
	}
	public CheckingAccount(String UName){
		this.CustomerID = UName;
	}


	public double open_CheckingAccount(){
			double bal = -100;
			boolean done = !CustomerName.equals("") && !CustomerID.equals("");
			try{
				if(done){

				DBConnection ToDB = new DBConnection();
				Connection con = ToDB.openConn();
				Statement st = con.createStatement();
				String sql = "SELECT CheckingAccountNumber FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; //SQL query command
				ResultSet Rslt = st.executeQuery(sql); //Inquire if the accountNumber exsits.
		        done = done && !Rslt.next();
					if (done) {
						Statement st2 = con.createStatement();
					    sql = "INSERT INTO CheckingAccount(CheckingAccountNumber, CustomerName, Balance, CustomerID) VALUES ('"+CheckingAccountNumber+ "','"+CustomerName+"','"+Balance+"','"+CustomerID+"')"; //Save the username, password and Name
					    st2.executeUpdate(sql);
					    bal = Balance;
				    }

			    st.close();
			    ToDB.closeConn();
				}

			}
			catch(java.sql.SQLException e){
				done = false;
				System.out.println("SQLException: " + e);
				while (e != null)
				{   System.out.println("SQLState: " + e.getSQLState());
					System.out.println("Message: " + e.getMessage());
					System.out.println("Vendor: " + e.getErrorCode());
					e = e.getNextException();
					System.out.println("");
				 }
			}
			catch (java.lang.Exception e){
		             done = false;
		    		 System.out.println("Exception: " + e);
					 e.printStackTrace ();
		    }
	return bal;
	}
	
	public boolean is_CheckingAccount_exist() {
		
		boolean Acc_no = true;
	
		try{
			DBConnection ToDB = new DBConnection();
			Connection con = ToDB.openConn();
			Statement st = con.createStatement();
			String sql = "SELECT CheckingAccountNumber FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; //SQL query command
			ResultSet Rslt = st.executeQuery(sql);
		     
				if (Rslt.next()) {
					Acc_no = true;
			    }
				else {
					Acc_no = false;
				}
			    st.close();
			    ToDB.closeConn();

		}
		catch(java.sql.SQLException e){
			
			System.out.println("SQLException: " + e);
			while (e != null)
			{   System.out.println("SQLState: " + e.getSQLState());
				System.out.println("Message: " + e.getMessage());
				System.out.println("Vendor: " + e.getErrorCode());
				e = e.getNextException();
				System.out.println("");
			}
		}
		catch (java.lang.Exception e){
			
			System.out.println("Exception: " + e);
			e.printStackTrace ();
		}
		
		return Acc_no;
	}


	public double deposit_to_CheckingAccount(double amount){

			double r_amount = -100;
			boolean done;
						try{

							DBConnection ToDB = new DBConnection();
							Connection con = ToDB.openConn();
							Statement st = con.createStatement();
							String sql = "SELECT CheckingAccountNumber, Balance FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; //SQL query command
							ResultSet Rslt = st.executeQuery(sql);
					        done = Rslt.next();
								if (done) {
									double bal = Rslt.getDouble("Balance");
									double New_bal = bal + amount;
								    sql = "UPDATE CheckingAccount SET Balance = '"+New_bal+"' WHERE CheckingAccountNumber='"+CheckingAccountNumber+"'";
								    st.executeUpdate(sql);
								    r_amount = New_bal;

							    }
						    st.close();
						    ToDB.closeConn();


						}
						catch(java.sql.SQLException e){
							done = false;
							System.out.println("SQLException: " + e);
							while (e != null)
							{   System.out.println("SQLState: " + e.getSQLState());
								System.out.println("Message: " + e.getMessage());
								System.out.println("Vendor: " + e.getErrorCode());
								e = e.getNextException();
								System.out.println("");
							 }
						}
						catch (java.lang.Exception e){
					             done = false;
					    		 System.out.println("Exception: " + e);
								 e.printStackTrace ();
					    }
	return r_amount;

	}

	public double withdraw_from_CheckingAccount(double amount){

			double r_amount = -100;
			boolean done;
						try{

								DBConnection ToDB = new DBConnection();
								Connection con = ToDB.openConn();
								Statement st = con.createStatement();
								String sql = "SELECT CheckingAccountNumber, Balance FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; //SQL query command
								ResultSet Rslt = st.executeQuery(sql);
						        done = Rslt.next();
									if (done) {
										double bal = Rslt.getDouble("Balance");
											if(bal>amount){
												double New_bal = bal - amount;
											    sql = "UPDATE CheckingAccount SET Balance = '"+New_bal+"' WHERE CheckingAccountNumber='"+CheckingAccountNumber+"'";
											    st.executeUpdate(sql);
											    r_amount = New_bal;
											}
								    }

								    st.close();
								    ToDB.closeConn();

						}
						catch(java.sql.SQLException e){
								done = false;
								System.out.println("SQLException: " + e);
									while (e != null)
									{   System.out.println("SQLState: " + e.getSQLState());
										System.out.println("Message: " + e.getMessage());
										System.out.println("Vendor: " + e.getErrorCode());
										e = e.getNextException();
										System.out.println("");
									 }
						}
						catch (java.lang.Exception e){
					             done = false;
					    		 System.out.println("Exception: " + e);
								 e.printStackTrace ();
					    }
		return r_amount;
	}

	public double ViewCheckingBalance(){
		double balance = -100;
		boolean done;

		try{
			DBConnection ToDB = new DBConnection();
			Connection con = ToDB.openConn();
			Statement st = con.createStatement();
			String sql = "SELECT Balance FROM CheckingAccount WHERE CheckingAccountNumber ='"+CheckingAccountNumber+"'"; //SQL query command
			ResultSet Rslt = st.executeQuery(sql);
		    done = Rslt.next();
				if (done) {
					balance = Rslt.getDouble("Balance");
			    }
			    st.close();
			    ToDB.closeConn();

		}
		catch(java.sql.SQLException e){
			done = false;
			System.out.println("SQLException: " + e);
			while (e != null)
			{   System.out.println("SQLState: " + e.getSQLState());
				System.out.println("Message: " + e.getMessage());
				System.out.println("Vendor: " + e.getErrorCode());
				e = e.getNextException();
				System.out.println("");
			}
		}
		catch (java.lang.Exception e){
			done = false;
			System.out.println("Exception: " + e);
			e.printStackTrace ();
		}
		return balance;
	}

	public Vector getCAccounts(){
			Vector accounts = new Vector();

			try{
				DBConnection ToDB = new DBConnection();
				Connection con = ToDB.openConn();
				Statement st = con.createStatement();
				String sql = "SELECT  CheckingAccountNumber FROM CheckingAccount WHERE CustomerID ='"+CustomerID+"'"; //SQL query command
				ResultSet Rslt = st.executeQuery(sql);

					while (Rslt.next()) {
						int ac_no = Rslt.getInt("CheckingAccountNumber");
						accounts.add(ac_no);
				    }
				    st.close();
				    ToDB.closeConn();
			}
			catch(java.sql.SQLException e){
				//done = false;
				System.out.println("SQLException: " + e);
				while (e != null)
				{   System.out.println("SQLState: " + e.getSQLState());
					System.out.println("Message: " + e.getMessage());
					System.out.println("Vendor: " + e.getErrorCode());
					e = e.getNextException();
					System.out.println("");
				}
			}
			catch (java.lang.Exception e){
				//done = false;
				System.out.println("Exception: " + e);
				e.printStackTrace ();
			}
	return accounts;
	}

	/*public static void main (String args []){


	}*/
}