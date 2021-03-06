package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.stage.FileChooser;
import org.json.JSONArray;

/**
 *
 * @author ite102772
 */
public class IO {

    private Game game;

    private FileOutputStream fos;
    private OutputStreamWriter out;
    private String toprint = "";
    private String startValues = "";
    private Players[] players;
    private String endGame = "";
    private String separator = "/";

    IO() {

    }

    Game Load(GUIConnector gui, Game game) throws FileNotFoundException {
        this.game = game;
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.setTitle("Choose location To Save Report");
        File selectedFile = null;
        if (selectedFile == null) {
            selectedFile = chooser.showOpenDialog(null);
        }

        Gson gson = new Gson();
        //JSONArray jsonArray = null;

        FileReader reader = new FileReader(selectedFile.getPath());

        SaveLoad load = gson.fromJson(reader, SaveLoad.class);

        ErrorHandling errorHandler = new ErrorHandling();
        if (errorHandler.loadError(gui, load)) {
            Players[] players = new Players[load.getDetectives().getNoOfDetectives() + 1];

            players[0] = new MisterX(load.getMisterX().isAi(), playerType.MisterX,
                    load.getMisterX().getRemainingTickets()[2], load.getMisterX().getRemainingTickets()[1],
                    load.getMisterX().getRemainingTickets()[0], load.getMisterX().getRemainingTickets()[3]);
            players[0].setCurrentStation(load.getMisterX().getCurrPos());
            players[0].setLastShowingStation(load.getMisterX().getLastShownPos());
            players[0].setJourneyBoard(Arrays.stream(load.getMisterX().getJourneyBoard()).boxed().collect(Collectors.toList()));

            for (int i = 1; i < players.length; i++) {
                players[i] = new Detectives(load.getDetectives().isAi(), playerType.Detective,
                        load.getDetectives().getPlayers()[i - 1].getRemainingTickets()[2],
                        load.getDetectives().getPlayers()[i - 1].getRemainingTickets()[1],
                        load.getDetectives().getPlayers()[i - 1].getRemainingTickets()[0],
                        load.getDetectives().getPlayers()[i - 1].getPosition());
            }

            this.game = new Game(gui, players, load.getWhosTurn(), load.isGameIsWon(), load.getCurrRoundNo(), load.getMisterX().getPossibleTargets());

        }
        return this.game;
    }

    void save(Players[] player, List<Station> targetStations, boolean won, int round, int whosTurn) {
        Gson gson = new Gson();

        //Mister X object 
        LoadMisterX mrX = new LoadMisterX();

        mrX.setAi(player[0].isAIControlled());
        mrX.setPossibleTargets(listToArray(targetStations));
        mrX.setLastShownPos(player[0].getLastShowingStation());
        mrX.setCurrPos(player[0].getCurrentStation());
        mrX.setRemainingTickets(new int[]{player[0].getTrainTickets(), player[0].getBusTickets(),
            player[0].getTaxiTickets(), player[0].getBoatTickets()});
        mrX.setJourneyBoard(player[0].getJourneyBoard().stream().mapToInt(Integer::intValue).toArray());

        //detectives players object
        LoadPlayers[] detectives = new LoadPlayers[player.length - 1];
        for (int i = 0; i < detectives.length; i++) {
            detectives[i] = new LoadPlayers();
            detectives[i].setPosition(player[i + 1].getCurrentStation());
            detectives[i].setRemainingTickets(new int[]{player[i + 1].getTrainTickets(),
                player[i + 1].getBusTickets(), player[i + 1].getTaxiTickets()});

        }

        //detective object
        LoadDetective detective = new LoadDetective();
        detective.setAi(player[1].isAIControlled());
        detective.setNoOfDetectives(player.length - 1);
        detective.setPlayers(detectives);

        //main object
        SaveLoad save = new SaveLoad();
        save.setMisterX(mrX);
        save.setDetectives(detective);
        save.setGameIsWon(won);
        save.setCurrRoundNo(round);
        save.setWhosTurn(whosTurn);

        jsonToFile(save);

    }

    private void jsonToFile(SaveLoad obj) {
        assert (obj != null);

        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.setTitle("Choose location To Save the Game");
        File selectedFile = null;
        if (selectedFile == null) {
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(".json", ".json"),
                    new FileChooser.ExtensionFilter(".txt", ".txt"));
            selectedFile = chooser.showSaveDialog(null);
        }
        if (selectedFile != null) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (FileWriter writer = new FileWriter(selectedFile.getPath())) {
                gson.toJson(obj, writer);
                writer.flush();
            } catch (IOException e) {
            }
        }
    }

    int[] listToArray(List<Station> targets) {
        int[] temp = new int[targets.size()];
        for (int i = 0; i < targets.size(); i++) {
            temp[i] = targets.get(i).getIdentifier();
        }
        return temp;
    }

    IO(String s) {
        try {

            URL url = IO.class.getProtectionDomain().getCodeSource().getLocation();
            String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
            String parentPath = new File(jarPath).getParentFile().getPath();

            this.fos = new FileOutputStream(parentPath + separator + s);
            this.out = new OutputStreamWriter(this.fos);
        } catch (IOException ex) {

        }
    }

    protected void logger(Players[] players, int idxCurrent) {
        try {
            toprint = idxCurrent + ", "
                    + players[idxCurrent].getCurrentStation() + ", "
                    + players[idxCurrent].getCurrentStation() + ", "
                    + players[idxCurrent].getTrainTickets() + ", "
                    + players[idxCurrent].getBusTickets() + ", "
                    + players[idxCurrent].getTaxiTickets() + ", "
                    + players[idxCurrent].getBoatTickets() + ", "
                    + idxCurrent + ", "
                    + players[idxCurrent].getWeight() + "\n";

    
            out.append(toprint);
            out.flush();
        } catch (IOException ex) {
           
        }
    }

    protected void startOfLog(Players[] players) throws IOException {
        this.players = players;
        startValues += players.length - 1 + ", " + players[0].isAIControlled()
                + ", " + players[1].isAIControlled() + "";
        for (int i = 0; i < players.length; i++) {
            startValues += ", " + players[i].getCurrentStation();
        }
        startValues += "\n";
        out.append(startValues);
    }

    public void closeLog() {
        try {
            if (fos != null) {
                fos.close();
            }
            if (out != null) {
                out.close();
            }
        } catch (IOException ex) {

        }
    }
}
