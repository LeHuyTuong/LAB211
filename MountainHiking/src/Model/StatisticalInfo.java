/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author USER
 */
public class StatisticalInfo {
    private String mountainCode;
    private int numOfStudent;
    private double totalCost;
    
    public StatisticalInfo(){
        
    }


    public StatisticalInfo(String mountainCode, List<Student> l, double tutionFee) {
        this.mountainCode = mountainCode;
        this.numOfStudent = 1;
        this.totalCost =tutionFee;    }

    @Override
    public String toString() {
        return  mountainCode +  numOfStudent  + totalCost + '}';
    }
    public StatisticalInfo(String mountainCoe, int numOfStudent, double totalCost) {
        this.mountainCode = mountainCoe;
        this.numOfStudent = numOfStudent;
        this.totalCost = totalCost;
    }

    public String getMountainCoe() {
        return mountainCode;
    }

    public void setMountainCoe(String mountainCoe) {
        this.mountainCode = mountainCoe;
    }

    public int getNumOfStudent() {
        return numOfStudent;
    }

    public void setNumOfStudent(int numOfStudent) {
        this.numOfStudent = numOfStudent;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
}
