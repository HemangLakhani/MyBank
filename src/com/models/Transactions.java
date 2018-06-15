package com.lakhani;


import java.lang.*;
import java.util.*;
import com.lakhani.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transactions{

private int TransactionID;
private String TransactionType;
private String TransactionDate;
private double Amount;
private String FromAccount;
private String ToAccount;
private String CustomerID;

private DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	public Transactions(String TransactionType, double Amount, String FromAcc, String ToAcc, String UName){

// This code snippet will generate TransactionID using random number.

		int Tran_ID = generator();
		this.TransactionID = Tran_ID;

// This code snippet will generate today's date and time when the constructor is called(or when the object is created)

		Date date = new Date();
		this.TransactionDate = sdf.format(date);

		this.TransactionType = TransactionType;
		this.Amount = Amount;
		this.FromAccount = FromAcc;
		this.ToAccount = ToAcc;
		this.CustomerID = UName;

	}

	private int generator(){
		// This code snippet is creating 6 digit random transaction id.
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		return n;
	}


	public void Record_Transactions(){

		try{
			DBConnection ToDB = new DBConnection();
			Connection con = ToDB.openConn();
			Statement st = con.createStatement();
			String sql = "SELECT TransactionID FROM Transactions WHERE TransactionID ='"+TransactionID+"'"; //SQL query command
			ResultSet Rslt = st.executeQuery(sql); //Inquire if the accountNumber exsits.
		    boolean done = !Rslt.next();
				if (done) {

				   	Statement st2 = con.createStatement();
				    sql = "INSERT INTO Transactions(TransactionID, TransactionType, TransactionDate, Amount, FromAccount, ToAccount, CustomerID) VALUES ('"+TransactionID+ "','"+TransactionType+"', '"+TransactionDate+"', '"+Amount+"','"+FromAccount+"', '"+ToAccount+"','"+CustomerID+"')"; //Save the username, password and Name
				    st2.executeUpdate(sql);
			    }
			    else{
						st.close();
						ToDB.closeConn();
					// The generator method will only generate upto 6 digit Transactions ID. Once it will use all the numbers and will start
					// produce the same ID everytime. This recursion will go in infinite loop. Which is not good. So, This issue should be
					// address in the near future.
					Record_Transactions();
					//System.out.println("THE TRANSACTION ID IS ALREADY EXISTS..!!!");
				}


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
	}



/*	public static void main (String args []){


	}*/

}