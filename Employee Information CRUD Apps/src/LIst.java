import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.Attributes.Name;

import javax.swing.JOptionPane;
import javax.xml.bind.DataBindingException;




public class LIst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		DBConnector con=new DBConnector();
//		con.Connect();
		//DBConnector db=new DBConnector();
		
		
		Connection conn=null;
		Statement st=null;
		ResultSet rs;
		
		
		
		try{
			 conn = DriverManager.getConnection("jdbc:mysql://localhost/person","root","");
		      st = conn.createStatement();	
		      
			String sql2="SELECT * FROM info";
			rs=st.executeQuery(sql2);
			
			while (rs.next()) {
				
				 System.out.print("\nName: "+rs.getString("Name")+"\nAddress: "+rs.getString("Address")+"\nPhone Number: "+rs.getString("Phon"));
				
			}
			  rs.close();
		      st.close();
		      conn.close();
		}
		catch (SQLException exa) {
			exa.printStackTrace();
			
			} 
		
		try{
			String sql="INSERT INTO info(Name,Address,Phon) VALUES('OOP','JU','41144')";
			//db.executeUpdate(sql);;
		
		  conn = DriverManager.getConnection("jdbc:mysql://localhost/person","root","");
	      st = conn.createStatement();	
			
		  st.executeUpdate(sql);
		 System.out.print("\nQuery Execudet and Insert");
		 
		
	      st.close();
	      conn.close();
		} 
		
		catch (SQLException ex) {
		ex.printStackTrace();
		} 
		
	
	    

	}

}
