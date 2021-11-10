package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;
import java.util.Objects;

public class Shell {
    String actionType;
    String name;
    Point point1;
    Point point2;
    ArrayList<String> shapeList;
    Double distance1;
    Double distance2;

    // throws IndexOutOfBoundsException, IllegalArgumentException, NumberFormatException
    public Shell(String s) {
        // S  is not null
        if(s.equals(""))
            throw new IllegalArgumentException();

        String[] sSplit = s.split(" ");
        ArrayList<String> keyword = new ArrayList<>();
        shapeList = new ArrayList<>();

        // get keyword list
        for (int i = 0; i < sSplit.length; i++) {
            if (!sSplit[i].equals("")) keyword.add(sSplit[i]);
        }

        // fill attribute
        actionType = keyword.get(0);

        switch (actionType) {
            case "rectangle":
                name = cfName(keyword.get(1));
                point1 = new Point(Double.parseDouble(keyword.get(2)), Double.parseDouble(keyword.get(3)));
                distance1 = safePosiNum(keyword.get(4));
                distance2 = safePosiNum(keyword.get(5));
                break;
            case "line":
                name = cfName(keyword.get(1));
                point1 = new Point(Double.parseDouble(keyword.get(2)), Double.parseDouble(keyword.get(3)));
                point2 = new Point(Double.parseDouble(keyword.get(4)), Double.parseDouble(keyword.get(5)));
                break;
            case "circle": // same with square
            case "square":
                name = cfName(keyword.get(1));
                point1 = new Point(Double.parseDouble(keyword.get(2)), Double.parseDouble(keyword.get(3)));
                distance1 = safePosiNum(keyword.get(4));
                break;
            case "group":
                name = cfName(keyword.get(1));
                for (int i = 2; i < keyword.size(); i++) {
                    String k = safeName(keyword.get(i));
                    // overlapping
                    if (k.equals(name)){
                        System.out.println("Collection can't contain itself!");
                        throw new IllegalArgumentException();
                    }
                    // doesn't exist
                    if (Clevis.findGraph(k) == null && Clevis.findShape(k) == null){
                        System.out.println("One shape doesn't exist!");
                        throw new  IllegalArgumentException();
                    }
                    if(!shapeList.contains(k)) shapeList.add(k);
                }
                break;
            case "ungroup": // same as below
                name = safeName(keyword.get(1));
                if (Clevis.findShape(name) == null){
                    System.out.println("This Shape doesn't exist!");
                    throw new IllegalArgumentException();
                }
                break;
            case "delete":  // same as below
            case "list":    // same as below
            case "boundingbox":
                name = safeName(keyword.get(1));
                if (Clevis.findGraph(name) == null && Clevis.findShape(name) == null){
                    System.out.println("One shape doesn't exist!");
                    throw new IllegalArgumentException();
                }
                break;
            case "pick-and-move":
                point1 = new Point(Double.parseDouble(keyword.get(1)), Double.parseDouble(keyword.get(2)));
                distance1 = Double.parseDouble(keyword.get(3));
                distance2 = Double.parseDouble(keyword.get(4));
                break;
            case "intersect":
                if (safeName(keyword.get(1)).equals(safeName(keyword.get(2)))){
                    System.out.println("Same Shape!");
                    throw new IllegalArgumentException();
                }
                // doesn't exist
                if (Clevis.findGraph(keyword.get(1)) == null && Clevis.findShape(keyword.get(1)) == null){
                    System.out.println("One shape doesn't exist!");
                    throw new  IllegalArgumentException();
                }
                if (Clevis.findGraph(keyword.get(2)) == null && Clevis.findShape(keyword.get(2)) == null){
                    System.out.println("One shape doesn't exist!");
                    throw new  IllegalArgumentException();
                }
                break;
            case "move":
                name = safeName(keyword.get(1));
                if (Clevis.findGraph(name) == null && Clevis.findShape(name) == null){
                    System.out.println("One shape doesn't exist!");
                    throw new IllegalArgumentException();
                }
                distance1 = Double.parseDouble(keyword.get(2));
                distance2 = Double.parseDouble(keyword.get(3));
                break;
            case "listAll": // same as below
            case "undo": // same as below
            case "redo": // same as below
            case "quit":
                break;
            default:
                System.out.println("Unknown command!");
                throw new IllegalArgumentException();
        }
        watcher();
    }

    public void watcher(){
        System.out.println(actionType);
        if (name != null) System.out.println(name);
        if (point1 != null) System.out.println(point1.x + " " + point1.y);
        if (point2 != null) System.out.println(point2.x + " " + point2.y);
        for (int i = 0; i < shapeList.size(); i++) {
            System.out.print(shapeList.get(i) + " ");
        }
        if (point1 != null) System.out.println(distance1);
        if (point1 != null) System.out.println(distance2);
    }

    // Name is beginning with alphabet and with (letter or number)
    public String safeName(String s){
        char c = s.charAt(0);
        boolean sLetter = (c >= 97  && c <= 122);
        boolean bLetter = (c >= 65  && c <= 90 );

        if(!(sLetter || bLetter)){
            System.out.println("Shape name with non-letter beginning!");
            throw new IllegalArgumentException();}
        for (int i = 1; i < s.length(); i++) {
            c = s.charAt(i);
            sLetter = (c >= 97  && c <= 122);
            bLetter = (c >= 65  && c <= 90 );
            boolean nLetter = (c <= 57 && c >= 48);
            if ( !(sLetter|| bLetter || nLetter) ){
                System.out.println("Shape name with illegal character!");
                throw new IllegalArgumentException();
            }
        }
        return s;
    }

    // Name is not conflict with exist
    public String cfName(String s){
        if (Clevis.findShapeWithDel(s) == null && Clevis.findGraphWithDel(s) == null)
            return safeName(s);
        else {
            System.out.println("Name Conflict!");
            throw new IllegalArgumentException();
        }
    }

    // ensure a num is positive
    public double safePosiNum(String s){
        double num = Double.parseDouble(s);
        if (num<=0) {
            System.out.println("You provide a illegal number!");
            throw new NumberFormatException();}
        return num;
    }






    // -------------Standardization--------------
    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getPoint1() {
        return point1;
    }

    public void setPoint1(Point point1) {
        this.point1 = point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public ArrayList<String> getShapeList() {
        return shapeList;
    }

    public void setShapeList(ArrayList<String> shapeList) {
        this.shapeList = shapeList;
    }

    public Double getDistance1() {
        return distance1;
    }

    public void setDistance1(Double distance1) {
        this.distance1 = distance1;
    }

    public Double getDistance2() {
        return distance2;
    }

    public void setDistance2(Double distance2) {
        this.distance2 = distance2;
    }

}
