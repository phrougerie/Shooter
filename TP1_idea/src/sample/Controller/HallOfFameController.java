package sample.Controller;



import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import sample.Level.Level;
import sample.Level.Level1;
import sample.Level.Level2;
import sample.Level.Level3;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Hall of fame menu controller.
 * @author phrougerie
 */
public class HallOfFameController implements Initializable {

    private int nbl = 3;

    private int idStage = 0;
    private BaseController bc = new BaseController();
    private Level currentLvl = new Level1();
    private Level[] allLevel = new Level[nbl];
    private String link = "src/sample/Audio/SE/SE1.wav";
    private AudioClip prevNextSE = new AudioClip(new File(link).toURI().toString());
    private StringProperty scores = new SimpleStringProperty(bc.highScoreLevel(idStage+1));
    public String getScores() {return scores.get();}
    public StringProperty scoresProperty() {return scores; }
    private StringProperty stageName = new SimpleStringProperty(currentLvl.getNom());
    public String getStageName() {return stageName.get();}
    public StringProperty stageNameProperty() {return stageName;}

    @FXML
    private javafx.scene.image.ImageView bg;
    @FXML
    private javafx.scene.control.Button Exit;


    /**
     * Initialise level list and background first image.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allLevel[0] = new Level1();
        allLevel[1] = new Level2();
        allLevel[2] = new Level3();
        bg.setImage(currentLvl.getBgPreview());
    }

    /**
     * Return to the main menu.
     */
    @FXML
    private void onExit(){
        // get a handle to the stage
        Stage stage = (Stage) Exit.getScene().getWindow();
        // do what you have to do
        stage.close();
    }


    /**
     * Check if a stage have already high score.
     * @param idStage Nummber of the stage to check
     */
    @FXML
    private void verifLevel(int idStage){
        Boolean verif=bc.verifLevel(idStage+1);
        if(verif){
            scores.setValue(bc.highScoreLevel(idStage+1));
        }
        else {
            scores.setValue("Aucun score ");
        }
    }

    /**
     * Select the previous Stage. If the current stage is the first, the last Stage is displayed.
     */
    @FXML
    private void previousStage(){
        if(idStage == 0 ) { idStage = nbl-1;
            verifLevel(idStage);
        }
        else{ idStage--;
            verifLevel(idStage);
        }
        majStage();
    }


    /**
     * Select the next Stage. If the current stage is the last, the first Stage is displayed.
     */
    @FXML
    private void nextStage(){
        if(idStage == nbl-1) { idStage = 0;
            verifLevel(idStage);
        }
        else{ idStage++;
            verifLevel(idStage);
        }
        majStage();
    }

    /**
     * Update backgrounds and current level.
     */
    private void majStage(){
        currentLvl = allLevel[idStage];
        bg.setImage(currentLvl.getBgPreview());
        stageName.setValue(currentLvl.getNom());
        prevNextSE.play();
    }


}
