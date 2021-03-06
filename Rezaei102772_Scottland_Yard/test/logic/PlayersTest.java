package logic;

import java.io.FileNotFoundException;
import org.junit.Test;
import static org.junit.Assert.*;

import static logic.playerType.*;
import org.json.JSONException;

/**
 *
 * @author Rezaei
 */
public class PlayersTest {

    @Test
    public void testConstructorPlayer() {
        Players[] players = new Players[4];

        for (int i = 0; i < players.length; i++) {
            if (i == 0) {
                players[i] = new MisterX(true, playerType.MisterX, players.length - 1/*number of detectives*/);
            } else {
                players[i] = new Detectives(false, playerType.values()[1]);
            }
        }
        assertEquals(4, players.length);
        assertTrue(players[0].isAIControlled());
        assertFalse(players[1].isAIControlled());
        assertFalse(players[2].isAIControlled());
        assertFalse(players[3].isAIControlled());
    }

    @Test
    public void testConstructorPlayer_playerType() {
        Players[] players = new Players[6];

        for (int i = 0; i < players.length; i++) {
            if (i == 0) {
                players[i] = new MisterX(true, playerType.MisterX, players.length - 1/*number of detectives*/);
            } else {
                players[i] = new Detectives(false, playerType.values()[1]);
            }
        }
        assertEquals(6, players.length);

        assertEquals(MisterX, players[0].getType());
        assertEquals(Detective, players[1].getType());
        assertEquals(Detective, players[2].getType());
        assertEquals(Detective, players[3].getType());
        assertEquals(Detective, players[4].getType());
        assertEquals(Detective, players[5].getType());
    }

    @Test
    public void testConstructorPlayer_MisterXtickets() {
        Players[] players = new Players[6];

        for (int i = 0; i < players.length; i++) {
            if (i == 0) {
                players[i] = new MisterX(true, playerType.MisterX, players.length - 1/*number of detectives*/);
            } else {
                players[i] = new Detectives(false, playerType.values()[1]);
            }
        }
        assertEquals(6, players.length);

        assertEquals(4, players[0].getTaxiTickets());
        assertEquals(3, players[0].getBusTickets());
        assertEquals(3, players[0].getTrainTickets());
        assertEquals(5, players[0].getBoatTickets());
    }

    @Test
    public void testConstructorPlayer_detectivesTickets() {
        Players[] players = new Players[6];

        for (int i = 0; i < players.length; i++) {
            if (i == 0) {
                players[i] = new MisterX(true, playerType.MisterX, players.length - 1/*number of detectives*/);
            } else {
                players[i] = new Detectives(false, playerType.values()[1]);
            }
        }
        assertEquals(6, players.length);

        assertEquals(10, players[5].getTaxiTickets());
        assertEquals(8, players[2].getBusTickets());
        assertEquals(4, players[4].getTrainTickets());
        assertEquals(0, players[3].getBoatTickets());
    }

    @Test
    public void testConstructorPlayer_increaseTicketsforMisterX() {
        Players[] players = new Players[6];

        for (int i = 0; i < players.length; i++) {
            if (i == 0) {
                players[i] = new MisterX(true, playerType.MisterX, players.length - 1/*number of detectives*/);
            } else {
                players[i] = new Detectives(false, playerType.values()[1]);
            }
        }
        assertEquals(6, players.length);

        assertEquals(4, players[0].getTaxiTickets());
        players[0].increaseTicket(TicketType.Taxi);
        assertEquals(5, players[0].getTaxiTickets());

        assertEquals(3, players[0].getBusTickets());
        players[0].increaseTicket(TicketType.Bus);
        assertEquals(4, players[0].getBusTickets());

        assertEquals(3, players[0].getTrainTickets());
        players[0].increaseTicket(TicketType.Underground);
        assertEquals(4, players[0].getTrainTickets());
    }

    @Test
    public void testConstructorPlayer_decreaseTicketsforDetectives() {
        Players[] players = new Players[6];

        for (int i = 0; i < players.length; i++) {
            if (i == 0) {
                players[i] = new MisterX(true, playerType.MisterX, players.length - 1/*number of detectives*/);
            } else {
                players[i] = new Detectives(false, playerType.values()[1]);
            }
        }
        assertEquals(6, players.length);

        assertEquals(10, players[5].getTaxiTickets());
        players[5].decreaseTicket(TicketType.Taxi);
        assertEquals(9, players[5].getTaxiTickets());

        assertEquals(8, players[2].getBusTickets());
        players[2].decreaseTicket(TicketType.Bus);
        players[2].decreaseTicket(TicketType.Bus);
        assertEquals(6, players[2].getBusTickets());

        assertEquals(4, players[4].getTrainTickets());
        players[4].decreaseTicket(TicketType.Underground);
        assertEquals(3, players[4].getTrainTickets());

        assertEquals(0, players[3].getBoatTickets());
    }

    @Test
    public void testPlayer_MisterX_neighbors() throws FileNotFoundException, JSONException {
        Players[] players = new Players[6];
        JSONParser json = new JSONParser();
        Board br = new Board(json.JsonValidator(new FakeGUI()));

        for (int i = 0; i < players.length; i++) {
            if (i == 0) {
                players[i] = new MisterX(true, playerType.MisterX, players.length - 1/*number of detectives*/);
            } else {
                players[i] = new Detectives(false, playerType.values()[1]);
            }
        }

        assertTrue(players[0].isAIControlled());
        players[0].setCurrentStation(102);
        Station st = br.getStation(players[0].getCurrentStation());//the index is 0-198 but station 1-199
        assertEquals(6, players[0].listOfAllNeighbors(st).size());

        players[0].setCurrentStation(67);
        st = br.getStation(players[0].getCurrentStation());//the index is 0-198 but station 1-199
        assertEquals(13, players[0].listOfAllNeighbors(st).size());

    }

}
