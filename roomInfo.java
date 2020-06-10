package main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class roomInfo extends JFrame implements ActionListener{
	
	public JButton update,delete,select;
	public JFrame jfm;
	public JPanel panel;
	public JLabel roomId,roomType,rent,availLabel;
        public JTextField roomIdText, typeText, rentText;
        
        public JComboBox roomBox;
        String selects, room, avails;
	int prc;
        
        public JCheckBox availyes,availno;
        public String availString;
        
        //pop up message
        public JLabel popup;
        
        ButtonGroup bg2;
        //room string
        String[] rooms = new String[100];
        
        //Database related
        ResultSet resultset = null;
	Connection con = null;
	PreparedStatement ps = null;
	Statement st = null;

	public roomInfo()
	{
		super("roomInfo");
		this.setSize(700,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                
		panel=new JPanel();
		panel.setLayout(null);
                panel.setBackground(Color.decode("#8ACFF0"));
                panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
                
                roomBox = new JComboBox(rooms);
		roomBox.setBounds(50, 30, 233, 26);
		panel.add(roomBox);
                
                roomId=new JLabel("Room ID");
		roomId.setBounds(400,150,90,40);
		panel.add(roomId);
                
                roomIdText=new JTextField();
		roomIdText.setBounds(470,153,110,30);
		panel.add(roomIdText);
                
                roomType=new JLabel("Type Of Room");
		roomType.setBounds(375,190,90,40);
		panel.add(roomType);
                
                typeText=new JTextField();
		typeText.setBounds(470,193,110,30);
		panel.add(typeText);
                
                rent=new JLabel("Rent Price");
		rent.setBounds(390,230,90,40);
		panel.add(rent);
                
                rentText=new JTextField();
		rentText.setBounds(470,233,110,30);
		panel.add(rentText);
                
                availLabel=new JLabel("Availability");
		availLabel.setBounds(390,270,90,40);
		panel.add(availLabel);
                
                availyes=new JCheckBox("yes");
		availyes.setBounds(480,270,50,40);
                availyes.setBackground(Color.decode("#8ACFF0"));
                availyes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(availyes);
                
                availno=new JCheckBox("no");
		availno.setBounds(550,270,50,40);
                availno.setBackground(Color.decode("#8ACFF0"));
                availno.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(availno);
                
                select=new JButton("Select");
		select.setBounds(110,250,110,30);
		panel.add(select);
		select.addActionListener(this);
                
		update=new JButton("Update");
		update.setBounds(210,450,110,30);
		panel.add(update);
		update.addActionListener(this);
		
		delete=new JButton("Delete");
		delete.setBounds(350,450,110,30);
		panel.add(delete);
                delete.addActionListener(this);
                
                bg2 = new ButtonGroup();
		bg2.add(availyes);
		bg2.add(availno);

		this.add(panel);
	}

	public void actionPerformed(ActionEvent ae) 
	{

		String elementText = ae.getActionCommand();
                
		if(elementText.equals(select.getText()))
		{
                    String id="",type="",avail="";
                    int rnt=0;
                     selects = roomBox.getSelectedItem().toString();
                      try{
				 Class.forName("com.mysql.cj.jdbc.Driver");
                 // Inserting data in database
                 con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost/client", "root", "");	
              
                //String sql="SELECT `roomId`, `typeOfRoom`, `rentPrice`, `availability` FROM `hotel` WHERE `roomId`=";
                 ps=con.prepareStatement
        ("SELECT `roomId`, `typeOfRoom`, `rentPrice`, `availability` FROM `hotel` WHERE `roomId`= ?");
                 ps.setString(1, selects);
                 resultset=ps.executeQuery();
                 if(resultset.next())
                 {
                     id=resultset.getString("roomId");
                     type=resultset.getString("typeOfRoom");
                     avail=resultset.getString("availability");
                     rnt=resultset.getInt("rentPrice");
                 }
                 System.out.println(id+type+avail+rnt);
                
                
                     }
                      catch(Exception e)
                      {
                          
                      }
                       roomIdText.setText(id);
                 typeText.setText(type);
                 rentText.setText(String.valueOf(rnt));
                  if(avail.equals("yes"))
                 {
                     availyes.setSelected(true);
                 }
                 else if(avail.equals("No"))
                 {
                     availno.setSelected(true);
                 }
                }
                if(elementText.equals(update.getText()))
		{
                    //JCheckbox configuration
                    if(availyes.isSelected())
                    {
                    availString ="yes";
                    }
                    else if(availno.isSelected())
                    {
                    availString="No";
                    }
                    String id=roomIdText.getText();
                    int price=Integer.valueOf(rentText.getText());
                    String type=typeText.getText();
                    try{
                    // Inserting data in database
                    con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost/client", "root", "");
                    
                    /*String sql = "Update into hotel (roomId,typeOfRoom,rentPrice,availability) values('" +id+ "', '" +type+ 
                                  "', '" +price+ "', '" +availString+ "') WHERE `roomId` = '"+selects+"'";*/
                    Statement st= (Statement) con.createStatement();
                    st.executeUpdate("DELETE FROM `hotel` WHERE `roomId`= '"+selects+"'");
                    st.executeUpdate
        ("insert into hotel (roomId,typeOfRoom,rentPrice,availability) values('" +id+ "', '" +type+ "', '" +price+ "', '" +availString+ "')");
                    //popup
                    popup = new JLabel("Update Successful");
                    popup.setForeground(Color.GREEN);
                    popup.setFont(new Font("Arial", Font.BOLD, 18));
                    JOptionPane.showMessageDialog(null, popup,"DONE",JOptionPane.INFORMATION_MESSAGE);
                    
                    System.out.println("hotel Successfully Inserted");            
                    login s=new login();
		    s.setVisible(true);
		    this.setVisible(false);           
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
	       	}

                if(elementText.equals(delete.getText()))
		{        
                    try {
                        con =(Connection) DriverManager.getConnection("jdbc:mysql://localhost/client", "root", "");
                        Statement st= (Statement) con.createStatement();
                        st.executeUpdate("DELETE FROM `hotel` WHERE `roomId` = '"+selects+"'");
                        //popup
                        popup = new JLabel("Successfully Deleted");
                        popup.setForeground(Color.GREEN);
                        popup.setFont(new Font("Arial", Font.BOLD, 18));
                        JOptionPane.showMessageDialog(null, popup,"DONE",JOptionPane.INFORMATION_MESSAGE);
                        
                        admin l=new admin();
                        l.setVisible(true);
                        this.setVisible(false);
                        
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
     
		}
		
	}

}
