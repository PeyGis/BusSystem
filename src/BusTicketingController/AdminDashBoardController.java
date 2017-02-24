/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusTicketingController;

import BusTicketingView.AdminControlPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author pages coffie
 */
public class AdminDashBoardController {
    
AdminControlPanel adminPage;
String name;

public AdminDashBoardController(String adminName){
this();
adminPage.setWelcomeLbl(adminName.toUpperCase());
name = adminName;

}

public AdminDashBoardController(){
adminPage = new AdminControlPanel();
adminPage.setVisible(true);
adminPage.registerButtons(new AdminCtrlActionListener());

}
class AdminCtrlActionListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== adminPage.getAddBusBtn()){
            adminPage.setVisible(false);
            //CreateBusForm bus = new CreateBusForm();
            //bus.setVisible(true);
            CreateNewBusCtrl ctrl;
            ctrl = new CreateNewBusCtrl();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
public String adminName(){
return name;
}
    
}
