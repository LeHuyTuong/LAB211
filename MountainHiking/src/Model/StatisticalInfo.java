/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

/**
 *
 * @author USER
 */
public class StatisticalInfo {
    private String mountainName;
    private int numOfStudent;
    private double totalCost;
    
    public StatisticalInfo(){
    }


    public StatisticalInfo(String mountainName, List<Student> l, double tutionFee) {
        this.mountainName = mountainName;
        this.numOfStudent = 1;
        this.totalCost =tutionFee;    }

    @Override
    public String toString() {
        return String.format("%-20s   | %-14s                  | %,.0f",
            this.mountainName,
            this.numOfStudent,
            this.totalCost
    );
    }
    public StatisticalInfo(String mountainName, int numOfStudent, double totalCost) {
        this.mountainName = mountainName;
        this.numOfStudent = numOfStudent;
        this.totalCost = totalCost;
    }

   
    public static String getMountainName(String id) {
        String Name = null;
        try{
            FileReader f = new FileReader("MountainList.csv");
            BufferedReader r = new BufferedReader(f);
            while(r.ready()){
                String s = r.readLine();
                String[] str = s.split(",");
                if(str.length > 0){
                    String name = str[1];
                    if(str[0].trim().equalsIgnoreCase(id.trim())){
                        Name = str[1].trim();
                        break;
                    }
                }
            }
            r.close();
            f.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return Name;
    }

    public void setMountainName(String mountainName) {
        this.mountainName = mountainName;
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
