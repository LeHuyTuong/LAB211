/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.FeastMenu;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author USER
 */
public class FeastMenuController {
    public static String FEASTMENU_INFO = "FeastMenu.csv";
    private static ArrayList<FeastMenu> menus = new ArrayList<FeastMenu>() ;

    public String HEADER_INFO = 
            "-------------------------------------------------------------------"
           +"List of Set Menus for ordering party:"
           +"-------------------------------------------------------------------";
    public String FOOTER_INFO = 
            "-------------------------------------------------------------------";
    public static void readFromFile(String fileSave) {
        try (FileReader f = new FileReader(fileSave);
                BufferedReader br = new BufferedReader(f)) {
            while (br.ready()) {
                String s = br.readLine();
                String[] arr = s.split(",");
                br.readLine();
                if (arr.length >= 1) {
                    String code = arr[0].trim();
                    String name = arr[1].trim();
                    String price = arr[2].trim();
                    String ingredients = arr[3].trim();
                    FeastMenu fm = new FeastMenu(code, name, price, ingredients);
                    menus.add(fm);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void showAll(){
        readFromFile(FEASTMENU_INFO);
        Collections.sort(menus, (s1,s2) -> s1.getPrice().compareToIgnoreCase(s2.getPrice()));
        for(FeastMenu fm : menus){
            //if(fm.getCode().equalsIgnoreCase(codeOfOrder)){
                System.out.printf("Code        : %-6s \n"
                        +         "Name        : %-25s \n"
                        +         "Price       : %-10s Vnd \n"
                        +         "Ingredients : %-100s \n" , 
                        fm.getCode(),
                        fm.getName(),
                        fm.getPrice(),
                        fm.getIngredients().replace("#","\n"));
            }
        }
    
    }

