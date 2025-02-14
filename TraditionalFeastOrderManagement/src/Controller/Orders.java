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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Orders extends HashMap<String, OrderDetails> {
    private static String CUT_INFO = 
            "-------------------------------------------------------------------";    
    private static String ORDER_INFO =
            "-------------------------------------------------------------------------------"
           +"ID    | Event date    | Customer ID    | Set Menu    |Price     |Tables | Cost "
           +"-------------------------------------------------------------------------------" ;
    private static String FILE_DATA = "FeastMenu.csv";
    private static String CUSTOMER_INFO = "customers.dat";
    private static Scanner sc = new Scanner(System.in);
    public static ArrayList<Customers> customers = new ArrayList<>();
    public Orders(){
        super();
         sc = new Scanner(System.in);
         customers = new ArrayList<Customers>();
         placeAFeastOrder();
    }

    public static void readFromFile(){
        
    }
    
    public static boolean checkCodeExist(String code){
        try(FileReader f = new FileReader(FILE_DATA);
            BufferedReader br = new BufferedReader(f)){
            String line ;
            while((line = br.readLine()) != null){
                String[] arr = line.split(",");
                if(arr.length > 1 && arr[0].equalsIgnoreCase(code) ){
                    return true;
                }
            }
        }catch(Exception e){
            System.out.println("Error opening file");
        }
        return false;
    }
    
    public static String inputCodeOfSetMenu(){
        String CodeOfSetMenu ;
        while(true){
            CodeOfSetMenu = sc.nextLine().trim();
            if(checkCodeExist(CodeOfSetMenu)){
                return CodeOfSetMenu;
             }else{
                System.out.println("Invalid input");
                break;
            }
        }
        return CodeOfSetMenu;
    }
    
    public static void saveToFile(String fileSaveName, ArrayList<OrderDetails> orderMenu){
        FileOutputStream fos = null;
        try{
            File f = new File(fileSaveName);
            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(OrderDetails o : orderMenu){
                oos.writeObject(o);
            }
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
    
    private static void placeAFeastOrder(){
        System.out.println(CUT_INFO);
        
    }
    public static String getCustomerID(ArrayList<Customers> customers){
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
    public static void showAll(ArrayList<OrderDetails> orders){
        boolean check = false;
        for(OrderDetails o : orders){
            System.out.printf("%-5s | %-10s | %-5s | %-5s | %,.0f%n | %-3f | %-,.0f%n",
                    Orders.getCustomerID(customers),
                    o.getEventDate(),
                    Orders.getCustomerID(customers),
                    o.getSetMenu(),
                    o.getPrice(),
                    o.getNumberOfTables(),
                    o.getTotalCost());
        }
    }
}
