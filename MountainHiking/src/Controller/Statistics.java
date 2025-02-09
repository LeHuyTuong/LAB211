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
import java.util.Map;
/**
 *
 * @author USER
 */
public class Statistics extends HashMap<String, StatisticalInfo>{

    private final String HEADER_TABLE =
        "---------------------------------------------------------------------------\n" +
        "   Peak Name     |    Number of Participants       |    Total Cost         \n" +
        "-----------------|---------------------------------|-----------------------";
    
    private final String FOOTER_TABLE = 
        "---------------------------------------------------------------------------";

    
    public Statistics() {
        super();
    }

    public Statistics(List<Student> l){
        super();
        statisticalize(l);
    }

    public final void statisticalize(List<Student> l) {
        for(Student i : l){
            if(this.containsKey(i.getMountainCode())){
                StatisticalInfo x = this.get(i.getMountainCode());
                x.setNumOfStudent(x.getNumOfStudent() + 1);
                x.setTotalCost(x.getTotalCost() + i.getTutionFee());
            }else{
                StatisticalInfo z = new StatisticalInfo(i.getMountainCode(), l, i.getTutionFee());
                this.put(i.getMountainCode(), z);
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
