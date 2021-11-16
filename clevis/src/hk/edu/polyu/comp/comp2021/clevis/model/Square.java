package hk.edu.polyu.comp.comp2021.clevis.model;

public class Square extends Graph{
    private final double width;
    private final double height;

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
        return isIntersected(new Rectangle("",that.getLocation(), that.getWidth(), that.getHeight()));
    }

    @Override
    protected void update() {
        xMin = (getLocation().getX());
        xMax = (getLocation().getX() + getWidth());
        yMin = (getLocation().getY() - getHeight());
        yMax = (getLocation().getY());
    }

    @Override
    public boolean isContained(Point p) {
        return new Circle("",p, getError()).isIntersected(this);
    }

    @Override
    public String listSelf(int indentation) {
        String outcome = "";
        for (int i = 0; i < indentation; i++) {
            outcome = outcome + "   ";
        }
        return outcome + ( "Square" + "Name: " + name + " Point(Left-Top): x= " +  String.format("%.2f", location.getX()) + " y= " + String.format("%.2f", location.getY()) + " Width= " + String.format("%.2f", getWidth()));

    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
