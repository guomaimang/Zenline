package hk.edu.polyu.comp.comp2021.clevis.model;

public class Circle extends Graph{
<<<<<<< HEAD
    final double r;

    // @JiaoZhiyang, checked and modify by HanJiaming
    public Circle(String name, Point p,double r){
        this.name = name;
        location = p;
        this.r = r;
        xMin = p.x - r;
        xMax = p.x + r;
        yMin = p.y - r;
        yMax = p.y + r;
=======
    double r;

    public Circle(Point p,double r){
        double a = 1.0;
        this.r=r;
        this.location=p;
>>>>>>> JiaoZhiyang
    }

    @Override
    // @JiaoZhiyang, checked and modify by HanJiaming
    public boolean isContained(Point p) {
<<<<<<< HEAD
        // error < 0.05 can be patience
        double y=(getLocation().y-p.y);
        double x=(getLocation().x-p.x);
        double temp = Math.sqrt(x * x + y * y);
        return Math.abs(r - temp) < error;
    }

    @Override
    // @HanJiaming
    public void listSelf() {
        System.out.println("半径: "+r+" 圆心: "+ "x= " + location.x + " y=" + location.y);
=======
        double y=(location.y-p.y);
        double x=(location.x-p.x);
        y=Math.abs(y);
        x=Math.abs(x);
        double temp = Math.sqrt(x * x + y * y);
        if(Math.abs(r-temp)<0.05){
            return true;
        }
        return false;
    }

    @Override
    public void listSelf() {
        System.out.println("半径"+r+"圆心"+this.location);
>>>>>>> JiaoZhiyang
    }

    @Override
    // @JiaoZhiyang, checked and modify by HanJiaming
    public boolean isIntersected(Rectangle that) {
<<<<<<< HEAD
        Point a = that.getLocation(), b = new Point(that.getxMax(), that.getyMax());
        Point c= new Point(that.getxMin(), that.getyMin()), d = new Point(that.getxMax(), that.getyMin());
        Line L1 = new Line("",a, b), L2 = new Line("",a, c), L3 = new Line("",b, d), L4 = new Line("",c, d);
        //Judging only 4 lines and gardens intersect
        return L1.isIntersected(this) || L2.isIntersected(this)
                || L3.isIntersected(this) || L4.isIntersected(this) ;
=======
        //只需要判断4条线与园是否相交
        Point a = that.location, b = new Point(that.xMax, that.yMax);
        Point c= new Point(that.xMin, that.yMin), d = new Point(that.xMax, that.yMin);
        Line L1 = new Line(a, b), L2 = new Line(a, c), L3 = new Line(b, d), L4 = new Line(c, d);
        return L1.isIntersected(this) || L2.isIntersected(this) ||
                L3.isIntersected(this) || L4.isIntersected(this) ;
>>>>>>> JiaoZhiyang
    }

    @Override
    // @JiaoZhiyang, checked and modify by HanJiaming
    public boolean isIntersected(Circle that) {
<<<<<<< HEAD
        double deltaY=Math.abs(that.getLocation().y- getLocation().y);
        double deltaX=Math.abs(that.getLocation().x- getLocation().x);
        double delta=Math.sqrt(deltaY*deltaY+deltaX*deltaX);
        double deltaMax=r+that.r;
        return !(delta > deltaMax);
=======
        double h=Math.abs(that.location.y-location.y);
        double w=Math.abs(that.location.x-location.x);
        double temp=Math.sqrt(h*h+w*w);
        double rr=r+that.r;
        if(temp>rr){
            return false;
        }
        return true;
>>>>>>> JiaoZhiyang
    }

    @Override
    // @JiaoZhiyang ,checked and modify by HanJiaming
    public boolean isIntersected(Line that) {
<<<<<<< HEAD
        return that.isIntersected(this);
=======
        //直接用线与圆形是否相交
        return that.isIntersected(this);
        /*原始判断方法
        //该线和x轴平行
        double midY;
        double midX;
        if(that.location.y == that.location2.y){
            midY =that.location.y;
            double temp = Math.abs((that.location.x -that.location2.x));
            midX = temp/2;
        }
        //该线和Y轴平行
        if(that.location.x == that.location2.x){
            midX =that.location.x;
            double temp = Math.abs((that.location.y-that.location2.y));
            midY =temp/2;
        }
        //该线段是斜线
        midX = Math.abs(that.location.x -that.location2.x)/2;
        midY =Math.abs(that.location.y-that.location2.y)/2;
        //赋值中点的坐标
        Point midPoint = new Point(midX,midY);
        //如果线段的俩个点和中点到圆心的距离都大于半径 说明该线段不和圆相交
        if(isContained(that.location)||isContained(that.location2)||isContained(midPoint)){
            return true;
        }else{
            return false;
        }
        */
>>>>>>> JiaoZhiyang
    }

    @Override
    // @HanJiaming
    public boolean isIntersected(Square that) {
<<<<<<< HEAD
        return isIntersected(new Rectangle("",that.getLocation(),that.width,that.height));
=======
        //只需要判断4条线与圆形是否相交
        Point a = that.location, b = new Point(that.xMax, that.yMax);
        Point c= new Point(that.xMin, that.yMin), d = new Point(that.xMax, that.yMin);
        Line L1 = new Line(a, b), L2 = new Line(a, c), L3 = new Line(b, d), L4 = new Line(c, d);
        return L1.isIntersected(this) || L2.isIntersected(this) ||
                L3.isIntersected(this) || L4.isIntersected(this) ;
>>>>>>> JiaoZhiyang
    }

}
