
package main;

import java.sql.*;


public class DBConnect {
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    //JDBC variables
    String JDBC_DRIVER;  
    String DB_URL;
    String USER;
    String PASS;
    
    public DBConnect(){
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/client";
        USER = "root";
        PASS = "";
        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            con = DriverManager.getConnection(DB_URL,USER,PASS);
        }
        catch(Exception ex){   
            System.out.println("error"+ex);
        }
    } 
    //ok..................
    public void close()throws SQLException{
        if(rs!=null)rs.close();
        if(rs!=null)st.close();
    }
    public ResultSet getData(String query) {
        try{
            st = con.createStatement();
            rs= st.executeQuery(query);
        }
        catch(Exception ex){
            System.out.println("DB Read Error !");
        }
        return rs;
   }
    public int updateDB(String sql){
        int numOfRowsUpdated=0;
        try{
            st = con.createStatement(); 
            numOfRowsUpdated=st.executeUpdate(sql);
            System.out.println(numOfRowsUpdated+" row(s) updated");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return numOfRowsUpdated;
    }

}
