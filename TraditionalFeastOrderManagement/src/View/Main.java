/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Model.Customer;
import View.Menu;
import Validator.Inputter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Customer> Customer = new ArrayList<>();
        Inputter inp = new Inputter();
        Controller controller = new Controller();
        int choice = 0;
        boolean cont = false;
        Scanner sc = new Scanner(System.in);
        do{
            Menu.menu();
            choice = inp.choice();
            switch(choice){
                case 1:
                    controller.registerCustomers();
                    break;
                case 2:
                    System.out.println("Enter ID to update");
                    String IDToUpdate = sc.nextLine();
                    controller.updateCustomerInformation(IDToUpdate);
                    break;
                case 3:
                    System.out.println("Enter Name to search");
                    String NameToSreach = sc.nextLine();
                    controller.searchForCustomerInformationByName(NameToSreach);
                    break;
                case 4: 
                    controller.displayFeastMenus();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    controller.saveDataToFile();
                    break;
                case 8:
                    controller.displayData();
                    break;
            }
        }while(choice <= 8 || cont);
    }
}
