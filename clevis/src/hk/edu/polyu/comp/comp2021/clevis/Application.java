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
                opc(shell);
            }catch (Exception e){
                System.out.println("Your command is not correct, please try again!");
            }

        }
        scanIn.close();
    }

    // to-do block
    // RECTANGLE,LINE,CIRCLE,SQUARE,GROUP,UNGROUP,BOUNDINGBOX,DELETE,MOVE,PICK_AND_MOVE,INTERSECT,LIST,LISTALL,QUIT,REDO,UNDO
    public static void opc(Shell s){

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
                Clevis.ungroup(Clevis.findShape(s.getName()));
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
                Clevis.pickAndMove(s.getName(), s.getPoint1());
            }
            case "intersect":{
                boolean isInc;
                String s1 = s.getShapeList().get(1);
                String s2 = s.getShapeList().get(2);

                if (Clevis.findGraph(s1) != null && Clevis.findGraph(s2) != null){
                    isInc = Clevis.findGraph(s1).isIntersected(Clevis.findGraph(s2));
                }
                else if (Clevis.findGraph(s1) != null && Clevis.findGraph(s2) == null){
                    isInc = Clevis.findShape(s2).isIntersected(Clevis.findGraph(s1));
                }
                else if (Clevis.findGraph(s1) == null && Clevis.findGraph(s2) != null){
                    isInc = Clevis.findShape(s1).isIntersected(Clevis.findGraph(s2));
                }
                else{
                    isInc = Clevis.findShape(s1).isIntersected(Clevis.findShape(s2));
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


