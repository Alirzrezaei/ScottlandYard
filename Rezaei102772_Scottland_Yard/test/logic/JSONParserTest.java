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
public class JSONParserTest {

    @Test
    public void testJSONParser_ListofStations() throws FileNotFoundException, JSONException {
        JSONParser j = new JSONParser();
        List<Station> stations;
        stations = j.JsonValidator(new FakeGUI());

        assertEquals(199, stations.size());
    }

    @Test
    public void testJSONParser_testValidation() throws FileNotFoundException, JSONException {
        JSONParser j = new JSONParser();
//        List<Station> stations;
//        stations = j.jsonParsing();
//        
//        assertEquals(199, stations.size());
        j.JsonValidator(new FakeGUI());
    }

}
