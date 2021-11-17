package hk.edu.polyu.comp.comp2021.clevis.test2;

 import hk.edu.polyu.comp.comp2021.clevis.model.*;
 import org.junit.Test;

 import java.util.ArrayList;

public class ClevisTest {

    @Test
    public void testClevisConstructor(){

    }

    @Test
    public void testListAll() {
        Circle yuan = new Circle("yuan", new Point(1, 1), 2);
        Rectangle changfangxing = new Rectangle("changfangxing", new Point(2, 2), 2, 3);
        Square zhengfangxing = new Square("zhengfangxing", new Point(3, 3), 3);
        Line xian = new Line("xian", new Point(4, 4), new Point(5, 5));
        Clevis.addGraph(yuan);
        Clevis.addGraph(changfangxing);
        Clevis.addGraph(zhengfangxing);
        Clevis.addGraph(xian);
        ArrayList<String> shapeList = new ArrayList<>();
        shapeList.add("yuan");
        shapeList.add("zhengfangxing");
        shapeList.add("xian");
        Shape shape = new Shape("shape", shapeList);
        Clevis.addShape(shape);
        Clevis.listAll();
    }

}