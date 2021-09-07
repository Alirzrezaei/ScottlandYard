/*
 * An advance programming project to implement Scotland_Yard game board
 */
package logic;

/**
 *
 * @author Alireza
 */
public class LoadPlayers {

    int position;
    int remainingTickets[];

    public int getPosition() {
        return position;
    }

    public int[] getRemainingTickets() {
        return remainingTickets;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setRemainingTickets(int[] remainingTickets) {
        this.remainingTickets = remainingTickets;
    }
}
