package hk.edu.polyu.comp.comp2021.clevis.test2;

import hk.edu.polyu.comp.comp2021.clevis.model.*;
import org.junit.Assert;
import org.junit.Test;

public class RectangleTest {

    @Test
    public void testConstructor() {
        Point p = new Point(0, 0);
        Rectangle r = new Rectangle("changfangxing", p, 2, 3);
        Assert.assertEquals("changfangxing", r.name);
        Assert.assertEquals(p, r.location);
        Assert.assertEquals(2.0, r.width, 0.005);
        Assert.assertEquals(3.0, r.height, 0.005);
    }

    @Test
    public void testListSelf() {
        Point p = new Point(0, 0);
        Rectangle r = new Rectangle("changfangxing", p, 2, 3);
        r.listSelf();
    }

    @Test
    public void testIsIntersectedRectangle() {
        Rectangle r1 = new Rectangle("changfangxing1", new Point(0, 0), 2, 3);
        Rectangle r2 = new Rectangle("changfangxing2", new Point(1, 1), 2, 3);
//        Assert.assertTrue(r1.isIntersected(r2));

        Rectangle r3 = new Rectangle("changfangxing3", new Point(0, 0), 2, 3);
        Rectangle r4 = new Rectangle("changfangxing4", new Point(5, 5), 2, 3);
//        Assert.assertFalse(r3.isIntersected(r4));
    }

    @Test
    public void testIsIntersectedCircle() {
        Rectangle r1 = new Rectangle("changfangxing1", new Point(0, 0), 2, 3);
        Circle c1 = new Circle("yuan1", new Point(1, 1), 2);
//        Assert.assertTrue(r1.isIntersected(c1));

        Rectangle r2 = new Rectangle("changfangxing2", new Point(0, 0), 2, 3);
        Circle c2 = new Circle("yuan2", new Point(2, 2), 1);
//        Assert.assertTrue(r2.isIntersected(c2));

        Rectangle r3 = new Rectangle("changfangxing3", new Point(0, 0), 2, 3);
        Circle c3 = new Circle("yuan3", new Point(2, 2), 0.5);
        Assert.assertFalse(r3.isIntersected(c3));
    }

    @Test
    public void testIsIntersectedLine() {
        Rectangle r1 = new Rectangle("changfangxing1", new Point(0, 0), 2, 3);
        Line l1 = new Line("xian1", new Point(0, 0), new Point(2, 2));
//        Assert.assertTrue(r1.isIntersected(l1));

        Rectangle r2 = new Rectangle("changfangxing2", new Point(0, 0), 2, 3);
        Line l2 = new Line("xian2", new Point(1, 1), new Point(2, 2));
        Assert.assertFalse(r2.isIntersected(l2));
    }

    @Test
    public void testIsIntersectedSquare() {
        Rectangle r1 = new Rectangle("changfangxing1", new Point(0, 0), 2, 3);
        Square s1 = new Square("zhengfangxing1", new Point(0, 0), 3);
//        Assert.assertTrue(r1.isIntersected(s1));

        Rectangle r2 = new Rectangle("changfangxing1", new Point(0, 0), 2, 3);
        Square s2 = new Square("zhengfangxing1", new Point(4, 4), 3);
//        Assert.assertFalse(r2.isIntersected(s2));
    }

    @Test
    public void testIsContainedPoint() {
        Rectangle r1 = new Rectangle("changfangxing1", new Point(0, 0), 2, 3);
        Point p1 = new Point(0, -1);
        Assert.assertTrue(r1.isContained(p1));

        Rectangle r2 = new Rectangle("changfangxing2", new Point(0, 0), 2, 3);
        Point p2 = new Point(1, 1);
        Assert.assertFalse(r2.isContained(p2));
    }

}
