package sample.Level;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Enemy.*;
import sample.Main;

import java.util.Map;
import java.util.Random;

public class Level3 extends Level{
    private String nom = "Stage 3 : Dimension";

    private int idLevel = 3;
    private int nbWave = 15;
    private int nbEnnemisWaveP = 50;
    private int nbEnnemisWaveS = 40;


    private String music = "src/sample/Audio/stage3.mp3";
    private Image bg = new Image(Main.class.getResource("image")+"/Level_03/BackGround_03.png");
    private Image bgPreview = new Image(Main.class.getResource("image")+"/Level_03/bgPreview3.png");

    @Override
    public int getNbWave() {
        return nbWave;
    }
    public int getNbEnnemisWaveS() {
        return nbEnnemisWaveS;
    }
    public int getNbEnnemisWaveP() {
        return nbEnnemisWaveP;
    }
    public Image getBg() {
        return bg;
    }
    public String getMusic() {
        return music;
    }
    public String getNom() {
        return nom;
    }
    public Image getBgPreview() {
        return bgPreview;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public Level3() {}



    public void createMap(Stage playStage, Group playRoot, Map<Integer, Ennemy> hm, int nbEnnemis){
        int range = 1;
        Random r = new Random();
        int ran=r.nextInt(range)+1;
        switch (ran){
            case 1:
                initWave(playStage,playRoot,hm,nbEnnemis);
                break;
        }

    }


    private void initWave(Stage playStage, Group playRoot, Map<Integer, Ennemy> hm, int nbEnnemis){
        int i;
        int range = 5;
        Random r = new Random();
        AbstractEnnemyFactory factory = new AbstractEnnemyFactory();
        for (i=1; i<= nbEnnemis; i++) {
            int ran=r.nextInt(range)+1;
            switch (ran){
                case 1:
                    hm.put(i, factory.createEnnemy(TypeEnnemi.ENNEMI5,playStage.getWidth(), playStage.getHeight()));
                    initializePath(i,playRoot, hm);
                    break;
                case 2:
                    hm.put(i, factory.createEnnemy(TypeEnnemi.ENNEMI4,playStage.getWidth(), playStage.getHeight()));
                    initializePath(i,playRoot, hm);
                    break;
                case 3:
                    hm.put(i, factory.createEnnemy(TypeEnnemi.ENNEMI3,playStage.getWidth(), playStage.getHeight()));
                    initializePath(i,playRoot, hm);
                    break;
                case 4:
                    hm.put(i, factory.createEnnemy(TypeEnnemi.ENNEMI2,playStage.getWidth(), playStage.getHeight()));
                    initializePath(i,playRoot, hm);
                    break;
                case 5:
                    hm.put(i, factory.createEnnemy(TypeEnnemi.ENNEMI1,playStage.getWidth(), playStage.getHeight()));
                    initializePath(i,playRoot, hm);
                    break;


            }





        }

    }



}
