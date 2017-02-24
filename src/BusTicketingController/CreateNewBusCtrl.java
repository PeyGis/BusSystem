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


/**
 *
 * @author pages coffie
 */
public class CreateNewBusCtrl {
    
MainModel model;
AdminControlPanel controlPanelView;
CreateBusForm createBus;
AdminDashBoardController adminController;

public CreateNewBusCtrl(){
model = new MainModel("root","pages");   
createBus  = new CreateBusForm();
createBus.setVisible(true);
createBus.registerButton(new CreateBusActionListener2());

}


class CreateBusActionListener2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getSource()==createBus.getSaveBtn()){
               
                
                    String busNo = createBus.getBusNo();
                    String busBrand = createBus.getBrand();
                    String busModel = createBus.getModel();
                    int busCapacity = createBus.getCapacity();
                    String busType = createBus.getBusType();
                   
                    String query = String.format("insert into bus values('%s','%s','%s','%s','%s')" ,busNo,busBrand,busModel,busCapacity,busType );
                    
                    try{
                    model.openConnection();
                    boolean success = model.executeStatement(query);
                    
                    
                    if(success == false){
                    JOptionPane.showMessageDialog(controlPanelView, "Successfully Created Bus");
                    int returnVal = JOptionPane.showConfirmDialog(controlPanelView, "Add Another Bus?");
                   
                    if(returnVal == 0){
                        CreateNewBusCtrl createNewBusCtrl = new CreateNewBusCtrl();
                    //createBus  = new CreateBusForm();
                    //createBus.setVisible(true);
                    }
                    else{
                    adminController = new AdminDashBoardController();
                    adminController.adminPage.setWelcomeLbl(adminController.adminName());
                    }
                    }
                    else {
                    JOptionPane.showMessageDialog(controlPanelView, "Couldn't Add Bus");
                    }
                    model.closeConnection();
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
