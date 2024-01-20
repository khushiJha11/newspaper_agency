package mysqlconnector;

import java.sql.Connection;
import java.sql.DriverManager;

public class sqlconnector {
	
	public static Connection getConnection()
	{
		Connection con=null;
			try{	
																		
				con=DriverManager.getConnection("jdbc:mysql://localhost/newspaperagencyproject","root","");
				System.out.println("Connnneeeccccttteeddddd");
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
			return con;
			
	}
	
	public static void main(String[] args) 
	{
		System.out.println("***");
		getConnection();

	}
	
}
