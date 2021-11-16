package hk.edu.polyu.comp.comp2021.clevis;

import hk.edu.polyu.comp.comp2021.clevis.model.*;

import static hk.edu.polyu.comp.comp2021.clevis.Application.opc;

import org.junit.Assert;
import org.junit.Test;


public class ApplicationTest {

    @Test
    public void opcTest() {
        opc(new Shell("line n1 1 2 3 4"));
        opc(new Shell("line n2 1 2 3 4"));
        opc(new Shell("intersect n1 n2"));
        opc(new Shell("list n1"));
        opc(new Shell("group g1 n1"));
        opc(new Shell("intersect g1 n2"));
        opc(new Shell("group g2 g1"));
        opc(new Shell("move g2 100 100"));
        opc(new Shell("list g2"));
        opc(new Shell("listAll"));
        opc(new Shell("ungroup g2"));
        opc(new Shell("boundingbox g1"));
        opc(new Shell("boundingbox n2"));
        opc(new Shell("pick-and-move 1.001 2.002 10 10"));
        opc(new Shell("pick-and-move 10.001 20.002 10 10"));
        opc(new Shell("move n2 1 3"));
        opc(new Shell("rectangle r1 19 20 21 11"));
        opc(new Shell("rectangle r2 19 10 21 11"));
        opc(new Shell("square s1 100 20 21"));
        opc(new Shell("move r1 1 3"));
        opc(new Shell("delete r1"));
        opc(new Shell("circle c1 1 4 9"));
        opc(new Shell("group g3 r2 s1"));
        opc(new Shell("intersect g3 c1"));
        opc(new Shell("intersect g3 g1"));
        opc(new Shell("delete g3"));
        opc(new Shell("quit"));
    }

    @Test
    public void testConstructor() {
        Point p = new Point(10, 10);
        double r = 5.0;
        Circle c = new Circle("yuan", p, r);
        Assert.assertEquals("yuan", c.getName());
        Assert.assertEquals(p, c.getLocation());
    }

    @Test
    public void testIsContainedPoint() {
        Point p = new Point(10, 10);
        Circle c = new Circle("yuan", p, 5.0);

        Point p1 = new Point(10, 15);
        Assert.assertTrue(c.isContained(p1));

        Point p2 = new Point(10, 16);
        Assert.assertFalse(c.isContained(p2));

    }

    @Test
    public void testListSelf() {
        Point p = new Point(10, 10);
        Circle c = new Circle("yuan", p, 5.0);
        Assert.assertEquals(c.listSelf(1),"   Circle Name: yuan R: 5.00 Round Point: x= 10.00 y= 10.00");
    }

    @Test
    public void testIsIntersectedRectangle() {
        Circle c = new Circle("yuan", new Point(10, 10), 5.0);
        Rectangle r = new Rectangle("changfangxing", new Point(10, 10), 2, 6);
        Rectangle r3 = new Rectangle("changfangxing", new Point(9, 9), 10, 10);
        Square s = new Square("changfangxing", new Point(10, 10), 2);
        Assert.assertEquals(r.listSelf(1),"   Rectangle: Name: changfangxing Point(Left-Top): x= 10.00 y= 10.00 Width= 2.00 Height= 6.00");
        Assert.assertTrue(c.isIntersected(r));
        Assert.assertTrue(c.isIntersected((Graph) r));
        Assert.assertTrue(r.isIntersected((Graph) r3));
        Assert.assertTrue(r.isIntersected((Graph) r));

        Circle c2 = new Circle("yuan2", new Point(10, 10), 5.0);
        Rectangle r2 = new Rectangle("changfangxing2", new Point(16, 16), 2, 6);
        Assert.assertFalse(c2.isIntersected(r2));
        Assert.assertFalse(r2.isIntersected(c2));
        Assert.assertFalse(s.isIntersected(c2));
    }

    @Test
    public void testIsIntersectedCircle() {
        Circle c = new Circle("yuan1", new Point(10, 10), 5.0);
        Circle c2 = new Circle("yuan2", new Point(11, 11), 5.0);
        Assert.assertTrue(c.isIntersected(c2));

        Circle c3 = new Circle("yuan1", new Point(10, 10), 5.0);
        Circle c4 = new Circle("yuan2", new Point(20, 20), 5.0);
        Assert.assertFalse(c3.isIntersected(c4));

        Circle c5 = new Circle("yuan1", new Point(10, 10), 5.0);
        Circle c6 = new Circle("yuan2", new Point(0, 0), 5.0);
        Assert.assertFalse(c5.isIntersected(c6));
    }

    @Test
    public void testIsIntersectedLine() {
        Circle c = new Circle("yuan1", new Point(10, 10), 5.0);
        Line l = new Line("xian", new Point(1, 1), new Point(0, 10));
        Rectangle r2 = new Rectangle("changfangxing2", new Point(0, 0), 2, 6);
        Assert.assertFalse(c.isIntersected(l));
        Assert.assertTrue(r2.isIntersected(l));

        Circle c2 = new Circle("yuan2", new Point(10, 10), 5.0);
        Line l2 = new Line("xian2", new Point(6, 6), new Point(8, 8));
        Assert.assertTrue(c2.isIntersected(l2));

    }

    @Test
    public void testIsIntersectedSquare() {
        Circle c = new Circle("yuan1", new Point(10, 10), 5.0);
        Square s = new Square("zhengfangxing1", new Point(10, 10), 6);
        s.listSelf(1);
        Assert.assertTrue(c.isIntersected(s));
        Assert.assertTrue(c.isIntersected((Graph) s));
        Assert.assertTrue(s.isIntersected((Graph) c));


        Circle c2 = new Circle("yuan2", new Point(10, 10), 5.0);
        Square s2 = new Square("zhengfangxing2", new Point(16, 16), 6);
        Assert.assertFalse(c2.isIntersected(s2));

    }
    @Test
    public void LogMechTest(){
        LogMech.write("Line n1 1 4 5 6");
        LogMech.write("group g1 n1");
        LogMech.write("quit");
        LogMech.outputTxt();
        LogMech.outputHtml();
    }


}