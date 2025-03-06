/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
import jdk.nashorn.internal.parser.TokenStream;

/**
 *
 * @author USER
 */
public class Inputter {

    public final String VIETTEL_PHONE_VALID = "^(086|096|097|098|032|033|034|035|036|037|038|039)\\d{7}$";
    public final String VINAPHONE_PHONE_VALID = "^(088|091|094|081|082|083|084|085)\\d{7}$";
    public final String MOBIFONE_PHONE_VALID = "^(089|090|093|070|076|077|078|079)\\d{7}$";
    private static String FILE_DATA = "FeastMenu.csv";

    private static Scanner sc;

    public Inputter() {
        this.sc = new Scanner(System.in);
    }

    public int choiceUpdate() {
        int choice = 0;
        while (true) {
            try {
                System.out.println("Enter your choice:");
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid input, enter again 1-3");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please try again");
            }
        }
        return choice;
    }

    public int choice() {
        int choice = 0;
        while (true) {
            try {
                System.out.println("Enter your choice:");
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= 1 && choice <= 8) {
                    break;
                } else {
                    System.out.println("Invalid input, Quit");
                    System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please try again");
            }
        }
        return choice;
    }

    public int choiceFile() {
        int choice = 0;
        while (true) {
            try {
                System.out.println("Enter your choice:");
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= 1 && choice <= 2) {
                    break;
                } else {
                    System.out.println("Invalid input, enter again 1-2");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please try 1-2");
            }
        }
        return choice;
    }

    public static String inputString(String mess, String pattern, String formatMess) {
        String data;
        while (true) {
            System.out.println(mess + ": ");
            data = sc.nextLine().toUpperCase();
            if (data != null && Acceptable.isValid(data, pattern)) {
                return data;
            } else {
                System.out.println("Invalid input, enter again " + formatMess);
            }
        }
    }

    public static Date parseDate(String mess) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        while (true) {
            System.out.println(mess);
            String eventDateStr = sc.nextLine().trim();

            try {
                Date eventDate = sdf.parse(eventDateStr);
                Date currentDate = new Date();

                if (!eventDate.before(currentDate)) {
                    return eventDate;
                }

                System.out.println("Event date must be in the future!");
            } catch (ParseException e) {
                System.out.println("Invalid date format! Use dd/MM/yyyy");
            }
        }
    }

    public static Date parseDateUpdate(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        while (true) {
            try {
                Date eventDate = sdf.parse(data);
                Date currentDate = new Date();

                if (eventDate.before(currentDate)) {
                    System.out.println("Event date must be in the future!");
                    return null;
                }

                return eventDate;
            } catch (ParseException e) {
                System.out.println("Invalid date format! Use dd/MM/yyyy");
                return null;
            }
        }
    }

    public static int inputInt(String mess, String error) {
        int data;
        while (true) {
            System.out.print("Enter number of tables: ");
            String input = sc.nextLine();

            try {
                data = Integer.parseInt(input);
                if (data > 0) {
                    break;
                } else {
                    System.out.println("Invalid input, enter again. Number must be greater than 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, enter again. Please enter a valid integer.");
            }
        }
        return data;
    }

    public static String inputUpdate(String mess, String pattern, String error, String oldData) {
        String data;
        while (true) {
            System.out.println(mess);
            data = sc.nextLine();
            if (data.equalsIgnoreCase("")) {
                System.out.println("Keep old data:  " + oldData);
                return oldData;
            } else if (data != null && Acceptable.isValid(data, pattern)) {
                return data.toUpperCase();
            } else {
                System.out.println("Invaid input " + error);
            }
        }
    }

    public static String formatName(String fullName) {
        String[] array = fullName.trim().split("\\s+");
        if (array.length == 0) {
            return "";
        }
        String lastName = array[array.length - 1];
        StringBuilder firstName = new StringBuilder();

        for (int i = 0; i < array.length - 1; i++) {
            firstName.append(array[i]);
            if (i < array.length - 2) {
                firstName.append(" ");
            }
        }
        return lastName + ", " + firstName.toString();
    }

    public static boolean checkCodeExist(String code) {
        try (FileReader f = new FileReader(FILE_DATA);
                BufferedReader br = new BufferedReader(f)) {
            String line;
            br.readLine(); // Bỏ qua header
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",", 4); // Chỉ split tối đa 4 trường
                if (arr.length < 4) {
                    continue; // Bỏ qua dòng thiếu trường
                }
                String currentCode = arr[0].trim();
                if (currentCode.equalsIgnoreCase(code.trim())) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
        return false;
    }

    public static String inputCodeOfSetMenu() {
        while (true) {
            System.out.println("Enter Code of Set Menu:");
            String codeOfSetMenu = sc.nextLine().trim().toUpperCase();

            if (codeOfSetMenu.isEmpty()) {
                System.out.println("Code cannot be blank!");
                continue;
            }

            if (checkCodeExist(codeOfSetMenu)) {
                return codeOfSetMenu;
            } else {
                System.out.println("Code does not exist");
            }
        }
    }
}
