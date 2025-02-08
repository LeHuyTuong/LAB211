/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *
 * @author USER
 */
public class Inputter {
    public final String MOBIFONE_PHONE_VALID = "^(089|090|093|070|076|077|078|079)\\d{7}$";
    public final String MOUNTAIN_INFO = "MountainList.csv";
    private static Scanner sc;
    public Inputter(){
        this.sc = new Scanner(System.in);
    }
    
    public void menu(){
        System.out.println(
                      "---------------------------------------------------------------------------\n" 
                    + "--1/- New Registration                                                   --\n"
                    + "--2/- Update Registration Information                                    --\n"
                    + "--3/- Display Registration List                                          --\n"
                    + "--4/- Delete Registration Information                                    --\n"
                    + "--5/- Search Participants by Name                                        --\n"
                    + "--6/- Filter Data by Campus                                              --\n"
                    + "--7/- Statistics of Registration Numbers by Location                     --\n"
                    + "--8/- Save Data to File                                                  --\n" 
                    + "--9/- Exit the Program                                                   --\n"
                    + "---------------------------------------------------------------------------\n"
            );
    }
    public void menuChoiceUpdate(){
        System.out.println(
                      "---------------------------------------------------------------------------\n" 
                    + "--1/- Update Name                                                        --\n"
                    + "--2/- Update Phone Number                                                --\n"
                    + "--3/- Update Email                                                       --\n"
                    + "--4/- Update Mountain Code                                               --\n"
                    + "---------------------------------------------------------------------------\n"
        );
    }
    
    public void menuChoiceCampus(){
        System.out.println(
                      "---------------------------------------------------------------------------\n" 
                    + "-- CE Can Tho                                                            --\n"
                    + "-- DE DA Nang                                                            --\n"
                    + "-- HE Ha Noi                                                             --\n"
                    + "-- SE Ho Chi Minh                                                        --\n"
                    + "-- QE Quy Nhon                                                           --\n"
                    + "---------------------------------------------------------------------------\n"
        );
    }
    
    public int getChoice(){
        int choice = 0;
        while(true){
            try{
                System.out.println("Enter choice:");
                choice = Integer.parseInt(sc.nextLine());
                if(choice >= 1 && choice <= 9){
                    break;
                }
                else{
                    System.out.println("Invalid input 1-9");
                }
            }catch(Exception e){
                System.out.println("Invalid input, please try again");
            }
        }
        return choice;
    }
    
    public int getChoiceUpdate(){
        int choice = 0;
        while(true){
            try{
                System.out.println("Enter choice:");
                choice = Integer.parseInt(sc.nextLine());
                if(choice >= 1 && choice <= 4){
                    break;
                }
                else{
                    System.out.println("Invalid input 1-4");
                }
            }catch(Exception e){
                System.out.println("Invalid input, please try again");
            }
        }
        return choice;
    }
    
    public static String getString(String mess, String pattern){
        String data;
        
        while(true){
            System.out.print(mess + ": ");
            data = sc.nextLine();
            if(data != null && Acceptable.isValid(data, pattern)){
                return data;
            }else{
                System.out.println("Invalid input, enter again");
            }
        }
    } 
    
    public boolean checkCodeExist(String id) {
        try (FileReader f = new FileReader(MOUNTAIN_INFO);
             BufferedReader r = new BufferedReader(f)) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length > 1 && arr[0].equalsIgnoreCase(id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
        return false;
    }


    public String inputMountainCode() {
        String mountainCode;
        while (true) {
            mountainCode = sc.nextLine().trim();
            if (checkCodeExist(mountainCode)) {
                break;
            } else {
                System.out.println("Mountain code does not exist. Please try again.");
            }
        }
        return mountainCode;
    }
}
