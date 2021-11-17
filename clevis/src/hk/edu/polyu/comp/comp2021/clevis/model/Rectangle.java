package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * Frame By @HanJiaming
 * For COMP2021 Project Only
 */
public class Rectangle extends Graph {
    private final double width;
    private final double height;

    /**
     * Instantiates a new Rectangle.
     *
     * @param name the name
     * @param p    the p
     * @param w    the w
     * @param h    the h
     */
    public Rectangle(String name, Point p, double w, double h) {
        this.setName(name);
        setLocation(p);
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
        return outcome + ("Rectangle:" +" Name: " + getName() + " Point(Left-Top): x= " +  String.format("%.2f", getLocation().getX()) + " y= " + String.format("%.2f", getLocation().getY()) + " Width= " + String.format("%.2f", getWidth()) + " Height= "+ String.format("%.2f", getHeight()));
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
        return !(this.getxMin() > that.getxMax() || that.getxMin() > this.getxMax() ||
                this.getyMin() > that.getyMax() || that.getyMin() > this.getyMax() ||
                (this.getyMax() < that.getyMax() &&  this.getyMin() > that.getyMin() && this.getxMax() < that.getxMax() && this.getxMin() > that.getyMax())||
                (that.getyMax() < this.getyMax() &&  that.getyMin() > this.getyMin() && that.getxMax() < this.getxMax() && that.getxMin() > this.getyMax()));
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
        setxMin(getLocation().getX());
        setxMax((getLocation().getX() + getWidth()));
        setyMin((getLocation().getY() - getHeight()));
        setyMax((getLocation().getY()));
    }

    @Override
    public boolean isContained(Point p) {
        return new Circle("",p, getERROR()).isIntersected(this);
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
