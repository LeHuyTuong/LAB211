/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

/**
 *
 * @author USER
 */
public interface Acceptable {

    public final String NAME_VALID = "^.{2,20}$";
    public static final String NUMBER_VALID = "^\\d{10}$";
    public final String EMAIL_VALID = "^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$";
    public final String PHONE_VALID = "^0[3|5|7|8|9][0-9]{8}$";
    public final String CUSTOMER_CODE_VALID = "[C|G|K][0-9]{4}$";

    static boolean isValid(String data, String pattern) {
        return data != null && data.matches(pattern);
    }

}
