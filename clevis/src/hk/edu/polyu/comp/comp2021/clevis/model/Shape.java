package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;

/**
 * The type Shape.
 */
public class Shape {

    private final String name;
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private final ArrayList<Graph> graphs = new ArrayList<>();
    public ArrayList<Shape> getShapes(){
        return shapes;
    }
    public ArrayList<Graph> getGraphs(){
        return graphs;
    }
    public int getSize(){return shapes.size()+graphs.size();}
    private int zcode = 0;
    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    /**
     * Instantiates a new Shape.
     *
     * @param name      the name
     * @param shapeList the shape list
     */
    public Shape(String name,ArrayList<String> shapeList){
        this.name = name;
        for (String s : shapeList) {
            // make sure that it is a graph or shape
            if (Clevis.findGraph(s) != null) {
                // Migration
                getGraphs().add(Clevis.findGraph(s));
                Clevis.innerRemove(Clevis.findGraph(s));
            }
            else if (Clevis.findShape(s) != null) {
                // Migration
                getShapes().add(Clevis.findShape(s));
                Clevis.innerRemove(Clevis.findShape(s));
            }
        }
        makeZcode();
        System.out.println("The shapes zcode is " + getZcode() +".");
    }
  
    private void makeZcode(){
        // initialization
        if (getShapes().size() != 0){
            setxMax(getShapes().get(0).getxMax());
            setxMin(getShapes().get(0).getxMin());
            setyMax(getShapes().get(0).getyMax());
            setyMin(getShapes().get(0).getyMin());
        }else {
            setxMax(getGraphs().get(0).getxMax());
            setxMin(getGraphs().get(0).getxMin());
            setyMax(getGraphs().get(0).getyMax());
            setyMin(getGraphs().get(0).getyMin());
        }
        // update arg
        for(Graph graph: getGraphs()){
            setxMax(Math.max(getxMax(), graph.getxMax()));
            setxMin(Math.min(getxMin(), graph.getxMin()));
            setyMax(Math.max(getyMax(), graph.getyMax()));
            setyMin(Math.min(getyMin(), graph.getyMin()));
        }
        for(Shape shape: getShapes()){
            setxMax(Math.max(getxMax(), shape.getxMax()));
            setxMin(Math.min(getxMin(), shape.getxMin()));
            setyMax(Math.max(getyMax(), shape.getyMax()));
            setyMin(Math.min(getyMin(), shape.getyMin()));
        }
        // update zcode
        int num = 0;
        for (Shape s : getShapes()) {
            num = Math.max(s.getZcode(), num);
        }
        for (Graph g : getGraphs()){
            num = Math.max(g.getZcode(), num);
        }
        setZcode(num);


    }

    /**
     * List self.
     *
     * @param indentation the indentation
     */
    public void listSelf(int indentation){
        String prestr = new String("");
        for (int i = 0; i < indentation; i++) {
            prestr = prestr + "   ";
        }
        System.out.println(prestr + "Group Name: " + getName());
        // Print Graph
        System.out.println(prestr + "   " + "Graphs:");
        for (Graph graph : getGraphs()) {
            System.out.println(graph.listSelf(indentation+2));
        }
        // Print Shape
        System.out.println(prestr + "   " + "Shapes:");
        for (Shape shape : getShapes()) {
            shape.listSelf(indentation+2);
        }
    }

    /**
     * Ungroup.
     */
    public void ungroup(){
        for (Graph graph: getGraphs()){
            // Migration
            Clevis.addGraph(graph);
        }
        for (Shape shape: getShapes()){
            // Migration
            Clevis.addShape(shape);
        }
        getShapes().clear();
        getGraphs().clear();
        Clevis.innerRemove(this);
    }

    /**
     * Boundingbox.
     */
    public void boundingbox(){
        System.out.println(String.format("%.2f", getxMin())+ " " + String.format("%.2f", getyMax()) + " " + String.format("%.2f",(getxMax() - getxMin())) + " " +String.format("%.2f",((getyMax() - getyMin()))));
    }

    /**
     * Is intersected boolean.
     *
     * @param shape the shape
     * @return the boolean
     */
    public boolean isIntersected(Shape shape) {
        for (Graph g: getGraphs()){
            if (shape.isIntersected(g)) return true;
        }
        for (Shape s: getShapes()) {
            if (shape.isIntersected(s)) return true;
        }
        return false;
    }

    /**
     * Is intersected boolean.
     *
     * @param graph the graph
     * @return the boolean
     */
    public boolean isIntersected(Graph graph){
        for (Graph g: getGraphs()){
            if (g.isIntersected(graph)) return true;
        }
        for (Shape s: getShapes()) {
            if (s.isIntersected(graph)) return true;
        }
        return false;
    }

    /**
     * Move.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void move(double dx, double dy) {
        for (Graph graph : getGraphs()) {
            graph.move(dx, dy);
        }
        for (Shape shape : getShapes()) {
            shape.move(dx, dy);
        }
    }

    /**
     * Is contained boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean isContained(Point p) {
        for (Graph graph : getGraphs()) {
            if(graph.isContained(p)) return true;
        }
        for (Graph shape : getGraphs()) {
            if(shape.isContained(p)) return true;
        }
        return false;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
// -------------- standardize ----------------
    public String getName() {
        return name;
    }

    /**
     * Get zcode int.
     *
     * @return the int
     */
    public int getZcode(){
        return zcode;
    }

    /**
     * Gets shapes.
     *
     * @return the shapes
     */
    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    /**
     * Gets graphs.
     *
     * @return the graphs
     */
    public ArrayList<Graph> getGraphs() {
        return graphs;
    }

    /**
     * Sets zcode.
     *
     * @param zcode the zcode
     */
    public void setZcode(int zcode) {
        this.zcode = zcode;
    }

    /**
     * Gets min.
     *
     * @return the min
     */
    public double getxMin() {
        return xMin;
    }

    /**
     * Sets min.
     *
     * @param xMin the x min
     */
    public void setxMin(double xMin) {
        this.xMin = xMin;
    }

    /**
     * Gets max.
     *
     * @return the max
     */
    public double getxMax() {
        return xMax;
    }

    /**
     * Sets max.
     *
     * @param xMax the x max
     */
    public void setxMax(double xMax) {
        this.xMax = xMax;
    }

    /**
     * Gets min.
     *
     * @return the min
     */
    public double getyMin() {
        return yMin;
    }

    /**
     * Sets min.
     *
     * @param yMin the y min
     */
    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    /**
     * Gets max.
     *
     * @return the max
     */
    public double getyMax() {
        return yMax;
    }

    /**
     * Sets max.
     *
     * @param yMax the y max
     */
    public void setyMax(double yMax) {
        this.yMax = yMax;
    }
}

