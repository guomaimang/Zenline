package hk.edu.polyu.comp.comp2021.clevis.model;

public class ShellText {
    String shapeName;
    String collectionName;
    Action actionType;
    Point point1;
    Point point2;
    String[] graphList;
    Double Distance1;
    Double Distance2;
    Double Distance3;

    public ShellText(String shell){
        String num1 = "";
        String num2 = "";
        String num3 = "";
        String num4 = "";
        String num5 = "";
        String action = "";

        String[] stringArr = shell.split("\\s");
        for (int i = 0; i < stringArr.length; i++) {
            switch (i){
                case 0:
                    action = stringArr[i];
                    break;
                case 1:
                    num1 = stringArr[i];
                    break;
                case 2:
                    num2 = stringArr[i];
                    break;
                case 3:
                    num3 = stringArr[i];
                    break;
                case 4:
                    num4 = stringArr[i];
                    break;
                case 5:
                    num5 = stringArr[i];
                    break;
            }
        }
        
        Action at = Action.valueOf(action);
        switch(at){
            case CIRCLE:
                System.out.println("111");
                actionType = Action.CIRCLE;
                point1 =
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
