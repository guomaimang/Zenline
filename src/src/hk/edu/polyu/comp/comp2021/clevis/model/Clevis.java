package hk.edu.polyu.comp.comp2021.clevis.model;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * 11.15/2021
 * @ HanJiaming
 *
 */
public class Clevis {

    private static final ArrayList<Graph> graphs = new ArrayList<>();
    private static final ArrayList<Shape> shapes = new ArrayList<>();
    private static final ArrayList<String> nameList = new ArrayList<>();

    /**
     * @param g the graph will be added
     */
    public static void addGraph(Graph g){
        getGraphs().add(g);
        getNameList().add(g.getName());
    }

    /**
     * @param c the shape will be added
     */
    public static void addShape(Shape c){
        getShapes().add(c);
        getNameList().add(c.getName());
    }


    /**
     * @param name the shape will be deleted
     */
    public static void delete(String name) {
        // check for graph
        for (Graph graph : getGraphs()) {
            if (graph.getName().equals(name)) {
                getGraphs().remove(graph);
                return;
                }
        }
        // check for shape
        for (Shape shape : getShapes()) {
            if (shape.getName().equals(name)) {
               getShapes().remove(shape);
                return;
            }
        }
    }

    /**
     * @param shapeName the shape will be listed
     */
  
    public static void list(String shapeName){
        for (Graph graph : getGraphs()) {
            if (graph.getName().equals(shapeName)) {
                System.out.println(graph.listSelf(0));
                return;
            }
        }
        for (Shape shape : getShapes()) {
            if (shape.getName().equals(shapeName)) {
                shape.listSelf(0);
                return;
            }
        }
    }

    /**
     * list all shapes
     */
    public static void listAll() {
        System.out.println("Graphs in Clevis:");
        for (Graph graph : getGraphs()) {
            System.out.println(graph.listSelf(1));
        }
        System.out.println("Shapes in Clevis:");
        for (Shape shape : getShapes()) {
            shape.listSelf(1);
        }
    }

    /**
     * @param name return the bounding box
     */
    public static void boundingbox(String name){
        if (findShape(name)!= null) Objects.requireNonNull(findShape(name)).boundingbox();
        else if (findGraph(name)!= null) Objects.requireNonNull(findGraph(name)).boundingbox();
    }

    /**
     * @param name return the bounding box
     * @param dx the x distance
     * @param dy the y distance
     */

    public static void move(String name,double dx, double dy){
        for (Graph graph : getGraphs()) {
            if (graph.getName().equals(name)) {
                graph.move(dx, dy);
                return;
            }
        }

        for (Shape shape : getShapes()) {
            if (shape.getName().equals(name)) {
                shape.move(dx,dy);
                return;
            }
        }
    }

    /**
     * @param p the key point
     * @param dx the x distance
     * @param dy the y distance
     */
    public static void pickAndMove(Point p,double dx,double dy){
        int zcode = 0;
        String name = "";

        for(Shape shape: getShapes()){
            if (shape.isContained(p) && shape.getZcode() > zcode) {
                    zcode = shape.getZcode();
                    name = shape.getName();
                }
            }
      
        for(Graph graph: getGraphs()){
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

    /**
     * @param name for check name if conflict
     * @return if conflict
     */
    public static boolean cfName(String name){
       return getNameList().contains(name);
    }

    /**
     * @param name the name of shape
     * @return the reference of shape or graph
     */
    public static Graph findGraph(String name){
        for (Graph graph : getGraphs()) {
            if (graph.getName().equals(name)) return graph;
        }
        return null;
    }
    /**
     * @param name the name of shape
     * @return the reference of shape or graph
     */
    public static Shape findShape(String name) {
        for (Shape shape : getShapes()) {
            if (shape.getName().equals(name)) {
                return shape;
            }
        }
        return null;
    }

    /**
     * @param graph will be removed
     */
    public static void innerRemove(Graph graph) {
        getGraphs().remove(graph);
    }
    /**
     * @param shape will be removed
     */
    public static void innerRemove(Shape shape){
        getShapes().remove(shape);
    }

    /**
     * @return graphlist
     */
    public static ArrayList<Graph> getGraphs() {
        return graphs;
    }
    /**
     * @return shapelist
     */
    public static ArrayList<Shape> getShapes() {
        return shapes;
    }
    /**
     * @return namelist
     */
    public static ArrayList<String> getNameList() {
        return nameList;
    }
}
