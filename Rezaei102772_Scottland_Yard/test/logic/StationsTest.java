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
public class StationsTest {

    public StationsTest() {
    }

    @Test
    public void testListofStations() throws FileNotFoundException, JSONException {

        JSONParser j = new JSONParser();
        List<Station> stations;
        stations = j.JsonValidator(new FakeGUI());

        assertEquals(199, stations.size());
    }

    @Test
    public void testConstructor() {

        Station station = new Station(1);

        station.setCoordinates(new Position(0.12210915818686401968, 0.04791154791154791232));

        assertEquals(1, station.getIdentifier());
        assertEquals(0.12210915818686401968, station.getCoordinates().getX(), 0.000000000000001);
        assertEquals(.04791154791154791232, station.getCoordinates().getY(), 0.000000000000001);
    }

    @Test
    public void testTaxiNeghbors() {

        Station station = new Station(1);

        station.setCoordinates(new Position(0.12210915818686401968, 0.04791154791154791232));
        Station[] taxi = new Station[]{new Station(8), new Station(9)};
        Station[] bus = new Station[]{new Station(46), new Station(58)};
        Station[] train = new Station[]{new Station(46)};
        Station[] Boat = new Station[]{};

        station.setTaxiNeighbors(taxi);
        station.setBusNeighbors(bus);
        station.setTrainNeighbors(train);
        station.setBoatNeighbors(Boat);

        assertEquals(2, station.getTaxiNeighbors().length);
        assertEquals(8, station.getTaxiNeighbors()[0].getIdentifier());
        assertEquals(9, station.getTaxiNeighbors()[1].getIdentifier());
    }

    @Test
    public void testBusNeghbors() {

        Station station = new Station(1);

        station.setCoordinates(new Position(0.12210915818686401968, 0.04791154791154791232));
        Station[] taxi = new Station[]{new Station(8), new Station(9)};
        Station[] bus = new Station[]{new Station(46), new Station(58)};
        Station[] train = new Station[]{new Station(46)};
        Station[] Boat = new Station[]{};

        station.setTaxiNeighbors(taxi);
        station.setBusNeighbors(bus);
        station.setTrainNeighbors(train);
        station.setBoatNeighbors(Boat);

        assertEquals(2, station.getBusNeighbors().length);
        assertEquals(46, station.getBusNeighbors()[0].getIdentifier());
        assertEquals(58, station.getBusNeighbors()[1].getIdentifier());
    }

    @Test
    public void testTrainNeghbors() {

        Station station = new Station(1);
        station.setCoordinates(new Position(0.12210915818686401968, 0.04791154791154791232));
        Station[] taxi = new Station[]{new Station(8), new Station(9)};
        Station[] bus = new Station[]{new Station(46), new Station(58)};
        Station[] train = new Station[]{new Station(46)};
        Station[] Boat = new Station[]{};

        station.setTaxiNeighbors(taxi);
        station.setBusNeighbors(bus);
        station.setTrainNeighbors(train);
        station.setBoatNeighbors(Boat);

        assertEquals(1, station.getTrainNeighbors().length);
        assertEquals(46, station.getTrainNeighbors()[0].getIdentifier());

    }

    @Test
    public void testBoatNeghbors() {

        Station station = new Station(1);

        station.setCoordinates(new Position(0.12210915818686401968, 0.04791154791154791232));
        Station[] taxi = new Station[]{new Station(8), new Station(9)};
        Station[] bus = new Station[]{new Station(46), new Station(58)};
        Station[] train = new Station[]{new Station(46)};
        Station[] Boat = new Station[]{};

        station.setTaxiNeighbors(taxi);
        station.setBusNeighbors(bus);
        station.setTrainNeighbors(train);
        station.setBoatNeighbors(Boat);

        assertEquals(0, station.getBoatNeighbors().length);
    }

    @Test
    public void testisTaxiNeighbors() {

        Station station = new Station(1);

        station.setCoordinates(new Position(0.12210915818686401968, 0.04791154791154791232));
        Station[] taxi = new Station[]{new Station(8), new Station(9)};
        Station[] bus = new Station[]{new Station(46), new Station(58)};
        Station[] train = new Station[]{new Station(46)};
        Station[] Boat = new Station[]{};

        station.setTaxiNeighbors(taxi);
        station.setBusNeighbors(bus);
        station.setTrainNeighbors(train);
        station.setBoatNeighbors(Boat);

        assertTrue(station.isTaxiNeighbor(8));
        assertTrue(station.isTaxiNeighbor(9));
        assertFalse(station.isTaxiNeighbor(7));
    }

    @Test
    public void testisBusNeighbors() {

        Station station = new Station(1);

        station.setCoordinates(new Position(0.12210915818686401968, 0.04791154791154791232));
        Station[] taxi = new Station[]{new Station(8), new Station(9)};
        Station[] bus = new Station[]{new Station(46), new Station(58)};
        Station[] train = new Station[]{new Station(46)};
        Station[] Boat = new Station[]{};

        station.setTaxiNeighbors(taxi);
        station.setBusNeighbors(bus);
        station.setTrainNeighbors(train);
        station.setBoatNeighbors(Boat);

        assertTrue(station.isBusNeighbor(46));
        assertTrue(station.isBusNeighbor(58));
        assertFalse(station.isBusNeighbor(75));
        assertFalse(station.isBusNeighbor(45));
    }

    @Test
    public void testisTrainNeighbors() {

        Station station = new Station(1);

        station.setCoordinates(new Position(0.12210915818686401968, 0.04791154791154791232));
        Station[] taxi = new Station[]{new Station(8), new Station(9)};
        Station[] bus = new Station[]{new Station(46), new Station(58)};
        Station[] train = new Station[]{new Station(46)};
        Station[] Boat = new Station[]{};

        station.setTaxiNeighbors(taxi);
        station.setBusNeighbors(bus);
        station.setTrainNeighbors(train);
        station.setBoatNeighbors(Boat);

        assertTrue(station.isTrainNeighbor(46));
        assertFalse(station.isTrainNeighbor(58));
        assertFalse(station.isTrainNeighbor(75));
        assertFalse(station.isTrainNeighbor(45));
    }

    @Test
    public void testisBoatNeighbors() {

        Station station = new Station(108);

        station.setCoordinates(new Position(0.84458834412580940931, 0.52579852579852581762));
        Station[] taxi = new Station[]{new Station(105), new Station(117), new Station(119)};
        Station[] bus = new Station[]{new Station(105), new Station(116), new Station(135)};
        Station[] train = new Station[]{};
        Station[] Boat = new Station[]{new Station(115)};

        station.setTaxiNeighbors(taxi);
        station.setBusNeighbors(bus);
        station.setTrainNeighbors(train);
        station.setBoatNeighbors(Boat);

        assertTrue(station.isBoatNeighbor(115));
        assertFalse(station.isBoatNeighbor(116));
        assertFalse(station.isBoatNeighbor(105));
        assertFalse(station.isBoatNeighbor(135));
    }
}
