package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;

/**
 *
 * This Class will process the string user input
 * @ ShuKunxin
 * @ HanJiaming
 *
 */
public class Shell {

    private final String actionType;
    private String name;
    private Point point1;
    private Point point2;
    private final ArrayList<String> shapeList =  new ArrayList<>();
    private Double distance1;
    private Double distance2;

    private static final int INT1 = 97;
    private static final int INT2 = 122;
    private static final int INT3 = 65;
    private static final int INT4 = 90;
    private static final int INT5 = 57;
    private static final int INT6 = 48;

    // throws IndexOutOfBoundsException, IllegalArgumentException, NumberFormatException
    /**
     * starter
     * @param s is what user input
     */
    public Shell(String s) {
        // S  is not null
        if(s.equals(""))
            throw new IllegalArgumentException();

        String[] sSplit = s.split(" ");
        ArrayList<String> keyword = new ArrayList<>();

        // get keyword list
        for (String value : sSplit) {
            if (!value.equals("")) keyword.add(value);
        }
        int size = keyword.size();

        // fill attribute
        actionType = keyword.get(0);


        switch (actionType) {
            case "rectangle":{
                if (size != 6) throw new IllegalArgumentException();
                name = cfName(keyword.get(1));
                point1 = new Point(Double.parseDouble(keyword.get(2)), Double.parseDouble(keyword.get(3)));
                distance1 = safePosiNum(keyword.get(4));
                distance2 = safePosiNum(keyword.get(5));
                break;}
            case "line":{
                if (size != 6) throw new IllegalArgumentException();
                name = cfName(keyword.get(1));
                point1 = new Point(Double.parseDouble(keyword.get(2)), Double.parseDouble(keyword.get(3)));
                point2 = new Point(Double.parseDouble(keyword.get(4)), Double.parseDouble(keyword.get(5)));
                break;}
            case "circle": // same with square
            case "square": {if (size != 5) throw new IllegalArgumentException();
                name = cfName(keyword.get(1));
                point1 = new Point(Double.parseDouble(keyword.get(2)), Double.parseDouble(keyword.get(3)));
                distance1 = safePosiNum(keyword.get(4));
                break;}
            case "group":{
                name = cfName(keyword.get(1));
                // no arg
                if (keyword.size() < 3) throw new IllegalArgumentException();
                for (int i = 2; i < keyword.size(); i++) {
                    String k = safeName(keyword.get(i));
                    // doesn't exist
                    if (Clevis.findGraph(k) == null && Clevis.findShape(k) == null){
                        System.out.println("One shape doesn't exist!");
                        throw new  IllegalArgumentException();
                    }
                    shapeList.add(k);
                }
                break;}
            case "ungroup": {
                if (size != 2) throw new IllegalArgumentException();
                name = safeName(keyword.get(1));
                if (Clevis.findShape(name) == null){
                    System.out.println("This Shape doesn't exist!");
                    throw new IllegalArgumentException();
                }
                break;}
            case "delete":  // same as below
            case "list":    // same as below
            case "boundingbox":{
                if (size != 2) throw new IllegalArgumentException();
                name = safeName(keyword.get(1));
                if (Clevis.findGraph(name) == null && Clevis.findShape(name) == null){
                    System.out.println("One shape doesn't exist!");
                    throw new IllegalArgumentException();
                }
                break;}
            case "pick-and-move":{
                if (size != 5) throw new IllegalArgumentException();
                point1 = new Point(Double.parseDouble(keyword.get(1)), Double.parseDouble(keyword.get(2)));
                distance1 = Double.parseDouble(keyword.get(3));
                distance2 = Double.parseDouble(keyword.get(4));
                break;}
            case "intersect":{
                if (size != 3) throw new IllegalArgumentException();
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
                shapeList.add(keyword.get(1));
                shapeList.add(keyword.get(2));
                break;}
            case "move": {if (size != 4) throw new IllegalArgumentException();
                name = safeName(keyword.get(1));
                if (Clevis.findGraph(name) == null && Clevis.findShape(name) == null){
                    System.out.println("One shape doesn't exist!");
                    throw new IllegalArgumentException();
                }
                distance1 = Double.parseDouble(keyword.get(2));
                distance2 = Double.parseDouble(keyword.get(3));
                break;}
            case "listAll": // same as below
            case "undo": // same as below
            case "redo": // same as below
            case "NULL": // same as below
            case "quit": {if (size != 1) throw new IllegalArgumentException();
                break;}
            default:
                System.out.println("Unknown command!");
                throw new IllegalArgumentException();
        }
    }

    // a tool to see attribute
    // Name is beginning with alphabet and with (letter or number)
    private String safeName(String s){
        char c = s.charAt(0);
        boolean sLetter = (c >= INT1 && c <= INT2);
        boolean bLetter = (c >= INT3 && c <= INT4);

        if(!(sLetter || bLetter)){
            System.out.println("Shape name with non-letter beginning!");
            throw new IllegalArgumentException();}
        for (int i = 1; i < s.length(); i++) {
            c = s.charAt(i);
            sLetter = (c >= INT1 && c <= INT2);
            bLetter = (c >= INT3 && c <= INT4);
            boolean nLetter = (c <= INT5 && c >= INT6);
            if ( !(sLetter|| bLetter || nLetter) ){
                System.out.println("Shape name with illegal character!");
                throw new IllegalArgumentException();
            }
        }
        return s;
    }
    // Name is not conflict with exist
    private String cfName(String s){
        if (!Clevis.cfName(s))
            return safeName(s);
        else {
            System.out.println("Name Conflict!");
            throw new IllegalArgumentException();
        }
    }
    // ensure a num is positive
    private double safePosiNum(String s){
        double num = Double.parseDouble(s);
        if (num<=0) {
            System.out.println("You provide a illegal number!");
            throw new NumberFormatException();}
        return num;
    }


    // -------------Standardization--------------
    /**
     * @return actionType
     *
     */
    public String getActionType() {
        return actionType;
    }
    /**
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * @return point 1
     */
    public Point getPoint1() {
        return point1;
    }
    /**
     * @return point 2
     */
    public Point getPoint2() {
        return point2;
    }
    /**
     * @return ShapeList
     */
    public ArrayList<String> getShapeList() {
        return shapeList;
    }
    /**
     * @return distance 1
     *
     */
    public Double getDistance1() {
        return distance1;
    }
    /**
     * @return distance 2
     */
    public Double getDistance2() {
        return distance2;
    }
}
