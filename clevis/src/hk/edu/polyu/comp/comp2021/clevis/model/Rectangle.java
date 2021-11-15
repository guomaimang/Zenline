package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * Frame By @HanJiaming
 * For COMP2021 Project Only
 */

public class Rectangle extends Graph {
    final double width;
    final double height;

    public Rectangle(String name, Point p, double w, double h) {
        this.name  = name;
        location = p;
        width = w;
        height = h;
        update();
    }
    @Override
    public String listSelf(int indentation) {
        String outcome = new String("");
        for (int i = 0; i < indentation; i++) {
            outcome = outcome + "   ";
        }
        return outcome + ("Rectangle:" +" Name: " + name + " Point(Left-Top): x= " +  String.format("%.2f",location.x) + " y= " + String.format("%.2f",location.y) + " Width= " + String.format("%.2f",width) + " Height= "+ String.format("%.2f",height));
    }

    @Override
    public boolean isIntersected(Rectangle that) {
        return (this.getxMin() > that.getxMax() || that.getxMin() > this.getxMax() ||
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
        return isIntersected(new Rectangle("",that.getLocation(),that.width,that.height));
    }

    @Override
    protected void update() {
        xMin = location.x;
        xMax = (location.x + width);
        yMin = (location.y - height);
        yMax = (location.y);
    }

    @Override
    public boolean isContained(Point p) {
        return new Circle("",p,error).isIntersected(this);
    }
}
