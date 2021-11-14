package hk.edu.polyu.comp.comp2021.clevis.model;

import javax.lang.model.element.Name;

public class Square extends Graph{
    final double width;
    final double height;

    public Square(String name, Point p, double l) {
        this.name = name;
        location = p;
        width = l;
        height = l;
        update();
    }

    @Override
    public boolean isIntersected(Rectangle that) {
        return that.isIntersected(this);
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
        xMin = (getLocation().x);
        xMax = (getLocation().x + width);
        yMin = (getLocation().y - height);
        yMax = (getLocation().y);
    }

    @Override
    public boolean isContained(Point p) {
        return getLocation().x - 0.05 < p.x &&
                p.x < getLocation().x + width + 0.05 &&
                p.y < getLocation().y + 0.05 &&
                getLocation().y - height - 0.05 < p.y;
    }

    @Override
    public String listSelf(int indentation) {
        String outcome = new String("");
        for (int i = 0; i < indentation; i++) {
            outcome = outcome + "   ";
        }
        return outcome + ( "Square" + "Name: " + name + " Point(Left-Top): x= " +  location.x + " y= " + location.y + " Width= " + width);

    }
}
