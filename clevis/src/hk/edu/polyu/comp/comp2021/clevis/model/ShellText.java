package hk.edu.polyu.comp.comp2021.clevis.model;

public class ShellText {
    String graphName;
    String collectionName;
    Action actionType;
    Point point1;
    Point point2;
    String graph;
    Double Distance1;
    Double Distance2;
    Double Distance3;

    public ShellText(String shell){
        
        Action ac = Action.valueOf(shell);
        switch(ac){
            case CIRCLE:
                System.out.println("111");
                actionType = Action.CIRCLE;
                break;
            case LINE:
                System.out.println("222");
                break;

        }
    }

    public static void main(String[] arg){
        // ShellText c= new ShellText("CIRCLE 5 3 8");
        ShellText a = new ShellText("CIRCLE");
        System.out.println(a.actionType);
    }


}
