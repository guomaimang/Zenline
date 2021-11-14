package hk.edu.polyu.comp.comp2021.clevis.model;
import java.util.ArrayList;

public class Clevis {

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
        System.out.println();
        System.out.print("Shape list: ");
        for (Shape shape :shapes){
            System.out.print(shape.getName() + " ");
        }
        System.out.println();
    }

    // this method will return ture if excess successful
    public static void delete(String name) {
        for (Graph graph : graphs) {
            if (graph.getName().equals(name)) {
                graphs.remove(graph);
                for (Shape shape : shapes) {
                    if (shape.contain(graph)) {
                        shape.delete(graph);
                        return;
                    }
                }
                return;
            }
        }
        for (Shape shape : shapes) {
            if (shape.getName().equals(name)) {
                shape.deleteSelf();
                shapes.remove(shape);
                for (Shape s : shapes) {
                    if (s.contain(shape)) s.delete(shape);
                    return;
                }
                return;
            }
        }

    }

    public static void list(String shapeName){
        for (Graph graph : graphs) {
            if (graph.getName().equals(shapeName)) {
                graph.listSelf();
                return;
            }
        }
        for (Shape shape : shapes) {
            if (shape.getName().equals(shapeName)) {
                shape.listSelf();
                return;
            }
        }
    }
    public static void listAll() {
        for (Graph graph : graphs) {
            graph.listSelf();
        }
        for (Shape shape : shapes) {
            shape.listSelf();
        }
    }
    public static void boundingbox(String n){
        if (findShape(n)!= null) findShape(n).boundingbox();
        else if (findGraph(n)!= null) findGraph(n).boundingbox();
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
    public static void pickAndMove(Point p,double dx,double dy){
        int zcode = 0;
        Graph g = null;
        Shape s = null;
        for(Shape shape:shapes){
            if (shape.isContained(p) && shape.getZcode() > zcode) {
                    s = shape;
                    zcode = s.getZcode();
                }
            }
        for(Graph graph:graphs){
            if (graph.isContained(p) && graph.getZcode() > zcode){
                g = graph;
                zcode = g.getZcode();
            }
        }
        if (zcode == 0) System.out.println("No Operation!");
        else if (g == null){
            move(s.getName(),dx,dy);
        }
        else if (s == null){
            move(g.getName(),dx,dy);
        }else if (s.getZcode() > g.getZcode()){
            move(s.getName(),dx,dy);
        }else {
            move(g.getName(),dx,dy);
        }

    }
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
    public static boolean isGraphInShape(String name){
        Graph g = findGraph(name);
        for (Shape shape : shapes) {
            if (shape.contain(g)) return true;
        }
        return false;

    }
    public static boolean isShapeInShape(String name){
        Shape s = findShape(name);
        for (Shape shape : shapes) {
            if (shape.contain(s)) return true;
        }
        return false;

    }
    public static Shape findShapeInShape(String name){
        Shape s = findShape(name);
        for (Shape shape : shapes) {
            if (shape.contain(s)) return shape;
        }
        return null;

    }

    public static void innerDelete(Graph graph) {
        graphs.remove(graph);
    }
    public static void innerDelete(Shape shape){
        shapes.remove(shape);
    }

    public void printClevis(){}
}
