package hk.edu.polyu.comp.comp2021.clevis.model;

public abstract class Graph {
    String name;
    Point location;

    double xMin;
    double xMax;
    double yMin;
    double yMax;

    private boolean isDelete;
    int zcode;

    public void move(double dx,double dy){
        location = new Point(dx+location.x,dy+location.y);
    }
    public abstract boolean isContained(Point p);

    public void doDelete(){
        isDelete = true;
    }
    public void recover(){
        isDelete = false;
    }

    public abstract boolean isIntersected(Rectangle that);
    public abstract boolean isIntersected(Circle that);
    public abstract boolean isIntersected(Line that);
    public abstract boolean isIntersected(Square that);

}

