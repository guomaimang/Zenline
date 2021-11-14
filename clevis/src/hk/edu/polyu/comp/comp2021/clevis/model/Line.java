package hk.edu.polyu.comp.comp2021.clevis.model;

public class Line extends Graph{
    private Point location2;

    public Line(String name,Point p1,Point p2){
        this.name  = name;
        location = p1;
        location2 = p2;
        update();
    }

    @Override
    public boolean isContained(Point p) {
        return false;
    }

    @Override
    public String listSelf(int indentation) {
        String outcome = new String("");
        for (int i = 0; i < indentation; i++) {
            outcome = outcome + "   ";
        }
        return outcome+("Line" + " Name: " + name + " Point1: x= " + String.format("%.2f",location.x) +" y= " + String.format("%.2f",location.y) + " Point2: x= " + String.format("%.2f",location2.x) + " y= " + String.format("%.2f",location.y));
    }

    @Override
    // @HanJiaming
    public void move(double dx, double dy) {
        location = (new Point(getLocation().x+dx, getLocation().y+dy));
        location2 = new Point(location2.x+dx, location2.y+dy);
    }

    @Override
    protected void update() {
        xMin = Math.min(location.x, location2.x);
        xMax = Math.max(location.x, location2.x);
        yMin = Math.max(location.y, location2.y);
        yMax = Math.max(location.y, location2.y);
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
        Point O = that.location;
        Point p1 = location, p2 = location2;
        double r = that.r;
        double a, b, c, dist1, dist2, angle1, angle2; // ax + by + c = 0;
        if (p1.x == p2.x) {
            a = 1; b = 0;c = -p1.x;//特殊情况判断，分母不能为零
        }
        else if (p1.y == p2.y) {
            a = 0;b = 1; c = -p1.y;//特殊情况判断，分母不能为零
        }
        else {
            a = p1.y - p2.y;
            b = p2.x - p1.x;
            c = p1.x * p2.y - p1.y * p2.x;
        }
        dist1 = a * O.x + b * O.y + c;
        dist1 *= dist1;
        dist2 = (a * a + b * b) * r * r;
        if (dist1 > dist2) return false;//点到直线距离大于半径r
        angle1 = (O.x - p1.x) * (p2.x - p1.x) + (O.y - p1.y) * (p2.y - p1.y);
        angle2 = (O.x - p2.x) * (p1.x - p2.x) + (O.y - p2.y) * (p1.y - p2.y);
        return angle1 > 0 && angle2 > 0;//余弦都为正，则是锐角
    }

    // @JiaoZhiyang,use for isIntersected(Line that)
    private double mul(Point a, Point b) {
        return a.x * b.x + a.y * b.y;
    }
    private double cha_mul(Point a, Point b) {
        return a.x * b.x + a.y * b.y;
    }
    private Point sub(Point a, Point b) {
        return new Point(a.x - b.x, a.y - b.y);
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
        Point a = location, b = location2, c = that.location, d = that.location2;
        if(on_seg(c, d, a) || on_seg(c, d, b) || on_seg(a, b, c) || on_seg(a, b, d))
            return true;
        return sig(cross(b, c, a)* cross(b, d, a)) < 0 && sig(cross(d, a, c)*cross(d, b, c)) < 0;
    }

    // @JiaoZhiyang
    @Override
    public boolean isIntersected(Square that) {
        return isIntersected(new Rectangle("",that.getLocation(),that.width,that.height));
    }
}
