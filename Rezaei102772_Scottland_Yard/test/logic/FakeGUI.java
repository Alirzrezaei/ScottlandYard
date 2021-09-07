/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author klk
 */
public class FakeGUI implements GUIConnector {

    /**
     *
     * @param x
     * @param y
     * @param player
     */
    @Override
    public void circle(double x, double y, int player) {

    }

    @Override
    public void updateLblTurn(int pl) {

    }

    @Override
    public void updateLblsTickets(Players player) {

    }

    /**
     *
     * @param idx
     * @param type
     */
    @Override
    public void UsedMisterxTickets(int idx, TicketType type) {

    }

    /**
     *
     * @param game
     * @param tickets
     * @param x
     * @param y
     * @param station
     * @param blackTicket
     */
    @Override
    public void showDropDawnList(Game game, boolean[] tickets, double x, double y, int station, boolean blackTicket) {

    }

    /**
     *
     * @param type
     */
    @Override
    public void endGame(playerType type) {

    }

    /**
     *
     */
    @Override
    public void clear() {

    }

    /**
     *
     * @param enable
     */
    @Override
    public void checkBoxBlackTicket(boolean enable) {

    }

    @Override
    public void notShowMisterX() {

    }

    @Override
    public void showError(String error) {

    }

}
