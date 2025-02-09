/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class Student implements Serializable{
    private String studentID;
    private String name;
    private String phoneNumber;
    private String email;
    private String mountainCode;
    private double tutionFee;
    
    public final String VIETTEL_PHONE_VALID = "^(086|096|097|098|032|033|034|035|036|037|038|039)\\d{7}$";
    public final String VINAPHONE_PHONE_VALID = "^(088|091|094|081|082|083|084|085)\\d{7}$";
    public Student(){
        this.tutionFee  = 6000000;
    }
    
    public Student(String studentID, String name, String phoneNumber, String email, String mountainCode, double tutionFee) {
        this.studentID = studentID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.mountainCode = mountainCode;
        this.tutionFee = tutionFee;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMountainCode() {
        return String.format("MT%02d", Integer.parseInt(mountainCode));
    }
    
    public String getMountainCodeStatistisc(){
        return mountainCode;
    }
    
    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public double getTutionFee() {
         if(getPhoneNumber().matches(VIETTEL_PHONE_VALID) || getPhoneNumber().matches(VINAPHONE_PHONE_VALID)){
            return tutionFee * 0.65;
            }
         else 
             return tutionFee;
    }

    public void setTutionFee(double tutionFee) {
        this.tutionFee = tutionFee;
    }

    @Override
    public String toString() {
        return "Student{" + "studentID=" + studentID + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + ", mountainCode=" + getMountainCode() + ", tutionFee=" + tutionFee + '}';
    }
    
}
