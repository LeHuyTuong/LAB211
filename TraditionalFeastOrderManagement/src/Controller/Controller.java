/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Validator.Inputter;
import Model.Customer;
import Model.OrderDetails;
import View.Menu;
import Validator.Acceptable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Controller {

    Scanner sc = new Scanner(System.in);
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<OrderDetails> ordersList = new ArrayList<>();
    Inputter inp = new Inputter();

    public void Controller() {
        this.customers = new ArrayList<>();
        importDataCustomer(CUSTOMERS_FILE);
        importDataCustomer(FEAST_ORDER_SERVICE_FILE);
    } 
    
    public String CUSTOMERS_FILE ="customers.dat";
    public String FEAST_ORDER_SERVICE_FILE = "feast_order_service.dat";
    
    public String HEADER_CUSTOMER_INFO = 
        "--------------------------------------------------------------------------\n"
       +"Code   |      Customer Name        | Phone     |Email\n"
       +"--------------------------------------------------------------------------";
    public String HEADER_ORDER_INFO = 
        "--------------------------------------------------------------------------\n"
       +"ID   |Event data |Customer ID| Set Menu| Price    |Tables| Cost \n"
       +"------------------------------------------------------";
    public String FOOTER_INFO =
        "---------------------------------------------------------------------------";

    
    public boolean isDuplicate(String id){
        for(Customer c : customers){
            if(c.equals(id)){
                return true;
            }
        }
        return false;
    }
    
    public void registerCustomers() {
        boolean valid = false;
        boolean continueRegistration = true;
        while (continueRegistration) {
            Customer c = new Customer();
            while(true){
                c.setCustomerCode(Inputter.inputString("Enter Customer Code", Acceptable.CUSTOMER_CODE_VALID,"C,K,G + 4 digits"));
                if(!isDuplicate(c.getCustomerCode())){
                    break;
                }else{
                    System.out.println("Data is Duplicate");
                }
            }
            c.setName(Inputter.inputString("Enter Name", Acceptable.NAME_VALID, "non empty String , 2-25 characters long"));
            c.setPhoneNumber(Inputter.inputString("Enter Phone Number", Acceptable.PHONE_VALID, "Viettel 033 , Vina , Mobi 076"));
            c.setEmail(Inputter.inputString("Enter Email", Acceptable.EMAIL_VALID,"example : tuong@gmail.com, tuong@fpt.edu.vn"));
            customers.add(c);
            System.out.println("Customer registered successfully");
            
            System.out.println("You want to continue entering new customers or return to the main menu (N/R)");
            String decision = sc.nextLine().toLowerCase();
            if (decision.equalsIgnoreCase("r")) {
                continueRegistration = false;
            } else if (!decision.equalsIgnoreCase("n")) {
                System.out.println("Invalid choice. Return menu ");
                continueRegistration = false;
            }
        }
    }

    
    public void updateCustomerInformation(String codeUpdate) {
        Scanner sc = new Scanner(System.in);
        Customer CustomerToUpdate = null;
        boolean continueUpdate = true;
        for (Customer c : customers) {
            if (c.getCustomerCode().equalsIgnoreCase(codeUpdate)) {
                CustomerToUpdate = c;
                break;
            }
        }
        if (CustomerToUpdate == null) {
            System.out.println("This customer does not exist.");
            return;
        }
        while (continueUpdate) {
            Menu.menuChoiceUpdate();
            int choice = inp.choiceUpdate();
            switch (choice) {
                case 1:
                    System.out.println("Enter new Name: ");
                    String newName = sc.nextLine();
                    if (newName.isEmpty()) {
                        System.out.println("Keep old Name " + CustomerToUpdate.getName());
                    } else if (newName.equalsIgnoreCase(inp.inputUpdate(newName, Acceptable.NAME_VALID))) {
                        CustomerToUpdate.setName(newName);
                        System.out.println("New Name is updated");
                    } else {
                        System.out.println("Data is invalid");
                    }
                    break;
                case 2:
                    System.out.println("Enter new Phone Number: ");
                    String newPhone = sc.nextLine();
                    if (newPhone.isEmpty()) {
                        System.out.println("Keep old Phone Number" + CustomerToUpdate.getPhoneNumber());
                    } else if (newPhone.equalsIgnoreCase(inp.inputUpdate(newPhone, Acceptable.PHONE_VALID))) {
                        CustomerToUpdate.setPhoneNumber(newPhone);
                        System.out.println("New Phone Number is update");
                    } else {
                        System.out.println("Data is invalid");
                    }
                    break;
                case 3:
                    System.out.println("Enter new Mail: ");
                    String newMail = sc.nextLine();
                    if (newMail.isEmpty()) {
                        System.out.println("Keep old Mail" + CustomerToUpdate.getEmail());
                    } else if (newMail.equalsIgnoreCase(inp.inputUpdate(newMail, Acceptable.EMAIL_VALID))) {
                        CustomerToUpdate.setEmail(newMail);
                        System.out.println("New Mail is update");
                    } else {

                        System.out.println("Data is invalid");
                    }
                    break;
            }

            System.out.println("You want to continue entering new customers or return to the main menu (N/R)");
            String decision = sc.nextLine().toLowerCase();
            if (decision.equalsIgnoreCase("r")) {
                continueUpdate = false;
            } else if (!decision.equalsIgnoreCase("n")) {
                System.out.println("Invalid choice. Return menu ");
                continueUpdate = false;
            }
        }
    }
    
    public void searchForCustomerInformationByName(String nameToSearch){
        boolean check = false;
        for(Customer c : customers){
            if(c.getName().equalsIgnoreCase(nameToSearch)){
                if(!check){
                    System.out.println(HEADER_CUSTOMER_INFO);
                    check = true;
                }
                System.out.printf("%-6s | %-25s | %-10s | %-20s \n",
                        c.getCustomerCode(),
                        c.getName(),
                        c.getPhoneNumber(),
                        c.getEmail());
            }
        }
        if(check){
            System.out.println(FOOTER_INFO);
        }else{
            System.out.println("No one matches the search criteria");
        }
    }
    

    public void displayFeastMenus() {
        FeastMenuController.showAll();
    }
    
    public void placeAFeastOrder(){
         
    }
    
    public void importDataCustomer(String fileSaveName){
        FileInputStream f = null;
        ObjectInputStream os = null;
        try{
            f = new FileInputStream(fileSaveName);
            os = new ObjectInputStream(f);
            if(fileSaveName.equalsIgnoreCase(CUSTOMERS_FILE)){
                while(true){
                    try{
                        Object obj = os.readObject();
                        Customer c = (Customer) obj;
                        this.customers.add(c);
                    }catch(Exception e){
                        break;
                    }
                }
            }
            else if(fileSaveName.equalsIgnoreCase(FEAST_ORDER_SERVICE_FILE)){
                while(true){
                    try{
                        Object obj = os.readObject();
                        OrderDetails od = (OrderDetails) obj;
                        this.ordersList.add(od);
                    }catch(Exception e){
                        break;
                    }
                }
            }
            os.close();
        }catch(Exception e){
            System.out.println("Error opening file");
        }
    }
    
    public void saveDataToFile(){
        Menu.menuFileToSave();
        int choice = inp.choiceFile();
        switch(choice){
            case 1:
                Customers.saveToFile(CUSTOMERS_FILE,customers);
                break;
            case 2:
                Orders.saveToFile(FEAST_ORDER_SERVICE_FILE,ordersList);
                break;
        }
    }
    
    public void displayData(){
        Menu.menuDisplayList();
        int choice = inp.choiceFile();
        switch(choice){
            case 1: 
                Customers.showData(customers);
                break;
            case 2: 
                
             
        }
        
    }

}
