package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.*;
import jdk.jshell.JShell;

import java.util.Scanner;

public class Application {
    public static boolean guard = true;
    public static void main(String[] args){

        Clevis clevis = new Clevis();
        // Initialize and utilize the system


        System.out.println("Welcome to use Clevis!");

        while (guard){
            String command = readUserInput("Please enter your command: ");
            ShellText shellText = new ShellText(command);
            if (shellText.actionType == Action.WARNING || checkCompliance(shellText)){
                System.out.println("Your command is not correct, please try again!");
            }else if (shellText.actionType == null) {
                System.out.println("System Error! Please connect hirsun@qq.com");
                guard = false;
            }
            else opc(shellText);
        }
    }

    public static String readUserInput(String msg){
        // Idea from COMP2021@PolyU COMP
        System.out.print(msg);
        Scanner scanIn = new Scanner(System.in);
        String inputString = scanIn.nextLine();
        scanIn.close();
        // Every command will be take down!
        LogMech.write(inputString);
        return inputString;
    }

    public static boolean checkCompliance(ShellText s){
        return false;
    }

    // to-do block
    // RECTANGLE,LINE,CIRCLE,SQUARE,GROUP,UNGROUP,BOUNDINGBOX,DELETE,MOVE,PICK_AND_MOVE,INTERSECT,LIST,LISTALL,QUIT,REDO,UNDO
    public static void opc(ShellText s){
        if (s.actionType == Action.RECTANGLE){
            // Check if command could be run
            if (Clevis.findGraphWithDel(s.shapeName) != null || Clevis.findShapeWithDel(s.shapeName) != null) {
                System.out.println("Your command is not correct, please try again!");
                return;
            }
            // run command
            Clevis.addGraph(new Rectangle(s.point1, s.distance1, s.distance2));
            // make snapshot
            VersionController.snapshot(s);
        }
        else if (s.actionType == Action.LINE){}
        else if (s.actionType == Action.CIRCLE){}
        else if (s.actionType == Action.GROUP){}
        else if (s.actionType == Action.UNGROUP){}
        else if (s.actionType == Action.BOUNDINGBOX){}
        else if (s.actionType == Action.DELETE){}
        else if (s.actionType == Action.MOVE){}
        else if (s.actionType == Action.INTERSECT){}
        else if (s.actionType == Action.LIST){}
        else if (s.actionType == Action.LISTALL){}
        else if (s.actionType == Action.REDO){}
        else if (s.actionType == Action.UNDO){}
        else {
            guard = false;
            System.out.println("Bye!");
            LogMech.outputHtml();
            LogMech.outputTxt();
        }
    }
}
