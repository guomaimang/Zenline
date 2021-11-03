package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.Action;
import hk.edu.polyu.comp.comp2021.clevis.model.Clevis;
import hk.edu.polyu.comp.comp2021.clevis.model.ShellText;
import java.util.Scanner;

public class Application {

    public static void main(String[] args){

        Clevis clevis = new Clevis();
        // Initialize and utilize the system

        boolean guard = true;
        System.out.println("Welcome to use Clevis!");

        while (guard){
            String command = readUserInput("Please enter your command: ");
            ShellText shellText = new ShellText(command);
            if (shellText.actionType == Action.WARNING || checkCompliance(shellText)){
                System.out.println("Your command is not correct, please try again!");
            }
        }
    }



    public static String readUserInput(String msg){
        // Idea from COMP2021@PolyU COMP
        System.out.print(msg);
        Scanner scanIn = new Scanner(System.in);
        String inputString = scanIn.nextLine();
        scanIn.close();
        return inputString;
    }

    public static boolean checkCompliance(ShellText s){
        return false;
    };


}
