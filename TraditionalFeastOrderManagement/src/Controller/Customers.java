/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Customer;
import Model.OrderDetails;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Customers {
    
    public static String HEADER_CUSTOMER_INFO = 
        "--------------------------------------------------------------------------\n"
       +"Code   |      Customer Name        | Phone     |Email\n"
       +"--------------------------------------------------------------------------";
     public static String FOOTER_INFO =
        "---------------------------------------------------------------------------";

    public static void saveToFile(String fileSaveName, ArrayList<Customer> customers){
        FileOutputStream fos = null;
        try{
            File f = new File(fileSaveName);
            fos = new FileOutputStream(f);
            ObjectOutput oos = new ObjectOutputStream(fos);
            for(Customer c : customers){
                oos.writeObject(c);
            }
            System.out.println("Sucessful save to file " + fileSaveName);
            oos.close();
        }catch(Exception e){
            System.out.println("Error opening file");
        }finally{
            try{
                fos.close();
            }catch(Exception e){
                System.out.println("Error closing file");
            }
        }
    }
    
    public static void showData(ArrayList<Customer> customers){
        boolean check = false;
        for(Customer c : customers){
            if(!check){
                System.out.println(HEADER_CUSTOMER_INFO);
                check = true;
            }
            System.out.printf("%-6s | %-25s |%-10s | %-20s%n",
                    c.getCustomerCode(),
                    c.getName(),
                    c.getPhoneNumber(),
                    c.getEmail());
        }
        if(check){
            System.out.println(FOOTER_INFO);
        }else{
            System.out.println("Does not have any customer information");
        }
    }
}
