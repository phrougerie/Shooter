package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Chara.Chara;
import sample.Controller.Mediator;
import sample.Level.*;


import java.io.File;


/**
 * @author phrougerie and kevalente
 */
public class PlayGround {

    private MediaPlayer mediaPlayer =null;
    private Level lvl;
    private Chara chara;
    private Mediator med = new Mediator();

    /**
     * Collect the information necessary for creating the game
     * @param lvl level to load
     * @param chara chara to load
     * @param volume volume for the music
     */
    public PlayGround(Level lvl, Chara chara, double volume) {
        this.lvl = lvl;
        this.chara = chara;

        try{
            String sound = lvl.getMusic();
            Media mediaSound = new Media(new File(sound).toURI().toString());
            mediaPlayer = new MediaPlayer(mediaSound);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        catch (RuntimeException re){
            System.out.println(re);
        }
    }


    /**
     * Generate game stage and all the visual element, and call the mediator.
     */
    public void gameStart() {

        final double  width = 625;
        final double height = 830;
        final String css = getClass().getResource("CSS/EndLevel.css").toExternalForm();

        Stage playStage = new Stage();
        playStage.initModality(Modality.APPLICATION_MODAL);

        /////////////////////////// generate basics  ///////////////////////////

        Group playRoot = new Group();
        /////////////////////////// generate level  ///////////////////////////
        //Background
        Image backgroundImg = lvl.getBg();
        ImageView backGround = new ImageView(backgroundImg);
        backGround.setFitWidth(width);
        backGround.relocate( 0, -backgroundImg.getHeight() + height);

        new AnimationTimer() {

            @Override
            public void handle(long l) {
                double y = backGround.getLayoutY() + 5;
                if( Double.compare( y, 0) >= 0) {
                    y = -backgroundImg.getHeight() + height + 5;
                }

                // move background
                backGround.setLayoutY(y);

            }

        }.start();
        playRoot.getChildren().add(backGround);

        /////////////////////////// CHARA ///////////////////////////
        playRoot.getChildren().add(chara.getAtk());
        Canvas canvas = new Canvas( 512, 512 );
        playRoot.getChildren().add( canvas );
        GraphicsContext gc = canvas.getGraphicsContext2D();

        chara.setAnimationTimer(gc, chara.getSprite());

        playRoot.getChildren().add(chara.getHitbox());
        playRoot.getChildren().add(chara.getHitZone());

        chara.getAtk().setVisible(false);
        /////////////////////////// PlayStage zone ///////////////////////////
        Scene playScene = new Scene(playRoot, 600,450);
        playScene.getStylesheets().addAll(css);
        playStage.setScene(playScene);


        playStage.setWidth(width);
        playStage.setHeight(height);
        playStage.setMinHeight(height);
        playStage.setMinWidth(width);
        playStage.setMaxHeight(height);
        playStage.setMaxWidth(width);
        chara.getHitbox().setLayoutX(playStage.getWidth()-205);
        chara.getHitbox().setLayoutY(playStage.getHeight()-245);

        canvas.setLayoutX(playStage.getWidth()-430);
        canvas.setLayoutY(playStage.getHeight()-458);

        playStage.show();


        playStage.setOnCloseRequest(we -> {
            if(mediaPlayer!=null) mediaPlayer.stop();
        });

        med.Mediation(playScene, playRoot,chara,canvas,playStage,lvl,mediaPlayer);

    }
}
