/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Student;
import Validator.Acceptable;
import Validator.Inputter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class Controller {

    private ArrayList<Student> students = new ArrayList<>();
    private String MOUNTAIN_INFO = "MountainList.csv";
    private String REGISTRATIONS__INFO = "registrations.dat";
    Inputter inp = new Inputter();

    public final String VIETTEL_PHONE_VALID = "^(086|096|097|098|032|033|034|035|036|037|038|039)\\d{7}$";
    public final String VINAPHONE_PHONE_VALID = "^(088|091|094|081|082|083|084|085)\\d{7}$";
    
    public Controller(){
        students = new ArrayList<>();
    }
    
    public boolean isDuplicate(String id){
        for(Student s : students){
            if(id.equals(s.getStudentID())){
                return true;
            }
        }
        return false;
    }
    
    public void addStudent() {
        Student s = new Student();
        while(true){
            s.setStudentID(Inputter.getString("Enter studentID " ,Acceptable.STUDENT_ID));
            String checkID = s.getStudentID();
            if(!isDuplicate(checkID)){
                break;
            }
            else{
                System.out.println("ID is Duplicate. ");
            }
        }
        s.setName(Inputter.getString("Enter Name ", Acceptable.NAME_VALID));
        s.setPhoneNumber(Inputter.getString("Enter Phone Number", Acceptable.PHONE_VALID));
        s.setEmail(Inputter.getString("Enter Email", Acceptable.EMAIL_VALID));
        System.out.print("Enter Mountain Code: ");
        s.setMountainCode(inp.inputMountainCode());
        students.add(s);
        System.out.println("Success add new student");
    }

    public void updateRegistration(String studentIDToUpdate) {
        Student studentToUpdate = null;
        for(Student s : students){
            if(s.getStudentID().equalsIgnoreCase(studentIDToUpdate)){
                studentToUpdate = s;
                break;
            }
        }
        if (studentToUpdate == null) {
            System.out.println("This student has not registered yet.");
            return ;
        }
        try{
            inp.menuChoiceUpdate();
            int choice = inp.getChoiceUpdate();
            switch(choice){
                case 1: 
                    String newName = Inputter.getString("Enter new Name ",Acceptable.NAME_VALID);
                    if( !newName.isEmpty()){
                        System.out.println("Keeping old name: " + studentToUpdate.getName());
                    }else{
                        System.out.println("Keeping old name: " + studentToUpdate.getName());
                    }
                    break;
                case 2:
                    String newPhone = Inputter.getString("Enter new Phone", Acceptable.PHONE_VALID);
                    if(!newPhone.isEmpty()){
                        studentToUpdate.setPhoneNumber(newPhone);
                    }
                    else{
                        System.out.println("Keeping old phone number" + studentToUpdate.getPhoneNumber());
                    }
                    break;
                case 3:
                    String newEmail = Inputter.getString("Enter new Phone", Acceptable.PHONE_VALID);
                    if(!newEmail.isEmpty()){
                        studentToUpdate.setEmail(newEmail);
                    }
                    else{
                        System.out.println("Keeping old email" + studentToUpdate.getEmail());
                    }
                    break;
                case 4: 
                    System.out.println("Enter new MountainCode");
                    String newMountainCode = inp.inputMountainCode();
                    if(!newMountainCode.isEmpty()){
                        studentToUpdate.setMountainCode(newMountainCode);
                    }
                    else{
                        System.out.println("Keeping old mountain Code" + studentToUpdate.getMountainCode());
                    }
                    break;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void displayRegistered() {
        if(students == null || students.isEmpty()){
        importData(REGISTRATIONS__INFO);
        }
        if (students.isEmpty()) {
            System.out.println("No students have registered yet.");
        }else{
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("Student ID     | Name          | Phone         | Peak Code     | Fee         ");
            System.out.println("---------------------------------------------------------------------------");
            for (Student s : students) {
            System.out.printf("%-14s | %-13s | %-13s | %-13s | %,.0f%n",
                            s.getStudentID(), 
                            s.getName(), 
                            s.getPhoneNumber(), 
                            s.getMountainCode(), 
                            s.getTutionFee());        
            }
        }
    }

    public void deleteRegistered(String StudentCodeToDelete) {
        
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        for (Student s : students) {
            if (s.getStudentID().trim().equalsIgnoreCase(StudentCodeToDelete.trim())) {
                System.out.println("Student Details:");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Student ID  : " + s.getStudentID());
                System.out.println("Name        : " + s.getName());
                System.out.println("Phone       : " + s.getPhoneNumber());
                System.out.println("Mountain    : " + s.getMountainCode());
                System.out.println("Fee         : " + s.getTutionFee());
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Are you want to sure delete this registration ?(Y/N): ");
                String sure = sc.nextLine().toLowerCase();
                if (sure.equalsIgnoreCase("y")) {
                    if (s.getStudentID().trim().equalsIgnoreCase(StudentCodeToDelete.trim())) {
                           students.remove(s);
                            check = true;
                            System.out.println("The registration has been successfully deleted.");
                            break;
                        }
                } else {
                    check = true;
                    System.out.println("The registration didn't delete.");
                }
            }
        }
        if(check == false){
            System.out.println("This student has not registered yet.");
            return;
        }
    }
    
    public void searchingByName(String nameToSearch){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Student ID     | Name          | Phone         | Peak Code     | Fee         ");
        System.out.println("---------------------------------------------------------------------------");
        for(Student s : students){
            if(s.getName().endsWith(nameToSearch)){
                System.out.printf("%-14s | %-13s | %-13s | %-13s | %,.0f%n",
                        s.getStudentID(),
                        s.getName(),
                        s.getPhoneNumber(),
                        s.getMountainCode(),
                        s.getTutionFee());
            }
        }
    }
    
    public void filterDataByCampus(String dataByCampus){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Student ID     | Name          | Phone         | Peak Code     | Fee         ");
        System.out.println("---------------------------------------------------------------------------");
            for(Student s : students){
                if(s.getStudentID().startsWith(dataByCampus)){
                    System.out.printf("%-14s | %-13s | %-13s | %-13s | %,.0f%n",
                        s.getStudentID(),
                        s.getName(),
                        s.getPhoneNumber(),
                        s.getMountainCode(),
                        s.getTutionFee()
                    );}
            }
    }
    
    public void importData(String fileSaveName){
        FileInputStream f = null;
        ObjectInputStream os = null;
        try{
            f = new FileInputStream(fileSaveName);
            os = new ObjectInputStream(f);
            while(true){
                try{
                Object obj = os.readObject();
                Student sv = (Student) obj;
                this.students.add(sv);
                }catch(Exception e){
                    break;
                }
            }
            os.close();
        }
        catch(Exception e){
           System.out.println("Error opening file");
        }
    }
    public void saveToFile(String fileSaveName) {
        FileOutputStream fos = null;
        try{
            File f = new File(fileSaveName);
            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Student i : students){
                oos.writeObject(i);
            }
            oos.close();
            System.out.println("Successfully to save in " + fileSaveName);
        }catch(Exception e){
            System.out.println("Error opening file");
        }finally{
            try{
                fos.close();
            }catch(Exception e){
                System.out.println("Error closing file ");
            }
        }
        
    }
    
    public boolean exitSave() {
        Scanner sc = new Scanner(System.in);
        if (students.isEmpty()) {
            System.out.println("You haven't saved yet.Do you want to save before exiting? (Y/N)");
            String sure = sc.nextLine().toLowerCase();
            if(sure.equalsIgnoreCase("y")){
                saveToFile( REGISTRATIONS__INFO);
                System.out.println("Data saved successfully");
            }
        }
        System.out.println("Exit program ");
        return true;
    }

}
