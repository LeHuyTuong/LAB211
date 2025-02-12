/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author USER
 */
public class Order {
    private String CustomerCode;
    private String CodeOfSetMenu;
    private int NumberOfTables;
    private String PreferredEventData;

    public Order(){
        
    }
    
    public Order(int NumberOfTable){
        NumberOfTable = 1;
    }
    public Order(String CustomerCode, String CodeOfSetMenu, int NumberOfTables, String PreferredEventData) {
        this.CustomerCode = CustomerCode;
        this.CodeOfSetMenu = CodeOfSetMenu;
        this.NumberOfTables = NumberOfTables;
        this.PreferredEventData = PreferredEventData;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String CustomerCode) {
        this.CustomerCode = CustomerCode;
    }

    public String getCodeOfSetMenu() {
        return CodeOfSetMenu;
    }

    public void setCodeOfSetMenu(String CodeOfSetMenu) {
        this.CodeOfSetMenu = CodeOfSetMenu;
    }

    public int getNumberOfTables() {
        return NumberOfTables;
    }

    public void setNumberOfTables(int NumberOfTables) {
        this.NumberOfTables = NumberOfTables;
    }

    public String getPreferredEventData() {
        return PreferredEventData;
    }

    public void setPreferredEventData(String PreferredEventData) {
        this.PreferredEventData = PreferredEventData;
    }
    
    
}
