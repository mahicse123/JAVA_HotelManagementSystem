package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import static main.AccessDataBase.*;

public class register extends JFrame implements ActionListener{

	public JLabel name;
	public JTextField nameText;
        
        public JLabel password;
	public JPasswordField passwordText;
        
        public JLabel address;
        public JTextField addressText;
        
        public JLabel mail;
        public JTextField mailText;
        
        public JLabel phone;
        public JTextField phoneText;
        
        public JButton submit,back;
        
	public JFrame jfm;
	public JPanel panel;
        
        public JLabel popup;
        //database
        AccessDataBase access = new AccessDataBase();
        ResultSet resultset = null;
        Connection con=null;
	
	public register() 
        {
		super("Home Page");
		this.setSize(700,700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);
		
		panel=new JPanel();
		panel.setLayout(null);
                panel.setBackground(Color.decode("#8ACFF0"));
                panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
		
		name=new JLabel("Enter Full name");
		name.setBounds(50,10,100,40);
		panel.add(name);
                
                nameText=new JTextField();
		nameText.setBounds(150,15,150,30);
		panel.add(nameText);
                
                password=new JLabel("Enter Password");
		password.setBounds(380,10,100,40);
		panel.add(password);
                
                passwordText=new JPasswordField();
		passwordText.setBounds(480,15,150,30);
		panel.add(passwordText);

                address=new JLabel("Address");
		address.setBounds(50,125,100,40);
		panel.add(address);
		
		addressText=new JTextField();
		addressText.setBounds(30,160,250,30);
		panel.add(addressText);
                
                mail=new JLabel("Email");
		mail.setBounds(80,190,90,40);
		panel.add(mail);
		
		mailText=new JTextField();
		mailText.setBounds(25,220,150,30);
		panel.add(mailText);
                
                phone=new JLabel("Phone No.");
		phone.setBounds(280,190,90,40);
		panel.add(phone);
		
		phoneText=new JTextField();
		phoneText.setBounds(225,220,150,30);
		panel.add(phoneText);
                
                submit=new JButton("Submit");
		submit.setBounds(200,300,110,30);
		panel.add(submit);
		submit.addActionListener(this);
                
                back=new JButton("Cancel");
		back.setBounds(350,300,110,30);
		panel.add(back);
		back.addActionListener(this);
                
		//	
                
		this.add(panel);
	}	
	public void actionPerformed(ActionEvent ae) 
	{
            String nameValue = nameText.getText();
            char[] input = passwordText.getPassword();
            String passValue = new String(input);
            String adrsValue=addressText.getText();
            String mailValue=mailText.getText();
            String phnNo=phoneText.getText();
		String elementText = ae.getActionCommand();
		if(elementText.equals("Submit"))
		{
                    if(nameValue.isEmpty() || passValue.isEmpty() || adrsValue.isEmpty() || !mailValue.contains("@") || phnNo.isEmpty())
                    {
                        popup = new JLabel("Registration Failed");
                        popup.setForeground(Color.RED);
                        popup.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null, popup,"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                    try{
                    // Inserting data in database
                    con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost/client", "root", "");
                    String sql = "insert into accounts (name,password,address,email,phone) values('" +nameValue+ "', '" +passValue+ 
                                  "', '" +adrsValue+ "', '" +mailValue+ "','"+phnNo+"')";
                    Statement st= (Statement) con.createStatement();
                    st.executeUpdate(sql);
                    //popup
                    popup = new JLabel("Registration Successful");
                    popup.setForeground(Color.GREEN);
                    popup.setFont(new Font("Arial", Font.BOLD, 18));
                    JOptionPane.showMessageDialog(null, popup,"DONE",JOptionPane.INFORMATION_MESSAGE);
                    
                    System.out.println("Successfully Inserted");            
                    login s=new login();
		    s.setVisible(true);
		    this.setVisible(false);           
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
                    }
		}
                if(elementText.equals("Cancel"))
                {
                    login s=new login();
		    s.setVisible(true);
		    this.setVisible(false);
                }
	}
}
