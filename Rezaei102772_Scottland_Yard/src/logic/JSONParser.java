package logic;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author ite102772
 */
public class JSONParser {

    private String jsonFile = "gui/jsonFile/net.json";
    private List<Station> stations = new LinkedList<>();

    private int[] getIntValues(JSONArray jsonData) throws JSONException {
        int[] intValue = new int[jsonData.length()];

        for (int i = 0; i < jsonData.length(); i++) {
            intValue[i] = jsonData.getInt(i);
        }

        return intValue;
    }

    private Station[] getStationFromInt(int[] neighbor) {
        Station[] temp = new Station[neighbor.length];

        for (int i = 0; i < temp.length; i++) {
            temp[i] = this.stations.get(neighbor[i] - 1);
        }

        return temp;
    }

    protected List<Station> JsonValidator(GUIConnector gui) {
        ErrorHandling errorHandler = new ErrorHandling();

        Gson gson = new Gson();
        JSONArray jsonArray = null;

        InputStreamReader reader;

        InputStream is = getClass().getClassLoader().getResourceAsStream(jsonFile);
        reader = new InputStreamReader(is);

        jsonArrayStations ja = gson.fromJson(reader, jsonArrayStations.class);

        if (errorHandler.jsonError(gui, ja)) {

            for (int i = 0; i < 199; i++) {
                this.stations.add(new Station(i + 1));
            }
            Station station;

            for (int i = 0; i < ja.getStations().length; i++) {
                this.stations.get(i).setCoordinates(ja.getStations()[i].getPosition());
                this.stations.get(i).setTaxiNeighbors(getStationFromInt(ja.getStations()[i].getCab()));
                this.stations.get(i).setBusNeighbors(getStationFromInt(ja.getStations()[i].getBus()));
                this.stations.get(i).setTrainNeighbors(getStationFromInt(ja.getStations()[i].getTube()));
                this.stations.get(i).setBoatNeighbors(getStationFromInt(ja.getStations()[i].getBoat()));
            }
        }

        return this.stations;
    }

}
