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
 * @author Alireza
 */
public class DetectivesTest {

    public DetectivesTest() {
    }

    @Test
    public void testChooseStation_ShortestPath() {
        Players[] players = new Players[4];

        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);

        Game game = new Game(new FakeGUI(), players, new int[]{1, 2, 3, 4});

        players[0].setCurrentStation(104);
        players[1].setCurrentStation(134);
        players[2].setCurrentStation(71);
        players[3].setCurrentStation(186);

        List<Station> targets = new LinkedList<>();
        targets.add(game.getBoard().getStation(104));
        targets.add(game.getBoard().getStation(127));
        targets.add(game.getBoard().getStation(117));
        targets.add(game.getBoard().getStation(118));

        Choose choose = players[2].AI(players, game.getBoard(), targets);
//        assertEquals(29, choose.getStation().getIdentifier());// as there is no detective around it. 3-0
//        assertEquals(TicketType.Boat, choose.getTicket());// station 29 has a bus connection, but MrX will use boat ticket as 
        //initially has 3 bus ticket and using it will reduce the point.

        System.out.println("choose" + choose.getStation().getIdentifier() + ", ticket, "
                + choose.getTicket() + ", weight" + choose.getWeight());

    }

    @Test
    public void testChooseStation_neighborWithTargetStations() {
        Players[] players = new Players[4];

        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);

        Game game = new Game(new FakeGUI(), players, new int[]{1, 2, 3, 4});

        players[0].setCurrentStation(104);
        players[1].setCurrentStation(134);
        players[2].setCurrentStation(71);
        players[3].setCurrentStation(186);

        List<Station> targets = new LinkedList<>();
        targets.add(game.getBoard().getStation(104));
        targets.add(game.getBoard().getStation(127));
        targets.add(game.getBoard().getStation(117));
        targets.add(game.getBoard().getStation(118));

        Choose choose = players[1].AI(players, game.getBoard(), targets);
//        assertEquals(29, choose.getStation().getIdentifier());// as there is no detective around it. 3-0
//        assertEquals(TicketType.Boat, choose.getTicket());// station 29 has a bus connection, but MrX will use boat ticket as 
        //initially has 3 bus ticket and using it will reduce the point.

        System.out.println("choose " + choose.getStation().getIdentifier() + ", ticket, "
                + choose.getTicket() + ", weight " + choose.getWeight());

    }

    @Test
    public void testChooseStation_neighborWithTrainStation() {
        Players[] players = new Players[4];

        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);

        Game game = new Game(new FakeGUI(), players, new int[]{1, 2, 3, 4});

        players[0].setCurrentStation(104);
        players[1].setCurrentStation(134);
        players[2].setCurrentStation(71);
        players[3].setCurrentStation(186);

        List<Station> targets = new LinkedList<>();
        targets.add(game.getBoard().getStation(104));
        targets.add(game.getBoard().getStation(127));
        targets.add(game.getBoard().getStation(117));
        targets.add(game.getBoard().getStation(186));

        Choose choose = players[3].AI(players, game.getBoard(), targets);
//        assertEquals(29, choose.getStation().getIdentifier());// as there is no detective around it. 3-0
//        assertEquals(TicketType.Boat, choose.getTicket());// station 29 has a bus connection, but MrX will use boat ticket as 
        //initially has 3 bus ticket and using it will reduce the point.

        System.out.println("choose" + choose.getStation().getIdentifier() + ", ticket, "
                + choose.getTicket() + ", weight " + choose.getWeight());

    }

    @Test
    public void testChooseStation_neighborWithTrain_ShortestPath_isOccupied() {
        Players[] players = new Players[4];

        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);

        Game game = new Game(new FakeGUI(), players, new int[]{1, 2, 3, 4});

        players[0].setCurrentStation(10);
        players[1].setCurrentStation(134);
        players[2].setCurrentStation(71);
        players[3].setCurrentStation(89);

        List<Station> targets = new LinkedList<>();
        targets.add(game.getBoard().getStation(104));
        targets.add(game.getBoard().getStation(127));
        targets.add(game.getBoard().getStation(117));
        targets.add(game.getBoard().getStation(118));

        Choose choose = players[2].AI(players, game.getBoard(), targets);

//        assertEquals(29, choose.getStation().getIdentifier());// as there is no detective around it. 3-0
//        assertEquals(TicketType.Boat, choose.getTicket());// station 29 has a bus connection, but MrX will use boat ticket as 
        //initially has 3 bus ticket and using it will reduce the point.
        System.out.println("choose" + choose.getStation().getIdentifier() + ", ticket, "
                + choose.getTicket() + ", weight " + choose.getWeight());

    }

}
