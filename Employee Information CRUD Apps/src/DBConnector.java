import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnector {


		public void getConnection() {
			
			
			   Connection conn=null;
			   Statement st=null;
			   ResultSet rs=null;
				
				try{
					
					 conn = DriverManager.getConnection("jdbc:mysql://localhost/person","root","");
				     st = conn.createStatement();	
					
					} 
					
					catch (SQLException ex) {
					ex.printStackTrace();
					} 
			
		} 
}
