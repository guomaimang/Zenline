package hk.edu.polyu.comp.comp2021.clevis.test2;

import hk.edu.polyu.comp.comp2021.clevis.model.*;
import org.junit.Assert;
import org.junit.Test;

public class SquareTest {

    @Test
    public void testConstructor() {
        Point p = new Point(10, 10);
        double l = 10.0;
        Square s = new Square("zhengfangxing", p, l);
        Assert.assertEquals("zhengfangxing", s.name);
        Assert.assertEquals(p, s.location);
        Assert.assertEquals(l, s.width, 0);
        Assert.assertEquals(l, s.height, 0);
    }

    @Test
    public void testListSelf() {
        Square s = new Square("zhengfangxing", new Point(10, 10), 10.0);
        s.listSelf();
    }

    @Test
    public void testIsContainedPoint() {
        Square s = new Square("zhengfangxing", new Point(10, 10), 10.0);

        Point p1 = new Point(10, 10);
        Assert.assertTrue(s.isContained(p1));

        Point p2 = new Point(20, 20);
        Assert.assertFalse(s.isContained(p2));

        Point p3 = new Point(18, 18);
        Assert.assertFalse(s.isContained(p3));
    }

    @Test
    public void testIsContainedSquare() {
        Square s = new Square("zhengfangxing", new Point(10, 10), 10.0);
        Square s2 = new Square("zhengfangxing2", new Point(11, 11), 11.0);
        Assert.assertFalse(s.isIntersected(s2));
    }

    @Test
    public void testIsContainedLine() {
        Square s = new Square("zhengfangxing", new Point(10, 10), 10.0);
        Line l = new Line("xian", new Point(11, 11), new Point(12, 12));
        Assert.assertFalse(s.isIntersected(l));

        Square s1 = new Square("zhengfangxing", new Point(10, 10), 10.0);
        Line l1 = new Line("xian", new Point(8, 8), new Point(12, 12));
//        Assert.assertTrue(s1.isIntersected(l1));
    }

    @Test
    public void testIsContainedCircle() {
        Square s = new Square("zhengfangxing", new Point(10, 10), 10.0);
        Circle c = new Circle("yuan", new Point(11, 11), 10.0);
//        Assert.assertTrue(s.isIntersected(c));
    }

    @Test
    public void testIsContainedRectangle() {
        Square s = new Square("zhengfangxing", new Point(10, 10), 10.0);
        Rectangle r = new Rectangle("changfangxing", new Point(11, 11), 11.0, 12.0);
//        Assert.assertTrue(s.isIntersected(r));

        Square s1 = new Square("zhengfangxing1", new Point(10, 10), 10.0);
        Rectangle r1 = new Rectangle("changfangxing1", new Point(21, 21), 11.0, 12.0);
//        Assert.assertFalse(s1.isIntersected(r1));
    }
}
