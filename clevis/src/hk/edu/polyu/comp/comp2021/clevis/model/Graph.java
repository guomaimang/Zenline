package hk.edu.polyu.comp.comp2021.clevis.model;

public abstract class Graph {
    protected String name;
    protected Point location;
    final double error = 0.05;
    protected final int zcode;

    protected double xMin;
    protected double xMax;
    protected double yMin;
    protected double yMax;
    private static int num = -1;

    public Graph(){
        zcode = num++;
    }

    public void move(double dx,double dy){
        location = (new Point(dx+ getLocation().x,dy+ getLocation().y));
        this.update();
    }
    protected abstract void update();

    public abstract boolean isContained(Point p);
    public abstract void listSelf();

    public boolean isIntersected(Graph that){
        // force convert to child
        Line l; Circle c; Square s; Rectangle r;
        if(that instanceof Line) {
            l = (Line) that;
            return this.isIntersected(l);
        }
        else if(that instanceof Circle) {
            c = (Circle) that;
            return this.isIntersected(c);
        }
        else if(that instanceof Square)  {
            s = (Square) that;
        return this.isIntersected(s);
        }
        else if(that instanceof Rectangle) {
            r = (Rectangle) that;
            return this.isIntersected(r);
        }
        else return false;
    }
    public abstract boolean isIntersected(Line that);
    public abstract boolean isIntersected(Circle that);
    public abstract boolean isIntersected(Rectangle that);
    public abstract boolean isIntersected(Square that);
    public void boundingbox(){
        System.out.println(xMin + " " + yMax + " " + (xMax-xMin) + " " +(yMax-yMin));
    }

    // ---------standardization----------------------
    public String getName() {
        return name;
    }
    public Point getLocation() {
        return location;
    }
    public int getZcode() {
        return zcode;
    }
    public double getxMin() {
        return xMin;
    }
    public double getxMax() {
        return xMax;
    }
    public double getyMin() {
        return yMin;
    }
    public double getyMax() {
        return yMax;
    }

}

