package logic;

import java.util.LinkedList;
import java.util.List;

/**
 * this class is creating the object of identifier with given parameters.
 * identifier can be stored in list of stations.
 *
 * @author Rezaei
 */
public class Station {

    private int identifier;
    private Position coordinates;

    private Station[] taxi;
    private Station[] bus;
    private Station[] train;
    private Station[] boat;

    public Station(int identifier) {
        this.identifier = identifier;
    }

    /**
     * the main constructor to make an identifier,
     *
     * @param station
     * @param coordinates
     * @param taxi
     * @param bus
     * @param train
     * @param boat
     */
    public Station(int station, Position coordinates, int[] taxi, int[] bus, int[] train, int[] boat) {
        this.identifier = station;
        this.coordinates = coordinates;
    }

    /**
     * this method is setting the position of the station
     *
     * @param coordinates
     */
    protected void setCoordinates(Position coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * this method is setting the taxi neighbors of the station
     *
     * @param taxi
     */
    protected void setTaxiNeighbors(Station[] taxi) {
        this.taxi = taxi;
    }

    /**
     * this method is setting the bus neighbors of the station
     *
     * @param bus
     */
    protected void setBusNeighbors(Station[] bus) {
        this.bus = bus;
    }

    /**
     * this method is setting the train neighbors of the station
     *
     * @param train
     */
    protected void setTrainNeighbors(Station[] train) {
        this.train = train;
    }

    /**
     * this method is setting the boat neighbors of the station.
     *
     * @param boat
     */
    protected void setBoatNeighbors(Station[] boat) {
        this.boat = boat;
    }

    /**
     * return the x and y coordinates of a identifier
     *
     * @return array of double, coordinations of stations
     */
    protected Position getCoordinates() {
        return this.coordinates;
    }

    /**
     * this method is returning the identifier number
     *
     * @return int identifier number
     */
    protected int getIdentifier() {
        return this.identifier;
    }

    /**
     * this method is returning all the taxi neighbors of a identifier in an
     * array
     *
     * @return int array of taxi neighbors
     */
    protected Station[] getTaxiNeighbors() {
        return this.taxi;
    }

    /**
     * this method is returning all the bus neighbors of a identifier in an
     * array
     *
     * @return int array of bus neighbors
     */
    protected Station[] getBusNeighbors() {
        return this.bus;
    }

    /**
     * this method is returning all the train neighbors of a identifier in an
     * array
     *
     * @return int array of train neighbors
     */
    protected Station[] getTrainNeighbors() {
        return this.train;
    }

    /**
     * this method is returning all the train neighbors of a identifier in an
     * array
     *
     * @return int array of train neighbors
     */
    protected Station[] getBoatNeighbors() {
        return this.boat;
    }

    /**
     * this method is checking if the given identifier is neighbor with this
     * station.
     *
     * @param identifier
     * @return true if this station is neighbor with given, otherwise false
     */
    protected boolean isNeighbor(int identifier) {
        boolean neighbor = false;

        if (!neighbor) {
            neighbor = isTaxiNeighbor(identifier);
        }
        if (!neighbor) {
            neighbor = isBusNeighbor(identifier);
        }
        if (!neighbor) {
            neighbor = isTrainNeighbor(identifier);
        }
        if (!neighbor) {
            neighbor = isBoatNeighbor(identifier);
        }

        return neighbor;
    }

    protected boolean isTaxiNeighbor(int identifier) {

        for (int i = 0; i < this.getTaxiNeighbors().length; i++) {
            if (this.getTaxiNeighbors()[i].getIdentifier() == identifier) {
                return true;
            }
        }
        return false;
    }

    protected boolean isBusNeighbor(int identifier) {

        for (int i = 0; i < this.getBusNeighbors().length; i++) {
            if (this.getBusNeighbors()[i].getIdentifier() == identifier) {
                return true;
            }
        }
        return false;
    }

    protected boolean isTrainNeighbor(int identifier) {

        for (int i = 0; i < this.getTrainNeighbors().length; i++) {
            if (this.getTrainNeighbors()[i].getIdentifier() == identifier) {
                return true;
            }
        }
        return false;
    }

    protected boolean isBoatNeighbor(int identifier) {

        for (int i = 0; i < this.getBoatNeighbors().length; i++) {
            if (this.getBoatNeighbors()[i].getIdentifier() == identifier) {
                return true;
            }
        }
        return false;
    }

    protected List<Station> getAllNeighbors() {
        List<Station> allNeighbors = new LinkedList<>();

        addNeighborsByMeans(allNeighbors, getTaxiNeighbors());
        addNeighborsByMeans(allNeighbors, getBusNeighbors());
        addNeighborsByMeans(allNeighbors, getTrainNeighbors());
        addNeighborsByMeans(allNeighbors, getBoatNeighbors());

        return allNeighbors;
    }

    private void addNeighborsByMeans(List<Station> list, Station[] neighbors) {
        for (int i = 0; i < neighbors.length; i++) {
            if (!list.contains(neighbors[i])) {
                list.add(neighbors[i]);
            }
        }
    }

    /**
     * this method is checking the this station is neighboring with given
     * station and change the values of the array based on response.
     *
     * @param identifier
     * @return array of boolean with true value if there is route
     */
    protected boolean[] hasMoreRoute(int identifier) {
        boolean[] routes = new boolean[4];

        routes[0] = isTaxiNeighbor(identifier);
        routes[1] = isBusNeighbor(identifier);
        routes[2] = isTrainNeighbor(identifier);
        routes[3] = isBoatNeighbor(identifier);

        return routes;
    }

    /**
     * this method return array of neighbors of this station respective to the
     * given ticket
     *
     * @param ticket
     * @return array of stations based on the ticket type.
     */
    protected Station[] getNeighborsByTicket(TicketType ticket) {
        assert (ticket != null);

        switch (ticket) {
            case Taxi:
                return getTaxiNeighbors();
            case Bus:
                return getBusNeighbors();
            case Underground:
                return getTrainNeighbors();
            default:
                return getBoatNeighbors();
        }
    }

}
