package sample.Level;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Enemy.Ennemy;

import java.util.Map;

public abstract class Level {

    /**
     * Get the number of enemies in the principal wave
     */
    public abstract int getNbEnnemisWaveP();
    /**
     * Get the number of enemies in the principal wave
     */
    public abstract int getNbEnnemisWaveS();

    /**
     * Get the image of the background
     */
    public abstract Image getBg();
    /**
     * Get the image of the background's preview
     */
    public abstract Image getBgPreview();
    /**
     * Get the music of the level
     */
    public abstract String getMusic();
    /**
     * Get the name of the level
     */
    public abstract String getNom();
    /**
     * Get the level ID
     */
    public abstract int getIdLevel() ;

    /**
     * Get the number of waves
     */
    public abstract int getNbWave();


    /**
     * Generate a random wave of enemy among the pre-created waves
     * @param playStage play stage
     * @param playRoot play group
     * @param hm the map which will receive the wave
     * @param nbEnnemis the number of enemy to generate
     */
    public abstract void createMap(Stage playStage, Group playRoot, Map<Integer, Ennemy> hm, int nbEnnemis);

    /**
     * Initialise the patern of the enemy
     * @param i index of the enemy
     * @param playRoot play group
     * @param hm wave
     */
    protected void initializePath(int i, Group playRoot, Map<Integer, Ennemy> hm){

        hm.get(i).setPath(playRoot);
        hm.get(i).setPathTransition();
        hm.get(i).setPath2(playRoot);
        hm.get(i).setPathTransitionS();

    }




}
