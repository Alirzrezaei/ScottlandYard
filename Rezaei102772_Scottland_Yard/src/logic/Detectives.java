package logic;

import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ite102772
 */
public class Detectives extends Players {

    //private int depth = 10;
    public Detectives(boolean AIControl, playerType type) {
        super(AIControl, type, 10, 8, 4, 0);
    }

    public Detectives(boolean AIControl, playerType type, int taxi, int bus, int train, int currentPos) {
        super(AIControl, type, taxi, bus, train, 0);
        this.setCurrentStation(currentPos);
    }

    /**
     * this method is doing every thing when the one of the detectives turn is
     * and is controlled by AI.
     *
     * @param players
     * @param board
     * @param targetPositions
     * @return the choice to play by
     */
    @Override
    protected Choose AI(Players[] players, Board board, List<Station> targetPositions) {

        Choose choose = null;
        double weight = 0.0;
        Choose temp = null;
        Station st = board.getStation(this.getCurrentStation());

        // first strategy: if the player is neighbor with any of terget positions
        if (!isNeighborWithTargets(targetPositions).isEmpty()
                && stationWithSmallestId(isNeighborWithTargets(targetPositions), players, board) != null) {
            Station station = stationWithSmallestId(isNeighborWithTargets(targetPositions), players, board);

            temp = weightWithDirectNeighborToTargets(targetPositions, board, players, station);

            if (choose == null) {
                choose = temp;
            } else {
                if (temp.getWeight() > choose.getWeight()) {
                    choose = temp;
                }
            }
        }
        // second strategy: if the player is directly neighboring with a train station
        if (trainStationNeighbor(players, board) != null) {
            temp = weightWithTrainNeighbor(targetPositions, trainStationNeighbor(players, board), players, board);
            if (choose == null) {
                choose = temp;
            } else {
                if (temp != null && temp.getWeight() > choose.getWeight()) {
                    choose = temp;
                }
            }
        }
        // third strategy: the shortest path from player to averaged position of target positions
        List<Station> path = bestPathToDestination(players, closestToAverageOfStations(board, targetPositions), board);
        if (!path.isEmpty()) {
            temp = weightWithShortestPath(targetPositions, path.get(0), players, board);

            if (choose == null) {
                choose = temp;
            } else {
                if (temp != null && temp.getWeight() > choose.getWeight()) {
                    choose = temp;
                }
            }
        }
        // forth strategy: move to a neighbor with smallest Identifier
        Station station = stationWithSmallestId(board.getStation(this.getCurrentStation()).getAllNeighbors(), players, board);

        if (station != null) {
            temp = weightWithMoveToSmallestNeighbor(targetPositions, station, players, board);
            if (choose == null) {
                choose = temp;
            } else {
                if (temp != null && temp.getWeight() > choose.getWeight()) {
                    choose = temp;
                }
            }
        }
        return choose;
    }

    protected List<Station> bestPathToDestination(Players[] player, Station station, Board br) {

        ShortestPathFind bfs = new ShortestPathFind();

        List<Station> neighbors = br.getStation(this.getCurrentStation()).getAllNeighbors();
        List<Station> bestPath = new LinkedList<>();

        for (int i = 0; i < neighbors.size(); i++) {

            if (!isOccupiedByDetectives(player, neighbors.get(i)) && canMoveToStation(br.getStation(this.getCurrentStation()), neighbors.get(i), br)) {

                List<Station> path = bfs.bestPath(neighbors.get(i), station, player);

                if (bestPath.isEmpty()) {
                    bestPath = path;
                } else {
                    if (path.size() < bestPath.size() || (path.size() == bestPath.size() && path.get(0).getIdentifier() < bestPath.get(0).getIdentifier())) {
                        bestPath = path;
                    }
                }
            }
        }

        return bestPath;
    }

    /**
     * this method is check if any of stations in given list is neighbor with
     * current station of the player and add it to temp list. otherwise an empty
     * list will be returned
     *
     * @param stations
     * @return List of station neighboring with current station of the player
     */
    private List<Station> isNeighborWithTargets(List<Station> stations) {
        List<Station> temp = new LinkedList<>();
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).isNeighbor(this.getCurrentStation())) {
                temp.add(stations.get(i));
            }
        }
        return temp;
    }

    private Choose weightWithDirectNeighborToTargets(List<Station> targetStations, Board board, Players[] players, Station station) {
        double weight = 0.0;
        double sum = 0.0;
        double temp = 0.0;
        Choose choose = null;

        if (targetStations.size() > 1 && station != null) {
            weight = weight + (neighborsWithStations(targetStations, station) / (targetStations.size() - 1) * 10);
        }

        List<Station> path = new ShortestPathFind().bestPath(station,
                closestToAverageOfStations(board, targetStations), players);

        if (path.size() > 1 && path.size() < 11) {
            weight = weight + (10.0 - (path.size() - 1));
        }

        boolean[] moreRoute = routesByMeans(station, this.getCurrentStation());

        for (int i = 0; i < moreRoute.length; i++) {
            temp = 0.0;
            if (moreRoute[i] && this.getTicketByType(TicketType.values()[i]) > 0) {
                TicketType ticket = TicketType.values()[i];
                this.decreaseTicket(ticket);

                temp = temp + (numberOfAvailableStations(station, players, board) / 13 * 4);
                temp = temp + (double) ((this.lowestNumberOfTicket() > 2.0) ? 3.0 : this.lowestNumberOfTicket());

                if (weight + temp > sum) {
                    sum = weight + temp;
                    choose = new Choose(station, sum, ticket);
                }
                this.increaseTicket(ticket);
            }
        }

        return choose;
    }

    protected Choose weightWithTrainNeighbor(List<Station> targetStations, Station station, Players[] players, Board board) {
        double weight = 0.0;
        double sum = 0.0;
        double temp = 0.0;
        Choose choose = null;

        List<Station> path = new ShortestPathFind().bestPath(station,
                closestToAverageOfStations(board, targetStations), players);

        if (targetStations.contains(station)) {
            targetStations.remove(station);
        }

        if (targetStations.size() > 0) {
            weight = weight + (neighborsWithStations(targetStations, station) / (targetStations.size()) * 10);
        }

        if (path.size() > 1 && path.size() < 11) {
            weight = weight + (10.0 - path.size() - 1);
        }

        boolean[] moreRoute = routesByMeans(station, this.getCurrentStation());

        for (int i = 0; i < moreRoute.length; i++) {
            temp = 0.0;
            if (moreRoute[i] && this.getTicketByType(TicketType.values()[i]) > 0) {
                TicketType ticket = TicketType.values()[i];
                this.decreaseTicket(ticket);

                temp = temp + numberOfAvailableStations(station, players, board) / 13 * 4;
                temp = temp + ((this.lowestNumberOfTicket() > 2) ? 3 : this.lowestNumberOfTicket());

                if (weight + temp > sum) {
                    sum = weight + temp;
                    choose = new Choose(station, sum, ticket);
                }
                this.increaseTicket(ticket);
            }
        }
        return choose;
    }

    protected Choose weightWithShortestPath(List<Station> targetStations, Station station, Players[] players, Board board) {
        Choose choose = null;

        double weight = 0.0;
        double sum = 0.0;
        double temp = 0.0;

        List<Station> path = new ShortestPathFind().bestPath(station, closestToAverageOfStations(board, targetStations), players);

        if (targetStations.contains(station)) {
            targetStations.remove(station);
        }

        if (targetStations.size() > 0) {
            weight = weight + (neighborsWithStations(targetStations, station) / (targetStations.size()) * 10);
        }

        if (path.size() > 1 && path.size() < 11) {
            weight = weight + (10.0 - path.size() - 1);
        }

        boolean[] moreRoute = routesByMeans(station, this.getCurrentStation());

        for (int i = 0; i < moreRoute.length; i++) {
            temp = 0.0;
            if (moreRoute[i] && this.getTicketByType(TicketType.values()[i]) > 0) {
                TicketType ticket = TicketType.values()[i];
                this.decreaseTicket(ticket);

                temp = temp + numberOfAvailableStations(station, players, board) / 13 * 4;
                temp = temp + ((this.lowestNumberOfTicket() > 2) ? 3 : this.lowestNumberOfTicket());

                if (weight + temp > sum) {
                    sum = weight + temp;
                    choose = new Choose(station, sum, ticket);
                }
                this.increaseTicket(ticket);
            }
        }
        return choose;
    }

    protected Choose weightWithMoveToSmallestNeighbor(List<Station> targetStations, Station station, Players[] players, Board board) {
        Choose choose = null;
        double weight = 0.0;
        double sum = 0.0;
        double temp = 0.0;

        ShortestPathFind shortPath = new ShortestPathFind();

        List<Station> path = shortPath.bestPath(station, closestToAverageOfStations(board, targetStations),
                players);

        if (targetStations.contains(station)) {
            targetStations.remove(station);
        }

        if (targetStations.size() > 0) {
            weight = weight + (neighborsWithStations(targetStations, station) / (targetStations.size()) * 10);
        }

        if (path.size() > 1 && path.size() < 11) {
            weight = weight + (10.0 - path.size() - 1);
        }

        boolean[] moreRoute = routesByMeans(station, this.getCurrentStation());

        for (int i = 0; i < moreRoute.length; i++) {
            temp = 0.0;
            if (moreRoute[i] && this.getTicketByType(TicketType.values()[i]) > 0) {
                TicketType ticket = TicketType.values()[i];
                this.decreaseTicket(ticket);

                temp = temp + numberOfAvailableStations(station, players, board) / 13 * 4;
                temp = temp + ((this.lowestNumberOfTicket() > 2) ? 3 : this.lowestNumberOfTicket());

                if (weight + temp > sum) {
                    sum = weight + temp;
                    choose = new Choose(station, sum, ticket);
                }
                this.increaseTicket(ticket);
            }
        }
        return choose;
    }

    /**
     * this method is find the station with smallest id from the given list of
     * stations
     *
     * @param stations
     * @return station with smallest id
     */
    private Station stationWithSmallestId(List<Station> stations, Players[] players, Board br) {
        Station station = null;

        for (int i = 0; i < stations.size(); i++) {
            if (!isOccupiedByDetectives(players, stations.get(i))
                    && canMoveToStation(br.getStation(this.getCurrentStation()), stations.get(i), br)) {
                if (station == null) {
                    station = stations.get(i);
                } else {
                    if (stations.get(i).getIdentifier() < station.getIdentifier()) {
                        station = stations.get(i);
                    }
                }
            }
        }

        return station;
    }

    /**
     * this method check if the given station can reach to any other stations of
     * the given list and increment the amount of access by 1
     *
     * @param stations
     * @param station
     * @return integer number stations which are neighbors of given station
     */
    private int neighborsWithStations(List<Station> stations, Station station) {
        assert (station != null);
        int neighbors = 0;
        List<Station> temp = stations;

        for (int i = 0; i < temp.size(); i++) {
            Station st = temp.get(i);
            int id = station.getIdentifier();
            if (st.isNeighbor(id)) {
                neighbors++;
            }
        }

        return neighbors;
    }

    /**
     * this method is averaging the x and y from Xs and Ys of all stations of
     * the targetedStations list and then find the closed station to this
     * average x and y
     *
     * @param board
     * @param targetedSations
     * @return closest station to average x and y
     */
    private Station closestToAverageOfStations(Board board, List<Station> targetedSations) {
        Station station = null;
        double xSum = 0.0;
        double ySum = 0.0;
        for (int i = 0; i < targetedSations.size(); i++) {
            xSum = xSum + targetedSations.get(i).getCoordinates().getX();
            ySum = ySum + targetedSations.get(i).getCoordinates().getY();
        }
        Position pos = new Position(xSum / (double) targetedSations.size(), ySum / (double) targetedSations.size());

        station = board.closestStationToPosition(pos);

        return station;
    }

    /**
     * This method is checking if the given station is occupied by a detective
     *
     * @param players
     * @param station
     * @return true if occupied
     */
    private boolean isOccupiedByDetectives(Players[] players, Station station) {
        boolean occupied = false;
        for (int i = 1; i < players.length && !occupied; i++) {
            if (players[i].getCurrentStation() == station.getIdentifier()) {
                occupied = true;
            }
        }
        return occupied;
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
    private boolean canMoveToStation(Station current, Station st, Board br) {
        boolean canMove = false;
        canMove = canMove || (br.isTaxiNeighbor(current.getIdentifier(), st.getIdentifier())
                && this.getTaxiTickets() > 0);

        canMove = canMove || (br.isBusNeighbor(current.getIdentifier(), st.getIdentifier())
                && this.getBusTickets() > 0);

        canMove = canMove || (br.isTrainNeighbor(current.getIdentifier(), st.getIdentifier())
                && this.getTrainTickets() > 0);

        canMove = canMove || (br.isBoatNeighbor(current.getIdentifier(), st.getIdentifier())
                && this.getBoatTickets() > 0);

        return canMove;
    }

    /**
     * this method is counting how many available stations are from the given
     * station. the player should have ticket to go.
     *
     * @param st
     * @param players
     * @param br
     * @return int number of available stations
     */
    private double numberOfAvailableStations(Station st, Players[] players, Board br) {
        List<Station> station = listOfAllNeighbors(st);

        boolean removed = false;
        for (int j = 0; j < station.size() && !removed; j++) {
            if (!canMoveToStation(st, station.get(j), br)) {
                station.remove(j);
                j--;
                removed = true;
            }
        }

        return station.size();
    }

    /**
     * this method is checking if the player can use more means of transport
     * from current station to given station
     *
     * @param st
     * @param neighborId
     * @return boolean with true values if there is route.
     */
    private boolean[] routesByMeans(Station st, int neighborId) {
        assert (st != null);
        return st.hasMoreRoute(neighborId);
    }

    /**
     * this method checks if the player is neighboring with a train station and
     * that station is free. if there are more than one train stations the
     * smallest one will be chosen
     *
     * @param players
     * @param board
     * @return station of train with smallest id and if is free.
     */
    private Station trainStationNeighbor(Players[] players, Board board) {
        Station station = null;
        boolean found = false;
        List<Station> stations = board.getStation(this.getCurrentStation()).getAllNeighbors();

        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).getTrainNeighbors().length > 0 && !isOccupiedByDetectives(players, stations.get(i))
                    && canMoveToStation(board.getStation(this.getCurrentStation()), stations.get(i), board)) {
                if (station == null) {
                    station = stations.get(i);
                } else {
                    if (stations.get(i).getIdentifier() < station.getIdentifier()) {
                        station = stations.get(i);
                    }
                }
            }
        }

        return station;
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
     * this method is returning the smallest amount of all means of transport
     *
     * @return int the smallest amount
     */
    @Override
    protected int lowestNumberOfTicket() {
        int no = 100;
        for (int i = 0; i < TicketType.values().length - 1; i++) {
            if (getAllTickets().get(TicketType.values()[i]).hashCode() < no) {
                no = getAllTickets().get(TicketType.values()[i]).hashCode();

            }
        }
        return no;
    }
}
