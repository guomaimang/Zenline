package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;

public class Shape {

    private final String name;
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private final ArrayList<Graph> graphs = new ArrayList<>();
    public Shape(String name,ArrayList<String> shapeList){
        this.name = name;
        for (String s : shapeList) {
            // make sure that it is a graph or shape
            if (Clevis.findGraph(s) != null) graphs.add(Clevis.findGraph(s));
            if (Clevis.findShape(s) != null) shapes.add(Clevis.findShape(s));
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
        double xMin,xMax,yMin,yMax;
    }
    public boolean intersect(Shape s){return false;}
    public boolean intersect(Graph g){return false;}


    //---------------standardize-----------------
    public String getName() {
        return name;
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
        for (Graph shape : graphs) {
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
        for (Graph graph:graphs){
            graphs.clear();
        }
        for (Shape shape : shapes) {
            shape.removeSelf();
        }

    }
    public void remove(Shape s){
        if (contain(s)) remove(s);
    }
    public void remove(Graph g){
        if (contain(g)) remove(g);
    }
}

