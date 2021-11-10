package hk.edu.polyu.comp.comp2021.clevis.model;

public class Square extends Graph{
    double width;
    double height;

    public Square(String name, Point p, double l) {
        this.name = name;
        location = p;
        width = l;
        height = l;

        xMin = (getLocation().x);
        xMax = (getLocation().x + width);
        yMin = (getLocation().y - height);
        yMax = (getLocation().y);
    }

    @Override
    public boolean isIntersected(Rectangle that) {
        return that.isIntersected(this);
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
        Rectangle temp = new Rectangle("",that.getLocation(),that.width,that.height);
        return temp.isIntersected(this);
    }

    @Override
    public boolean isContained(Point p) {
        return getLocation().x - 0.05 < p.x &&
                p.x < getLocation().x + width + 0.05 &&
                p.y < getLocation().y + 0.05 &&
                getLocation().y - height - 0.05 < p.y;
    }

    @Override
    public void listSelf() {

    }
}
