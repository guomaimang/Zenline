package hk.edu.polyu.comp.comp2021.clevis.model;
import java.util.ArrayList;

public class Clevis {

    private static final ArrayList<Graph> graphs = new ArrayList<>();
    private static final ArrayList<Shape> shapes = new ArrayList<>();
    private static final ArrayList<String> nameList = new ArrayList<>();

    public static void addGraph(Graph g){
        graphs.add(g);
        nameList.add(g.getName());
    }
    public static void addShape(Shape c){
        shapes.add(c);
        nameList.add(c.getName());
    }


    public static void delete(String name) {
        // check for graph
        for (Graph graph : graphs) {
            if (graph.getName().equals(name)) {
                graphs.remove(graph);
                return;
                }
        }
        // check for shape
        for (Shape shape : shapes) {
            if (shape.getName().equals(name)) {
               shapes.remove(shape);
                return;
            }
        }
    }
    public static void list(String shapeName){
        for (Graph graph : graphs) {
            if (graph.getName().equals(shapeName)) {
                System.out.println(graph.listSelf(0));
                return;
            }
        }
        for (Shape shape : shapes) {
            if (shape.getName().equals(shapeName)) {
                shape.listSelf(0);
                return;
            }
        }
    }
    public static void listAll() {
        System.out.println("Graphs in Clevis:");
        for (Graph graph : graphs) {
            System.out.println(graph.listSelf(1));
        }
        System.out.println("Shapes in Clevis:");
        for (Shape shape : shapes) {
            shape.listSelf(1);
        }
    }
    public static void boundingbox(String name){
        if (findShape(name)!= null) findShape(name).boundingbox();
        else if (findGraph(name)!= null) findGraph(name).boundingbox();
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
        String name = new String("");

        for(Shape shape:shapes){
            if (shape.isContained(p) && shape.getZcode() > zcode) {
                    zcode = shape.getZcode();
                    name = shape.getName();
                }
            }

        for(Graph graph:graphs){
            if (graph.isContained(p) && graph.getZcode() > zcode){
                zcode = graph.getZcode();
                name = graph.getName();
            }
        }
        if (zcode != 0 && !name.equals(""))
            move(name,dx,dy);
        else
            System.out.println("No such a shape or graph!");

    }

    public static boolean cfName(String name){
       return nameList.contains(name);
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

    public static void innerRemove(Graph graph) {
        graphs.remove(graph);
    }
    public static void innerRemove(Shape shape){
        shapes.remove(shape);
    }

}
