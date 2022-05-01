package hk.edu.polyu.comp.comp2021.clevis.test2;

import hk.edu.polyu.comp.comp2021.clevis.model.*;
import org.junit.Assert;
import org.junit.Test;

public class CircleTest {

    @Test
    public void testConstructor() {
        Point p = new Point(10, 10);
        double r = 5.0;
        Circle c = new Circle("yuan", p, r);
        Assert.assertEquals("yuan", c.name);
        Assert.assertEquals(p, c.location);
        Assert.assertEquals(r, c.r, 0.005);
    }

    @Test
    public void testIsContainedPoint() {
        Point p = new Point(10, 10);
        Circle c = new Circle("yuan", p, 5.0);

        Point p1 = new Point(10, 15);
        Assert.assertTrue(c.isContained(p1));

        Point p2 = new Point(10, 16);
        Assert.assertFalse(c.isContained(p2));

        Point p3 = new Point(5, 10);
        Assert.assertTrue(c.isContained(p3));

        Point p4 = new Point(5, 5);
        Assert.assertFalse(c.isContained(p4));
    }

    @Test
    public void testListSelf() {
        Point p = new Point(10, 10);
        Circle c = new Circle("yuan", p, 5.0);
        c.listSelf();
    }

    @Test
    public void testIsIntersectedRectangle() {
        Circle c = new Circle("yuan", new Point(10, 10), 5.0);
        Rectangle r = new Rectangle("changfangxing", new Point(10, 10), 2, 6);
//        Assert.assertTrue(c.isIntersected(r));

        Circle c2 = new Circle("yuan2", new Point(10, 10), 5.0);
        Rectangle r2 = new Rectangle("changfangxing2", new Point(16, 16), 2, 6);
        Assert.assertFalse(c2.isIntersected(r2));
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
        Line l = new Line("xian", new Point(1, 1), new Point(2, 2));
        Assert.assertFalse(c.isIntersected(l));

        Circle c2 = new Circle("yuan2", new Point(10, 10), 5.0);
        Line l2 = new Line("xian2", new Point(6, 6), new Point(8, 8));
//        Assert.assertTrue(c2.isIntersected(l2));
    }

    @Test
    public void testIsIntersectedSquare() {
        Circle c = new Circle("yuan1", new Point(10, 10), 5.0);
        Square s = new Square("zhengfangxing1", new Point(10, 10), 6);
//        Assert.assertTrue(c.isIntersected(s));

        Circle c2 = new Circle("yuan2", new Point(10, 10), 5.0);
        Square s2 = new Square("zhengfangxing2", new Point(16, 16), 6);
//        Assert.assertFalse(c2.isIntersected(s2));
    }

}
