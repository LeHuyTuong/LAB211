/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import validator.Inputter;
import model.Customer;
import model.OrderDetails;
import model.FeastMenu;
import model.Order;
import view.Menu;
import validator.Acceptable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
    private ArrayList<FeastMenu> menus = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    Inputter inp = new Inputter();

    private String CUSTOMERS_FILE = "customers.dat";
    private String FEAST_ORDER_SERVICE_FILE = "feast_order_service.dat";

    public String HEADER_CUSTOMER_INFO
            = "-------------------------------------------------------------------------------------------------\n"
            + "Code   |      Customer Name        | Phone     |Email\n"
            + "-------------------------------------------------------------------------------------------------";
    public String HEADER_ORDER_INFO
            = "-------------------------------------------------------------------------------------------------\n"
            + "ID   |Event data |Customer ID| Set Menu| Price    |Tables| Cost \n"
            + "-------------------------------------------------------------------------------------------------";
    public String FOOTER_INFO
            = "-------------------------------------------------------------------------------------------------";

    public void loadMenus() {
        FeastMenuController.readFromFile(FeastMenuController.FEASTMENU_INFO);
        this.menus = FeastMenuController.getMenus();
    }

    public Controller() {
        customers = new ArrayList<>();
        ordersList = new ArrayList<>();
        customers = new ArrayList<>();
        orders = new ArrayList<>();
        importDataCustomer(CUSTOMERS_FILE);
        importDataFeastMenu(FEAST_ORDER_SERVICE_FILE);
        loadMenus();
    }

    public boolean isDuplicate(String id) {
        for (Customer c : customers) {
            if (id.equalsIgnoreCase(c.getCustomerCode())) {
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
            while (true) {
                c.setCustomerCode(Inputter.inputString("Enter Customer Code", Acceptable.CUSTOMER_CODE_VALID, "C,K,G + 4 digits"));
                if (!isDuplicate(c.getCustomerCode())) {
                    break;
                } else {
                    System.out.println("Data is Duplicate");
                    return;
                }
            }
            c.setName(Inputter.inputString("Enter Name", Acceptable.NAME_VALID, "non empty String , 2-25 characters long"));
            c.setPhoneNumber(Inputter.inputString("Enter Phone Number", Acceptable.PHONE_VALID, "Viettel 033 , Vina , Mobi 076"));
            c.setEmail(Inputter.inputString("Enter Email", Acceptable.EMAIL_VALID, "example : tuong@gmail.com, tuong@fpt.edu.vn"));
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

    public void updateCustomerInformation() {
        Scanner sc = new Scanner(System.in);
        Customer CustomerToUpdate = null;
        boolean continueUpdate = true;

        do {
            System.out.println("Enter ID to update");
            String codeUpdate = sc.nextLine().toUpperCase();

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
            
            CustomerToUpdate.setName(Inputter.inputUpdate("Enter new Name ", Acceptable.NAME_VALID, "Name have 2-25 character", CustomerToUpdate.getName()));
            CustomerToUpdate.setPhoneNumber(Inputter.inputUpdate("Enter new Phone Number", Acceptable.PHONE_VALID, "Viettel 033 , Vina , Mobi 076", CustomerToUpdate.getPhoneNumber()));
            CustomerToUpdate.setEmail(Inputter.inputUpdate("Enter new Email", Acceptable.EMAIL_VALID, "example : tuong@gmail.com, tuong@fpt.edu.vn", CustomerToUpdate.getEmail()));
            System.out.println("You want to continue entering new customers or return to the main menu (N/R)");
            String decision = sc.nextLine().toLowerCase();
            if (decision.equalsIgnoreCase("r")) {
                continueUpdate = false;
            } else if (!decision.equalsIgnoreCase("n")) {
                System.out.println("Invalid choice. Return menu ");
                continueUpdate = false;
            }
        }while(continueUpdate);
    }

    public void searchForCustomerInformationByName(String nameToSearch) {
        boolean check = false;
        for (Customer c : customers) {
            if (c.getName().contains(nameToSearch)) {
                if (!check) {
                    System.out.println(HEADER_CUSTOMER_INFO);
                    check = true;
                }
                System.out.printf("%-6s | %-25s | %-10s | %-20s \n",
                        c.getCustomerCode(),
                        Inputter.formatName(c.getName()),
                        c.getPhoneNumber(),
                        c.getEmail());
            }
        }
        if (check) {
            System.out.println(FOOTER_INFO);
        } else {
            System.out.println("No one matches the search criteria");
        }
    }

    public void displayFeastMenus() {
        FeastMenuController.showAll();
    }

    public void placeAFeastOrder() {
        Orders.placeAFeastOrder(customers, menus, orders, ordersList);
    }
    public void updateOrderInformation(){
        Orders.ordersUpdate(ordersList, orders,menus);
    }
    public void importDataCustomer(String fileSaveName) {
        FileInputStream f = null;
        ObjectInputStream os = null;
        try {
            f = new FileInputStream(fileSaveName);
            os = new ObjectInputStream(f);
            while (true) {
                try {
                    Object obj = os.readObject();
                    Customer c = (Customer) obj;
                    this.customers.add(c);
                } catch (Exception e) {
                    break;
                }
            }

            os.close();
        } catch (Exception e) {
            System.out.println("Error opening file");
        }
    }

    public void importDataFeastMenu(String fileSaveName) {
        FileInputStream f = null;
        ObjectInputStream os = null;
        try {
            f = new FileInputStream(fileSaveName);
            os = new ObjectInputStream(f);
            while (true) {
                try {
                    Object obj = os.readObject();
                    OrderDetails o = (OrderDetails) obj;
                    this.ordersList.add(o);
                } catch (Exception e) {
                    break;
                }
            }
            os.close();
        } catch (Exception e) {
            System.out.println("Error opening file");
        }
    }

    public void saveDataToFile() {
        Customers.saveToFile(CUSTOMERS_FILE, customers);
        Orders.saveToFile(FEAST_ORDER_SERVICE_FILE, ordersList);
    }

    public void displayData() {
        Menu.menuDisplayList();
        int choice = inp.choiceFile();
        switch (choice) {
            case 1:
                Customers.showData(customers);
                break;
            case 2:
                Orders.showAll(ordersList, customers);
                break;
        }

    }

}
