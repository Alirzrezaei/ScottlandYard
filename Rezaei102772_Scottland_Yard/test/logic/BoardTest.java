package logic;

import java.io.FileNotFoundException;
import org.json.JSONException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rezaei
 */
public class BoardTest {

    @Test
    public void testConstructor() throws FileNotFoundException, JSONException {
        JSONParser parser = new JSONParser();
        Board board = new Board(parser.JsonValidator(new FakeGUI()));

        assertEquals(199, board.getStations().size());
    }

    @Test
    public void testGetIdentifier() throws FileNotFoundException, JSONException {
        JSONParser parser = new JSONParser();
        Board board = new Board(parser.JsonValidator(new FakeGUI()));

        assertEquals(199, board.getStations().size());

        assertEquals(1, board.getIdentifier(0));
        assertEquals(50, board.getIdentifier(49));
    }

    @Test
    public void testIsTaxiNeighbor() throws FileNotFoundException, JSONException {
        JSONParser parser = new JSONParser();
        Board board = new Board(parser.JsonValidator(new FakeGUI()));

        assertEquals(199, board.getStations().size());

        assertTrue(board.isTaxiNeighbor(108, 117));
        assertTrue(board.isTaxiNeighbor(108, 105));
        assertTrue(board.isTaxiNeighbor(108, 119));

        assertFalse(board.isTaxiNeighbor(108, 115));
        assertFalse(board.isTaxiNeighbor(108, 135));
    }

    @Test
    public void testIsBusNeighbor() throws FileNotFoundException, JSONException {
        JSONParser parser = new JSONParser();
        Board board = new Board(parser.JsonValidator(new FakeGUI()));

        assertEquals(199, board.getStations().size());

        assertTrue(board.isBusNeighbor(105, 72));
        assertTrue(board.isBusNeighbor(105, 89));
        assertTrue(board.isBusNeighbor(105, 108));

        assertFalse(board.isBusNeighbor(105, 90));
        assertFalse(board.isBusNeighbor(105, 106));
    }

    @Test
    public void testIsTrainNeighbor() throws FileNotFoundException, JSONException {
        JSONParser parser = new JSONParser();
        Board board = new Board(parser.JsonValidator(new FakeGUI()));

        assertEquals(199, board.getStations().size());

        assertTrue(board.isTrainNeighbor(140, 89));
        assertTrue(board.isTrainNeighbor(140, 128));
        assertTrue(board.isTrainNeighbor(140, 153));

        assertFalse(board.isTrainNeighbor(140, 133));
        assertFalse(board.isTrainNeighbor(140, 154));

        assertFalse(board.isTrainNeighbor(139, 154));
    }

    @Test
    public void testIsBoatNeighbor() throws FileNotFoundException, JSONException {
        JSONParser parser = new JSONParser();
        Board board = new Board(parser.JsonValidator(new FakeGUI()));

        assertEquals(199, board.getStations().size());

        assertTrue(board.isBoatNeighbor(108, 115));

        assertFalse(board.isBoatNeighbor(108, 133));
        assertFalse(board.isBoatNeighbor(108, 154));

        assertFalse(board.isBoatNeighbor(139, 154));
    }

}
