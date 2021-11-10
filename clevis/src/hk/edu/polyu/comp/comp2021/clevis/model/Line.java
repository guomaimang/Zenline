package hk.edu.polyu.comp.comp2021.clevis.model;

public class Line extends Graph{
    Point location2;

    public Line(String name,Point p1,Point p2){
        this.name  = name;
        location = p1;
        location2 = p2;
        xMin = Math.min(p1.x, p2.x);
        xMax = Math.max(p1.x, p2.x);
        yMin = Math.max(p1.y, p2.y);
        yMax = Math.max(p1.y, p2.y);
    }

    @Override
    public boolean isContained(Point p) {
        return false;
    }

    @Override
    public void listSelf() {
        System.out.printf("Line: Point1(%f, %f), Point1(%f, %f)", getLocation().x, getLocation().y, location2.x, location2.y);
    }

    @Override
    // @HanJiaming
    public void move(double dx, double dy) {
        location = (new Point(getLocation().x+dx, getLocation().y+dy));
        location2 = new Point(location2.x+dx, location2.y+dy);
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
