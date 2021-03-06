package gui;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import logic.GUIConnector;
import logic.Game;
import logic.Players;
import logic.TicketType;
import logic.playerType;

/**
 * javaFXGUI class is being used to update gui of the program. It receives all
 * the necessary elements from FXMLDocumentController during initializing and
 * then views will be updated from logic.
 *
 * @author Rezaei
 */
public class javaFXGUI implements GUIConnector {

    private Pane pnMain;
    private Label lblTurn;
    private Label[] lblTickets = new Label[4];
    private Label[] lblMisterXTickets = new Label[24];
    private Circle[] circle = new Circle[6];
    private Polygon[] triangle = new Polygon[6];
    private Text text;
    private ComboBox ticketSelection = null;
    private CheckBox chkBlackTicket;
    private String path = "gui/img/";
    private String ext = ".bmp";

    /**
     * constructor to make javaFXGUI.
     *
     * @param pnMain
     */
    public javaFXGUI(Pane pnMain, Label lblTurn, Label[] lblTickets, Label[] lblMisterXTickets, CheckBox chkBlackTicket) {
        this.pnMain = pnMain;
        this.lblTurn = lblTurn;
        this.lblTickets = lblTickets;
        this.lblMisterXTickets = lblMisterXTickets;
        this.chkBlackTicket = chkBlackTicket;
    }

    /**
     * this method is creating a circle object, bind it to main pane and return
     * it
     *
     * @param x
     * @param y
     * @param circle
     * @param color
     * @return circle
     */
    public Circle drawCircle(double x, double y, Circle circle, Polygon triangle, String color) {

        double xNorm = x * pnMain.getWidth();
        double yNorm = y * pnMain.getHeight();

        circle = new Circle();
        circle.setCenterX(15);
        circle.setCenterY(15);
        circle.setRadius(10);
        circle.setStyle("-fx-fill: " + color + ";");
        circle.setStroke(Color.BLACK);
        //bind the center position to the pane on which the circle is displayed 
        circle.centerXProperty().bind(this.pnMain.widthProperty().multiply(x));
        circle.centerYProperty().bind(this.pnMain.heightProperty().multiply(y));

        // add the circle to the pane
        this.pnMain.getChildren().add(circle);
        return circle;
    }

    /**
     * this method is creating a circle object, bind it to main pane and return
     * it
     *
     * @param x
     * @param y
     * @param circle
     * @param color
     * @return circle
     */
    public Circle drawCircleMisterX(double x, double y, Circle circle, String color) {

        double xNorm = x * pnMain.getWidth();
        double yNorm = y * pnMain.getHeight();
        text = createText("?");
        circle = new Circle();
        circle.setCenterX(30);
        circle.setCenterY(30);
        //circle.setRadius(20);
        circle.setStyle("-fx-fill: " + color + ";");
        circle.setStroke(Color.BLACK);
        circle.setRadius(getWidth(text) / 2.4 + 10);
        //bind the center position to the pane on which the circle is displayed 
        circle.centerXProperty().bind(this.pnMain.widthProperty().multiply(x));
        circle.centerYProperty().bind(this.pnMain.heightProperty().multiply(y));
        text.translateXProperty().bind(circle.centerXProperty().subtract(7.0));
        text.translateYProperty().bind(circle.centerYProperty().add(10.0));

        // add the circle to the pane
        this.pnMain.getChildren().addAll(circle, text);
        //this.pnMain.getChildren().add(text);
        return circle;
    }

    /**
     * this method is creating text with given string value and it apply
     * different styles on it
     *
     * @param string
     * @return Text a styled text
     */
    private Text createText(String string) {
        Text text = new Text(string);
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setStyle(
                "-fx-font-family: \"Times New Roman\";"
                + "-fx-font-weight: bold;"
                + "-fx-font-size: 28px;"
                + "-fx-fill: white;"
        );

        return text;
    }

    /**
     * this method is applying css styles on text and return width of
     *
     * @param text
     * @return double width of text
     */
    private double getWidth(Text text) {
        new Scene(new Group(text));
        text.applyCss();

        return text.getLayoutBounds().getWidth();
    }

    /**
     * this method is creating an instance of Polygon (triangle) using given
     * parameters and bind it to given circle.
     *
     *
     * @param x
     * @param y
     * @param triangle
     * @param circle
     * @return a triangle
     */
    private Polygon drawPolygon(double x, double y, Polygon triangle, Circle circle) {

        triangle = new Polygon();

        triangle.getPoints().addAll(new Double[]{
            x, (y + 9.0),
            (x + 8.0), (y + 25.0),
            (x - 8.0), (y + 25.0)});
        triangle.setFill(javafx.scene.paint.Color.LIGHTGRAY);
        triangle.setStroke(Color.BLACK);
        // bind the x and y properties of triangle to center of the given circle
        triangle.translateXProperty().bind(circle.centerXProperty());
        triangle.translateYProperty().bind(circle.centerYProperty());

        // add the triangle to the pane
        this.pnMain.getChildren().add(triangle);

        return triangle;
    }

    /**
     * this method is to create a circle and a triangle for the given player on
     * the gui board
     *
     * @param x
     * @param y
     * @param player
     */
    @Override
    public void circle(double x, double y, int player) {

        //remove existing circle and triangle from the pane
        if (circle[player] != null) {
            pnMain.getChildren().remove(circle[player]);
            pnMain.getChildren().remove(triangle[player]);
        }
        if (player != 0) {
            circle[player] = drawCircle(x, y, circle[player], triangle[player], Colors.values()[player].toString().toLowerCase());
            triangle[player] = drawPolygon(x, y, triangle[player], circle[player]);
        } else {
            pnMain.getChildren().remove(text);
            circle[player] = drawCircleMisterX(x, y, circle[player], Colors.values()[player].toString().toLowerCase());
        }

    }

    @Override
    public void updateLblTurn(int pl) {
        assert (pl > -1 && pl < 6);
        String s = "";

        if (pl == 0) {
            s = "MisterX";
        } else {
            s = s + Colors.values()[pl];
        }

        lblTurn.setText("Turn is: " + s);
        lblTurn.setAlignment(Pos.CENTER);
        lblTurn.setStyle("-fx-font-weight: bold;" + "-fx-background-color: lightgray;");
    }

    @Override
    public void updateLblsTickets(Players player) {
        for (int i = 0; i < lblTickets.length; i++) {
            lblTickets[i].setText("" + player.getAllTickets().get(TicketType.values()[i]));
            lblTickets[i].setAlignment(Pos.CENTER);
            lblTickets[i].setStyle("-fx-font-weight: bold;" + "-fx-background-color: lightgray;");
        }
    }

    @Override
    public void showDropDawnList(Game game, boolean[] tickets, double x, double y, int station, boolean blackTicket) {

        ticketSelection = new ComboBox();

        ticketSelection.setValue("Choose a Ticket");

        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i]) {
                ticketSelection.getItems().add(TicketType.values()[i].toString());
            }
        }

        ticketSelection.translateXProperty().bind(this.pnMain.widthProperty().multiply(x));
        ticketSelection.translateYProperty().bind(this.pnMain.heightProperty().multiply(y));

        this.pnMain.getChildren().add(ticketSelection);
        clickOnComboBox(game, x, y, station, blackTicket);

    }

    /**
     * this method is getting the user selection and then call doTurn method
     * from game to put the update user's information in logic and gui
     *
     * @param game
     * @param x
     * @param y
     * @param station
     */
    private void clickOnComboBox(Game game, double x, double y, int station, boolean blackTicket) {

        if (ticketSelection != null) {
            ticketSelection.valueProperty().addListener((e) -> {
                pnMain.setDisable(true);
                String t = ticketSelection.getValue().toString();
                this.pnMain.getChildren().remove(ticketSelection);
                TicketType type = null;
                for (int i = 0; i < TicketType.values().length && type == null; i++) {
                    if (TicketType.values()[i].toString().equals(t)) {
                        type = TicketType.values()[i];
                    }
                }
                game.doTurn(x, y, type, station, blackTicket);
                pnMain.setDisable(false);
            });
        }

    }

    /**
     * this method is opening a windows to show the winner of the game.
     *
     * @param type
     */
    @Override
    public void endGame(playerType type) {
        this.pnMain.setDisable(true);
        chkBlackTicket.setDisable(true);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setHeaderText("Winner");
        String s = "";
        if (type == playerType.MisterX) {
            s = "MisterX is the winner.";
        } else {
            s = "Detectives are the winner.";
        }
        alert.setContentText(s);
        alert.showAndWait(); 
    }

    /**
     * this method is removing all circles and triangles from gui
     */
    @Override
    public void clear() {
        for (int i = 0; i < circle.length; i++) {
            if (circle[i] != null) {
                this.pnMain.getChildren().removeAll(circle[i], triangle[i]);
                if (i == 0) {
                    this.pnMain.getChildren().remove(text);
                }
            }
        }
        for (int i = 0; i < lblMisterXTickets.length; i++) {
            this.lblMisterXTickets[i].setGraphic(null);

        }
    }

    @Override
    public void UsedMisterxTickets(int idx, TicketType type) {
        String s = "";
        if (type == TicketType.Boat) {
            s = "BlackTicket";
        } else {
            s = type.toString();
        }

        ImageView imgVwTicket = new ImageView();
        imgVwTicket.setImage(new Image(path + s + ext));
        lblMisterXTickets[idx].setGraphic(imgVwTicket);

        imgVwTicket.setFitHeight(45.0);
        imgVwTicket.setFitWidth(65.0);
    }

    @Override
    public void checkBoxBlackTicket(boolean enable) {
        chkBlackTicket.setDisable(enable);
   
    }

    public void notShowMisterX() {
        //remove existing circle and triangle from the pane
        if (circle[0] != null) {
            pnMain.getChildren().remove(circle[0]);
            pnMain.getChildren().remove(triangle[0]);
            pnMain.getChildren().remove(text);
        }
    }

    @Override
    public void showError(String error) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        String s = "" + error + " is not right. please be sure that the correct file is provided";

        alert.setContentText(s);
        alert.showAndWait();
        
    }
}
