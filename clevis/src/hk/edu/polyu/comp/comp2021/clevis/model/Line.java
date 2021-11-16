package hk.edu.polyu.comp.comp2021.clevis.model;

public class Line extends Graph{
    private Point location2;
    public Point getLocation2(){
        return location2;
    }

    public Line(String name,Point p1,Point p2){
        this.name  = name;
        location = p1;
        setLocation2(p2);
        update();
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
        return outcome+("Line" + " Name: " + name + " Point1: x= " + String.format("%.2f", location.getX()) +" y= " + String.format("%.2f", location.getY()) + " Point2: x= " + String.format("%.2f", getLocation2().getX()) + " y= " + String.format("%.2f", getLocation2().getY()));
    }

    @Override
    // @HanJiaming
    public void move(double dx, double dy) {
        location = (new Point(getLocation().getX() +dx, getLocation().getY() +dy));
        setLocation2(new Point(getLocation2().getX() +dx, getLocation2().getY() +dy));
    }

    @Override
    protected void update() {
        xMin = Math.min(location.getX(), getLocation2().getX());
        xMax = Math.max(location.getX(), getLocation2().getX());
        yMin = Math.max(location.getY(), getLocation2().getY());
        yMax = Math.max(location.getY(), getLocation2().getY());
    }

    @Override
    public boolean isIntersected(Rectangle that) {
        Point a = that.location, b = new Point(that.xMax, that.yMax);
        Point c= new Point(that.xMin, that.yMin), d = new Point(that.xMax, that.yMin);
        return isIntersected(new Line("",a, b)) || isIntersected(new Line("",a, c)) ||
                isIntersected(new Line("",b, d)) || isIntersected(new Line("",c, d));
    }

    @Override
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
        return (x > 0.05 ? 1 : 0) - (x < -0.05 ? 1:0);
    }
    private double cross(Point a, Point b, Point c) { return cha_mul(this.sub(a, c) , this.sub(b, c));}
    private double dot(Point a, Point b, Point c)  { return mul(this.sub(a, c) , this.sub(b, c)); }
    private boolean on_seg(Point a, Point b, Point c) { return sig(cross(a, b, c)) == 0 && sig(dot(a, b, c)) <= 0; }

    // @JiaoZhiyang
    @Override
    public boolean isIntersected(Line that) {
        Point a = location, b = getLocation2(), c = that.location, d = that.getLocation2();
        if(on_seg(c, d, a) || on_seg(c, d, b) || on_seg(a, b, c) || on_seg(a, b, d))
            return true;
        return sig(cross(b, c, a)* cross(b, d, a)) < 0 && sig(cross(d, a, c)*cross(d, b, c)) < 0;
    }

    // @JiaoZhiyang
    @Override
    public boolean isIntersected(Square that) {
        return isIntersected(new Rectangle("",that.getLocation(), that.getWidth(), that.getHeight()));
    }

    public void setLocation2(Point location2) {
        this.location2 = location2;
    }
}
