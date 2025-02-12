/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author USER
 */
public class Inputter {
    
    
    public final String VIETTEL_PHONE_VALID = "^(086|096|097|098|032|033|034|035|036|037|038|039)\\d{7}$";
    public final String VINAPHONE_PHONE_VALID = "^(088|091|094|081|082|083|084|085)\\d{7}$";
        public final String MOBIFONE_PHONE_VALID = "^(089|090|093|070|076|077|078|079)\\d{7}$";

    private static Scanner sc;    
    public Inputter(){
        this.sc = new Scanner(System.in);
    }
    
    
    public int choiceUpdate(){
        int choice = 0;
        while(true){
            try{
                System.out.println("Enter your choice:");
                choice = Integer.parseInt(sc.nextLine());
                if(choice >=1 && choice <= 3 ){
                    break;
                }else{
                    System.out.println("Invalid input, enter again 1-3");
                }
            }catch(Exception e){
                System.out.println("Invalid input, please try again");
            }
        }
        return choice;
    }
    public int choice(){
        int choice = 0;
        while(true){
            try{
                System.out.println("Enter your choice:");
                choice = Integer.parseInt(sc.nextLine());
                if(choice >=1 && choice <= 8 ){
                    break;
                }else{
                    System.out.println("Invalid input, enter again 1-8");
                }
            }catch(Exception e){
                System.out.println("Invalid input, please try again");
            }
        }
        return choice;
    }
    public int choiceFile(){
        int choice = 0;
        while(true){
            try{
                System.out.println("Enter your choice:");
                choice = Integer.parseInt(sc.nextLine());
                if(choice >= 1 && choice <= 2){
                    break;
                }else{
                    System.out.println("Invalid input, enter again 1-2");
                }
            }catch(Exception e){
                System.out.println("Invalid input, please try 1-2");
            }
        }return choice;
    }
    public static String inputString(String mess, String pattern, String formatMess){
        String data ;
        while(true){
            System.out.println(mess +": ");
            data = sc.nextLine();
            if(data != null && Acceptable.isValid(data, pattern)){
                return data;
            }
            else{
                System.out.println("Invalid input, enter again " + formatMess);
            }
        }
    }
    
    public static String inputUpdate(String data, String pattern){
        while(true){
            if(data != null && Acceptable.isValid(data, pattern)){
                return data;
            }else{
                System.out.println("Invaid input, enter again");
            }
        }
    }
}
