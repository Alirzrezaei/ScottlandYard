package logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author ite102772
 */
public class Game {

    private Board board;
    private JSONParser parser = new JSONParser();
    private Players[] players;
    private GUIConnector gui;
    private int currentIdx = 0;
    private int avoidedTurn = 1;
    private int round = 0;
    private boolean ComboBoxShown = false;
    private List<Station> expectedStations = new LinkedList<>();
    private int[] showUpRounds = new int[]{3, 8, 13, 18};
    private boolean cheatMisterX;
    private IO logger;

    /**
     * the main constructor of the game based on parameters received from gui
     *
     * @param gui
     * @param player
     * @param misterXAI
     * @param detectivesAI
     */
    public Game(GUIConnector gui, int player, boolean misterXAI, boolean detectivesAI) throws FileNotFoundException, IOException {
        this.gui = gui;

        // get a list of stations from json parser and pass it to board
//            this.board = new Board(parser.jsonParsing());
        this.board = new Board(parser.JsonValidator(gui));
        this.logger = new IO("game.log");

        if (board.getStations().size() > 0) {
            this.players = new Players[player + 1];

            for (int i = 0; i < players.length; i++) {
                if (i == 0) {
                    this.players[i] = new MisterX(misterXAI, playerType.MisterX, player);
                } else {
                    this.players[i] = new Detectives(detectivesAI, playerType.Detective);
                }
            }
            this.cheatMisterX = false;
            setStartingStations();
            this.gui.updateLblTurn(currentIdx);
            this.gui.updateLblsTickets(players[currentIdx]);
            this.logger.startOfLog(players);
            if (players[currentIdx].isAIControlled()) {
                botTurn();
            }

        } else {
            System.exit(0);
        }
    }

    Game(GUIConnector gui, Players[] players, int currentIdx, boolean won, int round, int[] targets) throws FileNotFoundException {
        this.gui = gui;
        this.gui.clear();

        this.board = new Board(parser.JsonValidator(this.gui));

        if (board.getStations().size() == 199) {
            this.currentIdx = currentIdx;
            this.round = round;
            this.players = new Players[players.length];
            this.players = players;
            for (int i = 0; i < players.length; i++) {
                Position position = board.getStation(players[i].getCurrentStation()).getCoordinates();
                this.gui.circle(position.getX(), position.getY(), i);

            }

            this.gui.updateLblTurn(this.currentIdx);
            this.gui.updateLblsTickets(players[this.currentIdx]);

            for (int i = 0; i < players[0].getJourneyBoard().size(); i++) {
                this.gui.UsedMisterxTickets(i, TicketType.values()[players[0].getJourneyBoard().get(i)]);
            }
            this.logger = new IO("game.log");
            if (this.players[this.currentIdx].isAIControlled() && !won) {
                botTurn();
            }else{
                nextPlayer();
            }

        } else {
            System.exit(0);
        }
    }

    /**
     * for testing
     */
    Game(GUIConnector gui, Players[] players, int[] stations) {
        this.gui = gui;

        // get a list of stations from json parser and pass it to board
        this.board = new Board(parser.JsonValidator(gui));

        this.players = players;
        this.logger = new IO("game.log");
        for (int i = 0; i < players.length; i++) {
            players[i].setCurrentStation(stations[i]);
        }

        expectedStations.add(board.getStation(players[0].getCurrentStation()));
    }

    protected List<Station> getExpectedStation() {
        return expectedStations;
    }

    /**
     * for testing
     *
     * @param stations
     */
    protected void setExpectedStation(List<Station> stations) {
        expectedStations = stations;
    }

    protected Board getBoard() {
        return this.board;
    }

    /**
     * this method is returning array of players
     *
     * @return array of players
     */
    public Players[] getPlayers() {
        return this.players;
    }

    /**
     * this method is returning number of players.
     *
     * @return int number of players
     */
    public int getNoOfPlayers() {
        return players.length;
    }

    /**
     * this method set starting points for each player and update the gui to
     * show them on the board
     */
    private void setStartingStations() {
        int starting[] = new int[]{13, 26, 29, 34, 50, 53, 91, 94, 103, 112, 117, 132, 138, 141, 155, 174, 197, 198};
        List<Integer> startingList = new LinkedList<>();

        for (int i = 0; i < starting.length; i++) {
            startingList.add(starting[i]);
        }

        Random rand = new Random();

        for (int i = 0; i < players.length; i++) {
            int index = rand.nextInt(startingList.size());

            players[i].setCurrentStation(startingList.get(index));
            if (players[i].getType() != playerType.MisterX) {
                this.gui.circle(board.getCoordinates(startingList.get(index))[0], board.getCoordinates(startingList.get(index))[1], i);
            } else {
                if (!players[i].isAIControlled()) {
                    this.gui.circle(board.getCoordinates(startingList.get(index))[0], board.getCoordinates(startingList.get(index))[1], i);
                }
            }

            startingList.remove(index);
        }
    }

    /**
     * this method is incrementing the idx of player that next player in the
     * array can play.
     */
    private void nextPlayer() {

        if (ifWon()) {

            this.gui.endGame(playerType.values()[1]);
        } else if (round == 22 && currentIdx == players.length - 1) {
            this.gui.endGame(playerType.values()[0]);
            logger.closeLog();
        } else {
            currentIdx = (currentIdx + 1) % players.length;

            if (players[currentIdx].getType() == playerType.MisterX) {
                this.gui.checkBoxBlackTicket(false);
            } else {
                this.gui.checkBoxBlackTicket(true);
            }
            if (!canPlay()) {
                if (avoidedTurn == players.length - 1) {
                    this.gui.endGame(playerType.values()[0]);

                } else {
                    if (players[currentIdx].getType() != playerType.MisterX) {
                        avoidedTurn++;
                    }
                    nextPlayer();
                }
            } else {
                this.gui.updateLblTurn(currentIdx);
                this.gui.updateLblsTickets(players[currentIdx]);

                if (players[currentIdx].isAIControlled()) {
                    botTurn();
                }

            }
        }
    }

    /**
     * this method is saving the game
     */
    public void save() {

        IO io = new IO();

        io.save(players, expectedStations, ifWon(), round, currentIdx);
    }

    /**
     * this method is loading the game.
     */
    public Game load(Game game) throws FileNotFoundException {

        IO io = new IO();
        game = io.Load(this.gui, game);
        return game;
    }

    private void botTurn() {
        Choose choose = players[currentIdx].AI(players, board, expectedStations);
        if (choose != null) {
            Position position = choose.getStation().getCoordinates();
            players[currentIdx].setWeight(choose.getWeight());
            doTurn(position.getX(), position.getY(), choose.getTicket(), choose.getStation().getIdentifier(), false);
        } else {
            if (!ifWon()) {
                nextPlayer();
            } else {
                gui.endGame(playerType.Detective);
            }
        }
    }

    /**
     * this method is receiving x and y coordinates and finds the correct
     * station matched on the board. -1 if there is no station is matched with
     * clicked position.
     *
     * @param x
     * @param y
     */
    public void getPositions(double x, double y, boolean blackTicket) {
    
        int station = board.stationByPoints(x, y);
 
        if (station > 0 && !ComboBoxShown) {

            if (this.board.isNeighbor(players[currentIdx].getCurrentStation(), station)
                    && ((this.players[currentIdx].getType() == playerType.Detective && !stationOccupiedByDetective(station))
                    || this.players[currentIdx].getType() == playerType.MisterX)) {
                //index of list is from 0 to 198 and stations are from 1 to 199
                double point[] = board.getCoordinates(station);
                if (moreRoute(station) == 1) {

                    int idx = -1;
                    boolean[] tickets = ifMoreRoute(station);
                    for (int i = 0; i < tickets.length; i++) {
                        if (tickets[i] && players[currentIdx].getTicketByType(TicketType.values()[i]) > 0) {
                            idx = i;
                        }
                    }
                    doTurn(x, y, TicketType.values()[idx], station, blackTicket);
                } else {
                    if (moreRoute(station) > 1) {
                        ComboBoxShown = true;
                        gui.showDropDawnList(this, ifMoreRoute(station), x, y, station, blackTicket);
                    }
                }
            }
        }
    }

    /**
     * this method is doing the turn of the player
     *
     * @param x
     * @param y
     * @param ticket
     * @param station
     */
    public void doTurn(double x, double y, TicketType ticket, int station, boolean blackTicket) {

        if (players[currentIdx].getType() == playerType.MisterX && (blackTicket || players[currentIdx].getAllTickets().get(ticket).hashCode() <= 0)) {
            ticket = TicketType.Boat;
        }

        if (players[currentIdx].getAllTickets().get(ticket).hashCode() > 0) {
            double point[] = board.getCoordinates(station);
            players[currentIdx].decreaseTicket(ticket);
            if (players[currentIdx].getType() != playerType.MisterX) {
                this.gui.circle(point[0], point[1], currentIdx);
            }
            avoidedTurn = 1;
            if (players[currentIdx].getType() == playerType.MisterX) {
                players[currentIdx].addToJourneyBoard(ticket);
                this.gui.UsedMisterxTickets(round, ticket);
                round++;
                if (!showMrX()) {
                    expectedStations = expectedMrXStations(ticket);
                } else {
                    expectedStations = new LinkedList<>();
                    expectedStations.add(board.getStation(station));
                    players[currentIdx].setLastShowingStation(board.getStation(station).getIdentifier());
                }
                if (!players[currentIdx].isAIControlled() || cheatMisterX) {
                    this.gui.circle(point[0], point[1], currentIdx);
                } else {
                    if (showMrX()) {
                        this.gui.circle(point[0], point[1], currentIdx);
                    }
                }
            } else {
                players[0].increaseTicket(ticket);
            }
            players[currentIdx].setCurrentStation(station);
            ComboBoxShown = false;
            logger.logger(players, currentIdx);
            nextPlayer();
        }
    }

    /**
     * this method is checking possible routes from user's current station to
     * given station. (possible transportation can be used)
     *
     * @param station
     * @return boolean array, true if there is route false if there is no route
     */
    private boolean[] ifMoreRoute(int station) {

        return board.getStation(players[currentIdx].getCurrentStation()).hasMoreRoute(station);
    }

    /**
     * this method is checking if there is more than one route from user's
     * current station to given station. (possible transportation can be used)
     *
     * @param station
     * @return int number of possible routes
     */
    private int moreRoute(int station) {
        int routes = 0;
        boolean[] tickets = ifMoreRoute(station);
        for (int i = 0; i < tickets.length; i++) {
      
            if (tickets[i] && players[currentIdx].getTicketByType(TicketType.values()[i]) > 0) {
                routes++;
            }
        }
        return routes;
    }

    /**
     * this method is checking if the MisterX's current station is equal to one
     * of the detectives ' current station the detectives won the game
     *
     * @return true if MisterX's station is equal to one of the detectives.
     */
    private boolean ifWon() {
        boolean won = false;

        for (int i = 1; i < players.length && !won; i++) {
            if (players[0].getCurrentStation() == players[i].getCurrentStation()) {
                won = true;
            }
        }

        return won;
    }

    /**
     * this method is checking if the current player can play with its remaining
     * tickets or not
     *
     * @return true if player can move
     */
    private boolean canPlay() {

        boolean canPlay = false;

        canPlay = canPlay || (board.getTaxiNeighbors(players[currentIdx].getCurrentStation()).length > 0
                && (players[currentIdx].getTaxiTickets() > 0 || players[currentIdx].getBoatTickets() > 0));

        canPlay = canPlay || (board.getBusNeighbors(players[currentIdx].getCurrentStation()).length > 0
                && (players[currentIdx].getBusTickets() > 0 || players[currentIdx].getBoatTickets() > 0));

        canPlay = canPlay || (board.getTrainNeighbors(players[currentIdx].getCurrentStation()).length > 0
                && (players[currentIdx].getTrainTickets() > 0 || players[currentIdx].getBoatTickets() > 0));

        canPlay = canPlay || (board.getBoatNeighbors(players[currentIdx].getCurrentStation()).length > 0 && players[currentIdx].getBoatTickets() > 0);

        return canPlay;
    }

    public void clear() {
        gui.clear();
    }

    /**
     * this method is checking the given station is occupied by a detective
     *
     * @param station
     * @return true if occupied
     */
    private boolean stationOccupiedByDetective(int station) {
        boolean occupied = false;

        for (int i = 1; i < players.length && !occupied; i++) {
            if (station == players[i].getCurrentStation()) {
                occupied = true;
            }
        }
        return occupied;
    }

    /**
     * this method is adding all neighboring station based on the station of the
     * list and ticket which is used.
     *
     * @param ticket
     * @return list of expected stations
     */
    protected List<Station> expectedMrXStations(TicketType ticket) {
        List<Station> temp = new LinkedList<>();

        if (ticket == TicketType.Boat) {//boat tickt is a black ticket
            for (int i = 0; i < expectedStations.size(); i++) {
                for (int j = 0; j < TicketType.values().length; j++) {
                    addArrayToList(temp, expectedStations.get(i).getNeighborsByTicket(TicketType.values()[j]));
                }
            }
        } else {
            for (int i = 0; i < expectedStations.size(); i++) {
                addArrayToList(temp, expectedStations.get(i).getNeighborsByTicket(ticket));
            }
        }

        return temp;
    }

    /**
     * this method is adding given array of station to the given list.
     *
     * @param temp
     * @param neighbors
     */
    private void addArrayToList(List<Station> temp, Station[] neighbors) {

        for (int i = 0; i < neighbors.length; i++) {
            if (!temp.contains(neighbors[i])) {
                temp.add(neighbors[i]);
            }
        }
    }

    /**
     * this method is checking if the current round is the equal round of 3, 8,
     * 13, 18 that the MisterX should be show up on the board
     *
     * @return
     */
    private boolean showMrX() {
        boolean found = false;
        for (int i = 0; i < showUpRounds.length && !found; i++) {
            if (showUpRounds[i] == round) {
                found = true;
            }
        }
        return found;
    }

    public void cheatMrX(boolean cheatMisterX) {
        this.cheatMisterX = cheatMisterX;
        if (cheatMisterX) {
            Position pos = board.getStation(players[0].getCurrentStation()).getCoordinates();
            this.gui.circle(pos.getX(), pos.getY(), 0);
        } else {
            this.gui.notShowMisterX();
        }
    }

    private void intArrayToStationList(int[] target) {
        this.expectedStations = new LinkedList<>();
        for (int i = 0; i < target.length; i++) {
            this.expectedStations.add(this.board.getStation(target[i]));
        }
    }
}
