package hk.edu.polyu.comp.comp2021.clevis.model;

public class Rectangle extends Graph {
    double width;
    double height;

    public Rectangle(Point p, double w, double h) {
        location = p;
        width = w;
        height = h;

        xMin = location.x;
        xMax = location.x + width;
        yMin = location.y - height;
        yMax = location.y;
    }
    @Override
    public void listSelf(){}

    @Override
    public boolean isIntersected(Rectangle that) {
        return (this.xMin > that.xMax || that.xMin > this.xMax ||
                this.yMin > that.yMax || that.yMin > this.yMax ||
                (this.yMax < that.yMax &&  this.yMin > that.yMin && this.xMax < that.xMax && this.xMin > that.yMax)||
                (that.yMax < this.yMax &&  that.yMin > this.yMin && that.xMax < this.xMax && that.xMin > this.yMax));
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
        return (this.xMin > that.xMax || that.xMin > this.xMax ||
                this.yMin > that.yMax || that.yMin > this.yMax ||
                (this.yMax < that.yMax &&  this.yMin > that.yMin && this.xMax < that.xMax && this.xMin > that.yMax)||
                (that.yMax < this.yMax &&  that.yMin > this.yMin && that.xMax < this.xMax && that.xMin > this.yMax));
    }

    @Override
    public boolean isContained(Point p) {
        return location.x - 0.05 < p.x &&
                p.x < location.x + width + 0.05 &&
                p.y < location.y + 0.05 &&
                location.y - height - 0.05 < p.y;
    }
}
