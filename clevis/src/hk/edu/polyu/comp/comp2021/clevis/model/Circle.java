package hk.edu.polyu.comp.comp2021.clevis.model;

public class Circle extends Graph{
    final double r;

    // @JiaoZhiyang, checked and modify by HanJiaming
    public Circle(String name, Point p,double r){
        this.name = name;
        location = p;
        this.r = r;
        update();
    }

    @Override
    protected void update() {
        xMin = location.x - r;
        xMax = location.x + r;
        yMin = location.y - r;
        yMax = location.y + r;
    }

    @Override
    // @JiaoZhiyang, checked and modify by HanJiaming
    public boolean isContained(Point p) {
        // error < 0.05 can be patience
        double y=(getLocation().y-p.y);
        double x=(getLocation().x-p.x);
        double temp = Math.sqrt(x * x + y * y);
        return Math.abs(r - temp) < error;
    }

    @Override
    // @HanJiaming
    public String listSelf(int indentation) {
        String outcome = new String("");
        for (int i = 0; i < indentation; i++) {
            outcome = outcome + "   ";
        }
        return outcome + ("Circle"+ " Name: " + name +" R: "+ String.format("%.2f",r) +" Round Point: " + "x= " + String.format("%.2f",location.x)  + " y= " + String.format("%.2f",location.y));
    }

    @Override
    // @JiaoZhiyang, checked and modify by HanJiaming
    public boolean isIntersected(Rectangle that) {
        Point a = that.getLocation(), b = new Point(that.getxMax(), that.getyMax());
        Point c= new Point(that.getxMin(), that.getyMin()), d = new Point(that.getxMax(), that.getyMin());
        Line L1 = new Line("",a, b), L2 = new Line("",a, c), L3 = new Line("",b, d), L4 = new Line("",c, d);
        //Judging only 4 lines and gardens intersect
        return L1.isIntersected(this) || L2.isIntersected(this)
                || L3.isIntersected(this) || L4.isIntersected(this) ;
    }

    @Override
    // @JiaoZhiyang, checked and modify by HanJiaming
    public boolean isIntersected(Circle that) {
        double deltaY=Math.abs(that.getLocation().y- getLocation().y);
        double deltaX=Math.abs(that.getLocation().x- getLocation().x);
        double delta=Math.sqrt(deltaY*deltaY+deltaX*deltaX);
        double deltaMax=r+that.r;
        return !(delta > deltaMax);
    }

    private boolean inCircle(Point p){
        return (p.x - location.x) * (p.x - location.x) + (p.y - location.y) * (p.y - location.y) - r * r <= 0;

    }
    @Override
    // @JiaoZhiyang ,checked and modify by HanJiaming
    public boolean isIntersected(Line that) {

        Point p1 = new Point(that.getLocation().x,that.getLocation().y);
        Point p2 = new Point(that.getLocation2().x,that.getLocation2().y);

        if (inCircle(p1) && inCircle(p2)) return false;
        if (!inCircle(p1) && inCircle(p2)) return true;
        if (inCircle(p1) && !inCircle(p2)) return true;

        double u,v,w,d1,d2,a1,a2;
        double x = location.x;
        double y = location.y;

        if(p1.x==p2.x) {u=1;v=0;w= -p1.x;}
        else if(p1.y==p2.y) {u=0;v=1;w= -p1.y;}
        else
        {
            u = p1.y-p2.y;
            v = p2.x-p1.x;
            w = p1.x * p2.y - p1.y*p2.x;
        }
        d1 = u * x + v * y + w;
        d1 *= d1;
        d2 = (u * u + v * v) * r * r;
        if (d1 > d2) return false;
        a1 = (x - p1.x) * (p2.x - p1.x) + (y - p1.y) * (p2.y - p1.y);
        a2 = (x - p2.x) * (p1.x - p2.x) + (y - p2.y) * (p1.y - p2.y);
        return a1 > 0 && a2 > 0;
    }

    @Override
    // @HanJiaming
    public boolean isIntersected(Square that) {
        return isIntersected(new Rectangle("",that.getLocation(),that.width,that.height));
    }
}
