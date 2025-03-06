 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import model.Customer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import validator.Inputter;

/**
 *
 * @author USER
 */
public class Customers {
    
    public static String HEADER_CUSTOMER_INFO = 
        "-------------------------------------------------------------------------------------------------\n"
       +"Code   |      Customer Name        | Phone     |Email\n"
       +"-------------------------------------------------------------------------------------------------";
     public static String FOOTER_INFO =
        "-------------------------------------------------------------------------------------------------";
    private static String CUSTOMER_INFO = "customers.dat";
    public static void saveToFile(String fileSaveName, ArrayList<Customer> customers){
        FileOutputStream fos = null;
        try{
            File f = new File(fileSaveName);
            fos = new FileOutputStream(f);
            ObjectOutput oos = new ObjectOutputStream(fos);
            for(Customer c : customers){
                oos.writeObject(c);
            }
            System.out.println("Customer data has been successfully saved to " + fileSaveName);
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
    
    public static String getCustomerID(ArrayList<Customer> customers){
        String CustomerID = null ;
        try(FileReader f = new FileReader(CUSTOMER_INFO) ;
                BufferedReader br = new BufferedReader(f)){
            String line ;
            while((line = br.readLine()) != null){
                String[] arr = line.split(",");
                if(arr.length > 3){
                    CustomerID = arr[0].trim();
                }
            }
            br.close();
            f.close();
        }catch(Exception e){
            System.out.println("Error opening file");
        }
        return CustomerID;
    }
    
    public static void showCustomerCode(ArrayList<Customer> customers){
        System.out.println("Customer can choice:");
        for(Customer c : customers){
            System.out.printf("%-6s | %-25s |\n",
                    c.getCustomerCode(),
                    Inputter.formatName(c.getName()));
        }
    }
    
    public static void showCustomersToUpdate(ArrayList<Customer> customers, String code){
        System.out.println(HEADER_CUSTOMER_INFO);
        for(Customer c : customers){
            if(c.getCustomerCode().equalsIgnoreCase(code)){
                System.out.printf("%-6s | %-25s |%-10s | %-20s%n",
                    c.getCustomerCode(),
                    Inputter.formatName(c.getName()),
                    c.getPhoneNumber(),
                    c.getEmail());
            }
        }
        System.out.println(FOOTER_INFO);
    }
    public static void showData(ArrayList<Customer> customers){
        boolean check = false;
        Collections.sort(customers, Comparator.comparing(Customer::getName));
        for(Customer c : customers){
            if(!check){
                System.out.println(HEADER_CUSTOMER_INFO);
                check = true;
            }
            System.out.printf("%-6s | %-25s |%-10s | %-20s%n",
                    c.getCustomerCode(),
                    Inputter.formatName(c.getName()),
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
