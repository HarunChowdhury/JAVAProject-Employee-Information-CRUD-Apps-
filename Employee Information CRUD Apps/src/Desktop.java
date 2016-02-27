import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.management.modelmbean.ModelMBean;
import javax.sound.midi.MidiDevice.Info;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.*;
import java.util.jar.Attributes.Name;
import java.awt.Button;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.UIManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;





public class Desktop extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField addressTextField;
	private JTextField phonNumTextField;
	private java.awt.List aList;
	private JTable table;
	private JTable showTable;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Desktop frame = new Desktop();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/***
	 * Create the frame.
	 */
	public Desktop() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		
		
		
		
		
		
		// Preson Info Print  Button
		
		JButton Save = new JButton("Save");
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String name=nameTextField.getText();
				 String address=addressTextField.getText();
				 String phoneNum=phonNumTextField.getText();
				 
				Connection conn=null;
				Statement st=null;
				
				String sql="INSERT INTO info(Name,Address,Phon) VALUES('"+name+"','"+address+"','"+phoneNum+"')";
				
				try{
					
				 conn = DriverManager.getConnection("jdbc:mysql://localhost/person","root","");
			     st = conn.createStatement();	
				 st.executeUpdate(sql);
				 JOptionPane.showMessageDialog(null, "Data Saved In Database ! WoW WOW Yeh Yeh !!!");
				 
				 nameTextField.setText(" ");
				 addressTextField.setText(" ");
				 phonNumTextField.setText(" ");
				} 
				
				catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
				}
								
			}			  
		});
		
		Save.setBounds(332, 11, 89, 25);
		contentPane.add(Save);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(155, 11, 152, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		addressTextField.setBounds(155, 42, 152, 20);
		contentPane.add(addressTextField);
		
		phonNumTextField = new JTextField();
		phonNumTextField.setColumns(10);
		phonNumTextField.setBounds(155, 73, 152, 20);
		contentPane.add(phonNumTextField);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 14, 83, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 45, 83, 14);
		contentPane.add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(10, 79, 114, 14);
		contentPane.add(lblPhoneNumber);
		
		aList = new java.awt.List();
		aList.setBounds(10, 155, 240, 96);
		contentPane.add(aList);
		
		
		// Clear Button
		
		Button clearButton = new Button("Clear");
		clearButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 
				  showTable.removeAll();
				    aList.removeAll();
				    
				    
			}
		});
		
		clearButton.setBounds(332, 101, 89, 25);
		contentPane.add(clearButton);
		
		
		// Show Button
		
		JButton showButton = new JButton("Show");
		showButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
							
				Connection conn=null;
				Statement st=null;
				ResultSet rs;
				
				String sql="SELECT * FROM info";
				
				try{
				
				  conn = DriverManager.getConnection("jdbc:mysql://localhost/person","root","");
			      st = conn.createStatement();	
				  rs=st.executeQuery(sql);

				  aList.add("Name\tAddress\tPhone");
			    while (rs.next()) {
					
					aList.add(rs.getString("Name")+rs.getString("Address")+rs.getString("Phon")); 
			     }

			    showTableData();
			    
				 JOptionPane.showMessageDialog(null, "Query Execudet and Retrive Data in List and Table !!!!");
				} 
				
				catch (SQLException ex) {
				ex.printStackTrace();
				} 				
			}
		});
		showButton.setBounds(10, 124, 89, 25);
		contentPane.add(showButton);
		
		table = new JTable();
		table.setBounds(389, 229, -139, -77);
		contentPane.add(table);
		
		showTable = new JTable();
		showTable.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
			}
		});
		showTable.setFillsViewportHeight(true);
		showTable.setCellSelectionEnabled(true);
		showTable.setColumnSelectionAllowed(true);
		showTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		showTable.setBounds(258, 147, 176, 104);
		contentPane.add(showTable);
		
		// Update Button 
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				   
				
				Connection conn=null;
				Statement st=null;
				ResultSet rs;	
				PreparedStatement ps; 
				try {
						conn=DriverManager.getConnection("jdbc:mysql://localhost/person","root","");
		                String sql="UPDATE info SET Name='"+nameTextField.getText()+"'"+"WHERE Address='"+addressTextField.getText()+"'";
		               
						st=conn.createStatement();
						st.executeUpdate(sql);
					    
					    
					    JOptionPane.showMessageDialog(null,addressTextField.getText()+"  is Updated");			
			        }
			     catch (Exception e) {
				 
				JOptionPane.showMessageDialog(null,e.getMessage());
			     }	
			}			
		});
		btnUpdate.setBounds(332, 72, 89, 23);
		contentPane.add(btnUpdate);
		
		
		// Delete Button 
		
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Connection conn=null;
					Statement st=null;
					ResultSet rs;	
					PreparedStatement ps; 
					//String sql1="SELECT * from info";
					

					 conn=DriverManager.getConnection("jdbc:mysql://localhost/person","root","");
					 String sql="DELETE FROM info WHERE Name='"+nameTextField.getText()+"'";
					st=conn.createStatement();
					st.executeUpdate(sql);
				    
				    
				    JOptionPane.showMessageDialog(null,nameTextField.getText()+" All info is Deleted");
					
				} catch (Exception e) {
					 
					JOptionPane.showMessageDialog(null,e.getMessage());
				}
			}
		});
		btnDelete.setBounds(332, 41, 89, 23);
		contentPane.add(btnDelete);
		
		
		
		// Refresh Button
		
		JButton btnRefreshDataBox = new JButton("Refresh Data Box");
		btnRefreshDataBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Connection conn=null;
				Statement st=null;
				ResultSet rs;
				
				String sql="SELECT * FROM info";
				
				try{
				
				  conn = DriverManager.getConnection("jdbc:mysql://localhost/person","root","");
			      st = conn.createStatement();	
				  rs=st.executeQuery(sql);

				  aList.add("Name\tAddress\tPhone");
			    while (rs.next()) {
					
					aList.add(rs.getString("Name")+rs.getString("Address")+rs.getString("Phon")); 
			     }

			    showTableData();
			    
				 JOptionPane.showMessageDialog(null, "List Box and Table are Refreshed !!! WoW WOW!!!!");
				} 
				
				catch (SQLException ex) {
				ex.printStackTrace();
				} 				
				
				
			}
		});
		btnRefreshDataBox.setBounds(119, 126, 125, 23);
		contentPane.add(btnRefreshDataBox);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{nameTextField, addressTextField, phonNumTextField, Save, showButton, clearButton, lblNewLabel, lblAddress, lblPhoneNumber, aList, table, showTable}));
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{nameTextField, addressTextField, phonNumTextField, Save, showButton, clearButton, contentPane, lblNewLabel, lblAddress, lblPhoneNumber, aList, table, showTable}));
	
	}

	// Display Table Data
	
	
	public void showTableData() {
	          
	    	Connection conn=null;
			Statement st=null;
			ResultSet rs;	
			PreparedStatement ps; 
	        try {        	
	          String sql="SELECT * FROM info";
	      	  conn = DriverManager.getConnection("jdbc:mysql://localhost/person","root","");
		       st = conn.createStatement();	
		       ps = conn.prepareStatement(sql);
		       rs = ps.executeQuery();
		       
		      
		       showTable.setModel(DbUtils.resultSetToTableModel(rs));
	        }
	           catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, ex.getMessage());
	        }
	    }
	}

