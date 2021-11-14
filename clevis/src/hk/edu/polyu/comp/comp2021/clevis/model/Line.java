package hk.edu.polyu.comp.comp2021.clevis.model;

public class Line extends Graph{
<<<<<<< HEAD
    private Point location2;

    public Line(String name,Point p1,Point p2){
        this.name  = name;
        location = p1;
        location2 = p2;
        xMin = Math.min(p1.x, p2.x);
        xMax = Math.max(p1.x, p2.x);
        yMin = Math.max(p1.y, p2.y);
        yMax = Math.max(p1.y, p2.y);
=======
    Point location2;
    final double pi = 0.05;//误差
    public Line(Point p1,Point p2){
        location = p1;//点1
        location2 = p2;//点2

>>>>>>> JiaoZhiyang
    }

    @Override
    //判断一个图形是否包含一个点
    public boolean isContained(Point p) {
        Circle c = new Circle(p, 0.05);
        return c.isIntersected(this);
    }

    @Override
    //Lists the basic information about the shape named n. For each simple
    //shape, lists the types of information used to construct the shape. For instance, lists
    //the name, the center, and the radius of a circle. For each group shape, lists the
    //name of the group and all the shapes directly contained in the group. All floating
    //point numbers should be rounded to 2 decimal places.
    // 翻译：列出关于名为n的形状的基本信息。对于每个简单的形状，列出了用于构造该形状的信息类型。例如，列出了一个圆的名称、中心和半径。
    // 对于每个组形状，列出组的名称和直接包含在组中的所有形状。所有的浮点数都应该是四舍五入到小数点后2位。
    public void listSelf() {
<<<<<<< HEAD
        System.out.printf("Line: Point1(%f, %f), Point2(%f, %f)", location.x, location.y, location2.x, location2.y);
=======
        System.out.printf("Line: Point1(%f, %f), Point1(%f, %f)", location.x, location.y, location2.x, location2.y);
>>>>>>> JiaoZhiyang
    }

    @Override
    // @HanJiaming
    public void move(double dx, double dy) {
<<<<<<< HEAD
        location = (new Point(getLocation().x+dx, getLocation().y+dy));
        location2 = new Point(location2.x+dx, location2.y+dy);
=======
        location = new Point(dx+location.x,dy+location.y);
        location2 = new Point(dx+location2.x,dy+location2.y);
>>>>>>> JiaoZhiyang
    }

    @Override
    //以下方法用于判断连个图形是否相交
    public boolean isIntersected(Rectangle that) {
<<<<<<< HEAD
        Point a = that.location, b = new Point(that.xMax, that.yMax);
        Point c= new Point(that.xMin, that.yMin), d = new Point(that.xMax, that.yMin);
        return isIntersected(new Line("",a, b)) || isIntersected(new Line("",a, c)) ||
                isIntersected(new Line("",b, d)) || isIntersected(new Line("",c, d));
=======
        //转化为判断与4条边是否相交
        Point a = that.location, b = new Point(that.xMax, that.yMax);
        Point c= new Point(that.xMin, that.yMin), d = new Point(that.xMax, that.yMin);
        return isIntersected(new Line(a, b)) || isIntersected(new Line(a, c)) ||
                isIntersected(new Line(b, d)) || isIntersected(new Line(c, d));
>>>>>>> JiaoZhiyang
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
<<<<<<< HEAD
        return angle1 > 0 && angle2 > 0;//余弦都为正，则是锐角
=======
        if (angle1 > 0 && angle2 > 0) return true;//余弦都为正，则是锐角
        return false;
>>>>>>> JiaoZhiyang
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
<<<<<<< HEAD
=======
//        Point a = location, b = location2, c = that.location, d = that.location2;
//        if(!(Math.min(a.x,b.x)<=Math.max(c.x,d.x)
//                &&Math.min(c.y,d.y)<=Math.max(a.y,b.y)
//                &&Math.min(c.x,d.x)<=Math.max(a.x,b.x)
//                &&Math.min(a.y,b.y)<=Math.max(c.y,d.y)))
//            return false;
//        double u,v,w,z;
//        u=(c.x-a.x)*(b.y-a.y)-(b.x-a.x)*(c.y-a.y);
//        v=(d.x-a.x)*(b.y-a.y)-(b.x-a.x)*(d.y-a.y);
//        w=(a.x-c.x)*(d.y-c.y)-(d.x-c.x)*(a.y-c.y);
//        z=(b.x-c.x)*(d.y-c.y)-(d.x-c.x)*(b.y-c.y);
//        return (u*v<=pi&&w*z<=pi);
>>>>>>> JiaoZhiyang
        Point a = location, b = location2, c = that.location, d = that.location2;
        if(on_seg(c, d, a) || on_seg(c, d, b) || on_seg(a, b, c) || on_seg(a, b, d))
            return true;
        return sig(cross(b, c, a)* cross(b, d, a)) < 0 && sig(cross(d, a, c)*cross(d, b, c)) < 0;
    }

    // @JiaoZhiyang
    @Override
    public boolean isIntersected(Square that) {
<<<<<<< HEAD
        return isIntersected(new Rectangle("",that.getLocation(),that.width,that.height));
=======
        //转化为判断与4条边是否相交
        Point a = that.location, b = new Point(that.xMax, that.yMax);
        Point c= new Point(that.xMin, that.yMin), d = new Point(that.xMax, that.yMin);
        return isIntersected(new Line(a, b)) || isIntersected(new Line(a, c)) ||
                isIntersected(new Line(b, d)) || isIntersected(new Line(c, d));
    }

    double mul(Point a, Point b) {
        return a.x * b.x + a.y * b.y;
    }
    double cha_mul(Point a, Point b) {
        return a.x * b.x + a.y * b.y;
    }
    Point sub(Point a, Point b) {
        return new Point(a.x - b.x, a.y - b.y);
    }
    static int sig(double x) {
        int i = (x > 0.05 ? 1 : 0) - (x < -0.05 ? 1:0);
        return i;
>>>>>>> JiaoZhiyang
    }
    double cross(Point a, Point b, Point c) { return cha_mul(this.sub(a, c) , this.sub(b, c));}
    double dot(Point a, Point b, Point c)  { return mul(this.sub(a, c) , this.sub(b, c)); }
    boolean on_seg(Point a, Point b, Point c) { return sig(cross(a, b, c)) == 0 && sig(dot(a, b, c)) <= 0; }//判断是否在点上
}
