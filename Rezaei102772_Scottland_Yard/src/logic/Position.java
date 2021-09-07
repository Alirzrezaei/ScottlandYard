package logic;

/**
 *
 * @author Rezaei
 */
public class Position {

    private double x;
    private double y;

    Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * this method is returning the x value of the position.
     *
     * @return double X value
     */
    double getX() {
        return this.x;
    }

    /**
     * this method is returning the y value of the position.
     *
     * @return double Y value
     */
    double getY() {
        return this.y;
    }
}
