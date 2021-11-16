package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * Frame By @HanJiaming
 * For COMP2021 Project Only
 */

public class Rectangle extends Graph {
    private final double width;
    private final double height;

    public Rectangle(String name, Point p, double w, double h) {
        this.name  = name;
        location = p;
        width = w;
        height = h;
        update();
    }
    @Override
    public String listSelf(int indentation) {
        String outcome = "";
        for (int i = 0; i < indentation; i++) {
            outcome = outcome + "   ";
        }
        return outcome + ("Rectangle:" +" Name: " + name + " Point(Left-Top): x= " +  String.format("%.2f", location.getX()) + " y= " + String.format("%.2f", location.getY()) + " Width= " + String.format("%.2f", getWidth()) + " Height= "+ String.format("%.2f", getHeight()));
    }

    @Override
    public boolean isIntersected(Rectangle that) {
        return !(this.getxMin() > that.getxMax() || that.getxMin() > this.getxMax() ||
                this.getyMin() > that.getyMax() || that.getyMin() > this.getyMax() ||
                (this.getyMax() < that.getyMax() &&  this.getyMin() > that.getyMin() && this.getxMax() < that.getxMax() && this.getxMin() > that.getyMax())||
                (that.getyMax() < this.getyMax() &&  that.getyMin() > this.getyMin() && that.getxMax() < this.getxMax() && that.getxMin() > this.getyMax()));
    }

    @Override
    public boolean isIntersected(Circle that) {
        return that.isIntersected(this);
    }

    @Override
    public boolean isIntersected(Line that) {
        return that.isIntersected(this);
    }

    @Override
    public boolean isIntersected(Square that) {
        return isIntersected(new Rectangle("",that.getLocation(), that.getWidth(), that.getHeight()));
    }

    @Override
    protected void update() {
        xMin = location.getX();
        xMax = (location.getX() + getWidth());
        yMin = (location.getY() - getHeight());
        yMax = (location.getY());
    }

    @Override
    public boolean isContained(Point p) {
        return new Circle("",p, getError()).isIntersected(this);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
