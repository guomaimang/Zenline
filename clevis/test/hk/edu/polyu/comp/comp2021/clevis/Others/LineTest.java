package hk.edu.polyu.comp.comp2021.clevis.test2;

import hk.edu.polyu.comp.comp2021.clevis.model.Line;
import hk.edu.polyu.comp.comp2021.clevis.model.Point;
import org.junit.Assert;
import org.junit.Test;

public class LineTest {

    @Test
    public void testConstructor() {
        double x1 = 2;
        double y1 = 2;
        double x2 = 3;
        double y2 = 3;
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        Line line = new Line("xianduan", p1, p2);
        Assert.assertEquals("xianduan", line.name);
        Assert.assertEquals(p1, line.location);
        Assert.assertEquals(p2, line.location2);
    }

    @Test
    public void testMove() {
        double x1 = 1;
        double y1 = 1;
        double x2 = 2;
        double y2 = 2;
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        Line line = new Line("xianduan", p1, p2);
        Assert.assertEquals("xianduan", line.name);
        Assert.assertEquals(line.location, p1);
        Assert.assertEquals(line.location2, p2);
        line.move(10, 10);

        Assert.assertEquals(line.location, new Point(x1 + 10, y1 + 10));
        Assert.assertEquals(line.location2, new Point(x2 + 10, y2 + 10));
    }

    @Test
    public void testIsContained() {
        double x1 = 1;
        double y1 = 1;
        double x2 = 2;
        double y2 = 2;
        Line line = new Line("xianduan2", new Point(x1, y1), new Point(x2, y2));
        // FIXME the case must modify
        Assert.assertFalse(line.isContained(new Point(x1, y1)));
    }

    @Test
    public void testListSelf() {
        Line line = new Line("xianduan3", new Point(1, 1), new Point(3, 3));
        line.listSelf();
    }

}
