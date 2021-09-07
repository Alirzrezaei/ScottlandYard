package logic;

import java.util.LinkedList;
import java.util.List;

/**
 * the Board class receives a list of stations and work on it. It finds out if
 * two stations are neighbors to each other or if a station has
 *
 * @author Rezaei
 */
public class Board {

    private List<Station> stations;

    /**
     * the main constructor of the baord class which receives a list of stations
     * as parameter.
     *
     * @param stations
     */
    Board(List<Station> stations) {
        this.stations = stations;
    }

    Station getStation(int identifier) {
        return this.stations.get(identifier - 1);
    }

    /**
     * return list of all stations as linked list
     *
     * @return stations
     */
    protected List<Station> getStations() {
        return stations;
    }

    /**
     * this method is returning the identifier of the station based on given
     * index.
     *
     * @param idx
     * @return integer identifier of stations
     */
    protected int getIdentifier(int idx) {
        return stations.get(idx).getIdentifier();
    }

    /**
     * return the x and y coordinates of the station based on given index
     *
     * @param idx
     * @return double array x and y coordinates
     */
    protected double[] getCoordinates(int idx) {
        //index of list is from 0 to 198 and stations are from 1 to 199
        return new double[]{getCoordinateX(idx - 1), getCoordinateY(idx - 1)};
    }

    /**
     * return X coordinate of the station based on given index.
     *
     * @param idx
     * @return double X coordinate
     */
    protected double getCoordinateX(int idx) {
        return stations.get(idx).getCoordinates().getX();
    }

    /**
     * return the y coordinate of the station based on given index
     *
     * @param idx
     * @return double y coordinate
     */
    protected double getCoordinateY(int idx) {
        return stations.get(idx).getCoordinates().getY();
    }

    /**
     * return an array containing taxi neighbor of the station based on given
     * index
     *
     * @param idx
     * @return integer array of taxi neighbors
     */
    protected Station[] getTaxiNeighbors(int idx) {
        //index of list is from 0 to 198 and stations are from 1 to 199
        return stations.get(idx - 1).getTaxiNeighbors();
    }

    /**
     * return an array containing bus neighbor of the station based on given
     * index
     *
     * @param idx
     * @return integer array of bus neighbors
     */
    protected Station[] getBusNeighbors(int idx) {
        //index of list is from 0 to 198 and stations are from 1 to 199
        return stations.get(idx - 1).getBusNeighbors();
    }

    /**
     * return an array containing train neighbor of the station based on given
     * index
     *
     * @param idx
     * @return integer array of train neighbors
     */
    protected Station[] getTrainNeighbors(int idx) {
        //index of list is from 0 to 198 and stations are from 1 to 199
        return stations.get(idx - 1).getTrainNeighbors();
    }

    /**
     * return an array containing boat neighbor of the station based on given
     * index
     *
     * @param idx
     * @return integer array of boat neighbors
     */
    protected Station[] getBoatNeighbors(int idx) {
        //index of list is from 0 to 198 and stations are from 1 to 199
        return stations.get(idx - 1).getBoatNeighbors();
    }

    /**
     * this method is checking and return true if two given stations are
     * neighbor
     *
     * @param current
     * @param idx
     * @return true if neighbors
     */
    protected boolean isNeighbor(int current, int idx) {

        return stations.get(current - 1).isNeighbor(idx);
    }

    /**
     * this method is checking if the two given stations are neighbors through
     * taxi routes
     *
     * @param current
     * @param idx
     * @param isNeighbor
     * @return true if neighbors
     */
    protected boolean isTaxiNeighbor(int current, int idx) {

        return stations.get(current - 1).isTaxiNeighbor(idx);
    }

    /**
     * this method is checking if the two given stations are neighbors through
     * bus routes
     *
     * @param current
     * @param idx
     * @param isNeighbor
     * @return true if neighbors
     */
    protected boolean isBusNeighbor(int current, int idx) {

        return stations.get(current - 1).isBusNeighbor(idx);
    }

    /**
     * this method is checking if the two given stations are neighbors through
     * train routes
     *
     * @param current
     * @param idx
     * @param isNeighbor
     * @return true if neighbors
     */
    protected boolean isTrainNeighbor(int current, int idx) {

        return stations.get(current - 1).isTrainNeighbor(idx);
    }

    /**
     * this method is checking if the two given stations are neighbors through
     * boat routes
     *
     * @param current
     * @param idx
     * @param isNeighbor
     * @return true if neighbors
     */
    protected boolean isBoatNeighbor(int current, int idx) {

        return stations.get(current - 1).isBoatNeighbor(idx);
    }

    /**
     * this method is calculating the distance of each station with clicked x
     * and y coordinates. if the distance is less than 0.012 it mean user
     * clicked almost on a station and it returns the correct station.
     *
     * @param x
     * @param y
     * @return integer station
     */
    protected int stationByPoints(double x, double y) {
        boolean found = false;
        int station = -1;
        for (int i = 0; i < stations.size() && !found; i++) {
            double calc = Math.abs(Math.sqrt(Math.pow(x - stations.get(i).getCoordinates().getX(), 2) + Math.pow(y - stations.get(i).getCoordinates().getY(), 2)));

            if (calc < 0.012) {
                found = true;
                station = stations.get(i).getIdentifier();

            }
        }
        return station;
    }

    /**
     * this method is calculating the distance of each station to the given
     * position and return the closest to the position
     *
     * @param x
     * @param y
     * @return closest station
     */
    protected Station closestStationToPosition(Position position) {

        Station st = stations.get(0);
        double distance = Math.abs(Math.sqrt(Math.pow(position.getX() - st.getCoordinates().getX(), 2)
                + Math.pow(position.getY() - st.getCoordinates().getY(), 2)));

        for (int i = 1; i < stations.size(); i++) {

            double temp = Math.abs(Math.sqrt(Math.pow(position.getX() - stations.get(i).getCoordinates().getX(), 2)
                    + Math.pow(position.getY() - stations.get(i).getCoordinates().getY(), 2)));

            if (temp < distance) {
                distance = temp;
                st = stations.get(i);
            }
        }

        return st;
    }
}
