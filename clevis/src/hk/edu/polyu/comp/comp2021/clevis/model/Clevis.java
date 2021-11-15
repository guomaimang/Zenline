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
        for (Shape shape : shapes) {
            System.out.println("Shapes in Clevis:");
            shape.listSelf(1);
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

    public static void delete(String name) {
        for (Graph graph : graphs) {
            if (graph.getName().equals(name)) {
                // remove self in clevis
                graphs.remove(graph);
                // remove self in shapes
                for (Shape shape : shapes) {
                    if (shape.contain(graph)) {
                        shape.innerRemove(graph);
                    }
                }
                // remove empty
                update();
                return;
            }
        }
        for (Shape shape : shapes) {
            if (shape.getName().equals(name)) {
                // remove self in clevis
                shapes.remove(shape);
                // remove sub graph in clevis
                for (Graph g: shape.getGraphs()){
                    graphs.remove(g);
                }
                // remove sub shape in clevis
                for (Shape s : shape.getShapes()) {
                    Clevis.delete(s.getName());
                }
                // remove self in shapes
                for (Shape s : shapes) {
                    if (s.contain(shape)) {
                        s.innerRemove(shape);
                    }
                }
                // remove empty
                update();
                return;
            }
        }

    }
    public static void update(){
        for (Shape shape:shapes){
            shape.update();
            if (shape.getSize() == 0) delete(shape.getName());
        }
    }

    public void printClevis(){}
}
