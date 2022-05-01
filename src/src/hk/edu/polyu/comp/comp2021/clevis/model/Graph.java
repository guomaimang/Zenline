package hk.edu.polyu.comp.comp2021.clevis.model;

/**
 * The type Graph.
 */
public abstract class Graph {
    private String name;
    private Point location;
    private final int zcode;
    private final double ERROR;


    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;
    private static int num = 0;
    private static int getNum(){
        return ++num;
    }

    /**
     * Instantiates a new Graph.
     */
    public Graph(){
        zcode = getNum();
        System.out.println("The graph's zcode is "+ getZcode() + "." );
        ERROR = 0.05;
    }

    /**
     * Move.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void move(double dx,double dy){
        setLocation((new Point(dx+ getLocation().getX(),dy+ getLocation().getY())));
        this.update();
    }

    /**
     * Update.
     */
    protected abstract void update();

    /**
     * Is contained boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public abstract boolean isContained(Point p);

    /**
     * List self string.
     *
     * @param indentation the indentation
     * @return the string
     */
    public abstract String listSelf(int indentation);

    /**
     * Is intersected boolean.
     *
     * @param g the g
     * @return the boolean
     */
    public boolean isIntersected(Graph g){
        return this.isIntersected(g);
    }

    /**
     * Boundingbox.
     */
    public void boundingbox(){
        System.out.println(String.format("%.2f", getxMin())+ " " + String.format("%.2f", getyMax()) + " " + String.format("%.2f",(getxMax() - getxMin())) + " " +String.format("%.2f",((getyMax() - getyMin()))));
    }

    // -------------standardization----------------------
    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Gets zcode.
     *
     * @return the zcode
     */
    public int getZcode() {
        return zcode;
    }

    /**
     * Gets min.
     *
     * @return the min
     */
    public double getxMin() {
        return xMin;
    }

    /**
     * Gets max.
     *
     * @return the max
     */
    public double getxMax() {
        return xMax;
    }

    /**
     * Gets min.
     *
     * @return the min
     */
    public double getyMin() {
        return yMin;
    }

    /**
     * Gets max.
     *
     * @return the max
     */
    public double getyMax() {
        return yMax;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    public double getERROR() {
        return ERROR;
    }

    /**
     * Sets min.
     *
     * @param xMin the x min
     */
    public void setxMin(double xMin) {
        this.xMin = xMin;
    }

    /**
     * Sets max.
     *
     * @param xMax the x max
     */
    public void setxMax(double xMax) {
        this.xMax = xMax;
    }

    /**
     * Sets min.
     *
     * @param yMin the y min
     */
    public void setyMin(double yMin) {
        this.yMin = yMin;
    }

    /**
     * Sets max.
     *
     * @param yMax the y max
     */
    public void setyMax(double yMax) {
        this.yMax = yMax;
    }
}

