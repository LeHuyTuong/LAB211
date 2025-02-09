/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author USER
 */
public class Menu {
    public static void menu(){
        System.out.println(
                      "---------------------------------------------------------------------------\n" 
                    + "--1/- New Registration                                                   --\n"
                    + "--2/- Update Registration Information                                    --\n"
                    + "--3/- Display Registration List                                          --\n"
                    + "--4/- Delete Registration Information                                    --\n"
                    + "--5/- Search Participants by Name                                        --\n"
                    + "--6/- Filter Data by Campus                                              --\n"
                    + "--7/- Statistics of Registration Numbers by Location                     --\n"
                    + "--8/- Save Data to File                                                  --\n" 
                    + "--9/- Exit the Program                                                   --\n"
                    + "---------------------------------------------------------------------------\n"
            );
    }
    public static void menuChoiceUpdate(){
        System.out.println(
                      "---------------------------------------------------------------------------\n" 
                    + "--1/- Update Name                                                        --\n"
                    + "--2/- Update Phone Number                                                --\n"
                    + "--3/- Update Email                                                       --\n"
                    + "--4/- Update Mountain Code                                               --\n"
                    + "---------------------------------------------------------------------------\n"
        );
    }
    
    public static void menuChoiceCampus(){
        System.out.println(
                      "---------------------------------------------------------------------------\n" 
                    + "-- CE Can Tho                                                            --\n"
                    + "-- DE DA Nang                                                            --\n"
                    + "-- HE Ha Noi                                                             --\n"
                    + "-- SE Ho Chi Minh                                                        --\n"
                    + "-- QE Quy Nhon                                                           --\n"
                    + "---------------------------------------------------------------------------\n"
        );
    }
}
