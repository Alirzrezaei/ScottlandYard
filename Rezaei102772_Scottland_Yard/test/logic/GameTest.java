/*
 * An advance programming project to implement Scotland_Yard game board
 */
package logic;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rezaei
 */
public class GameTest {

    public GameTest() {
    }

    @Test
    public void testExpectedStationsMrX_currentStation_1_byBus() {

        Players[] players = new Players[4];

        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);

        int[] stations = new int[]{1, 67, 87, 30};
        int currentIdx = 0;

        Game game = new Game(new FakeGUI(), players, stations);

        List<Station> list = game.expectedMrXStations(TicketType.Bus);

        assertEquals(2, list.size());

        assertEquals(46, list.get(0).getIdentifier());
        assertEquals(58, list.get(1).getIdentifier());

    }

    @Test
    public void testExpectedStationMrX_currentStation_29_byBus() {

        Players[] players = new Players[4];

        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);

        int[] stations = new int[]{29, 67, 87, 30};
        int currentIdx = 0;

        Game game = new Game(new FakeGUI(), players, stations);

        List<Station> list = game.expectedMrXStations(TicketType.Bus);

        assertEquals(4, list.size());

        assertEquals(15, list.get(0).getIdentifier());
        assertEquals(41, list.get(1).getIdentifier());
        assertEquals(42, list.get(2).getIdentifier());
        assertEquals(55, list.get(3).getIdentifier());

    }

    @Test
    public void testExpectedStationMrX_currentStations_29_72_byBus() {

        Players[] players = new Players[4];
        List<Station> list = new LinkedList<>();
        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);

        int[] stations = new int[]{29, 67, 87, 30};
        int currentIdx = 0;

        Game game = new Game(new FakeGUI(), players, stations);

        list.add(game.getBoard().getStation(29));
        list.add(game.getBoard().getStation(72));
        game.setExpectedStation(list);

        assertEquals(2, list.size());

        list = game.expectedMrXStations(TicketType.Bus);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ", " + list.get(i).getIdentifier());

        }

        assertEquals(6, list.size());

        assertEquals(15, list.get(0).getIdentifier());
        assertEquals(41, list.get(1).getIdentifier());
        assertEquals(42, list.get(2).getIdentifier());
        assertEquals(55, list.get(3).getIdentifier());
        assertEquals(105, list.get(4).getIdentifier());
        assertEquals(107, list.get(5).getIdentifier());

    }

    @Test
    public void testExpectedStationMrX_currentStations_29_72_byBlackTicket() {

        Players[] players = new Players[4];
        List<Station> list = new LinkedList<>();
        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);

        int[] stations = new int[]{29, 67, 87, 30};
        int currentIdx = 0;

        Game game = new Game(new FakeGUI(), players, stations);

        list.add(game.getBoard().getStation(29));
        list.add(game.getBoard().getStation(72));
        game.setExpectedStation(list);

        assertEquals(2, list.size());

        list = game.expectedMrXStations(TicketType.Boat);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ", " + list.get(i).getIdentifier());

        }

        assertEquals(12, list.size());

        assertTrue(list.contains(game.getBoard().getStation(15)));
        assertTrue(list.contains(game.getBoard().getStation(105)));
        assertTrue(list.contains(game.getBoard().getStation(107)));
        assertTrue(list.contains(game.getBoard().getStation(41)));
        assertTrue(list.contains(game.getBoard().getStation(16)));
        assertTrue(list.contains(game.getBoard().getStation(90)));

    }
}
