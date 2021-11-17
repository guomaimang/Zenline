package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * The type Line.
 */
public class Line extends Graph{
    private static final double DOUBLE1 = 0.05;
    private Point location2;

    /**
     * Get location 2 point.
     *
     * @return the point
     */
    public Point getLocation2(){
        return location2;
    }

    /**
     * Instantiates a new Line.
     *
     * @param name the name
     * @param p1   the p 1
     * @param p2   the p 2
     */
    public Line(String name,Point p1,Point p2){
        this.setName(name);
        setLocation(p1);
        setLocation2(p2);
        update();
    }

    @Override
    public boolean isContained(Point p) {
        return new Circle("",p, getERROR()).isIntersected(this);
    }

    @Override
    public String listSelf(int indentation) {
        String outcome = "";
        for (int i = 0; i < indentation; i++) {
            outcome = outcome + "   ";
        }
        return outcome+("Line" + " Name: " + getName() + " Point1: x= " + String.format("%.2f", getLocation().getX()) +" y= " + String.format("%.2f", getLocation().getY()) + " Point2: x= " + String.format("%.2f", getLocation2().getX()) + " y= " + String.format("%.2f", getLocation2().getY()));
    }

    @Override
    // @HanJiaming
    public void move(double dx, double dy) {
        setLocation((new Point(getLocation().getX() +dx, getLocation().getY() +dy)));
        setLocation2(new Point(getLocation2().getX() +dx, getLocation2().getY() +dy));
    }

    @Override
    protected void update() {
        setxMin(Math.min(getLocation().getX(), getLocation2().getX()));
        setxMax(Math.max(getLocation().getX(), getLocation2().getX()));
        setyMin(Math.max(getLocation().getY(), getLocation2().getY()));
        setyMax(Math.max(getLocation().getY(), getLocation2().getY()));
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
        Point a = that.getLocation(), b = new Point(that.getxMax(), that.getyMax());
        Point c= new Point(that.getxMin(), that.getyMin()), d = new Point(that.getxMax(), that.getyMin());
        return isIntersected(new Line("",a, b)) || isIntersected(new Line("",a, c)) ||
                isIntersected(new Line("",b, d)) || isIntersected(new Line("",c, d));
    }


    /**
     * Is intersected boolean.
     *
     * @param that the that
     * @return the boolean
     */
// @JiaoZhiyang
    public boolean isIntersected(Circle that) {
        return that.isIntersected(this);
    }


    // @JiaoZhiyang,use for isIntersected(Line that)
    private double mul(Point a, Point b) {
        return a.getX() * b.getX() + a.getY() * b.getY();
    }
    private double cha_mul(Point a, Point b) {
        return a.getX() * b.getX() + a.getY() * b.getY();
    }
    private Point sub(Point a, Point b) {
        return new Point(a.getX() - b.getX(), a.getY() - b.getY());
    }
    private static int sig(double x) {
        return (x > DOUBLE1 ? 1 : 0) - (x < -DOUBLE1 ? 1:0);
    }
    private double cross(Point a, Point b, Point c) { return cha_mul(this.sub(a, c) , this.sub(b, c));}
    private double dot(Point a, Point b, Point c)  { return mul(this.sub(a, c) , this.sub(b, c)); }
    private boolean on_seg(Point a, Point b, Point c) { return sig(cross(a, b, c)) == 0 && sig(dot(a, b, c)) <= 0; }


    /**
     * Is intersected boolean.
     *
     * @param that the that
     * @return the boolean
     */
    public boolean isIntersected(Line that) {
        Point a = getLocation(), b = getLocation2(), c = that.getLocation(), d = that.getLocation2();
        if(on_seg(c, d, a) || on_seg(c, d, b) || on_seg(a, b, c) || on_seg(a, b, d))
            return true;
        return sig(cross(b, c, a)* cross(b, d, a)) < 0 && sig(cross(d, a, c)*cross(d, b, c)) < 0;
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

    /**
     * Sets location 2.
     *
     * @param location2 the location 2
     */
    public void setLocation2(Point location2) {
        this.location2 = location2;
    }
}
