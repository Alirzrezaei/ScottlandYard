package gui;

import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GUIConnector;

/**
 *
 * @author Alireza
 */
public class FXMLDocumentController implements Initializable {

    private boolean DetectivesAI = false;
    private boolean MisterXAI = true;
    private int detectives = 3;
    private Label[] lblMisterX;
    private Label[] lblTickets;

    @FXML
    private Pane pnMap;
    @FXML
    private Pane pnChange;

    private logic.Game game;
    private GUIConnector gui;
    @FXML
    private Label lblTaxi;
    @FXML
    private Label lblBus;
    @FXML
    private Label lblTrain;
    @FXML
    private Label lblBoat;
    @FXML
    private Label lblTaxiNum;
    @FXML
    private Label lblBusNum;
    @FXML
    private Label lblTrainNum;
    @FXML
    private Label lblBoatNum;
    @FXML
    private Label lblMisterXOne;
    @FXML
    private Label lblMisterXTwo;
    @FXML
    private Label lblMisterXThree;
    @FXML
    private Label lblMisterXFour;
    @FXML
    private Label lblMisterXFive;
    @FXML
    private Label lblMisterXSix;
    @FXML
    private Label lblMisterXSeven;
    @FXML
    private Label lblMisterXEight;
    @FXML
    private Label lblMisterXNine;
    @FXML
    private Label lblMisterXTen;
    @FXML
    private Label lblMisterXEleven;
    @FXML
    private Label lblMisterXTwelf;
    @FXML
    private Label lblMisterXThirteen;
    @FXML
    private Label lblMisterXFourteen;
    @FXML
    private Label lblMisterXFifteen;
    @FXML
    private Label lblMisterXSixteen;
    @FXML
    private Label lblMisterXSeventeen;
    @FXML
    private Label lblMisterXEighteen;
    @FXML
    private Label lblMisterXNinteen;
    @FXML
    private Label lblMisterXTwenty;
    @FXML
    private Label lblMisterXtwentyone;
    @FXML
    private Label lblMisterXTwentyTwo;
    @FXML
    private Label lblMisterXTwentythree;
    @FXML
    private Label lblMisterXTwentyfour;
    private ComboBox comboNumDetective;
    private CheckBox chkBoxMisterX;
    private CheckBox chkBoxDetectives;
    private Button btnOK;
    private Circle circle = null;
    private Polygon triangle = null;
    @FXML
    private MenuItem menuNewGame;
    @FXML
    private MenuItem MenuSave;
    @FXML
    private MenuItem menuLoad;
    @FXML
    private MenuItem howToPlay;
    @FXML
    private SplitPane splPnRight;

    final SplitPane s = new SplitPane();
    @FXML
    private Label lblTurn;

    private final String PATH = "gui/img/";
    private final String EXT = ".bmp";
    @FXML
    private CheckBox chkBlackTicket;
    @FXML
    private CheckMenuItem menuCheate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setPnMap();
        setTicketPictures();
        disableControls();
        lblsToArray();
        try {
            playerChooser();
        } catch (IOException ex) {
            showError();
            System.exit(0);
        }

        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {

            double diffWidth = (pnChange.getWidth() - pnChange.getMinWidth());
            double diffHeight = (pnChange.getHeight() - pnChange.getMinHeight());

            // pnMap.setPrefSize(pnChange.getMinWidth() + Math.min(diffWidth, diffHeight), pnChange.getMinHeight() + Math.min(diffWidth, diffHeight));
            pnMap.setPrefWidth(pnMap.getMinWidth() + Math.min(diffWidth, diffHeight));
            pnMap.setPrefHeight(pnMap.getMinHeight() + Math.min(diffWidth, diffHeight));

        };

        pnChange.widthProperty().addListener(stageSizeListener);
        pnChange.heightProperty().addListener(stageSizeListener);

    }

    private void setPnMap() {

        ImageView map = new ImageView();
        map.setImage(new Image(PATH + "Spielplan" + EXT));
        pnMap.getChildren().add(map);
        map.fitWidthProperty().bind(pnMap.widthProperty());
        map.fitHeightProperty().bind(pnMap.heightProperty());
    }

    private void setTicketPictures() {
        ImageView taxi = new ImageView();
        taxi.setImage(new Image(PATH + "Taxi" + EXT));
        lblTaxi.setGraphic(taxi);
        taxi.fitWidthProperty().bind(lblTaxi.widthProperty());
        taxi.fitHeightProperty().bind(lblTaxi.heightProperty());

        ImageView bus = new ImageView();
        bus.setImage(new Image(PATH + "Bus" + EXT));
        lblBus.setGraphic(bus);
        bus.fitWidthProperty().bind(lblBus.widthProperty());
        bus.fitHeightProperty().bind(lblBus.heightProperty());

        ImageView underground = new ImageView();
        underground.setImage(new Image(PATH + "Underground" + EXT));
        lblTrain.setGraphic(underground);
        underground.fitWidthProperty().bind(lblTrain.widthProperty());
        underground.fitHeightProperty().bind(lblTrain.heightProperty());

        ImageView boat = new ImageView();
        boat.setImage(new Image(PATH + "BlackTicket" + EXT));
        lblBoat.setGraphic(boat);
        boat.fitWidthProperty().bind(lblBoat.widthProperty());
        boat.fitHeightProperty().bind(lblBoat.heightProperty());
    }

    @FXML
    private void onClickMenuClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void onClickMenuAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("about me");
        alert.setContentText("The Scotland Yard board\n"
                + "Number of Players: 2-6 Players\n"
                + "Developed with: Java programming language\n"
                + "Requirement: JDK 8 or higher\n \n"
                + "Scotland Yard is a board game in which a team of players, as police, "
                + "cooperate to track down a player controlling a criminal around a board representing the streets of London, "
                + "first published in 1983.\n"
                + "\ndeveloped by Alireza Rezaei");
        alert.showAndWait();
    }

    private void playerChooser() throws IOException {

        if (game != null) {
            game.clear();
        }

        Label secondLabel = new Label("Detectives");

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("3", "4", "5");
        comboBox.setValue("3");

        CheckBox misterX = new CheckBox("Mister-by-AI");
        CheckBox detectiveSelection = new CheckBox("Detectives-by-AI");

        misterX.setSelected(true);

        Button btnOk = new Button("OK");

        StackPane secondaryLayout = new StackPane();

        secondaryLayout.getChildren().addAll(secondLabel, comboBox, misterX, detectiveSelection);
        secondaryLayout.getChildren().add(btnOk);
        btnOk.setPrefSize(75, 25);
        secondLabel.setTranslateX(-65);
        secondLabel.setTranslateY(-50);
        comboBox.setTranslateX(30);
        comboBox.setTranslateY(-50);
        misterX.setTranslateX(-60);
        misterX.setTranslateY(0);
        detectiveSelection.setTranslateX(-50);
        detectiveSelection.setTranslateY(20);
        btnOk.setTranslateX(0);
        btnOk.setTranslateY(50);

        Scene secondScene = new Scene(secondaryLayout, 250, 150);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Player Chooser");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(500);
        newWindow.setY(500);
        newWindow.setAlwaysOnTop(true);

        newWindow.show();

        btnOk.setOnAction((ActionEvent e) -> {
            if (comboBox.getValue().equals("3")) {
                detectives = 3;
            } else if (comboBox.getValue().equals("4")) {
                detectives = 4;
            } else if (comboBox.getValue().equals("5")) {
                detectives = 5;
            } else {
                detectives = 3;
            }

            if (misterX.isSelected()) {
                this.MisterXAI = true;
            } else {
                this.MisterXAI = false;
            }

            if (detectiveSelection.isSelected()) {
                this.DetectivesAI = true;
            } else {
                this.DetectivesAI = false;
            }
            newWindow.close();
            for (Node node : splPnRight.lookupAll(".split-pane-divider")) {
                node.setVisible(false);

            }
            chkBlackTicket.setDisable(false);
            this.gui = new javaFXGUI(pnMap, lblTurn, lblTickets, lblMisterX, chkBlackTicket);
            try {
                this.game = new logic.Game(this.gui, detectives, MisterXAI, DetectivesAI);
            } catch (FileNotFoundException ex) {

            } catch (IOException ex) {

            }

            pnMap.setDisable(false);

        });

    }

    private void disableControls() {
        pnMap.setDisable(true);
    }

    @FXML
    private void onClickNewGame(ActionEvent event) throws IOException {
        disableControls();
        playerChooser();
    }

    @FXML
    private void onClickSave(ActionEvent event) {
        game.save();
    }

    @FXML
    private void onClickLoad(ActionEvent event) throws FileNotFoundException {
        this.game = game.load(this.game);
    }

    @FXML
    private void onClickHowtoPlay(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("How to Play");
        alert.setHeaderText("Game Rules");
        Text t = new Text("Objective:\n");
        t.setFont(Font.font("Verdana", 20));
        t.setFill(Color.RED);
        alert.setContentText("Objective:\n"
                + "if you are MisterX, you must stay undercover to escape from your/n "
                + "pursuers until the detectives can no longer move\n"
                + "However if your a detective, your purpose if to catch MisterX by\n"
                + "moving onto the station where he is currently hiding\n \n"
                + "preparation:\n"
                + "-> One player becomes MisterX. The remaining players are detectives.\n "
                + "-> Share out the tickets;\n"
                + "   MisterX gets 4 Taxi, 3 bus, 3 underground and as many black tickets\n"
                + "   as there are detectives.\n"
                + "   each detective gets 10 taxi, 8 bus, 4 underground.\n"
                + "-> MisterX and detectives are placed on random starting stations.\n\n"
                + "Playing the Game: \n"
                + "-> When MisterX is controlled by human the figur will be shown on the map.\n"
                + "   But when it is controlled by AI, its location will not be revealed until \n"
                + "   it reached to round 3, 8, 13, 18. Its used tickets are placed on travel log.\n"
                + "-> The detectives should work individually in choosing their individual\n"
                + "   means of transport. They then give up a ticket and move their game figurs\n"
                + "   to the next stop. MisterX gets the used tickets.\n\n"
                + "End of the Game: \n"
                + "-> MisterX wins, if:\n"
                + "  a) the detectives are no longer to move, or\n"
                + "  b) MisterX reaches the last space in the travel log.\n"
                + "-> All detectives win, if one of them succeeds in moving onto MisterX station.\n\n"
                + "Black tickets: \n"
                + "MisterX can use black ticket in place of other tickets. The black tickets stand for\n"
                + "any means of transport and it is indeed a black day for  the detectives when one\n"
                + "appears, for it prevents them from knowing what means of transport Mr.X has used. \n"
                + " Even harder on the detectives is that Mr.X can use black ticket to travel with boat\n"
                + "(from point 194 to 157, from 157 to 115, from 115 to 108, or vice versa.)\n\n"
                + "Is more than on figure allowed to stand on one location?\n"
                + "-> No Two detectives may never stand on the same location.\n\n"
                + "Can player move back and forth?\n"
                + "-> Yes, it is possible to retrace your steps in subsequent move. Ofcourse you must \n"
                + "hand in a ticket.\n\n"
                + "Are the detectives allowed to exchange tickets?\n"
                + "-> No, each detevtives can only use his\\her own tickets. \n\n"
                + "What if a detective is unable to move?\n"
                + "-> The game goes on and other detectives tries to catch MisterX. ");
        alert.showAndWait();
    }

    @FXML
    private void handleMouseClickPane(javafx.scene.input.MouseEvent event) {

        double xNorm = event.getX() / this.pnMap.getWidth();
        double yNorm = event.getY() / this.pnMap.getHeight();

        this.game.getPositions(xNorm, yNorm, chkBlackTicket.isSelected());

    }

    private void lblsToArray() {

        //put the labels for mister's used tickets in an array
        lblMisterX = new Label[24];
        lblMisterX[0] = lblMisterXOne;
        lblMisterX[1] = lblMisterXTwo;
        lblMisterX[2] = lblMisterXThree;
        lblMisterX[3] = lblMisterXFour;
        lblMisterX[4] = lblMisterXFive;
        lblMisterX[5] = lblMisterXSix;
        lblMisterX[6] = lblMisterXSeven;
        lblMisterX[7] = lblMisterXEight;
        lblMisterX[8] = lblMisterXNine;
        lblMisterX[9] = lblMisterXTen;
        lblMisterX[10] = lblMisterXEleven;
        lblMisterX[11] = lblMisterXTwelf;
        lblMisterX[12] = lblMisterXThirteen;
        lblMisterX[13] = lblMisterXFourteen;
        lblMisterX[14] = lblMisterXFifteen;
        lblMisterX[15] = lblMisterXSixteen;
        lblMisterX[16] = lblMisterXSeventeen;
        lblMisterX[17] = lblMisterXEighteen;
        lblMisterX[18] = lblMisterXNinteen;
        lblMisterX[19] = lblMisterXTwenty;
        lblMisterX[20] = lblMisterXtwentyone;
        lblMisterX[21] = lblMisterXTwentyTwo;
        lblMisterX[22] = lblMisterXTwentythree;
        lblMisterX[23] = lblMisterXTwentyfour;

        // put the labels for tickets in an array
        lblTickets = new Label[4];
        lblTickets[0] = lblTaxiNum;
        lblTickets[1] = lblBusNum;
        lblTickets[2] = lblTrainNum;
        lblTickets[3] = lblBoatNum;
    }

    @FXML
    private void onCllichCheatMisterX(ActionEvent event) {
        if (menuCheate.isSelected()) {
            this.game.cheatMrX(true);
        } else {
            this.game.cheatMrX(false);
        }
    }
    private void showError(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Error Happend");
        alert.setContentText("The error has happend so the program is not wokring" 
              );
        alert.showAndWait();
    }
}
