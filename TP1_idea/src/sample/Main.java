package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Controller.BaseController;

import java.io.IOException;

/**
 * Show the main menu and control it actions.
 * @author phrougerie and kevalente
 */
public class Main extends Application {

    /**
     * Places the element of the menu.
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        final String css = getClass().getResource("CSS/MainStyle.css").toExternalForm();
        AnchorPane root = FXMLLoader.load(getClass().getResource("View/mainPage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("J-Project");
        scene.getStylesheets().addAll(css);
        primaryStage.setScene(scene);

        BaseController bdcontr = new BaseController();
        bdcontr.createTableLevels();
        primaryStage.setWidth(367.0);
        primaryStage.setHeight(450);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    /**
     * Add the css menu to a scene and generate a new window corresponding to the scene.
     * @param sce the scene for generating window
     * @param title the title of the new window
     */
    @FXML
    private void menuWindowGen (AnchorPane root, Scene sce, String title)  {
        final String css = getClass().getResource("CSS/Menu.css").toExternalForm();
        Stage menuStage = new Stage();
        sce.getStylesheets().addAll(css);
        menuStage.setTitle(title);
        menuStage.setScene(sce);
        menuStage.initModality(Modality.APPLICATION_MODAL);
        menuStage.setWidth(361);
        menuStage.setHeight(449);
        menuStage.setResizable(false);
        menuStage.initStyle(StageStyle.UNDECORATED);
        menuStage.show();

    }

    /**
     * Show the play menu.
     * @throws IOException
     */
    @FXML
    private void showPlayMenu() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("View/PlayMenu.fxml"));
        Scene menuScene = new Scene(root);
        menuWindowGen(root, menuScene, "Menu");

    }

    /**
     * Show the Hall of Fame.
     * @throws IOException
     */
    @FXML
    private void showHall () throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("View/HallOfFame.fxml"));
        Scene hallScene = new Scene(root);
        menuWindowGen(root, hallScene, "Hall of fame");
    }

    /**
     * Show the Credits.
     * @throws IOException
     */
    @FXML
    private void showCredit () throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("View/Credit.fxml"));
        Scene credScene = new Scene(root);
        menuWindowGen(root, credScene, "Credits");
    }




    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }



}