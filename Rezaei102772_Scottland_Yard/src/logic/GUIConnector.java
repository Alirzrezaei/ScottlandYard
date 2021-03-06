package logic;

import javafx.scene.shape.Circle;

/**
 *
 * @author Rezaei
 */
public interface GUIConnector {

    public void circle(double x, double y, int player);

    public void updateLblTurn(int pl);

    public void updateLblsTickets(Players player);

    public void UsedMisterxTickets(int idx, TicketType type);

    public void showDropDawnList(Game game, boolean[] tickets, double x, double y, int station, boolean blackTicket);

    public void endGame(playerType type);

    public void clear();

    public void checkBoxBlackTicket(boolean enable);

    public void notShowMisterX();

    public void showError(String error);
}
