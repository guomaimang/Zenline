package hk.edu.polyu.comp.comp2021.clevis.model;

import java.util.ArrayList;

public class Shape {

    public String name;
    public boolean isDelete;
    public ArrayList<Graph> graphs = new ArrayList<>();

    public void listSelf() {}
    public Shape(String[] graphList){
        for (int i = 0; i < graphList.length-1; i++) {
            if(Clevis.findGraph(graphList[i]) != null) graphs.add(Clevis.findGraph(graphList[i]));
            }
        }

    }

