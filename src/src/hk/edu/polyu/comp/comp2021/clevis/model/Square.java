package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * The type Square.
 */
public class Square extends Graph{
    private final double width;
    private final double height;

    /**
     * Instantiates a new Square.
     *
     * @param name the name
     * @param p    the p
     * @param l    the l
     */
    public Square(String name, Point p, double l) {
        this.setName(name);
        setLocation(p);
        width = l;
        height = l;
        update();
    }

    @Override
    public boolean isIntersected(Graph that){
        // force convert to child
        Line l;
        if (that instanceof Line) {
            l = (Line) that;
            return this.isIntersected(l);
        }
        Circle c;
        if(that instanceof Circle) {
            c = (Circle) that;
            return this.isIntersected(c);
        }
        Square s;
        if(that instanceof Square)  {
            s = (Square) that;
            return this.isIntersected(s);
        }
        Rectangle r;
        if(that instanceof Rectangle) {
            r = (Rectangle) that;
            return this.isIntersected(r);
        }
        else return false;
    }

    /**
     * Is intersected boolean.
     *
     * @param that the that
     * @return the boolean
     */
    public boolean isIntersected(Rectangle that) {
        return that.isIntersected(this);
    }


    /**
     * Is intersected boolean.
     *
     * @param that the that
     * @return the boolean
     */
    public boolean isIntersected(Circle that) {
        return that.isIntersected(this);
    }


    /**
     * Is intersected boolean.
     *
     * @param that the that
     * @return the boolean
     */
    public boolean isIntersected(Line that) {
        return that.isIntersected(this);
    }


    /**
     * Is intersected boolean.
     *
     * @param that the that
     * @return the boolean
     */
    public boolean isIntersected(Square that) {
        return isIntersected(new Rectangle("",that.getLocation(), that.getWidth(), that.getHeight()));
    }

    @Override
    protected void update() {
        setxMin((getLocation().getX()));
        setxMax((getLocation().getX() + getWidth()));
        setyMin((getLocation().getY() - getHeight()));
        setyMax((getLocation().getY()));
    }

    @Override
    public boolean isContained(Point p) {
        return new Circle("",p, getERROR()).isIntersected(this);
    }

    @Override
    public String listSelf(int indentation) {
        return "   ".repeat(Math.max(0, indentation)) + ( "Square" + "Name: " + getName() + " Point(Left-Top): x= " +  String.format("%.2f", getLocation().getX()) + " y= " + String.format("%.2f", getLocation().getY()) + " Width= " + String.format("%.2f", getWidth()));

    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }
}
