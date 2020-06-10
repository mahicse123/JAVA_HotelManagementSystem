package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class login extends JFrame implements ActionListener{
	
        public JLabel title;
    
	public JTextField userMailText;
	public JPasswordField pass;
	public JButton login,createNew;
	public JFrame jfm;
	public JPanel panel;
	public JLabel userMail,passWord;
        
        //pop up message
        public JLabel MsgText;
        //database
        AccessDataBase accessDataBase = new AccessDataBase();
        ResultSet resultset = null;
        Connection con = null; 
        PreparedStatement ps = null;

	public login()
	{
		super("Login");
		this.setSize(700,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);
                
                //extra option
                UIManager.put("Button.select", Color.LIGHT_GRAY);
                
                
		panel=new JPanel();
		panel.setLayout(null);
                panel.setBackground(Color.decode("#8ACFF0"));
                panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
                
                //project title
		title=new JLabel("Hotel Management System");
		title.setBounds(180,70,400,70);
                title.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 25));
		panel.add(title);
                
                
		userMail=new JLabel("EMail");
		userMail.setBounds(310,180,90,40);
		panel.add(userMail);
		
		userMailText=new JTextField();
		userMailText.setBounds(240,210,180,30);
                userMailText.setBackground(Color.decode("#F8FFD8"));
                userMailText.setBorder(BorderFactory.createLineBorder(Color.darkGray,2));
		panel.add(userMailText);
		
		passWord=new JLabel("Password");
		passWord.setBounds(300,250,90,40);
		panel.add(passWord);
		
		pass=new JPasswordField();
		pass.setBounds(240,280,180,30);
                pass.setBackground(Color.decode("#F8FFD8"));
                pass.setBorder(BorderFactory.createLineBorder(Color.darkGray,2));
		panel.add(pass);
		
		login=new JButton("Login");
		login.setBounds(210,330,110,30);
                login.setForeground(Color.RED);
		panel.add(login);
		login.addActionListener(this);
		
		createNew=new JButton("Create New");
		createNew.setBounds(350,330,110,30);
		panel.add(createNew);
                createNew.addActionListener(this);
                //extra option
                createNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                
               
		
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae) 
	{
		String elementText = ae.getActionCommand();
		
		if(elementText.equals(createNew.getText()))
		{
			register h=new register();
			h.setVisible(true);
			this.setVisible(false);      
		}
                
                if(elementText.equals(login.getText()))
		{        
                    String mailValue = userMailText.getText();
                    char[] input = pass.getPassword();
                    String passString = new String(input);
                    
                    if(mailValue.equals("manan") && passString.equals("123"))
                    {
                    MsgText = new JLabel("Login Successful");
                    MsgText.setForeground(Color.GREEN);
                    MsgText.setFont(new Font("Arial", Font.BOLD, 18));
                    JOptionPane.showMessageDialog(null, MsgText,"DONE",JOptionPane.INFORMATION_MESSAGE);
                        
                    
                    admin a=new admin();
		    a.setVisible(true);
		    this.setVisible(false);  
                    }
                    else
                    {
                       boolean flag = false;
                       try{
                           Class.forName("com.mysql.cj.jdbc.Driver");// load driver
           				System.out.println("driver loaded");
           				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client", "root", "");
                    	   System.out.println(mailValue+"  & "+passString);
                    	   resultset=null;
     
          	   	ps=con.prepareStatement("SELECT `email`, `password` FROM `accounts` WHERE `email` = ? and `password` = ? ");
                    	   	
    					ps.setString(1, mailValue);
    					ps.setString(2, passString);
    					
    					 System.out.println("mail: "+mailValue+" pass:  "+passString);
    					 
    					resultset=ps.executeQuery();
    					
                       if (resultset.next()) {
                           flag =true;
                           MsgText = new JLabel("Login Successful");
                           MsgText.setForeground(Color.GREEN);
                           MsgText.setFont(new Font("Arial", Font.BOLD, 18));
                           JOptionPane.showMessageDialog(null, MsgText,"DONE",JOptionPane.INFORMATION_MESSAGE);
                           
                           System.out.println(mailValue);
                           
                           Select s=new Select();
                           s.loginmail=mailValue;
                           System.out.println(mailValue);
		           s.setVisible(true);
		           this.setVisible(false); 
                       }else   
                       {
                           MsgText = new JLabel("Login Failed");
                           MsgText.setForeground(Color.RED);
                           MsgText.setFont(new Font("Arial", Font.BOLD, 18));
                           JOptionPane.showMessageDialog(null, MsgText,"ERROR",JOptionPane.ERROR_MESSAGE); 
                       }
                       
                       }     
                       catch(Exception ex){
                               
                            JOptionPane.showMessageDialog(null,ex);
                        }
                     
                    }
                    
                       
                        
		}
		
	}

}
