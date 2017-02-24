/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusTicketing;

import BusTicketingController.LoginController;
import BusTicketingController.MainController;
import javax.swing.SwingUtilities;

/**
 *
 * @author pages coffie
 */
public class MainBusTicketing {
    
    public static void main(String[] args){
    
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                                           
               
               MainController ctrl = new MainController();
               //LoginController login = new LoginController();
            }
        }); 
        
        
    
    }
}
