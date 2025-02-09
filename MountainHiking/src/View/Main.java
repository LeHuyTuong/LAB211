/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Model.Student;
import Validator.Acceptable;
import Validator.Inputter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.AccessibleAction;

/**
 *
 * @author USER
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inputter inp = new Inputter();
        Controller controller = new Controller();
        ArrayList<Student> students = new ArrayList<>();
        String fileSave = "registrations.dat";
        int choice = 0;
        do{
            Menu.menu();
            choice = inp.getChoice();
            switch(choice){
                case 1:
                    controller.addStudent();
                    break;
                case 2:
                    System.out.println("Enter student ID to update:");
                    String IDToUpdate = sc.nextLine();
                    controller.updateRegistration(IDToUpdate);
                    break;
                case 3:
                    controller.displayRegistered();
                    break;
                case 4:
                    System.out.println("Enter Student Code to delete");
                    String deleteCode = sc.nextLine();
                    controller.deleteRegistered(deleteCode);
                    break;
                case 5:
                    System.out.println("Enter name to search");
                    String searchName = sc.nextLine();
                    controller.searchingByName(searchName);
                    break;
                case 6:
                    Menu.menuChoiceCampus();
                    String campus = Inputter.getString("Choice Campus", Acceptable.CAMPUS_CODE);
                    controller.filterDataByCampus(campus);
                    break;
                case 7:
                    controller.statisticalizeByMountainPeak();
                    break;
                case 8:
                    controller.saveToFile( fileSave);
                    break;
                case 9:
                     if (controller.exitSave()) {
                        System.exit(0);
                    }
                    break;
                    
            }
        }while(choice < 10);
    }
}
