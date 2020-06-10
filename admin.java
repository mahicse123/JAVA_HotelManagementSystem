package main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class admin extends JFrame implements ActionListener
{

    public JButton roomInfo,bookingInfo,addroom,back;
    public JFrame jfm;
    public JPanel panel;
    

    public admin()
    {
        super("admin");
        this.setSize(700,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#8ACFF0"));
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        roomInfo=new JButton("Room Info");
        roomInfo.setBounds(300,250,110,30);
        panel.add(roomInfo);
        roomInfo.addActionListener(this);

        bookingInfo=new JButton("Booking Info");
        bookingInfo.setBounds(300,300,110,30);
        panel.add(bookingInfo);
        bookingInfo.addActionListener(this);

        addroom=new JButton("Add Room");
        addroom.setBounds(300,350,110,30);
        panel.add(addroom);
        addroom.addActionListener(this);

        back=new JButton("Back");
        back.setBounds(300,400,110,30);
        panel.add(back);
        back.addActionListener(this);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae)
    {
        String elementText = ae.getActionCommand();

        if(elementText.equals(roomInfo.getText()))
        {
            roomInfo r=new roomInfo();
            r.setVisible(true);
            this.setVisible(false);

        }

        if(elementText.equals(bookingInfo.getText()))
        {
        	Bookinginfo bk=new Bookinginfo();
        	bk.setVisible(true);
        	

        }
        if(elementText.equals(addroom.getText()))
        {
            addHotel a=new addHotel();
            a.setVisible(true);
            this.setVisible(false);
        }
        if(elementText.equals(back.getText()))
        {
            login l=new login();
            l.setVisible(true);
            this.setVisible(false);

        }

    }

}
