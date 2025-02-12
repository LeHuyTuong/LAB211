/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.OrderDetails;
/**
 *
 * @author USER
 */
import Model.Order;
import java.util.ArrayList;
import java.util.HashMap;
public class Orders extends HashMap<String, OrderDetails> {
    private static String CUT_INFO = 
            "-------------------------------------------------------------------";
    private static ArrayList<Order> orderMenu = new ArrayList();
    
    public Orders(){
        super();
        placeAFeastOrder();
    }

    public static void readFromFile(){
        
    }
    
    private void placeAFeastOrder() {
        System.out.println(CUT_INFO);
        
    }
}
