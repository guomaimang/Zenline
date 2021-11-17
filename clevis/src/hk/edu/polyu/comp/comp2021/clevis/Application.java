package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.*;

import java.util.Objects;
import java.util.Scanner;

/**
 *
 * Starter
 * @ Han Jiaming
 * 11.3/2021
 *
 */


public class Application {

    // if guard the process
    private static boolean guard = true;

    /**
     *
     * Starter
     * use args
     *
     * @param args -html log.html -txt -txt txt.html
     */
    public static void main(String[] args){
        if (args.length<4 || !args[0].equals("-html") || !args[2].equals("-txt")){
            System.out.println("Error Argument!");
            return;
        }else {
            LogMech.setHtmlName(args[1]);
            LogMech.setTxtName(args[3]);
        }

        System.out.println("Welcome to use Clevis!");
        Scanner scanIn = new Scanner(System.in);
        Shell shell;
        while (guard){
            try {
                System.out.print("Please enter your command: ");
                String inputString = scanIn.nextLine();
                LogMech.write(inputString);
                shell = new Shell(inputString);
            }catch (Exception e){
                System.out.println("Your command is not correct, please try again!");
                shell = new Shell("NULL");
            }
                opc(shell);
        }
        scanIn.close();
    }


    /**
     *
     * to-do block
     * @ Han Jiaming
     * 11.3/2021
     * RECTANGLE,LINE,CIRCLE,SQUARE,GROUP,UNGROUP,BOUNDINGBOX,DELETE,MOVE,PICK_AND_MOVE,INTERSECT,LIST,LISTALL,QUIT,REDO,UNDO
     * @param s is an object of Shell
     *
     */
    public static void opc(Shell s){
        if(s == null || s.getActionType().equals("NULL")) return;

        switch (s.getActionType()) {
            case "rectangle": {
                Rectangle g = new Rectangle(s.getName(),s.getPoint1(), s.getDistance1(), s.getDistance2());
                Clevis.addGraph(g);
                break;
            }
            case "line": {
                Line g = new Line(s.getName(),s.getPoint1(), s.getPoint2());
                Clevis.addGraph(g);
                break;
            }
            case "circle": {
                Circle g = new Circle(s.getName(),s.getPoint1(), s.getDistance1());
                Clevis.addGraph(g);
                break;
            }
            case "square":{
                Square g = new Square(s.getName(),s.getPoint1(),s.getDistance1());
                Clevis.addGraph(g);
                break;
            }
            case "group":{
                Shape c = new Shape(s.getName(),s.getShapeList());
                Clevis.addShape(c);
                break;
            }
            case "ungroup":{
                Objects.requireNonNull(Clevis.findShape(s.getName())).ungroup();
            }
            case "delete":{
                Clevis.delete(s.getName());
                break;
            }
            case "boundingbox":{
                Clevis.boundingbox(s.getName());
                break;
            }
            case "move":{
                Clevis.move(s.getName(),s.getDistance1(), s.getDistance2());
                break;
            }
            case "pick-and-move":{
                Clevis.pickAndMove(s.getPoint1(),s.getDistance1(),s.getDistance2());
                break;
            }
            case "intersect":{
                boolean isInc;
                String s1 = s.getShapeList().get(0);
                String s2 = s.getShapeList().get(1);

                if (Clevis.findGraph(s1) != null && Clevis.findGraph(s2) != null){
                    isInc = Objects.requireNonNull(Clevis.findGraph(s1)).isIntersected(Clevis.findGraph(s2));
                }
                else if (Clevis.findGraph(s1) != null && Clevis.findGraph(s2) == null){
                    isInc = Objects.requireNonNull(Clevis.findShape(s2)).isIntersected(Clevis.findGraph(s1));
                }
                else if (Clevis.findGraph(s1) == null && Clevis.findGraph(s2) != null){
                    isInc = Objects.requireNonNull(Clevis.findShape(s1)).isIntersected(Clevis.findGraph(s2));
                }
                else{
                    isInc = Objects.requireNonNull(Clevis.findShape(s1)).isIntersected(Clevis.findShape(s2));
                }

                if (isInc) System.out.println("They are intersected");
                else System.out.println("They are not intersected");
                break;



            }
            case "list":{
                Clevis.list(s.getName());
                break;}
            case "listAll":{
                Clevis.listAll();
                break;
            }
            case "NULL":{
                break;
            }
            case "quit":{
                guard = false;
                System.out.println("Bye!");
                LogMech.outputHtml();
                LogMech.outputTxt();
            }
            }
        }

}


