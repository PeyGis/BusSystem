/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusTicketingController;

import BusTicketingView.AdminControlPanel;
import BusTicketingView.CreateBusForm;
import BusTicketingModel.MainModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author pages coffie
 */
public class CreateNewBusController {
MainModel model;
AdminControlPanel controlPanelView;
CreateBusForm createBus;
AdminDashBoardController adminController;

public CreateNewBusController(){
 model = new MainModel("root","pages");   
createBus  = new CreateBusForm();
createBus.setVisible(true);
createBus.registerButton(new CreateBusActionListener());

}


class CreateBusActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource()==createBus.getSaveBtn()){
               
                try {
                    String busNo = createBus.getBusNo();
                    String busBrand = createBus.getBrand();
                    String busModel = createBus.getModel();
                    int busCapacity = createBus.getCapacity();
                    String busType = createBus.getBusType();
                    int execute;
                    String query = String.format("insert into bus values('%s','%s','%s','%s','%s')" ,busNo,busBrand,busModel,busCapacity,busType );
                    
                    Connection connect = model.openConnection();
                    PreparedStatement pstmt = connect.prepareStatement(query);
                    //execute = pstmt.execute();
                    execute = pstmt.executeUpdate();
                    model.closeConnection();
                    //loginCtrl.model.executeStatement(query);
                    
                    //Connection connect;
                    //connect = model.openConnection();
                    //PreparedStatement pstmt = connect.prepareStatement(query);
                    //execute = pstmt.executeUpdate();
                    
                    if(execute != 0){
                    int returnVal = JOptionPane.showConfirmDialog(controlPanelView, "Add Another Bus?");
                   
                    if(returnVal == 0){
                    createBus  = new CreateBusForm();
                    createBus.setVisible(true);
                    }
                    else{
                    controlPanelView = new AdminControlPanel();
                    controlPanelView.setVisible(true);
                    }
                    }
                    else {
                    JOptionPane.showMessageDialog(controlPanelView, "Couldn't Add Bus");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(controlPanelView, "Unseccessful Addition");
                    //Logger.getLogger(CreateNewBusController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }
        }

}    

private boolean insertIntoDatabase(String query) throws SQLException{

return model.executeStatement(query);
}    
}

