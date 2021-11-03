package hk.edu.polyu.comp.comp2021.clevis.model;

public class Circle extends Graph{
    @Override
    public boolean isContained(Point p) {
        return false;
    }

    @Override
    public void listSelf() {
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
