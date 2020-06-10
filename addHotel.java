package main;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class addHotel extends JFrame implements ActionListener{

        public JLabel roomId;
	public JTextField roomIdText;
        public JLabel availability;
	public JTextField availabilityText;
        public JLabel roomType;
	public JTextField roomTypeText;
        public JLabel ammount;
        public JTextField ammountText;
	public JButton submit,cancel;
	public JFrame jfm;
	public JPanel panel;

        //pop up message
        public JLabel popup;
        //database
        AccessDataBase access = new AccessDataBase();
        ResultSet resultset = null;
        Connection con=null;
	public addHotel()
	{
		super("Hotel Add");
		this.setSize(700,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);
                
                
                
		panel=new JPanel();
		panel.setLayout(null);
                panel.setBackground(Color.decode("#8ACFF0"));
                panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
                
                roomId=new JLabel("Room ID");
		roomId.setBounds(305,180,90,40);
		panel.add(roomId);
                
                roomIdText=new JTextField();
		roomIdText.setBounds(240,210,180,30);
		panel.add(roomIdText);
                
                roomType=new JLabel("Type Of Room (single/double)");
		roomType.setBounds(250,240,200,40);
		panel.add(roomType);
                
                roomTypeText=new JTextField();
		roomTypeText.setBounds(240,270,180,30);
		panel.add(roomTypeText);
                
                availability=new JLabel("Availability (yes/no)");
		availability.setBounds(270,300,150,40);
		panel.add(availability);
                
                availabilityText=new JTextField();
		availabilityText.setBounds(240,330,180,30);
		panel.add(availabilityText);
                
                ammount=new JLabel("Rent Prince");
		ammount.setBounds(300,360,90,40);
		panel.add(ammount);
                
                ammountText=new JTextField();
		ammountText.setBounds(240,390,180,30);
		panel.add(ammountText);
                
		submit=new JButton("Submit");
		submit.setBounds(210,500,110,30);
		panel.add(submit);
		submit.addActionListener(this);

		cancel=new JButton("Cancel");
		cancel.setBounds(350,500,110,30);
		panel.add(cancel);
                cancel.addActionListener(this);

		this.add(panel);
	}

	public void actionPerformed(ActionEvent ae)
	{
                String idValue = roomIdText.getText();
                String availValue = availabilityText.getText();
                String typeValue= roomTypeText.getText();
                String rentValue=  ammountText.getText();
            
		String elementText = ae.getActionCommand();

		if(elementText.equals(submit.getText()))
		{
                    if(idValue.isEmpty() || availValue.isEmpty() || typeValue.isEmpty() || rentValue.isEmpty())
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
                    String sql = "insert into hotel (roomId,typeOfRoom,rentPrice,availability) values('" +idValue+ "', '" +typeValue+ 
                                  "', '" +rentValue+ "', '" +availValue+ "')";
                    Statement st= (Statement) con.createStatement();
                    st.executeUpdate(sql);
                    
                    popup = new JLabel("Registration Successful");
                    popup.setForeground(Color.GREEN);
                    popup.setFont(new Font("Arial", Font.BOLD, 18));
                    JOptionPane.showMessageDialog(null, popup,"DONE",JOptionPane.INFORMATION_MESSAGE);
                        
                        login l=new login();
			l.setVisible(true);
			this.setVisible(false);
                        
                    }   catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }	
		}
                }

                if(elementText.equals(cancel.getText()))
		{
                    admin s=new admin();
                    s.setVisible(true);
                    this.setVisible(false);
                    }
		}

        }