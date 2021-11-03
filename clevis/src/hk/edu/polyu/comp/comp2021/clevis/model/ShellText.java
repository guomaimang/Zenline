package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.Arrays;

public class ShellText {
    public String shapeName;  //set of graph
    public String graphName;  //one graph
    public Action actionType;
    public Point point1;
    public Point point2;
    public String[] graphList;
    public Double distance1;
    public Double distance2;


    public ShellText(String shell){

        String[] shellList = fen(shell);

        Action at = Action.valueOf(shellList[0]);
        switch(at){
            case RECTANGLE:
                actionType = Action.RECTANGLE;
                graphName = shellList[1];
                point1 = new Point(Double.valueOf(shellList[2]),Double.valueOf(shellList[3]));
                distance1 = Double.valueOf(shellList[4]);
                distance2 = Double.valueOf(shellList[5]);
                break;

            case LINE:
                actionType = Action.LINE;
                graphName = shellList[1];
                point1 = new Point(Double.valueOf(shellList[2]),Double.valueOf(shellList[3]));
                point2 = new Point(Double.valueOf(shellList[4]),Double.valueOf(shellList[5]));
                break;

            case CIRCLE:
                actionType = Action.CIRCLE;
                graphName = shellList[1];
                point1 = new Point(Double.valueOf(shellList[2]),Double.valueOf(shellList[3]));
                distance1 = Double.valueOf(shellList[4]);
                break;

            case SQUARE:
                actionType = Action.SQUARE;
                graphName = shellList[1];
                point1 = new Point(Double.valueOf(shellList[2]),Double.valueOf(shellList[3]));
                distance1 = Double.valueOf(shellList[4]);
                break;

            case UNGROUP:
                actionType = Action.UNGROUP;
                shapeName = shellList[1];
                break;

            case BOUNDINGBOX:
                actionType = Action.BOUNDINGBOX;
                shapeName = shellList[1];
                break;

            case MOVE:
                actionType = Action.MOVE;
                shapeName = shellList[1];
                distance1 = Double.valueOf(shellList[2]);
                distance2 = Double.valueOf(shellList[3]);
                break;

            case PICK_AND_MOVE:
                actionType = Action.PICK_AND_MOVE;
                point1 = new Point(Double.valueOf(shellList[1]),Double.valueOf(shellList[2]));
                distance1 = Double.valueOf(shellList[3]);
                distance2 = Double.valueOf(shellList[4]);
                break;

            case INTERSECT:
                actionType = Action.INTERSECT;
                graphList = new String[]{shellList[1], shellList[2]};
                break;

            case LIST:
                actionType = Action.LIST;
                shapeName = shellList[1];
                break;

            case UNDO:
                actionType = Action.UNDO;
                break;

            case REDO:
                actionType = Action.REDO;
                break;

            case QUIT:
                actionType = Action.QUIT;
                break;

            case LISTALL:
                actionType = Action.LISTALL;
                break;

            case GROUP:
                actionType = Action.GROUP;
                graphList = Arrays.copyOfRange(shellList,1,shellList.length-1);

            default:
                actionType = Action.WARNING;
                break;
        }
    }

    public static String[] fen(String shell){
        int i = 1;
        shell = shell.trim();

        String [] spString = shell.split("\\s+");
        for(String ignored : spString){
            i++;
        }
        String[] a = new String[i];
        int j = 0;
        for(String str : spString){
            a[j++] = str;
        }
        return a;
    }


    public static void main(String[] arg){

        ShellText a = new ShellText("RECTANGLE      changfangxing       3   2   8 10");
        System.out.print(a.actionType + " ");
        System.out.print(a.graphName+ " ");
        System.out.print(a.point1.x+ " ");
        System.out.print(a.point1.y+ " ");
        System.out.print(a.distance1+ " ");
        System.out.println(a.distance2+ " ");

      
        ShellText b = new ShellText("LINE xian 1 2 4 10");
        System.out.print(b.actionType + " ");
        System.out.print(b.graphName+ " ");
        System.out.print(b.point1.x+ " ");
        System.out.print(b.point1.y+ " ");
        System.out.print(b.point2.x+ " ");
        System.out.println(b.point2.y+ " ");

        ShellText c= new ShellText("CIRCLE       yi       3   2   8");
        System.out.print(c.actionType + " ");
        System.out.print(c.graphName+ " ");
        System.out.print(c.point1.x+ " ");
        System.out.print(c.point1.y+ " ");
        System.out.println(c.distance1+ " ");


        ShellText d= new ShellText("   SQUARE zfx   3   4   8");
        System.out.print(d.actionType + " ");
        System.out.print(d.graphName+ " ");
        System.out.print(d.point1.x+ " ");
        System.out.print(d.point1.y+ " ");
        System.out.println(d.distance1+ " ");

        ShellText e= new ShellText("   UNGROUP ug  ");
        System.out.print(e.actionType + " ");
        System.out.println(e.shapeName+ " ");

        ShellText f= new ShellText("BOUNDINGBOX bb  ");
        System.out.print(f.actionType + " ");
        System.out.println(f.shapeName+ " ");

        ShellText g= new ShellText("MOVE ug  3 3");
        System.out.print(g.actionType + " ");
        System.out.print(g.shapeName+ " ");
        System.out.print(g.distance1+ " ");
        System.out.println(g.distance2+ " ");

        ShellText h= new ShellText("PICK_AND_MOVE 12 23 1 4  ");
        System.out.print(h.actionType + " ");
        System.out.print(h.point1.x+ " ");
        System.out.print(h.point1.y+ " ");
        System.out.print(h.distance1+ " ");
        System.out.println(h.distance2+ " ");

        ShellText j= new ShellText("INTERSECT ins dyg deg  ");
        System.out.print(j.actionType + " ");
        for (int i = 0; i < j.graphList.length; i++) {
            System.out.print(j.graphList[i] + " ");
        }
        System.out.println(" ");

        ShellText k= new ShellText("LIST list  ");
        System.out.print(k.actionType + " ");
        System.out.println(k.shapeName+ " ");

        ShellText l= new ShellText("UNDO  ");
        System.out.println(l.actionType + " ");

        ShellText m= new ShellText("REDO");
        System.out.println(m.actionType + " ");

        ShellText n= new ShellText("QUIT");
        System.out.println(n.actionType + " ");

        ShellText o= new ShellText("LISTALL");
        System.out.println(o.actionType + " ");

        ShellText p= new ShellText("GROUP           3   yusdh 2 123 12 skx 1 32 1 8 9");
        System.out.print(p.actionType + " ");
        for (int q = 0; q < p.graphList.length; q++) {
            System.out.print(p.graphList[q] + " ");
    }


}
}
