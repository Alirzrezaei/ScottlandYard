/*
 * An advance programming project to implement Scotland_Yard game board
 */
package logic;

/**
 *
 * @author Alireza
 */
public class LoadDetective {

    int noOfDetectives;
    boolean ai;
    LoadPlayers players[];

    public int getNoOfDetectives() {
        return noOfDetectives;
    }

    public boolean isAi() {
        return ai;
    }

    public LoadPlayers[] getPlayers() {
        return players;
    }

    public void setNoOfDetectives(int noOfDetectives) {
        this.noOfDetectives = noOfDetectives;
    }

    public void setAi(boolean ai) {
        this.ai = ai;
    }

    public void setPlayers(LoadPlayers[] players) {
        this.players = players;
    }

}
