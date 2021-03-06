package logic;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ite102772
 */
public class MisterX extends Players {

    public MisterX(boolean AIControl, playerType type, int detectives) {
        super(AIControl, type, 4, 3, 3, detectives);
    }

    public MisterX(boolean AIControl, playerType type, int taxi, int bus, int train, int detectives) {
        super(AIControl, type, taxi, bus, train, detectives);
    }

    /**
     * this method is doing all the necessary things when the misterX's turn is
     * and is controlled by AI
     *
     * @param players
     * @param board
     * @return Choose with ticket and station
     */
    @Override
    protected Choose AI(Players[] players, Board board, List<Station> targetPositions) {
        assert (board != null && players != null);
        Choose choose = null;

        Station st = board.getStation(players[0].getCurrentStation());
        List<Station> neighbors = /*list of free stations*/ ifOccupiedByDetective(
                        /*list of all stations*/listOfAllNeighbors(st), players);

        if (!neighbors.isEmpty()) {
            double sum = 0.0;
            for (int i = 0; i < neighbors.size(); i++) {

                if (canMoveToStation(this, this.getCurrentStation(), neighbors.get(i), board)) {

                    boolean[] routes = routesByMeans(st, neighbors.get(i).getIdentifier());

                    for (int j = 0; j < routes.length; j++) {

                        if (routes[j] && this.getTicketByType(TicketType.values()[j]) > 0) {
                            TicketType ticket = TicketType.values()[j];

                            Choose temp = calcNeighbor(neighbors.get(i), sum, players, board, ticket, false);
                            if (temp != null && temp.getWeight() > sum) {
                                choose = temp;
                                sum = temp.getWeight();
                            }

                            if (this.getBoatTickets() > 0) {
                                temp = calcNeighbor(neighbors.get(i), sum, players, board, ticket, true);
                                if (temp != null && temp.getWeight() > sum) {
                                    choose = temp;
                                    sum = temp.getWeight();
                                }
                            }
                        }
                    }
                }
            }
        }
        return choose;
    }

    private Choose calcNeighbor(Station station, double sum,
            Players[] players, Board board, TicketType ticket, boolean blackTicket) {

        Choose choose = null;

        if (!blackTicket) {
            this.decreaseTicket(ticket);
        }

        double detectiveAccess = ((players.length - 1) - countDetectiveAccess(players, station, board)) * 10;
        double moveNextRound = numberOfAvailableStations(station, players, ticket, board) / 13.0 * 4.0;
        double ticketCount = (this.lowestNumberOfTicket() > 2) ? 3 : this.lowestNumberOfTicket();

        if ((detectiveAccess + moveNextRound + ticketCount) > sum) {
            sum = (detectiveAccess + moveNextRound + ticketCount);

            if (!blackTicket) {
                choose = new Choose(station, sum, ticket);
            } else {
                choose = new Choose(station, sum, TicketType.Boat);
            }
        }

        if (!blackTicket) {
            this.increaseTicket(ticket);
        }
        return choose;
    }

    /**
     * check if the station is list is occupied by a detective, it will be
     * removed from list of possible station to move in
     *
     * @param stations
     * @param players
     * @return list of stations
     */
    protected List<Station> ifOccupiedByDetective(List<Station> stations, Players[] players) {
        boolean isDeleted;

        for (int i = 0; i < stations.size(); i++) {
            isDeleted = false;
            for (int j = 1; j < players.length && !isDeleted; j++) {
                if (players[j].getCurrentStation() == stations.get(i).getIdentifier()) {
                    stations.remove(i);
                    isDeleted = true;
                    i--;
                }
            }
        }
        return stations;
    }

    /**
     * this method return all methods of the given station
     *
     * @param st
     * @param board
     * @return list of stations
     */
    @Override
    protected List<Station> listOfAllNeighbors(Station st) {

        return st.getAllNeighbors();
    }

    /**
     * this method is checking if the given player can move to the given
     * station. it checks if the player's current station is a neighbor of given
     * station and player can move to the station with its available ticket.
     *
     * @param p
     * @param st
     * @param br
     * @return true if player can move to given station
     */
    private boolean canMoveToStation(Players p, int current, Station st, Board br) {

        boolean canMove = false;

        canMove = canMove || (br.isTaxiNeighbor(current, st.getIdentifier()) && (p.getTaxiTickets() > 0 || p.getBoatTickets() > 0));

        canMove = canMove || (br.isBusNeighbor(current, st.getIdentifier()) && (p.getBusTickets() > 0 || p.getBoatTickets() > 0));

        canMove = canMove || (br.isTrainNeighbor(current, st.getIdentifier()) && (p.getTrainTickets() > 0 || p.getBoatTickets() > 0));

        canMove = canMove || (br.isBoatNeighbor(current, st.getIdentifier()) && p.getBoatTickets() > 0);

        return canMove;
    }

    /**
     * this method count how many detectives are neighbor of the given station
     * and can move to the given station at the next round
     *
     * @param pl
     * @param st
     * @param br
     * @return number of detectives
     */
    private int countDetectiveAccess(Players[] pl, Station st, Board br) {

        int count = 0;

        for (int i = 1; i < pl.length; i++) {
            if (br.isNeighbor(pl[i].getCurrentStation(), st.getIdentifier()) && canMoveToStation(pl[i], pl[i].getCurrentStation(), st, br)) {
                count++;
            }
        }

        return count;
    }

    private boolean[] routesByMeans(Station st, int neighborId) {

        return st.hasMoreRoute(neighborId);
    }

    /**
     * this method is counting how many available stations are from the given
     * station. the player should have ticket to go and station should be free.
     *
     * @param st
     * @param players
     * @param ticket
     * @param br
     * @return int number of available station.
     */
    private int numberOfAvailableStations(Station st, Players[] players, TicketType ticket, Board br) {
        List<Station> station = listOfAllNeighbors(st);

        for (int i = 1; i < players.length; i++) {
            boolean removed = false;
            for (int j = 0; j < station.size() && !removed; j++) {
                if (station.get(j).getIdentifier() == players[i].getCurrentStation() || !canMoveToStation(this, st.getIdentifier(), station.get(j), br)) {
                    station.remove(j);
                    j--;
                    removed = true;
                }

            }
        }

        return station.size();
    }

}
