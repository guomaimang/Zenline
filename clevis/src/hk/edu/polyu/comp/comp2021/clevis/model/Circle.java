package hk.edu.polyu.comp.comp2021.clevis.model;

public class Circle extends Graph{
    private final double r;

    // @JiaoZhiyang, checked and modify by HanJiaming
    public Circle(String name, Point p,double r){
        this.name = name;
        location = p;
        this.r = r;
        update();
    }

    @Override
    protected void update() {
        xMin = location.getX() - getR();
        xMax = location.getX() + getR();
        yMin = location.getY() - getR();
        yMax = location.getY() + getR();
    }

    @Override
    // @JiaoZhiyang, checked and modify by HanJiaming
    public boolean isContained(Point p) {
        // error < 0.05 can be patience
        double y=(getLocation().getY() - p.getY());
        double x=(getLocation().getX() - p.getX());
        double temp = Math.sqrt(x * x + y * y);
        return Math.abs(getR() - temp) < getError();
    }

    @Override
    // @HanJiaming
    public String listSelf(int indentation) {
        String outcome = "";
        for (int i = 0; i < indentation; i++) {
            outcome = outcome + "   ";
        }
        return outcome + ("Circle"+ " Name: " + name +" R: "+ String.format("%.2f", getR()) +" Round Point: " + "x= " + String.format("%.2f", location.getX())  + " y= " + String.format("%.2f", location.getY()));
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
        double deltaY=Math.abs(that.getLocation().getY() - getLocation().getY());
        double deltaX=Math.abs(that.getLocation().getX() - getLocation().getX());
        double delta=Math.sqrt(deltaY*deltaY+deltaX*deltaX);
        double deltaMax= getR() + that.getR();
        return !(delta > deltaMax);
    }

    private boolean inCircle(Point p){
        return (p.getX() - location.getX()) * (p.getX() - location.getX()) + (p.getY() - location.getY()) * (p.getY() - location.getY()) - getR() * getR() <= 0;

    }
    @Override
    // @JiaoZhiyang ,checked and modify by HanJiaming
    public boolean isIntersected(Line that) {

        Point p1 = new Point(that.getLocation().getX(), that.getLocation().getY());
        Point p2 = new Point(that.getLocation2().getX(), that.getLocation2().getY());

        if (inCircle(p1) && inCircle(p2)) return false;
        if (!inCircle(p1) && inCircle(p2)) return true;
        if (inCircle(p1) && !inCircle(p2)) return true;

        double u,v,w,d1,d2,a1,a2;
        double x = location.getX();
        double y = location.getY();

        if(p1.getX() == p2.getX()) {u=1;v=0;w= -p1.getX();}
        else if(p1.getY() == p2.getY()) {u=0;v=1;w= -p1.getY();}
        else
        {
            u = p1.getY() - p2.getY();
            v = p2.getX() - p1.getX();
            w = p1.getX() * p2.getY() - p1.getY() * p2.getX();
        }
        d1 = u * x + v * y + w;
        d1 *= d1;
        d2 = (u * u + v * v) * getR() * getR();
        if (d1 > d2) return false;
        a1 = (x - p1.getX()) * (p2.getX() - p1.getX()) + (y - p1.getY()) * (p2.getY() - p1.getY());
        a2 = (x - p2.getX()) * (p1.getX() - p2.getX()) + (y - p2.getY()) * (p1.getY() - p2.getY());
        return a1 > 0 && a2 > 0;
    }

    @Override
    // @HanJiaming
    public boolean isIntersected(Square that) {
        return isIntersected(new Rectangle("",that.getLocation(), that.getWidth(), that.getHeight()));
    }

    public double getR() {
        return r;
    }
}
