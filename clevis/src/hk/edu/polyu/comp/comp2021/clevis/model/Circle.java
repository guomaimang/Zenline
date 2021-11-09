package hk.edu.polyu.comp.comp2021.clevis.model;

public class Circle extends Graph{
    double r;

    public Circle(Point p,double r){
        double a = 1.0;
        this.r=r;
        this.location=p;
    }

    @Override
    public boolean isContained(Point p) {
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
    }

    @Override
    public boolean isIntersected(Rectangle that) {
        //只需要判断4条线与园是否相交
        Point a = that.location, b = new Point(that.xMax, that.yMax);
        Point c= new Point(that.xMin, that.yMin), d = new Point(that.xMax, that.yMin);
        Line L1 = new Line(a, b), L2 = new Line(a, c), L3 = new Line(b, d), L4 = new Line(c, d);
        return L1.isIntersected(this) || L2.isIntersected(this) ||
                L3.isIntersected(this) || L4.isIntersected(this) ;
    }

    @Override
    public boolean isIntersected(Circle that) {
        double h=Math.abs(that.location.y-location.y);
        double w=Math.abs(that.location.x-location.x);
        double temp=Math.sqrt(h*h+w*w);
        double rr=r+that.r;
        if(temp>rr){
            return false;
        }
        return true;
    }

    @Override
    public boolean isIntersected(Line that) {
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
    }

    @Override
    public boolean isIntersected(Square that) {
        //只需要判断4条线与圆形是否相交
        Point a = that.location, b = new Point(that.xMax, that.yMax);
        Point c= new Point(that.xMin, that.yMin), d = new Point(that.xMax, that.yMin);
        Line L1 = new Line(a, b), L2 = new Line(a, c), L3 = new Line(b, d), L4 = new Line(c, d);
        return L1.isIntersected(this) || L2.isIntersected(this) ||
                L3.isIntersected(this) || L4.isIntersected(this) ;
    }

}
