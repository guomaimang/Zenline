package hk.edu.polyu.comp.comp2021.clevis.model;

public class Line extends Graph{
    Point location2;
    final double pi = 0.05;//误差
    public Line(Point p1,Point p2){
        location = p1;//点1
        location2 = p2;//点2

    }

    @Override
    //判断一个图形是否包含一个点
    public boolean isContained(Point p) {
        //判断一条线是否包含一个点
        double k = (location.y - location2.y) / (location.x - location2.x);
        double b = location.y - k * location.x;
        //y = kx + b
        if (Math.abs(p.x * k + b - p.y)  < pi) {
            return p.x >= Math.min(location.x, location2.x) && p.x <= Math.max(location.x, location2.x);
        }
        //否则点不在线上
        return false;
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
        System.out.printf("Line: Point1(%f, %f), Point1(%f, %f)", location.x, location.y, location2.x, location2.y);
    }

    @Override
    public void move(double dx, double dy) {
        location = new Point(dx+location.x,dy+location.y);
        location2 = new Point(dx+location2.x,dy+location2.y);
    }

    @Override
    //以下方法用于判断连个图形是否相交
    public boolean isIntersected(Rectangle that) {
        //转化为判断与4条边是否相交
        Point a = that.location, b = new Point(that.xMax, that.yMax);
        Point c= new Point(that.xMin, that.yMin), d = new Point(that.xMax, that.yMin);
        return isIntersected(new Line(a, b)) || isIntersected(new Line(a, c)) ||
                isIntersected(new Line(b, d)) || isIntersected(new Line(c, d));
    }

    @Override
    public boolean isIntersected(Circle that) {
        Point c = that.location, L1 = location, L2 = location2;
        double r = that.r;
        double t1 = dist(c, L1) - r, t2 = dist(c, L2) - r;
        Point t = c;
        if(t1 < pi || t2 < pi) return t1 > -pi || t2 > -pi;
        t = new Point(L1.y - L2.y, L2.x - L1.x);
//        t.x += L1.y - L2.y;
//        t.y += L2.x - L1.x;
        return mul(L1, c, t) * mul(L2, c, t) < pi && p_to_line(c, L1, L2) - r < pi;
    }

    @Override
    public boolean isIntersected(Line that) {
        Point a = location, b = location2, c = that.location, d = that.location2;
        if(!(Math.min(a.x,b.x)<=Math.max(c.x,d.x)
                &&Math.min(c.y,d.y)<=Math.max(a.y,b.y)
                &&Math.min(c.x,d.x)<=Math.max(a.x,b.x)
                &&Math.min(a.y,b.y)<=Math.max(c.y,d.y)))
            return false;
        double u,v,w,z;
        u=(c.x-a.x)*(b.y-a.y)-(b.x-a.x)*(c.y-a.y);
        v=(d.x-a.x)*(b.y-a.y)-(b.x-a.x)*(d.y-a.y);
        w=(a.x-c.x)*(d.y-c.y)-(d.x-c.x)*(a.y-c.y);
        z=(b.x-c.x)*(d.y-c.y)-(d.x-c.x)*(b.y-c.y);
        return (u*v<=pi&&w*z<=pi);
    }

    @Override
    public boolean isIntersected(Square that) {
        //转化为判断与4条边是否相交
        Point a = that.location, b = new Point(that.xMax, that.yMax);
        Point c= new Point(that.xMin, that.yMin), d = new Point(that.xMax, that.yMin);
        return isIntersected(new Line(a, b)) || isIntersected(new Line(a, c)) ||
                isIntersected(new Line(b, d)) || isIntersected(new Line(c, d));
    }
    double mul(Point p1, Point p2, Point p0){
        return (p1.x-p0.x)*(p2.y-p0.y)-(p2.x-p0.x)*(p1.y-p0.y);
    }
    double dist(Point p1, Point p2){
        return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
    }
    double p_to_line(Point p, Point L1, Point L2){
        return Math.abs(mul(p, L1, L2)) / dist(L1, L2);
    }

}
