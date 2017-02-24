/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusTicketingController;

import BusTicketingModel.MainModel;
import BusTicketingView.LoginForm;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author pages coffie
 */
public class LoginController {
    
    LoginForm loginForm;
    MainModel model;
    
    public LoginController(){
    
        model = new MainModel("root","pages");
        loginForm = new LoginForm();
        loginForm.setVisible(true);
        loginForm.registerButtons(new LoginActionListener());
    }
    
    class LoginActionListener implements ActionListener{
    
        @Override
        public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == loginForm.getLoginBtn()){
            if(loginForm.getUserName().equals("") || loginForm.getUserPassword().equals("")){
                JOptionPane.showMessageDialog(loginForm, "Provide Correct Details");
        }
            else {
            String name = loginForm.getUserName();
            String pswd = loginForm.getUserPassword(); 
           loginForm.resetFields();
                try {
                    model.openConnection();
                     if(model.isLoginSuccessful(name, pswd)){
                     JOptionPane.showMessageDialog(loginForm, "Login Successful");
                     //loginForm.resetFields();
                      loginForm.setVisible(false);
                     AdminDashBoardController adminCtrl = new AdminDashBoardController(name);
                     //AdminControlPanel adminPage = new AdminControlPanel();
                     //adminPage.setWelcomeLbl(name.toUpperCase());
                     //adminPage.setVisible(true);
                     
                     }
                     else {
                     JOptionPane.showMessageDialog(loginForm, "Login UnSuccessful. Try Again");
                     }
                         
                         model.closeConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
           
            
        }
        }
        else if (ae.getSource() == loginForm.getNewUserBtn()){
            loginForm.setVisible(false);
        NewUserController createNew = new NewUserController(new LoginController());
        }
    
    }
}
}
