/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusTicketingController;

import BusTicketingView.WelcomePage;
import BusTicketingView.LoginForm;
import BusTicketingView.UserControlPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author pages coffie
 */
public class MainController {
    
    WelcomePage welcomePage;
    LoginController loginCtrl;
    
    public MainController(){
    
        welcomePage = new WelcomePage();
        welcomePage.setVisible(true);
        welcomePage.registerButtons( new ControllerActionListener());
        
        
    }
    
    class ControllerActionListener implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent ae){
    
       if(ae.getSource()== welcomePage.getAdminBtn()){
       LoginForm login = new LoginForm(); 
       welcomePage.setVisible(false);
       //login.setVisible(true);
       LoginController loginctrl = new LoginController();
       
        }
        else if(ae.getSource()== welcomePage.getUserBtn()){
            welcomePage.setVisible(false);
            UsersDashBoardController userCtrl = new UsersDashBoardController();
        //UserControlPanel userPage = new UserControlPanel();
           
        //userPage.setVisible(true);
        //userPage.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }
    }
    
}
