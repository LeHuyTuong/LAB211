/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author USER
 */
public class Menu {
    public static void menu(){
        System.out.println(
                "-------------------------------------------------------------------------------------------------\n"
               + "1.Register customers                                                                          --\n"
               + "2.Update customer information                                                                 --\n"
               + "3.Search for customer information by name                                                     --\n"
               + "4.Display feast menus                                                                         --\n" 
               + "5.Place a feast order                                                                         --\n"
               + "6.Update order information                                                                    --\n"
               + "7.Save to file                                                                                --\n"
               + "8.Display Customer or Order lists                                                             --\n"
               +"-------------------------------------------------------------------------------------------------"
        );
    }
    
    public static void menuDisplayList(){
        System.out.println(
                "-------------------------------------------------------------------------------------------------\n"
               +"1.Display customers list                                                                         \n"
               +"2.Display order list                                                                             \n"
               +"-------------------------------------------------------------------------------------------------"
        );
    }
  
}
