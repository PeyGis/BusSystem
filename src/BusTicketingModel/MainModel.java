/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusTicketingModel;
import BusTicketingView.LoginForm;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author pages coffie
 */
public class MainModel {
    private String userName;
    private String password;
    LoginForm loginForm;
    Connection conn = null; 
    ResultSet resultset = null;
    PreparedStatement pstmt = null;
    
    public MainModel(String name, String pswd){
    this.userName = name;
    this.password = pswd;
    
    try{
    Class.forName("com.mysql.jdbc.Driver").newInstance();
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BusTicketProject","root","pages");
    conn.close();
    
    } catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e){
    JOptionPane.showMessageDialog(loginForm, "Cannot Connect to Database");
    System.exit(0);
    }
    }
    
    public boolean isLoginSuccessful(String name, String pswd) throws Exception{
        
        try{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BusTicketProject","root","pages");
        String query = String.format("SELECT * FROM Admin WHERE adminName = '%s' AND password = '%s'", name, pswd);
        
        resultset = this.getResult(query);
        if(resultset.next()){
        this.closeConnection();
         return true;
        }
        else {
            this.closeConnection();
            return false;
        }
        } catch(Exception e){
            JOptionPane.showMessageDialog(loginForm, "Wrong Details");
        }
    return false;
    
    }
    
    public Connection openConnection() throws SQLException {
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BusTicketProject","root","pages");
      return conn; 
    } catch(Exception e){
       JOptionPane.showMessageDialog(loginForm, "Cannot Open Coonection");
       
    }
    return null;
    }
    
    public void closeConnection() throws SQLException {
     conn.close();
    } 
    
    public ResultSet getResult(String query) throws SQLException {
    conn = this.openConnection();
    pstmt = conn.prepareStatement(query);
    resultset = pstmt.executeQuery();
    return resultset;
    
    }
    
    public boolean executeStatement(String query) throws SQLException{
    conn = this.openConnection();
    pstmt = conn.prepareStatement(query);
    boolean success = pstmt.execute();
        return success;
    }
}
