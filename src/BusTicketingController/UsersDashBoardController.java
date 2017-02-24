/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusTicketingController;

import BusTicketingView.UserControlPanel;
import BusTicketingModel.MainModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pages coffie
 */
public class UsersDashBoardController {
    UserControlPanel userPage;
    MainModel model;
    DefaultTableModel tableModel;
    private final String[] columnNames;
    
    public UsersDashBoardController(){
        this.columnNames = new String[]{"Bus No.","Brand","Model","Type","Capacity"};
        model = new MainModel("root","pages");  
        //tableModel = (DefaultTableModel)userPage.getBusTable().getModel();
        tableModel = new DefaultTableModel(columnNames,0);
        userPage = new UserControlPanel();
        userPage.setVisible(true);
        userPage.registerButtons(new UsersActionListener());
    
    }
    
    class UsersActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(userPage.getSearchBtn())){
                String query = "select * from Bus order by bustype;";
                setTable(query);
            }
        }
    
    
    }
    public void setTable(String query){
        try {
            Connection con = model.openConnection();
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            int rowIndex = 0;
            while(rs.next()){
                String busId = rs.getString(1);
                String brand = rs.getString(2);
                String busmodel = rs.getString(3);
                Integer capacity = rs.getInt(4);
                String type = rs.getString(5); 
                
                tableModel.addRow(new Object[20]);
                tableModel.setValueAt(busId, rowIndex, 0);
                tableModel.setValueAt(brand, rowIndex, 1);
                tableModel.setValueAt(busmodel, rowIndex, 2);
                tableModel.setValueAt(type, rowIndex, 3);
                tableModel.setValueAt(capacity, rowIndex, 4);
                rowIndex++;
                
            }
            con.close();
            model.closeConnection();
            userPage.getBusTable().setModel(tableModel);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsersDashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
