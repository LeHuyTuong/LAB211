/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.OrderDetails;
import model.Order;
import model.Customer;
import validator.Inputter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import model.FeastMenu;
import validator.Acceptable;

public class Orders {

    private static String CUT_INFO
            = "-------------------------------------------------------------------------------------------------";
    private static String ORDER_INFO
            = "-------------------------------------------------------------------------------------------------\n"
            + "ID    | Event date   | Customer ID  | Set Menu | Price     | Tables | Total Cost     \n"
            + "-------------------------------------------------------------------------------------------------";
    private static String ORDER_UPDATE_INFO
            = "-------------------------------------------------------------------------------------------------\n"
            + "ID    | Event date   | Customer ID  | Tables     \n"
            + "-------------------------------------------------------------------------------------------------";

    private static Scanner sc = new Scanner(System.in);

    private static Inputter inp = new Inputter();

    public Orders() {
        super();
        sc = new Scanner(System.in);
    }

    public static void saveToFile(String fileSaveName, ArrayList<OrderDetails> orderMenu) {
        FileOutputStream fos = null;
        try {
            File f = new File(fileSaveName);
            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (OrderDetails o : orderMenu) {
                oos.writeObject(o);
            }
            oos.close();
            System.out.println("Order data has been successfully saved to " + fileSaveName);
        } catch (Exception e) {
            System.out.println("Error opening file");
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                System.out.println("Error closing file");
            }
        }
    }

    
    public static void placeAFeastOrder(ArrayList<Customer> customers, ArrayList<FeastMenu> menus, ArrayList<Order> orders, ArrayList<OrderDetails> order_menu) {
        boolean check = true;
        while (check) {
            Customers.showCustomerCode(customers);
            String customerCode = Inputter.inputString("Enter customerCode", Acceptable.CUSTOMER_CODE_VALID, "Customer code is C,K,G + 4 digits");
            Customer customer = null;
            for (Customer c : customers) {
                if (c.getCustomerCode().equals(customerCode)) {
                    customer = c;
                    break;
                }
            }
            if (customer == null) {
                System.out.println("Customer does not exist");
                continue;
            }
            FeastMenuController.showInfomation();
            String menuCode = Inputter.inputCodeOfSetMenu();

            FeastMenu selectedMenu = null;
            for (FeastMenu f : menus) {
                if (f.getCode().equalsIgnoreCase(menuCode)) {
                    selectedMenu = f;
                    break;
                }
            }
            if (selectedMenu == null) {
                System.out.println("Error: Menu code '" + menuCode + "' does not exist!");
                return;
            }

            int numberOfTables = Inputter.inputInt("Enter number of tables", "Number must be greater than 0");
            Date eventDate = Inputter.parseDate("Enter eventDate: ");

            int maxOrderId = 0;
            for (OrderDetails o : order_menu) {
                if (o.getOrderID() > maxOrderId) {
                    maxOrderId = o.getOrderID();
                }
            }
            int orderId = maxOrderId + 1;
            Order newOrder = new Order(orderId, customerCode, menuCode, numberOfTables, eventDate);
            orders.add(newOrder);

            double price = Double.parseDouble(selectedMenu.getPrice());
            double totalCost = price * numberOfTables;
            OrderDetails orderDetails = new OrderDetails(orderId, eventDate, customerCode,
                    menuCode, price, numberOfTables, totalCost);
            order_menu.add(orderDetails);

            System.out.println(CUT_INFO);
            System.out.printf("Customer order information [Order ID: %d]%n", orderId);
            System.out.println(CUT_INFO);
            System.out.printf("Code          : %s%n", customer.getCustomerCode());
            System.out.printf("Customer name : %s%n", customer.getName());
            System.out.printf("Phone number  : %s%n", customer.getPhoneNumber());
            System.out.printf("Email         : %s%n", customer.getEmail());
            System.out.println(CUT_INFO);
            System.out.printf("Code of Set Menu : %s%n", selectedMenu.getCode());
            System.out.printf("Set menu name    : %s%n", selectedMenu.getName());
            System.out.printf("Event date       : %s%n",
                    new SimpleDateFormat("dd/MM/yyyy").format(eventDate));
            System.out.printf("Number of tables : %d%n", numberOfTables);
            System.out.printf("Price            : %,.0f Vnd%n", Double.parseDouble(selectedMenu.getPrice()));
            System.out.println("Ingredients:");
            System.out.println(selectedMenu.getIngredients().replaceAll("#", "\n").replaceAll("\"", ""));
            System.out.println(CUT_INFO);
            System.out.printf("Total cost       : %,.0f Vnd%n", totalCost);
            System.out.println(CUT_INFO);
            System.out.println("Order Successful");
            System.out.println("You want to continue entering new order or return to the main menu (N/R)");
            String decision = sc.nextLine().toLowerCase();
            if (decision.equalsIgnoreCase("r")) {
                check = false;
            } else if (!decision.equalsIgnoreCase("n")) {
                System.out.println("Invalid choice. Return menu ");
                check = false;
            }
        }
    }

    public static void ordersUpdate(ArrayList<OrderDetails> order_menus, ArrayList<Order> orders, ArrayList<FeastMenu> menus) {
        Scanner sc = new Scanner(System.in);
        int orderId = Inputter.inputInt("Enter order ID: ", "Order ID must be greater than 0");

        OrderDetails orderToUpdate = null;
        for (OrderDetails o : order_menus) {
            if (o.getOrderID() == orderId) {
                orderToUpdate = o;
                break;
            }
        }

        if (orderToUpdate == null) {
            System.out.println("This Order does not exist.");
            return;
        }

        while (true) {
            System.out.print("Old Menu code : " + orderToUpdate.getSetMenu() + "\nEnter new Set Menu Code: ");
            String newMenuCode = sc.nextLine().trim().toUpperCase();

            if (newMenuCode.isEmpty()) {
                System.out.println("Keep old data");
                break;
            }

            FeastMenu newMenu = null;
            for (FeastMenu f : menus) {
                if (f.getCode().equalsIgnoreCase(newMenuCode)) {
                    newMenu = f;
                    break;
                }
            }

            if (newMenu != null) {
                orderToUpdate.setSetMenu(newMenuCode);
                orderToUpdate.setPrice(Double.parseDouble(newMenu.getPrice()));
                break;
            } else {
                System.out.println("Menu code is exist , please enter again  ");
            }
        }

        while (true) {
            System.out.print("Old number: " + orderToUpdate.getNumberOfTables() + "\nEnter new number of tables: ");
            String newTables = sc.nextLine().trim();
            if (newTables.isEmpty()) {
                System.out.println("Keep old data ");
                break;
            }

            try {
                int newTableCount = Integer.parseInt(newTables);
                if (newTableCount > 0) {
                    orderToUpdate.setNumberOfTables(newTableCount);
                    break;
                } else {
                    System.out.println("Invalid number of tables, must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter again with number ");
            }
        }

        while (true) {
            System.out.printf("Old date : %s%n ", new SimpleDateFormat("dd/MM/yyyy").format(orderToUpdate.getEventDate()) + "\nEnter new event date: ");
            String newDateStr = sc.nextLine().trim();

            if (newDateStr.isEmpty()) {
                System.out.printf("Keep old data");
                break;
            }
            try {
                Date newDate = Inputter.parseDateUpdate(newDateStr);
                if (newDate != null && newDate.after(new Date())) {
                    orderToUpdate.setEventDate(newDate);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid date");
            }
        }

        double totalCost = orderToUpdate.getPrice() * orderToUpdate.getNumberOfTables();
        orderToUpdate.setTotalCost(totalCost);

        System.out.println("Order updated successfully!");
    }

    public static void showAll(ArrayList<OrderDetails> orders, ArrayList<Customer> customers) {
        System.out.println(ORDER_INFO);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Customer customer = null;
        for (Customer c : customers) {
            customer = c;
            break;
        }
        Collections.sort(orders, (s1, s2) -> s1.getEventDate().compareTo(s2.getEventDate()));
        for (OrderDetails o : orders) {
            String formattedDate = sdf.format(o.getEventDate());
            System.out.printf("%-5s | %-12s | %-12s | %-8s | %,.0f | %-6d | %,.0f%n \n",
                    o.getOrderID(),
                    formattedDate,
                    o.getCustomerID(),
                    o.getSetMenu(),
                    o.getPrice(),
                    o.getNumberOfTables(),
                    o.getTotalCost());
        }
        System.out.println(CUT_INFO);
    }
}
