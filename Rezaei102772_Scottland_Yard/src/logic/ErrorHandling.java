/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author ite102772
 */
public class ErrorHandling {

    protected boolean loadError(GUIConnector gui, SaveLoad obj) {
        boolean error = true;

        if (obj.getDetectives().getPlayers().length < 3 || obj.getDetectives().getPlayers().length > 5) {
            error = false;

            gui.showError("load: array of detectives ");

        }
        if (error && (obj.getDetectives().getNoOfDetectives() < 3 || obj.getDetectives().getNoOfDetectives() > 5)) {
            error = false;
            gui.showError("load: number of detectives ");
        }

        if (error && obj.getDetectives().getNoOfDetectives() != obj.getDetectives().getPlayers().length) {
            error = false;
            gui.showError("load: number of detectives ");
        }

        if (error && (obj.getMisterX().getCurrPos() < 1 || obj.getMisterX().getCurrPos() > 199)) {
            error = false;
            gui.showError("load: MrX's Pos ");
        }

        if (error) {
            for (int i = 0; i < obj.getDetectives().getPlayers().length && error; i++) {
                if (obj.getDetectives().getPlayers()[i].getPosition() < 1
                        || obj.getDetectives().getPlayers()[i].getPosition() > 199) {
                    error = false;
                    gui.showError("load: detective " + i + "'s Pos ");
                }
            }
        }
        if (error) {
            for (int i = 0; i < obj.getDetectives().getPlayers().length && error; i++) {
                if (obj.getDetectives().getPlayers()[i] == null) {
                    error = false;
                    gui.showError("load: detective(null)");
                }

            }
        }

        if (error && (obj.getMisterX().getLastShownPos() < 1 || obj.getMisterX().getLastShownPos() > 199)) {
            error = false;
            gui.showError("load: MrX's last shown Pos");
        }

        if (error && (obj.getWhosTurn() < 0 || obj.getWhosTurn() > (obj.getDetectives().getNoOfDetectives()))) {
            error = false;
            gui.showError("load: player turn ");
        }
        
        if (error && (obj.getCurrRoundNo() < 0 || obj.getCurrRoundNo() > 22)) {
            error = false;
            gui.showError("load: round ");
        }

        return error;
    }

    boolean jsonError(GUIConnector gui, jsonArrayStations obj) {
        boolean error = true;

        if (obj.getStations().length != 199) {
            error = false;
            gui.showError("json: stations size ");
        }
        for (int i = 0; i < obj.getStations().length && error; i++) {
            if (obj.getStations()[i].getIdentifier() < 1 || obj.getStations()[i].getIdentifier() > 199) {
                error = false;
                gui.showError("json: id of station " + i + " ");
            }
            if (error && obj.getStations()[i].getPosition().getX() < 0.0 || obj.getStations()[i].getPosition().getX() > 1.0
                    || obj.getStations()[i].getPosition().getY() < 0.0 || obj.getStations()[i].getPosition().getY() > 1.0) {
                error = false;
                gui.showError("json: pos of station " + i + " ");
            }
            for (int j = 0; j < obj.getStations()[i].getTube().length && error; j++) {
                if (obj.getStations()[i].getTube()[j] < 1 || obj.getStations()[i].getTube()[j] > 199) {
                    error = false;
                    gui.showError("json: train neighbor " + i + " ");
                }
            }
            for (int j = 0; j < obj.getStations()[i].getBus().length && error; j++) {
                if (obj.getStations()[i].getBus()[j] < 1 || obj.getStations()[i].getBus()[j] > 199) {
                    error = false;
                    gui.showError("json: bus neighbor " + i + " ");
                }
            }
            for (int j = 0; j < obj.getStations()[i].getCab().length && error; j++) {
                if (obj.getStations()[i].getCab()[j] < 1 || obj.getStations()[i].getCab()[j] > 199) {
                    error = false;
                    gui.showError("json: taxi neighbor " + i + " ");
                }
            }
            for (int j = 0; j < obj.getStations()[i].getBoat().length && error; j++) {
                if (obj.getStations()[i].getBoat()[j] < 1 || obj.getStations()[i].getBoat()[j] > 199) {
                    error = false;
                    gui.showError("json: boat neighbor " + i + " ");
                }
            }
        }

        return error;
    }
}
