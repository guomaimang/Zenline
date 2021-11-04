package hk.edu.polyu.comp.comp2021.clevis.model;
import java.util.ArrayList;

public class Clevis {

    public Clevis(){}

    private static ArrayList<Graph> graphs = new ArrayList<>();
    private static ArrayList<Shape> shapes = new ArrayList<>();

    public static void addGraph(Graph g){
        graphs.add(g);
    }
    public static void addCollection(Shape c){
        shapes.add(c);
    }

    public static void deleteShape(String shapeName){
        for (int i = 0; i < graphs.size()-1 ; i++) {
            if (graphs.get(i).name.equals(shapeName))  deleteGraph(graphs.get(i));
        }
        for (int i = 0; i < shapes.size()-1; i++) {
            if(shapes.get(i).name.equals(shapeName))  deleteCollection(shapes.get(i));
        }
    }
    public static void deleteGraph(Graph g){
        if (g.isDelete) System.out.println("Delete Successful!");
        else System.out.println("There is not such a shape! You have already delete it!");}
    public static void deleteCollection(Shape g){
        if (g.isDelete) System.out.println("Delete Successful!");
        else System.out.println("There is not such a shape! You have already delete it!");}

    public static boolean listShape(String shapeName){
        for (int i = 0; i < graphs.size()-1 ; i++) {
            if (graphs.get(i).name.equals(shapeName)) {
                graphs.get(i).listSelf();
                return true;}
        }
        for (int i = 0; i < shapes.size()-1; i++) {
             if(shapes.get(i).name.equals(shapeName)) {
                shapes.get(i).listSelf();
                return true;}
        }
        System.out.println("There is not such a shape!");
        return false;
    }

    // public int getGraphNum(){return graphs.size();}
    // public int getCollectionNum(){return shapes.size();}

    public static Graph findGraph(String name){
        for (int i = 0; i < graphs.size()-1; i++) {
            if (graphs.get(i).name.equals(name)){
                if (!graphs.get(i).isDelete) return graphs.get(i);
                else {
                    System.out.println("This graph has been deleted! You can't do this operation!");
                    return null;
                }
            }
        }
        System.out.println("There isn't a graph named such");
        return null;
    }
    public static Shape findShape(String name) {
        for (int i = 0; i < shapes.size()-1; i++) {
            if (shapes.get(i).name.equals(name)){
                if (!shapes.get(i).isDelete) return shapes.get(i);
                else {
                    System.out.println("This shape has been deleted! You can't do this operation!");
                    return null;
                }
            }
        }
        System.out.println("There isn't a shape named such");
        return null;
    }
    public static Graph findGraphWithDel(String name){
        for (int i = 0; i < graphs.size()-1; i++) {
            if (graphs.get(i).name.equals(name)) return graphs.get(i);}
        return null;
    }
    public static Shape findShapeWithDel(String name){
        for (int i = 0; i < shapes.size()-1; i++) {
            if (shapes.get(i).name.equals(name)) return shapes.get(i);}
        return null;
    }

    public void printClevis(){}
}
