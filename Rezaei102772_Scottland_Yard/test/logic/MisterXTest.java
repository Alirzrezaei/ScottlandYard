/*
 * An advance programming project to implement Scotland_Yard game board
 */
package logic;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rezaei
 */
public class MisterXTest {

    public MisterXTest() {
    }

    @Test
    public void testMisterX_hasInitialTicket_busRoute() {

        Players[] players = new Players[4];

        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);

        Game game = new Game(new FakeGUI(), players, new int[]{1, 2, 3, 4});

        players[0].setCurrentStation(15);
        players[1].setCurrentStation(13);
        players[2].setCurrentStation(52);
        players[3].setCurrentStation(54);

        Choose choose = players[0].AI(players, game.getBoard(), game.getExpectedStation());
        assertEquals(29, choose.getStation().getIdentifier());// as there is no detective around it. 3-0
        assertEquals(TicketType.Boat, choose.getTicket());// station 29 has a bus connection, but MrX will use boat ticket as 
        //initially has 3 bus ticket and using it will reduce the point. 
    }

    @Test
    public void testMisterX_hasMoreBusTicket_busRoute() {

        Players[] players = new Players[4];

        players[0] = new MisterX(true, playerType.MisterX, 4, 4, 4, 4);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);

        Game game = new Game(new FakeGUI(), players, new int[]{1, 2, 3, 4});

        players[0].setCurrentStation(15);
        players[1].setCurrentStation(13);
        players[2].setCurrentStation(52);
        players[3].setCurrentStation(54);

        Choose choose = players[0].AI(players, game.getBoard(), game.getExpectedStation());
        assertEquals(29, choose.getStation().getIdentifier());// as there is no detective around it. 3-0
        assertEquals(TicketType.Bus, choose.getTicket());// station 29 has a bus connection, but MrX will use Bus ticket as 
        //he has more than 3 ticket of this type and the point for bus ticket will be 3
    }

    @Test
    public void testMisterX_oneDetectiveNextToMisterX() {

        Players[] players = new Players[4];

        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);

        Game game = new Game(new FakeGUI(), players, new int[]{1, 2, 3, 4});

        players[0].setCurrentStation(113);
        players[1].setCurrentStation(100);// next to MrX
        players[2].setCurrentStation(132);
        players[3].setCurrentStation(111);

        Choose choose = players[0].AI(players, game.getBoard(), game.getExpectedStation());
        assertEquals(125, choose.getStation().getIdentifier());// as there is no detective around it. 3-0
        assertEquals(TicketType.Taxi, choose.getTicket());

    }

    @Test
    public void testMisterX_oneDetectiveNeighborsMisterX_useBlackTicket() {

        Players[] players = new Players[4];

        players[0] = new MisterX(true, playerType.MisterX, 3/*Taxi*/, 5/*Bus*/, 3/*Train*/, 4/*Boat*/);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);

        Game game = new Game(new FakeGUI(), players, new int[]{1, 2, 3, 4});

        players[0].setCurrentStation(168);
        players[1].setCurrentStation(185);
        players[2].setCurrentStation(155);//next to MrX
        players[3].setCurrentStation(153);

        Choose choose = players[0].AI(players, game.getBoard(), game.getExpectedStation());
        assertEquals(184, choose.getStation().getIdentifier());
        assertEquals(TicketType.Boat, choose.getTicket());

    }

    @Test
    public void testMisterX_allDetectiveNeighborsMisterX() {

        Players[] players = new Players[4];

        players[0] = new MisterX(true, playerType.MisterX, 3, 5, 2, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);

        Game game = new Game(new FakeGUI(), players, new int[]{1, 2, 3, 4});

        players[0].setCurrentStation(168); // has three following neighbors, 167, 155, 184

        players[1].setCurrentStation(167);//next to MrX
        players[2].setCurrentStation(155);//next to MrX
        players[3].setCurrentStation(184);//next to MrX

        Choose choose = players[0].AI(players, game.getBoard(), game.getExpectedStation());

        assertEquals(null, choose);//there is not free stations to go

    }

}
