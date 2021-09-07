/*
 * An advance programming project to implement Scotland_Yard game board
 */
package logic;

/**
 *
 * @author Rezaei
 */
public class Choose {

    private TicketType ticket;
    private Station station;
    private double weight;

    Choose(Station station, double weight, TicketType ticket) {
        this.ticket = ticket;
        this.station = station;
        this.weight = weight;
    }

    Station getStation() {
        return station;
    }

    TicketType getTicket() {
        return ticket;
    }

    double getWeight() {
        return weight;
    }
}
