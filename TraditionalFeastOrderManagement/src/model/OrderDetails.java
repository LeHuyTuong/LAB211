/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author USER
 */
public class OrderDetails implements Serializable {
    private int orderID;
    private Date eventDate;
    private String customerID;
    private String setMenu;
    private double price;
    private int numberOfTables;
    private double totalCost;
    
    public OrderDetails(int orderID, Date eventDate, String customerID, String setMenu, double price, int numberOfTables, double totalCost) {
        this.orderID = orderID;
        this.eventDate = eventDate;
        this.customerID = customerID;
        this.setMenu = setMenu;
        this.price = price;
        this.numberOfTables = numberOfTables;
        this.totalCost = totalCost;
    }


    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getSetMenu() {
        return setMenu;
    }

    public void setSetMenu(String setMenu) {
        this.setMenu = setMenu;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
