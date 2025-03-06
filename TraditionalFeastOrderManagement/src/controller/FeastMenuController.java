/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.FeastMenu;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author USER
 */
public class FeastMenuController {

    public static String FEASTMENU_INFO = "FeastMenu.csv";
    private static ArrayList<FeastMenu> menus = new ArrayList<FeastMenu>();

    static ArrayList<FeastMenu> getMenus() {
        return menus;
    }

    public static String HEADER_INFO
            = "-------------------------------------------------------------------------------------------------\n"
            + "List of Set Menus for ordering party:\n"
            + "-------------------------------------------------------------------------------------------------";
    public static String FOOTER_INFO
            = "-------------------------------------------------------------------------------------------------";

    public static void readFromFile(String fileSave) {
        try (FileReader f = new FileReader(fileSave);
                BufferedReader br = new BufferedReader(f)) {
            br.readLine();
            while (br.ready()) {
                String s = br.readLine();
                String[] arr = s.split(",",4);
                if (arr.length < 4) continue;
                    String code = arr[0].trim();
                    String name = arr[1].trim();
                    String price = arr[2].trim();
                    String ingredients = arr[3].trim();
                    FeastMenu fm = new FeastMenu(code, name, price, ingredients);
                    menus.add(fm);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showAll() {
        if(menus == null){
            readFromFile(FOOTER_INFO);
        }
        Collections.sort(menus, (s1, s2) -> s1.getPrice().compareToIgnoreCase(s2.getPrice()));
        for (FeastMenu fm : menus) {
            System.out.printf("Code        : %-6s \n"
                    + "Name        : %-25s \n"
                    + "Price       : %,.0f Vnd \n"
                    + "Ingredients : \n%-100s \n",
                    fm.getCode(),
                    fm.getName(),
                    Double.parseDouble(fm.getPrice()),
                    fm.getIngredients().replaceAll("#", "\n").replaceAll("\"", ""));
            System.out.println(FOOTER_INFO);
        }
    }
    
    public static void showInfomation(){
        if(menus == null){
            readFromFile(FEASTMENU_INFO);
        }
        for(FeastMenu fm : menus){
            System.out.printf("Code : %-6s \n"
                            + "Name : %-25s\n",
                    fm.getCode(),
                    fm.getName());
        }
    }
    
}
