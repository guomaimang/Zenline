package hk.edu.polyu.comp.comp2021.clevis.model;
import java.util.ArrayList;

public class Clevis {

    public Clevis(){}

    private static final ArrayList<Graph> graphs = new ArrayList<>();
    private static final ArrayList<Shape> shapes = new ArrayList<>();
    public static void addGraph(Graph g){
        graphs.add(g);
        watcher();
    }
    public static void addShape(Shape c){
        shapes.add(c);
        watcher();
    }

    private static void watcher(){
        System.out.print("Graph list: ");
        for (Graph graph : graphs){
            System.out.print(graph.getName() + " ");
        }
        System.out.println("");
        System.out.print("Shape list: ");
        for (Shape shape :shapes){
            System.out.print(shape.getName() + " ");
        }
        System.out.println("");
    }

    // this method will return ture if excess successful
    public static void delete(String name){
        for (Graph graph : graphs) {
           if (graph.getName().equals(name)){
               graphs.remove(graph);
               for (Shape shape : shapes) {
                   if (shape.contain(graph)) shape.remove(graph);
               }
           }
        }
        for (Shape shape : shapes) {
            if (shape.getName().equals(name)) {
                shape.removeSelf();
                shapes.remove(shape);
            }
        }

        for (Shape shape : shapes) {
            if (shape.getName().equals(name)){
                shapes.remove(shape);
                for (Shape s : shapes) {
                    if (shape.contain(s)) shape.remove(s);
                }
            }
        }
    }

    public static void list(String shapeName){
        for (Graph graph : graphs) {
            if (graph.getName().equals(shapeName)) {
                graph.listSelf();
                break;
            }
        }
        for (Shape shape : shapes) {
            if (shape.getName().equals(shapeName)) {
                shape.listSelf();
                break;
            }
        }
    }
    public static void listAll(){
    }
    public static void boundingbox(String n){
    }
    public static void move(String name,double dx, double dy){
        for (Graph graph : graphs) {
            if (graph.getName().equals(name)) {
                graph.move(dx, dy);
                return;
            }
        }

        for (Shape shape : shapes) {
            if (shape.getName().equals(name)) {
                shape.move(dx,dy);
                return;
            }
        }
    }
    public static void pickAndMove(String n,Point p){}
    public static void ungroup(Shape s){
        s.ungroup();
        shapes.remove(s);
    }

    public static Graph findGraph(String name){
        for (Graph graph : graphs) {
            if (graph.getName().equals(name)) return graph;
            }
        return null;
    }
    public static Shape findShape(String name) {
        for (Shape shape : shapes) {
            if (shape.getName().equals(name)) {
                return shape;
            }
        }
        return null;
    }
    public static boolean findGraphInShape(Graph g){

        for (Shape shape : shapes) {
            if (shape.contain(g)) return true;
        }
        return false;

    }
    public static boolean findGraphInShape(Shape s){

        for (Shape shape : shapes) {
            if (shape.contain(s)) return true;
        }
        return false;

    }

    public void printClevis(){}
}
