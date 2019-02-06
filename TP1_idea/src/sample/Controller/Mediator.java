package sample.Controller;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.*;
import sample.Chara.Chara;
import sample.Enemy.Ennemy;
import sample.Enemy.EnnemyTask;
import sample.Level.Level;



import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Master of the rules of the game.
 * @author kevalente and phrougerie
 */
public class Mediator {
    private  double tmp =0;

    private int nbLife = 5;

    private Map<Integer, Ennemy> hmEnnemysP = new ConcurrentHashMap<>();
    private Map<Integer, Ennemy> hmEnnemysS = new ConcurrentHashMap<>();
    private boolean isActivated =false;

    private long timetouch=0;

    private int score=0;
    private IntegerProperty nbKill = new SimpleIntegerProperty(0);

    private DoubleProperty time = new SimpleDoubleProperty(0);
    private Timer timer = new Timer();

    private int currentWave =1;

    private AnimationTimer gameLoop;

    private ColliDetection cD = new ColliDetection();

    NumberBinding scoreLvl = nbKill.multiply(150).add(time.multiply(25));


    /**
     * Control the limit of the stage.
     * Do what they are to do when keyboard event rises.
     * Handles attack
     * Handles collision between hero and enemies
     * Manage lives
     * Manage enemies waves
     * Trigger game over.
     * Trigger game loop.
     * Call loopMap
     * Display end screen
     * Calculate score
     * Manage invicibility
     *
     * @param playScene actual Scene
     * @param playRoot group corresponding to the scene
     * @param chara current chara
     * @param canvas canvas related to the chara' sprite
     * @param level current lvl
     */
    public void Mediation(Scene playScene, Group playRoot, Chara chara, Canvas canvas, Stage playStage, Level level, MediaPlayer mediaPlayer){
        final long startNanoTime = System.nanoTime();
        final int MAX_CHARS = 3 ;
        level.createMap(playStage,playRoot, hmEnnemysP,level.getNbEnnemisWaveP());

        //////////////////////////////////// Bandeau //////////////////////////////////////////
        ScreenInfo screenInfo = new ScreenInfo(chara,playStage.getHeight(),playStage.getWidth(), nbLife,0, nbKill.getValue());
        setInfo(playRoot, playStage,screenInfo);

        //////////////////////////////////// Clavier //////////////////////////////////////////
        CharaController characon = new CharaController(playScene,chara,canvas);
        characon.addKeyHandler();

        //////////////////////////////////// Colli //////////////////////////////////////////
        Set<Map.Entry<Integer, Ennemy>> setHm = hmEnnemysP.entrySet();
        Iterator<Map.Entry<Integer, Ennemy>> it = setHm.iterator();

        timer.schedule(new EnnemyTask(setHm,it,playRoot,timer),0,100);



        this.gameLoop = new AnimationTimer() {

            public void handle(long currentNanoTime) {
                //////////////////////////////////// Bords //////////////////////////////////////////
                if (characon.getRight() == true && chara.getHitbox().getLayoutX()<playStage.getWidth()-45) {
                    characon.moveRight();
                }
                if (characon.getUp() == true && chara.getHitbox().getLayoutY()>0) {
                    characon.moveUp();
                }
                if (characon.getLeft() == true && chara.getHitbox().getLayoutX()>10) {
                    characon.moveLeft();
                }
                if (characon.getDown() == true && chara.getHitbox().getLayoutY()<playStage.getHeight()-190) {
                    characon.moveDown();
                }
                if (characon.getAtk() == true) {
                    chara.getHitZone().setLayoutX(chara.getHitbox().getLayoutX()+7);
                    chara.getHitZone().setLayoutY(chara.getHitbox().getLayoutY()+15);


                    chara.getAtk().setLayoutX(chara.getHitbox().getLayoutX()-40);
                    chara.getAtk().setLayoutY(chara.getHitbox().getLayoutY()-55);

                    chara.getAtk().setVisible(true);
                    offense(playRoot, hmEnnemysP, chara);
                    offense(playRoot, hmEnnemysS, chara);
                    characon.setAtk();

                    Task<Void> sleeper = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                            }
                            return null;
                        }
                    };
                    sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent event) {
                            chara.getAtk().setVisible(false);
                        }
                    });
                    new Thread(sleeper).start();
                }
                if(timetouch==0){
                    timetouch=currentNanoTime-1;
                }


            //////////////////////////////// GESTION COLLI ///////////////////////////////////////////
                Set<Map.Entry<Integer, Ennemy>> coupleP = hmEnnemysP.entrySet();
                Iterator<Map.Entry<Integer, Ennemy>> unVillainP = coupleP.iterator();

                Rectangle heroHitbox = chara.getHitbox();
                if (tmp ==0){
                    tmp= currentNanoTime;
                }
                if ((currentNanoTime-tmp)/1000000000 >=0.5){
                    displayScreenInfo(playRoot,screenInfo, startNanoTime, currentNanoTime, chara, playStage );
                    tmp=currentNanoTime;
                }

                loopMap(unVillainP,currentNanoTime,canvas,cD,heroHitbox,playRoot,screenInfo,startNanoTime,chara,playStage,level,characon,mediaPlayer,MAX_CHARS,gameLoop,isActivated,level.getNbEnnemisWaveP());


                Set<Map.Entry<Integer, Ennemy>> coupleS = hmEnnemysS.entrySet();
                Iterator<Map.Entry<Integer, Ennemy>> unVillainS = coupleS.iterator();

                isActivated = loopMap(unVillainS,currentNanoTime,canvas,cD,heroHitbox,playRoot,screenInfo,startNanoTime,chara,playStage,level,characon,mediaPlayer,MAX_CHARS,gameLoop,isActivated,level.getNbEnnemisWaveS());


                if (hmEnnemysP.get(level.getNbEnnemisWaveP()/6).getCursor().getHitbox().getTranslateY()>=playStage.getHeight() && isActivated==false){

                    isActivated = true;
                    level.createMap(playStage,playRoot,hmEnnemysS,level.getNbEnnemisWaveS());
                    initializeEnnemisPath(playRoot,hmEnnemysS);
                }


                if (nbLife == 0) {
                    displayScreenInfo(playRoot,screenInfo, startNanoTime, currentNanoTime, chara, playStage );
                    stop();
                    timer.cancel();
                    score = scoreLvl.getValue().intValue();
                    Text gameOver = new Text("Game Over\nVotre score est : " + score + "\nAppuyer sur espace pour continuer");
                    gameOver.setLayoutX(playStage.getWidth()/4);
                    gameOver.setLayoutY(playStage.getHeight()/2);
                    gameOver.setFont(Font.font(20));
                    gameOver.setFill(Color.WHITE);
                    playRoot.getChildren().add(gameOver);
                    validation(characon,playRoot,gameOver,playStage,MAX_CHARS,score,level,mediaPlayer);
                }
            }
        };
        gameLoop.start();

    }

    /**
     * Detect collision
     * Manage lives
     * Manage invincibility time
     * Calculate score
     * Activate the waves
     * Delete enemies who overpasses the stage's borders
     *
     * @param unVillain Enemy list
     * @param canvas Hero's canvas
     * @param cD collision controller
     * @param playRoot actual group of the play scene
     * @param screenInfo Bandeau of game information
     * @param playStage actual stage
     * @param characon Keyborad controller
     * @param MAX_CHARS Limit of the pseudo size
     * @param isActivated State of the list of enemies
     * @param nbEnnemis Number of enemies
     * @return
     * true if the secondary enemy's map is activated.
     */
    private boolean loopMap(Iterator<Map.Entry<Integer, Ennemy>> unVillain, long currentNanoTime, Canvas canvas, ColliDetection cD, Rectangle heroHitbox, Group playRoot, ScreenInfo screenInfo, long startNanoTime, Chara chara, Stage playStage, Level level, CharaController characon, MediaPlayer mediaPlayer, int MAX_CHARS, AnimationTimer gameLoop, boolean isActivated, int nbEnnemis){

        while(unVillain.hasNext()){
            Map.Entry<Integer, Ennemy> theNext = unVillain.next();
            if(((currentNanoTime - timetouch) == 1) || ((currentNanoTime - timetouch) >= 2000000000)){
                canvas.setOpacity(1);
                if (nbLife >= 0) {
                    if (cD.isBoum(heroHitbox, theNext.getValue().getHitbox())) {

                        timetouch = currentNanoTime;

                        if (nbLife > 0){
                            nbLife--;
                        }
                        removeEnnemi(playRoot,theNext);
                        canvas.setOpacity(0.5);
                    }
                }
            }

            if (theNext.getKey() == level.getNbEnnemisWaveP() - 1 && currentWave == level.getNbWave() && nbEnnemis == level.getNbEnnemisWaveP()) {
                if (theNext.getValue().getCursor().getHitbox().getTranslateY() > playStage.getHeight()) {
                    gameLoop.stop();
                    timer.cancel();
                    score = scoreLvl.getValue().intValue() + 10000 * nbLife + 5000;
                    Text victory = new Text("Victoire\nVotre score est : " + score + "\nAppuyer sur espace pour continuer");
                    victory.setLayoutX(playStage.getWidth() / 4);
                    victory.setLayoutY(playStage.getHeight() / 2);
                    victory.setFont(Font.font(25));
                    victory.setFill(Color.BLUE);
                    playRoot.getChildren().add(victory);
                    validation(characon, playRoot, victory, playStage, MAX_CHARS, score, level, mediaPlayer);
                }
            }


            else if (nbEnnemis == level.getNbEnnemisWaveS() && currentWave <= level.getNbWave()-1 ){
                if(theNext.getKey()==level.getNbEnnemisWaveS()-1){
                    if(theNext.getValue().getCursor().getHitbox().getTranslateY()>playStage.getHeight()) {
                        hmEnnemysS.clear();
                        isActivated=false;
                    }
                }

            }
            else if(theNext.getKey()==level.getNbEnnemisWaveP()-1){
                if(theNext.getValue().getCursor().getHitbox().getTranslateY()>playStage.getHeight()) {
                    //emptyEnnemis(playRoot, hmEnnemysP);
                    hmEnnemysP.clear();
                    level.createMap(playStage,playRoot, hmEnnemysP,level.getNbEnnemisWaveP());
                    initializeEnnemisPath(playRoot,hmEnnemysP);
                    currentWave++;
                }
            }
                    if(theNext.getValue().getCursor().getHitbox().getTranslateY()>playStage.getHeight()){
                        removeEnnemi(playRoot, theNext);
                    }


        }

        return isActivated;

    }


    /**
     * Initialise the path of all the enemies in a hashmap.
     *
     * @param playRoot actual group where enemies will evolve
     * @param hm map of enemies who need to be initiate
     */
    private void initializeEnnemisPath(Group playRoot ,Map<Integer, Ennemy> hm ){
        Set<Map.Entry<Integer, Ennemy>> setHm = hm.entrySet();
        Iterator<Map.Entry<Integer, Ennemy>> it = setHm.iterator();
        timer = new Timer();
        timer.schedule(new EnnemyTask(setHm,it,playRoot,timer),0,100);
    }


    /**
     * Add the screenInfo to the group and place the banner on the screen.
     */
    private void setInfo(Group playRoot, Stage playStage, ScreenInfo screenInfo) {

        screenInfo.setLayoutX(0);
        screenInfo.setLayoutY(playStage.getHeight()-158);
        playRoot.getChildren().add(screenInfo);
    }



    /**
     * Add the screenInfo to the group.
     */
    private void removeEnnemi(Group playRoot,Map.Entry<Integer, Ennemy> theNext){
        theNext.getValue().getHitbox().setLayoutX(-100);
        playRoot.getChildren().remove(theNext.getValue().getCursor().getSprite());
        playRoot.getChildren().remove(theNext.getValue().getCursor().getHitbox());
    }


    /**
     * Test if an enemy is on the zone of a chara's attack.
     * If it is, delete this enemy and increment the counter.
     */
    private void offense (Group root,Map<Integer, Ennemy> hm, Chara c){
        Set<Map.Entry<Integer, Ennemy>> setHm = hm.entrySet();
        Iterator<Map.Entry<Integer, Ennemy>> it = setHm.iterator();
        while(it.hasNext()){
            Map.Entry<Integer, Ennemy> theNext = it.next();
            if(cD.isBoum(c.getHitZone(), theNext.getValue().getHitbox())){
                removeEnnemi(root,theNext);
                nbKill.setValue(nbKill.getValue()+1);
            }
        }
    }

    /**
     * Display the Bandeau with game information.
     */
    private void displayScreenInfo(Group playRoot, ScreenInfo screenInfo, long startNanoTime, long currentNanoTime, Chara chara, Stage playStage ){
        playRoot.getChildren().remove(screenInfo);
        time.setValue((currentNanoTime-startNanoTime)/1000000000);
        ScreenInfo screenInfo2 = new ScreenInfo(chara,playStage.getHeight(),playStage.getWidth(), nbLife, time.getValue(), nbKill.getValue());
        setInfo(playRoot, playStage,screenInfo2);

    }


    /**
     * Display final screen
     *
     * @param characon Keyboard event listener
     * @param playRoot Current group of graphic element
     * @param gameOver Text to display when the game is over
     * @param playStage Window wherre display
     * @param MAX_CHARS Maximum number in the name
     * @param score Player's score
     * @param level actual level
     */
    private void validation(CharaController characon, Group playRoot, Text gameOver, Stage playStage, int MAX_CHARS, int score, Level level, MediaPlayer mediaPlayer){

        AnimationTimer verif = new AnimationTimer() {

            public void handle(long currentNanoTime) {
                if (characon.getValidate1() == true ) {
                    playRoot.getChildren().remove(gameOver);
                    TextField name = new TextField();
                    name.setLayoutX(playStage.getWidth()/2.5);
                    name.setLayoutY(playStage.getHeight()/2.5);
                    name.setFont(Font.font(25));
                    name.setPrefColumnCount(3);
                    name.setTextFormatter(new TextFormatter<String>(change ->
                            change.getControlNewText().length() <= MAX_CHARS ? change : null));

                    Rectangle backgroundList = new Rectangle(playStage.getWidth()/3,playStage.getHeight()/3,Color.BLACK);
                    backgroundList.setLayoutX(playStage.getWidth()/3.20);
                    backgroundList.setLayoutY(playStage.getHeight()/3.5);
                    playRoot.getChildren().add(backgroundList);
                    backgroundList.setOpacity(0.5);
                    Button submit = new Button("Valider");
                    submit.setMinSize(70,55);
                    submit.setLayoutX(playStage.getWidth()/2.5);
                    submit.setLayoutY(playStage.getHeight()/2.12);
                    playRoot.getChildren().add(submit);
                    playRoot.getChildren().add(name);

                    stop();
                    submit.setOnAction(e -> {
                        if ((name.getText() != null && !name.getText().isEmpty())) {
                            playRoot.getChildren().remove(submit);
                            playRoot.getChildren().remove(name);
                            BaseController bc = new BaseController();
                            try {

                                bc.insertScore(playStage, playRoot, score, level.getIdLevel(), name.getText());
                                String tot = bc.highScoreLevel(level.getIdLevel());
                                Text list = new Text(tot);
                                list.setLayoutX(playStage.getWidth()/3);
                                list.setLayoutY(playStage.getHeight()/3);
                                list.setFont(Font.font("Arial",30));
                                list.setFill(Color.WHITE);

                                playRoot.getChildren().add(list);
                            } catch (ClassNotFoundException a) {
                                a.printStackTrace();}

                            Button exitButton = new Button("Quitter");
                            exitButton.setLayoutX(playStage.getWidth()/2.6);
                            exitButton.setLayoutY(playStage.getHeight()/1.9);
                            playRoot.getChildren().add(exitButton);

                            exitButton.setOnAction(event -> {
                                if(mediaPlayer!=null) mediaPlayer.stop();
                                playStage.close();
                            });
                        }

                    });


                }
            }};
        verif.start();
    }


}


