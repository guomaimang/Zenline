package hk.edu.polyu.comp.comp2021.clevis.model;

public class Line extends Graph{
    Point location2;

    public Line(Point p1,Point p2){
        location = p1;
        location2 = p2;

    }

    @Override
    public boolean isContained(Point p) {
        return false;
    }

    @Override
    public void listSelf() {
    }

    @Override
    public void move(double dx, double dy) {

    }

    @Override
    public boolean isIntersected(Rectangle that) {
        return false;
    }

    @Override
    public boolean isIntersected(Circle that) {
        return false;
    }

    @Override
    public boolean isIntersected(Line that) {
        return false;
    }

    @Override
    public boolean isIntersected(Square that) {
        return false;
    }
}
