/*
 * An advance programming project to implement Scotland_Yard game board
 */
package logic;

/**
 *
 * @author Rezaei
 */
public class jsonStation {

    private int identifier;
    private Position position;
    private int[] tube;
    private int[] bus;
    private int[] cab;
    private int[] boat;

    public int getIdentifier() {
        return identifier;
    }

    public Position getPosition() {
        return position;
    }

    public int[] getTube() {
        return tube;
    }

    public int[] getBus() {
        return bus;
    }

    public int[] getCab() {
        return cab;
    }

    public int[] getBoat() {
        return boat;
    }

}
