package logic;

import com.sun.media.jfxmedia.events.PlayerStateEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ite102772
 */
public class Players {

    private playerType type;
    private boolean AIControl;
    private int currentStation;
    private int previousStation;
    private int lastShowingStation;
    private List<Integer> journey;
    private double weight = 0.0;

    private HashMap<TicketType, Integer> ticket = new HashMap<TicketType, Integer>();

    Players(boolean AIControl, playerType type, int taxi, int bus, int train, int boat) {
        this.AIControl = AIControl;
        this.type = type;

        ticket.put(TicketType.Taxi, taxi);
        ticket.put(TicketType.Bus, bus);
        ticket.put(TicketType.Underground, train);
        ticket.put(TicketType.Boat, boat);

        journey = new LinkedList<>();
        
    }

    /**
     * this method is returning if the player is controlled by AI.
     *
     * @return boolean
     */
    protected boolean isAIControlled() {
        return AIControl;
    }

    /**
     * this method is returning the array of all tickets.
     *
     * @return Array of tickets
     */
    public HashMap getAllTickets() {

        return this.ticket;
    }

    /**
     * this method is returning the type of player
     *
     * @return playerType
     */
    protected playerType getType() {
        return type;
    }

    /**
     * this method is returning the number of taxi tickets
     *
     * @return integer number of taxi ticket
     */
    protected int getTaxiTickets() {
        return ticket.get(TicketType.Taxi);
    }

    /**
     * this method is returning the number of bus tickets
     *
     * @return integer number of bus ticket
     */
    protected int getBusTickets() {
        return ticket.get(TicketType.Bus);
    }

    /**
     * this method is returning the number of train tickets
     *
     * @return integer number of train tickets
     */
    protected int getTrainTickets() {
        return ticket.get(TicketType.Underground);
    }

    /**
     * this method is returning the number of boat tickets (black ticket)
     *
     * @return integer number of boat ticket
     */
    protected int getBoatTickets() {
        return ticket.get(TicketType.Boat);
    }

    /**
     * this method is returning the current station of the player
     *
     * @return integer current station
     */
    protected int getCurrentStation() {
        return currentStation;
    }

    /**
     * this method is setting the given station as current station of the
     * player.
     *
     * @param integer currentStation
     */
    protected void setCurrentStation(int currentStation) {
        this.currentStation = currentStation;
    }

    public void setPreviousStation(int previousStation) {
        this.previousStation = previousStation;
    }

    public int getPreviousStation() {
        return previousStation;
    }

    protected void setLastShowingStation(int lastShowingStation) {
        this.lastShowingStation = lastShowingStation;
    }

    protected int getLastShowingStation() {
        return this.lastShowingStation;
    }

    protected void addToJourneyBoard(TicketType ticket) {
        journey.add(ticket.ordinal());
    }

    protected List<Integer> getJourneyBoard() {
        return this.journey;
    }

    protected void setJourneyBoard(List<Integer> journey) {
        this.journey = journey;

    }

    protected void setWeight(double weight) {
        this.weight = weight;
    }

    protected double getWeight() {
        return this.weight;
    }

    /**
     * this method is receiving a ticket and increase the number of ticket by 1
     * based on the type.
     *
     * @param ticket
     */
    protected void increaseTicket(TicketType ticket) {

        this.ticket.put(ticket, this.ticket.get(ticket) + 1);

    }

    /**
     * this method is receiving a ticket and decrease the number of ticket by 1
     * based on the type.
     *
     * @param ticket
     */
    protected void decreaseTicket(TicketType ticket) {
        this.ticket.put(ticket, this.ticket.get(ticket) - 1);

    }

    protected List<Station> listOfAllNeighbors(Station st) {
        return new LinkedList<Station>();
    }

    protected Choose AI(Players[] players, Board board, List<Station> targetPositions) {
        return null;
    }

    protected int getTicketByType(TicketType ticket) {
        return this.ticket.get(ticket);
    }

    /**
     * this method is returning the smallest amount of all means of transport
     *
     * @return int the smallest amount
     */
    protected int lowestNumberOfTicket() {
        int no = 100;
        for (int i = 0; i < TicketType.values().length; i++) {
            if (getAllTickets().get(TicketType.values()[i]).hashCode() < no) {
                no = getAllTickets().get(TicketType.values()[i]).hashCode();
            }
        }
        return no;
    }
}
