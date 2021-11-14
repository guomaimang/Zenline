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
        updateSelf();
    }
    private void updateSelf(){
        // check if empty
        if(shapes.size()+ graphs.size() == 0){
            removeSelf();
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
        System.out.println("Shapes:");
        for (Shape shape : shapes) {
                System.out.println("Name: " + shape.getName());
                shape.listSelf();
        }

        System.out.println("Graphs:");
        for (Graph graph : graphs) {
            graph.listSelf();
        }

    }
    public void ungroup(){
        shapes.clear();
        graphs.clear();
    }

    public void boundingbox(){
        System.out.println(xMin + " " + yMax + " " + (xMax-xMin) + " " +(yMax-yMin));
    }
    public boolean intersect(Shape s){return false;}
    public boolean intersect(Graph g){return false;}

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

    public void removeSelf() {
        for (Graph graph:graphs)
            Clevis.innerDelete(graph);
        graphs.clear();
        for (Shape shape:shapes)
            Clevis.innerDelete(shape);
        shapes.clear();
    }
    public void remove(Graph g){
        graphs.remove(g);
        updateSelf();
    }
    public void remove(Shape s){
        shapes.remove(s);
        updateSelf();
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

