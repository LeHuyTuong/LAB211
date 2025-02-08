/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

/**
 *
 * @author USER
 * 
 */public interface Acceptable {
    String STUDENT_ID = "^(SE|HE|DE|QE|CE)[0-9]{6}$";            
    String NAME_VALID = "^[A-Za-z ]{2,20}$";           
    String PHONE_VALID = "^0[3|5|7|8|9][0-9]{8}$"; 
    String VIETTEL_VALID = "^0(3[2-9]|9[6-8])[0-9]{7}$"; 
    String VNPT_VALID = "^0(7[6-9]|8[1-5])[0-9]{7}$"; 
    String EMAIL_VALID = "^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$"; 
    String CAMPUS_CODE="^(SE|HE|DE|QE|CE)$";
   
    static boolean isValid(String data, String pattern) {
        return data != null && data.matches(pattern);
    }
}
