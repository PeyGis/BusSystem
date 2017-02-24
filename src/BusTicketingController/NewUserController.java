/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusTicketingController;

import BusTicketingModel.MainModel;
import BusTicketingView.AdminControlPanel;
import BusTicketingView.CreateNewUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author pages coffie
 */
class NewUserController {
    LoginController loginCtrl;
    MainModel mainModel;
    CreateNewUser newUser;
    
    public NewUserController(LoginController logCtrl) {
    loginCtrl = logCtrl;
    //loginCtrl.loginForm.setVisible(false);
    newUser = new CreateNewUser();
    newUser.setVisible(true);
    newUser.registerButton(new CreateUserActionListener());
    }
    
    class CreateUserActionListener implements ActionListener{
        
    @Override
    public void actionPerformed(ActionEvent ae){
    if(ae.getSource() == newUser.getSaveBtn()){
        try {
            
            String name = newUser.getUserName();
            
            String passWord = newUser.getUserPassword();
            String query = String.format("insert into Admin (adminName, password) values ('%s', '%s')", name, passWord);
            int execute;
            try (Connection connect = loginCtrl.model.openConnection()) {
                PreparedStatement pstmt = connect.prepareStatement(query);
                //execute = pstmt.execute();
                execute = pstmt.executeUpdate();
                //loginCtrl.model.executeStatement(query);
            }
           
            
            if(execute != 0){
            JOptionPane.showMessageDialog(newUser, "Successfully Created Account for  " + name);
            //LoginController loginCtrl = new LoginController();
            AdminControlPanel adminPage = new AdminControlPanel();
            adminPage.setWelcomeLbl(name.toUpperCase());
                     adminPage.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(newUser, "UnSuccessfully, Tyr Again");
            }
           // loginCtrl.model.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(NewUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    }
    
                    
    
    
    
    }

