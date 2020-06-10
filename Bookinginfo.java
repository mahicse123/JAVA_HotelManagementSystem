package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Bookinginfo extends JFrame {

	private JPanel panel;
	private JTable table;
	 ResultSet resultset = null;
     Connection con = null; 
     PreparedStatement ps = null;
	
	

	/**
	 * Create the frame.
	 */
	public Bookinginfo()
	{
		super("Booking info");
		this.setBounds(100, 100, 658, 464);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 16, 596, 271);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		 try{
             Class.forName("com.mysql.cj.jdbc.Driver");// load driver
				System.out.println("driver loaded");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client", "root", "");
				ps=con.prepareStatement("SELECT * FROM `booking`");
				resultset=ps.executeQuery();
                              //  System.out.println(resultset.getString("name"));
                                table.setModel(DbUtils.resultSetToTableModel(resultset));
		 }
		 catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }
		 this.add(panel);
	}
	
}
