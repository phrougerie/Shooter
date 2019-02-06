package sample.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import sample.*;
import sample.Chara.Chara;
import sample.Chara.Chara1;
import sample.Chara.Chara2;
import sample.Level.*;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import java.text.NumberFormat;

/**
 * Game option selection menu controller.
 * Initialise the playground for the next Game.
 * The background is composed of the stage preview and the character preview and is upload if one of the two changes.
 *
 * @author kevalente
 */
public class PlayMenuController implements Initializable {

    //Initial Volume
    private static final Integer INIT_VALUE = 50;

    private IntegerStringConverter isc = new IntegerStringConverter();

    private PlayGround pg;

    private int nbc = 2;  //Character's number
    private int nbl = 3; //Stage's number
    private int idChara = 0; //Position in the character array
    private int idStage = 0; //Position in the level array
    private Chara[] allChara = new Chara[nbc];
    private Level[] allLevel = new Level[nbl];

    private double vol = INIT_VALUE; //Volume

    private Level currentLvl = new Level1();
    private Chara currentChara = new Chara1();

    private String link = "src/sample/Audio/SE/SE1.wav";
    private AudioClip prevNextSE = new AudioClip(new File(link).toURI().toString()); //Button SoundEffect
    private MediaPlayer music = null;

    private StringProperty name = new SimpleStringProperty(currentChara.getNom());

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    private StringProperty stageName = new SimpleStringProperty(currentLvl.getNom());

    public String getStageName() {
        return stageName.get();
    }

    public StringProperty stageNameProperty() {
        return stageName;
    }

    @FXML
    private javafx.scene.image.ImageView preview;
    @FXML
    private javafx.scene.image.ImageView bg;
    @FXML
    private javafx.scene.control.Button Exit;
    @FXML
    private javafx.scene.control.Slider slider;
    @FXML
    private javafx.scene.control.Label lab;

    /**
     * initialize the stage and chara array, the initial char and stage selected, the volume and the fxml objects.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allChara[0] = new Chara1();
        allChara[1] = new Chara2();
        allLevel[0] = new Level1();
        allLevel[1] = new Level2();
        allLevel[2] = new Level3();
        preview.setImage(currentChara.getCharaView());
        bg.setImage(currentLvl.getBgPreview());
        slider.setValue(INIT_VALUE);
        lab.setText(isc.toString(INIT_VALUE));
        lab.textProperty().bindBidirectional(slider.valueProperty(), NumberFormat.getIntegerInstance());
        slider.setBlockIncrement(15);
        slider.setMajorTickUnit(15);
        majMusic();
        slider.valueProperty().addListener((ov, old_val, new_val) -> {
            vol = new_val.intValue() / 100.00;
            music.setVolume(vol);
        });

    }

    /**
     * Change the music to correspond with the current level.
     */
    private void majMusic() {
        try {
            String sound = currentLvl.getMusic();
            Media mediaSound = new Media(new File(sound).toURI().toString());
            music = new MediaPlayer(mediaSound);
            music.setCycleCount(MediaPlayer.INDEFINITE);
            music.play();
            music.setVolume(vol);

        } catch (RuntimeException re) {
            System.out.println(re);
        }
    }

    /**
     * Decrement volume by 10 points. If volume is under 10, it become 0.
     */
    @FXML
    private void decVol() {
        Integer tmp = Integer.parseInt(lab.textProperty().getValue().trim());
        if (tmp < 10) {
            lab.textProperty().setValue("0");
        } else {
            tmp = tmp - 10;
            lab.textProperty().setValue(Integer.toString(tmp));
        }
        SEmenu();

    }



    /**
     * Increment volume by 10 points. If volume is over 90, it become 100.
     */
    @FXML
    private void incVol() {
        Integer tmp = Integer.parseInt(lab.textProperty().getValue().trim());
        if (tmp>90){
            lab.textProperty().setValue("100");
        }
        else{
            tmp = tmp + 10;
            lab.textProperty().setValue(Integer.toString(tmp));
        }
        SEmenu();
    }

    /**
     * return to main menu
     */
    @FXML
    private void onExit(){
        music.stop();
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }

    /**
     * Generate the playGround and close the window for start the game.
     * @see PlayGround
     */
    @FXML
    private void showPlay() {
        music.stop();
        pg = new PlayGround(currentLvl, currentChara, music.getVolume());
        pg.gameStart();
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }

    /**
     * Change the chara to the previous in the list. Change for the last if the current chara is the first.
     */
    @FXML
    private void previousChara(){
        if(idChara == 0) { idChara = nbc-1; }
        else{ idChara--; }
        majChara();
    }

    /**
     * Change the chara to the next in the list. Change for the first if the current chara is the last.
     */
    @FXML
    private void nextChara(){
        if(idChara == nbc-1) { idChara = 0; }
        else{ idChara++; }
        majChara();
    }

    /**
     * Update current chara and update the character preview in the background and the label name.
     */
    private void majChara(){
        currentChara = allChara[idChara];
        preview.setImage(currentChara.getCharaView());
        name.setValue(currentChara.getNom());
        SEmenu();
    }

    /**
     * Select the previous Stage. If the current stage is the first, the last Stage is displayed.
     */
    @FXML
    private void previousStage(){
        if(idStage == 0) { idStage = nbl-1; }
        else{ idStage--; }
        majStage();
    }

    /**
     * Select the next Stage. If the current stage is the last, the first Stage is displayed.
     */
    @FXML
    private void nextStage(){
        if(idStage == nbl-1) { idStage = 0; }
        else{ idStage++; }
        majStage();
    }

    /**
     * Update current chara and update the background, the music and the label stage.
     */
    private void majStage(){
        currentLvl = allLevel[idStage];
        bg.setImage(currentLvl.getBgPreview());
        stageName.setValue(currentLvl.getNom());
        SEmenu();
        music.stop();
        majMusic();
    }

    /**
     * Play menu sound effect.
     */
    private void SEmenu(){
        try {
            prevNextSE.play();
        }
        catch (RuntimeException re){
            System.out.println(re);
        }
    }


}
