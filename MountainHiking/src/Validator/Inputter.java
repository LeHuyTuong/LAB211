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
    public static String getFormatUpdate(String data, String pattern){
        while(true){
            if(data != null && Acceptable.isValid(data, pattern)){
                return data;
            }else if(data == null){
                return "Keeping old data";
            }
            else{
                return "Invalid input, enter again";
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
    
    public String inputMountainCodeUpdate(){
        String mountainCode = "";
        while(true){
            System.out.println("Enter new Mountain Code:");
            mountainCode = sc.nextLine();
            if(checkCodeExist(mountainCode)){
                break;
            }
            else if(mountainCode.isEmpty()){
                break;
            }
            else{
                System.out.println("Mountain code does not exist. Please try again.");
                continue;
            }
        }
        return mountainCode;
    }
}
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
    public static String getFormatUpdate(String data, String pattern){
        while(true){
            if(data != null && Acceptable.isValid(data, pattern)){
                return data;
            }else if(data == null){
                return "Keeping old data";
            }
            else{
                return "Invalid input, enter again";
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
    
    public String inputMountainCodeUpdate(){
        String mountainCode = "";
        while(true){
            System.out.println("Enter new Mountain Code:");
            mountainCode = sc.nextLine();
            if(checkCodeExist(mountainCode)){
                break;
            }
            else if(mountainCode.isEmpty()){
                break;
            }
            else{
                System.out.println("Mountain code does not exist. Please try again.");
                continue;
            }
        }
        return mountainCode;
    }
}
