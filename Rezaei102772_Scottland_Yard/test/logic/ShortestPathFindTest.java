/*
 * An advance programming project to implement Scotland_Yard game board
 */
package logic;

import java.io.FileNotFoundException;
import java.util.List;
import org.json.JSONException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rezaei
 */
public class ShortestPathFindTest {

    public ShortestPathFindTest() {
    }

    @Test
    public void testSomeMethod() throws FileNotFoundException, JSONException {
        JSONParser js = new JSONParser();
        Board br = new Board(js.JsonValidator(new FakeGUI()));

        ShortestPathFind path = new ShortestPathFind();
        Players[] players = new Players[4];
        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);
        players[0].setCurrentStation(104);
        players[1].setCurrentStation(134);
        players[2].setCurrentStation(71);
        players[3].setCurrentStation(186);

        List<Station> route = path.bestPath(br.getStation(71), br.getStation(116), players);

        assertNotNull(route);

        assertEquals(5, route.size());

        for (int i = 0; i < route.size(); i++) {
            System.out.println("tests: " + route.get(i).getIdentifier());
        }
    }

    @Test
    public void test_station61_63() throws FileNotFoundException, JSONException {
        JSONParser js = new JSONParser();
        Board br = new Board(js.JsonValidator(new FakeGUI()));

        ShortestPathFind path = new ShortestPathFind();

        Players[] players = new Players[4];
        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);
        players[0].setCurrentStation(104);
        players[1].setCurrentStation(134);
        players[2].setCurrentStation(71);
        players[3].setCurrentStation(186);
        List<Station> route = path.bestPath(br.getStation(61), br.getStation(63), players);

        assertNotNull(route);

        assertEquals(4, route.size());

        for (int i = 0; i < route.size(); i++) {
            System.out.println("tests: " + route.get(i).getIdentifier());
        }

    }

    @Test
    public void test_station3_36() throws FileNotFoundException, JSONException {
        JSONParser js = new JSONParser();
        Board br = new Board(js.JsonValidator(new FakeGUI()));

        ShortestPathFind path = new ShortestPathFind();
        Players[] players = new Players[4];
        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);
        players[0].setCurrentStation(104);
        players[1].setCurrentStation(134);
        players[2].setCurrentStation(71);
        players[3].setCurrentStation(186);
        List<Station> route = path.bestPath(br.getStation(3), br.getStation(36), players);

        assertNotNull(route);

        assertEquals(4, route.size());

        for (int i = 0; i < route.size(); i++) {
            System.out.println("tests: " + route.get(i).getIdentifier());
        }

    }

    @Test
    public void test_station29_7() throws FileNotFoundException, JSONException {
        JSONParser js = new JSONParser();
        Board br = new Board(js.JsonValidator(new FakeGUI()));

        ShortestPathFind path = new ShortestPathFind();
        Players[] players = new Players[4];
        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);
        players[0].setCurrentStation(104);
        players[1].setCurrentStation(134);
        players[2].setCurrentStation(71);
        players[3].setCurrentStation(186);
        List<Station> route = path.bestPath(br.getStation(29), br.getStation(7), players);

        assertNotNull(route);

        assertEquals(3, route.size());

        for (int i = 0; i < route.size(); i++) {
            System.out.println("tests: " + route.get(i).getIdentifier());
        }

    }

    @Test
    public void test_station1_175() throws FileNotFoundException, JSONException {
        JSONParser js = new JSONParser();
        Board br = new Board(js.JsonValidator(new FakeGUI()));

        ShortestPathFind path = new ShortestPathFind();
        Players[] players = new Players[4];
        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);
        players[0].setCurrentStation(104);
        players[1].setCurrentStation(134);
        players[2].setCurrentStation(71);
        players[3].setCurrentStation(186);
        List<Station> route = path.bestPath(br.getStation(1), br.getStation(175), players);

        assertNotNull(route);

        assertEquals(8, route.size());

        for (int i = 0; i < route.size(); i++) {
            System.out.println("tests: " + route.get(i).getIdentifier());
        }

    }

    @Test
    public void test_station19_175() throws FileNotFoundException, JSONException {
        JSONParser js = new JSONParser();
        Board br = new Board(js.JsonValidator(new FakeGUI()));

        ShortestPathFind path = new ShortestPathFind();
        Players[] players = new Players[4];
        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);
        players[0].setCurrentStation(104);
        players[1].setCurrentStation(134);
        players[2].setCurrentStation(71);
        players[3].setCurrentStation(186);
        List<Station> route = path.bestPath(br.getStation(19), br.getStation(175), players);

        assertNotNull(route);

        assertEquals(10, route.size());

        for (int i = 0; i < route.size(); i++) {
            System.out.println("tests: " + route.get(i).getIdentifier());
        }

    }

    @Test
    public void test_station55_116() throws FileNotFoundException, JSONException {
        JSONParser js = new JSONParser();
        Board br = new Board(js.JsonValidator(new FakeGUI()));

        ShortestPathFind path = new ShortestPathFind();
        Players[] players = new Players[4];
        players[0] = new MisterX(true, playerType.MisterX, 3);
        players[1] = new Detectives(false, playerType.Detective);
        players[2] = new Detectives(false, playerType.Detective);
        players[3] = new Detectives(false, playerType.Detective);
        players[0].setCurrentStation(104);
        players[1].setCurrentStation(134);
        players[2].setCurrentStation(71);
        players[3].setCurrentStation(89);
        List<Station> route = path.bestPath(br.getStation(55), br.getStation(116), players);

        assertNotNull(route);

        assertEquals(6, route.size());

        for (int i = 0; i < route.size(); i++) {
            System.out.println("tests: " + route.get(i).getIdentifier());
        }
    }

}
