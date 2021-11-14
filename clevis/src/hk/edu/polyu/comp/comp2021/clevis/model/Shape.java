package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;

public class Shape {

    private final String name;
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private final ArrayList<Graph> graphs = new ArrayList<>();
    private int zcode = 0;
    private double xMin,xMax,yMin,yMax;

    public Shape(String name,ArrayList<String> shapeList){
        this.name = name;
        for (String s : shapeList) {
            // make sure that it is a graph or shape
            if (Clevis.findGraph(s) != null) {
                graphs.add(Clevis.findGraph(s));
            }
            else if (Clevis.findShape(s) != null) {
                shapes.add(Clevis.findShape(s));
            }
        }
        update();
    }
    private void update(){
        // check if empty
        if(shapes.size()+ graphs.size() == 0){
            deleteSelf();
            System.out.println(this.name +" has been empty,it will be deleted!");
            return;
        }

        // update zcode
        int num = 0;
        for (Shape s : shapes) {
            num = Math.max(s.getZcode(), num);
        }
        for (Graph g :graphs){
            num = Math.max(g.getZcode(), num);
        }
        zcode = num;

        // update arg
        if (shapes.size() != 0){
            xMax = shapes.get(0).xMax;
            xMin = shapes.get(0).xMin;
            yMax = shapes.get(0).yMax;
            yMin = shapes.get(0).yMin;
        }else {
            xMax = graphs.get(0).xMax;
            xMin = graphs.get(0).xMin;
            yMax = graphs.get(0).yMax;
            yMin = graphs.get(0).yMin;
        }
        for(Graph graph:graphs){
            xMax = Math.max(xMax,graph.xMax);
            xMin = Math.min(xMin,graph.xMin);
            yMax = Math.max(yMax,graph.yMax);
            yMin = Math.min(yMin,graph.yMin);
        }
        for(Shape shape:shapes){
            xMax = Math.max(xMax,shape.xMax);
            xMin = Math.min(xMin,shape.xMin);
            yMax = Math.max(yMax,shape.yMax);
            yMin = Math.min(yMin,shape.yMin);
        }
    }

    public void listSelf() {
        // First, Print self
        System.out.println("Shapes:");
        for (Shape shape : shapes) {
                System.out.println(shape.getName() + " ");
        }

        System.out.println("Graphs:");
        for (Graph graph : graphs) {
            System.out.println(graph.getName() + " ");
        }

    }
    public void ungroup(){
        shapes.clear();
        graphs.clear();
        if (Clevis.isShapeInShape(this.getName())){
            Shape s = Clevis.findShapeInShape(this.getName());
            s.remove(this);
        }

    }

    public void boundingbox(){
        System.out.println(xMin + " " + yMax + " " + (xMax-xMin) + " " +(yMax-yMin));
    }
    public String getName() {
        return name;
    }
    public int getZcode(){
        return zcode;
    }


    public boolean isIntersected(Shape shape) {
        return false;
    }
    public boolean isIntersected(Graph graph){
        return false;
    }

    public void move(double dx, double dy) {
        for (Graph graph : graphs) {
            graph.move(dx, dy);
        }
        for (Shape shape : shapes) {
            shape.move(dx, dy);
        }
    }
    public boolean contain(Shape s){
        return shapes.contains(s);
    }
    public boolean contain(Graph g){
        return graphs.contains(g);
    }

    public void deleteSelf() {
        for (Graph graph:graphs)
            Clevis.innerDelete(graph);
        graphs.clear();
        for (Shape shape:shapes)
            Clevis.innerDelete(shape);
        shapes.clear();
        Clevis.innerDelete(this);
    }
    public void delete(Graph g){
        graphs.remove(g);
        update();
    }
    public void delete(Shape s){
        shapes.remove(s);
        update();
    }
    public void remove(Shape s){
        shapes.remove(s);
    }

    public boolean isContained(Point p) {
        for (Graph graph : graphs) {
            if(graph.isContained(p)) return true;
        }
        for (Graph shape : graphs) {
            if(shape.isContained(p)) return true;
        }
        return false;
    }
}

