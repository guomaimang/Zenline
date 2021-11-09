package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.*;

import java.util.Scanner;

public class Application {

    // if guard the process
    public static boolean guard = true;

    public static void main(String[] args){
        System.out.println("Welcome to use Clevis!");
        Scanner scanIn = new Scanner(System.in);
        while (guard){
            try {
                System.out.print("Please enter your command: ");
                String inputString = scanIn.nextLine();
                Shell shell = new Shell(inputString);
            }catch (Exception e){
                System.out.println("Your command is not correct, please try again!");
            }
        }
        scanIn.close();
    }

    // to-do block
    // RECTANGLE,LINE,CIRCLE,SQUARE,GROUP,UNGROUP,BOUNDINGBOX,DELETE,MOVE,PICK_AND_MOVE,INTERSECT,LIST,LISTALL,QUIT,REDO,UNDO
    public static void opc(Shell s){
            guard = false;
            System.out.println("Bye!");
            LogMech.outputHtml();
            LogMech.outputTxt();
        }
    }

