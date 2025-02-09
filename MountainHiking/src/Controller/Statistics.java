/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.HashMap;
import Model.StatisticalInfo;
import Model.Student;
import java.util.List;
/**
 *
 * @author USER
 */
public class Statistics extends HashMap<String, StatisticalInfo>{

    private final String HEADER_TABLE =
        "--------------------------------------------------------------------------------\n" +
        "   Peak Name           |    Number of Participants       |    Total Cost         \n" +
        "-----------------------|---------------------------------|----------------------";
    
    private final String FOOTER_TABLE = 
        "--------------------------------------------------------------------------------";

    
    public Statistics() {
        super();
    }

    public Statistics(List<Student> students){
        super();
        statisticalize(students);
    }

    public final void statisticalize(List<Student> students) {
        for(Student i : students){
            String mountainName = StatisticalInfo.getMountainName(i.getMountainCodeStatistisc());
            if(this.containsKey(mountainName)){
                StatisticalInfo x = this.get(mountainName);
                x.setNumOfStudent(x.getNumOfStudent() + 1);
                x.setTotalCost(x.getTotalCost() + i.getTutionFee());
            }else{
                StatisticalInfo z = new StatisticalInfo(mountainName, 1, i.getTutionFee());
                this.put(mountainName, z);
            }
        }
    }

    public void show(){
        System.out.println(HEADER_TABLE);
        for(StatisticalInfo i : this.values()){
            System.out.println(i);
        }
        System.out.println(FOOTER_TABLE);
    }
    
    
}
