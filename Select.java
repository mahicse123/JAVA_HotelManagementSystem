
package main;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;

//for table
import javax.swing.*;

public class Select extends JFrame implements ActionListener {

	public JLabel type;
	public JLabel dtype;

	public JLabel price;
	public JLabel dprice;

	public JLabel avail;
	public JLabel davail;

	public JButton rent, select;

	String[] rooms = new String[100];
	public JComboBox comboBox;
	String selects, room, avails;
	int prc;

	public JFrame jfm;
	public JPanel panel;

	JButton update, logout,delete;
	String loginmail;
        
        public JLabel popup;

	AccessDataBase access = new AccessDataBase();

	ResultSet resultset = null;
	Connection con = null;
	PreparedStatement ps = null;
	Statement st = null;

	public Select() {
		super("Selection Page");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// load driver

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/client", "root", "");

			st = con.createStatement();
			resultset = st.executeQuery("SELECT `roomId` FROM `hotel`");
			int i = 0;
			while (resultset.next()) {
				rooms[i] = resultset.getString("roomId");
				i++;

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.decode("#8ACFF0"));
		panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

		comboBox = new JComboBox(rooms);
		comboBox.setBounds(50, 10, 233, 26);
		panel.add(comboBox);

		type = new JLabel("Type of room");
		type.setBounds(300, 10, 100, 40);
		panel.add(type);

		dtype = new JLabel();
		dtype.setBounds(400, 15, 150, 30);
		panel.add(dtype);

		price = new JLabel("price");
		price.setBounds(300, 50, 100, 40);
		panel.add(price);

		dprice = new JLabel();
		dprice.setBounds(400, 55, 150, 30);
		panel.add(dprice);

		avail = new JLabel("Availability");
		avail.setBounds(300, 90, 100, 40);
		panel.add(avail);

		davail = new JLabel();
		davail.setBounds(400, 95, 150, 30);
		panel.add(davail);

		select = new JButton("Select this");
		select.setBounds(50, 130, 110, 30);
		panel.add(select);
		select.addActionListener(this);

		update = new JButton("Update Info");
		update.setBounds(50, 250, 100, 30);
		panel.add(update);
		update.addActionListener(this);

		logout = new JButton("Log-out");
		logout.setBounds(50, 300, 100, 30);
		panel.add(logout);
		logout.addActionListener(this);

		rent = new JButton("Rent this");
		rent.setBounds(300, 300, 110, 30);
		panel.add(rent);
		rent.addActionListener(this);
                
                delete = new JButton("Delete Account");
		delete.setBounds(50, 350, 150, 30);
		panel.add(delete);
		delete.addActionListener(this);

		this.add(panel);
	}

	public void actionPerformed(ActionEvent ae) {
		String elementText = ae.getActionCommand();
		
		 if(elementText.equals(update.getText()))
		{
			
			register h=new register();
			String n="",pass="",add="",em="";
			int ph=0;
			
			 try{
				 Class.forName("com.mysql.cj.jdbc.Driver");
                 // Inserting data in database
                 con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost/client", "root", "");	
              
                String sql="SELECT * FROM `accounts` WHERE `email`= '"+loginmail+"'";
                 Statement st= (Statement) con.createStatement();
                 resultset=st.executeQuery(sql);
                 
                 if(resultset.next())
                 {
                	 n=resultset.getString("name");
                	 pass=resultset.getString("password");
                	 add=resultset.getString("address");
                	 em=resultset.getString("email");
                	 ph=resultset.getInt("phone");
                 }
                 
                     st.executeUpdate("DELETE FROM `accounts` WHERE `email` = '"+loginmail+"'");
          
			     }
			 	catch(Exception e)
			 	{
			 		
			 		System.out.println(e.getMessage());
			 	
			 	}
			 
			 h.nameText.setText(n);
			 h.passwordText.setText(pass);
			 h.addressText.setText(add);
			 h.mailText.setText(em);
			 h.phoneText.setText(String.valueOf(ph));
			 
			 h.setVisible(true);
			 this.setVisible(false); 
			
			
		}
		else if(elementText.equals(logout.getText()))
		{
			login o=new login();
	                o.setVisible(true);	
                        this.setVisible(false);
		}
		else if(elementText.equals(select.getText()))
		{
			selects = comboBox.getSelectedItem().toString();
			

			try {
			
				
				resultset = null;
			
				ps = con.prepareStatement("SELECT  `typeOfRoom`, `rentPrice`, `availability` FROM `hotel` WHERE `roomId`= ?  ");
				ps.setString(1, selects);
				
				resultset = ps.executeQuery();
			
				if (resultset.next()) {
					room = resultset.getString("typeOfRoom");
					prc = resultset.getInt("rentPrice");
					avails=resultset.getString("availability");

				}

				this.setVisible(false);
				
				dtype.setText(room);
				dprice.setText(String.valueOf(prc));
				davail.setText(avails);
				
				this.setVisible(true);
			} 
			catch (Exception e) {
				System.out.println(e.toString());

			}
		}
		else if(elementText.equals(rent.getText()))
		{
                     if(davail.getText().equals("yes"))
            {
            		String name="",roomid="",email="";
                try
                {

                    ps = con.prepareStatement("UPDATE `hotel` SET `availability`= ? WHERE `roomId`= ?  ");
                    ps.setString(1, "No");
                    ps.setString(2, selects);
                    System.out.print(selects);
                    ps.executeUpdate();
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
                int option = JOptionPane.showConfirmDialog(null, "Do You want to confirm the booking?", "Confirm",
                             JOptionPane.YES_NO_OPTION);
                if (option == 0)
                {
                    System.out.println("account: "+loginmail+" room: "+selects);
                    try {
                    	resultset=null;
                    ps = con.prepareStatement
                 ("SELECT `name` , `email` , `roomId` FROM `accounts` , `hotel` WHERE `email`='"+loginmail+"' and `roomId`='"+selects+"' ");
                    
                    
						resultset=ps.executeQuery();
						if(resultset.next()) {
						name=resultset.getString("name");
						email=resultset.getString("email");
						roomid=resultset.getString("roomId");
						}
						else
						{
							System.out.println("no idea");
						}
		ps=con.prepareStatement("INSERT INTO `booking`(`name`, `email`, `roomid`, `date`) VALUES (?,?,?,?)");
						ps.setString(1, name);
						ps.setString(2, email);
						ps.setString(3, roomid);
						System.out.println(name+"  "+roomid);
						DateFormat dateFormat = new SimpleDateFormat("DD/MM/yyyy");
						Date date = new Date();
						String currentDate=date.toString();
						
						ps.setString(4,currentDate);
						
						ps.executeUpdate();
						
					} 
                    catch (SQLException e)
                    {
						
						System.out.println(e.getMessage());
					}
                    
                    login s = new login();
                    s.setVisible(true);
                    this.setVisible(false);
                    System.out.print("yes");
                }
                else
                {
                    System.out.print("no");
                }
            }
                  else  if(davail.getText().equals("No"))
                    {
                        popup = new JLabel("ROOM NOT AVAILABLE");
                        popup.setForeground(Color.RED);
                        popup.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null, popup,"FAILED",JOptionPane.ERROR_MESSAGE);
                    }
                    if(davail.getText().equals("")){
                        popup = new JLabel("NO ROOM SELECTED");
                        popup.setForeground(Color.RED);
                        popup.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null, popup,"ERROR",JOptionPane.NO_OPTION);
                    }
			
			
		}
                else if(elementText.equals(delete.getText()))
                {
                    try {
                        con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost/client", "root", "");
                       // String sql="SELECT * FROM `accounts` WHERE `email`= '"+loginmail+"'";
                        Statement st= (Statement) con.createStatement();
                        //resultset=st.executeQuery(sql);
                        st.executeUpdate("DELETE FROM `accounts` WHERE `email` = '"+loginmail+"'");
                        //popup
                        popup = new JLabel("Successfully Deleted");
                        popup.setForeground(Color.GREEN);
                        popup.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null, popup,"DONE",JOptionPane.INFORMATION_MESSAGE);
                        
                        login l=new login();
                        l.setVisible(true);
                        this.setVisible(false);
                        
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                 
                }
	}

}
