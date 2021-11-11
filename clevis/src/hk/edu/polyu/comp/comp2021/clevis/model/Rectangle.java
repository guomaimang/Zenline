package hk.edu.polyu.comp.comp2021.clevis.model;

public class Rectangle extends Graph {
    double width;
    double height;

    public Rectangle(String name, Point p, double w, double h) {
        this.name  = name;
        location = p;
        width = w;
        height = h;

        xMin = location.x;
        xMax = (getLocation().x + width);
        yMin = (getLocation().y - height);
        yMax = (getLocation().y);
    }
    @Override
    public void listSelf(){}

    public boolean isIntersected(Rectangle that) {
        return (this.getxMin() > that.getxMax() || that.getxMin() > this.getxMax() ||
                this.getyMin() > that.getyMax() || that.getyMin() > this.getyMax() ||
                (this.getyMax() < that.getyMax() &&  this.getyMin() > that.getyMin() && this.getxMax() < that.getxMax() && this.getxMin() > that.getyMax())||
                (that.getyMax() < this.getyMax() &&  that.getyMin() > this.getyMin() && that.getxMax() < this.getxMax() && that.getxMin() > this.getyMax()));
    }

    public boolean isIntersected(Circle that) {
        return false;
    }

    public boolean isIntersected(Line that) {
        return false;
    }

    public boolean isIntersected(Square that) {
        Rectangle temp = new Rectangle("",that.getLocation(),that.width,that.height);
        return isIntersected(temp);
    }

    @Override
    public boolean isContained(Point p) {
        return getLocation().x - 0.05 < p.x &&
                p.x < getLocation().x + width + 0.05 &&
                p.y < getLocation().y + 0.05 &&
                getLocation().y - height - 0.05 < p.y;
    }
}
