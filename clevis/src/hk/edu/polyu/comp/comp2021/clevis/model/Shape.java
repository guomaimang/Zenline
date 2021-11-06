package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;

public class Shape {

    public String name;
    public boolean isDelete;
    public boolean isGroup;
    public ArrayList<Shape> shapes = new ArrayList<>();
    public ArrayList<Graph> graphs = new ArrayList<>();

    public void listSelf() {}

    public void group(String[] shapeList) {
        for (int i = 0; i < shapeList.length - 1; i++) {
            if (Clevis.findGraph(shapeList[i]) != null) graphs.add(Clevis.findGraph(shapeList[i]));
            if (Clevis.findShape(shapeList[i]) != null) shapes.add(Clevis.findShape(shapeList[i]));
        }
        isGroup = true;
    }
    public void ungroup(){
        isGroup = false;
    }
    public void boundingbox(){}
    public boolean intersect(Shape s){return false;}
    public boolean intersect(Graph g){return false;}
}

