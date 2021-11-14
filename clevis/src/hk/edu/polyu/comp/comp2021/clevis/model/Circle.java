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
    public void listSelf() {
        System.out.println("R: "+r+" Round Point: "+ "x= " + location.x + " y=" + location.y);
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

    @Override
    // @JiaoZhiyang ,checked and modify by HanJiaming
    public boolean isIntersected(Line that) {
        return that.isIntersected(this);
    }

    @Override
    // @HanJiaming
    public boolean isIntersected(Square that) {
        return isIntersected(new Rectangle("",that.getLocation(),that.width,that.height));
    }
}
