package sample.Level;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Enemy.*;
import sample.Main;

import java.util.Map;
import java.util.Random;

public class Level1 extends Level{
    private String nom = "Stage 1 : Rouages";
    private int nbEnnemisWaveP = 50;
    private int nbEnnemisWaveS = 40;


    private int idLevel = 1;
    private int nbWave = 6;
    private String music = "src/sample/Audio/stage1.mp3";
    private Image bg = new Image(Main.class.getResource("image")+"/Level_01/BackGround_01.png");
    private Image bgPreview = new Image(Main.class.getResource("image")+"/Level_01/bgPreview.png");

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
    public int getNbEnnemisWaveS() {
        return nbEnnemisWaveS;
    }

    public int getNbWave() { return nbWave; }

    public int getIdLevel() {
        return idLevel;
    }

    public Level1() {}



    public void createMap(Stage playStage, Group playRoot, Map<Integer, Ennemy> hm, int nbEnnemis){
        int range = 3;
        Random r = new Random();
        int ran = r.nextInt(range)+1;
        switch (ran){
            case 1:
                initWave1(playStage,playRoot,hm,nbEnnemis);
                break;
            case 2 :
                initWave2(playStage,playRoot,hm,nbEnnemis);
                break;
            case 3 :
                initWave3(playStage,playRoot,hm,nbEnnemis);
                break;
        }

    }


    private void initWave1(Stage playStage, Group playRoot, Map<Integer, Ennemy> hm, int nbEnnemis){
            int i;
            for (i=1; i<= nbEnnemis; i+=2) {
                AbstractEnnemyFactory factory = new AbstractEnnemyFactory();
                hm.put(i, factory.createEnnemy(TypeEnnemi.ENNEMI1,playStage.getWidth(), playStage.getHeight()));
                initializePath(i,playRoot, hm);

                hm.put(i+1, factory.createEnnemy(TypeEnnemi.ENNEMI2,playStage.getWidth(),playStage.getHeight()));
                initializePath(i+1,playRoot, hm);

            }

    }

    private void initWave2(Stage playStage, Group playRoot, Map<Integer, Ennemy> hm, int nbEnnemis){
        int i;
        for (i=1; i<= nbEnnemis; i+=3) {
            AbstractEnnemyFactory factory = new AbstractEnnemyFactory();



            hm.put(i, factory.createEnnemy(TypeEnnemi.ENNEMI3,playStage.getWidth(), playStage.getHeight()));
            initializePath(i,playRoot, hm);

            hm.put(i+1, factory.createEnnemy(TypeEnnemi.ENNEMI4,playStage.getWidth(),playStage.getHeight()));
            initializePath(i+1,playRoot, hm);



            if(i==nbEnnemis-1 && nbEnnemis == this.nbEnnemisWaveP){
                break;
            }

            if(nbEnnemis == this.nbEnnemisWaveS && i==37){

                hm.put(i, factory.createEnnemy(TypeEnnemi.ENNEMI2,playStage.getWidth(), playStage.getHeight()));
                initializePath(i,playRoot, hm);
                hm.put(i+1, factory.createEnnemy(TypeEnnemi.ENNEMI4,playStage.getWidth(),playStage.getHeight()));
                initializePath(i+1,playRoot, hm);
                hm.put(i+2, factory.createEnnemy(TypeEnnemi.ENNEMI2,playStage.getWidth(), playStage.getHeight()));
                initializePath(i+2,playRoot, hm);
                hm.put(i+3, factory.createEnnemy(TypeEnnemi.ENNEMI2,playStage.getWidth(), playStage.getHeight()));
                initializePath(i+3,playRoot, hm);
                break;
            }

            hm.put(i+2, factory.createEnnemy(TypeEnnemi.ENNEMI2,playStage.getWidth(), playStage.getHeight()));
            initializePath(i+2,playRoot, hm);
        }

    }

    private void initWave3(Stage playStage, Group playRoot, Map<Integer, Ennemy> hm, int nbEnnemis){
        int i;
        for (i=1; i<= nbEnnemis; i+=2) {
            AbstractEnnemyFactory factory = new AbstractEnnemyFactory();
            hm.put(i, factory.createEnnemy(TypeEnnemi.ENNEMI1,playStage.getWidth(), playStage.getHeight()));
            initializePath(i,playRoot, hm);

            hm.put(i+1, factory.createEnnemy(TypeEnnemi.ENNEMI4,playStage.getWidth(),playStage.getHeight()));
            initializePath(i+1,playRoot, hm);

        }

    }



 }
