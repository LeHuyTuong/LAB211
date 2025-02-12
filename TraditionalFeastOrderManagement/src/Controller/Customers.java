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
    private static ArrayList<Customer> customers;

    public Customers(){
        customers = new ArrayList<>();
    }
    
    public static void saveToFile(String fileSaveName){
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
}
