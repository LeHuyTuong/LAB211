/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author USER
 */
public class Order {
    private int orderID;
    private String codeOfSetMenu;
    private int numberOfTables;
    private Date  preferredEventData;

    public Order(){
        
    }

    public Order(int orderID, String customerCode, String codeOfSetMenu, int numberOfTables, Date preferredEventData) {
        this.orderID = orderID;
        this.codeOfSetMenu = codeOfSetMenu;
        this.numberOfTables = numberOfTables;
        this.preferredEventData = preferredEventData;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCodeOfSetMenu() {
        return codeOfSetMenu;
    }

    public void setCodeOfSetMenu(String codeOfSetMenu) {
        this.codeOfSetMenu = codeOfSetMenu;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public Date  getPreferredEventData() {
        return preferredEventData;
    }

    public void setPreferredEventData(Date  preferredEventData) {
        this.preferredEventData = preferredEventData;
    }
    
    
}
