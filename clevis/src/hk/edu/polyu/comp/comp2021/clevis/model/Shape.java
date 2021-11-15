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
    public void update(){
        // check if empty
        if(shapes.size()+ graphs.size() == 0){
            Clevis.delete(this.name);
            System.out.println(this.name +" will be deleted!");
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

    public void listSelf(int indentation){
        String prestr = new String("");
        for (int i = 0; i < indentation; i++) {
            prestr = prestr + "   ";
        }
        System.out.println(prestr + "Group Name: " + name);
        // Print Graph
        System.out.println(prestr + "   " + "Graphs:");
        for (Graph graph : graphs) {
            System.out.println(graph.listSelf(indentation+2));
        }
        // Print Shape
        System.out.println(prestr + "   " + "Shapes:");
        for (Shape shape : shapes) {
            shape.listSelf(indentation+2);
        }
    }
    public void ungroup(){
        shapes.clear();
        graphs.clear();
        if (Clevis.isShapeInShape(this.getName())){
            Shape s = Clevis.findShapeInShape(this.getName());
            s.ungroup(this);
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
        for (Shape shape:shapes){
            Clevis.innerDelete(shape);
            shape.deleteSelf();
        }
        shapes.clear();
    }
    public void innerRemove(Graph g){
        graphs.remove(g);
        update();
    }
    public void innerRemove(Shape s){
        shapes.remove(s);
        update();
    }
    private void ungroup(Shape s){
        shapes.remove(s);
        update();
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

