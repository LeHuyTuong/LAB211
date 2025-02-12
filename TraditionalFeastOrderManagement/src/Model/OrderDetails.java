/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author USER
 */
public class OrderDetails implements Serializable {
    private String OrderID;
    private Date EventDate;
    private String CustomerID;
    private String SetMenu;
    private int price;
    private int NumberOfTables;
    private int TotalCost;

    public OrderDetails(){
        
    }
    
    public OrderDetails(String OrderID, Date EventDate, String CustomerID, String SetMenu, int price, int NumberOfTables, int TotalCost) {
        this.OrderID = OrderID;
        this.EventDate = EventDate;
        this.CustomerID = CustomerID;
        this.SetMenu = SetMenu;
        this.price = price;
        this.NumberOfTables = NumberOfTables;
        this.TotalCost = TotalCost;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public Date getEventDate() {
        return EventDate;
    }

    public void setEventDate(Date EventDate) {
        this.EventDate = EventDate;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }

    public String getSetMenu() {
        return SetMenu;
    }

    public void setSetMenu(String SetMenu) {
        this.SetMenu = SetMenu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberOfTables() {
        return NumberOfTables;
    }

    public void setNumberOfTables(int NumberOfTables) {
        this.NumberOfTables = NumberOfTables;
    }

    public int getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(int TotalCost) {
        this.TotalCost = TotalCost;
    }
}
