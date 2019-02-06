package sample.Controller;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import sample.Chara.Chara;


/**
 * Detect the different keyboard entrances.
 * Note : for moving character, boolean are used and return to the mediator for allowed diagonal moves and forbid the player to overtake limits.
 * @author kevalente and phrougerie
 */
public class CharaController {

    private Boolean up, right, left, down, validate1,atk;
    private Scene scene;
    private Chara chara;
    private Canvas can;
    private int vitesse = 3;
    private AtkController attacon;


    public CharaController(Scene scene, Chara chara, Canvas can) {
        this.scene = scene;
        this.chara = chara;
        this.can = can;
        up = right = left = down = validate1 = atk = false;
        attacon = new AtkController();
    }

    public Boolean getUp() {
        return up;
    }
    public Boolean getRight() {
        return right;
    }
    public Boolean getLeft() {
        return left;
    }
    public Boolean getDown() {
        return down;
    }

    public Boolean getAtk() { return atk;}
    public Boolean getValidate1() { return validate1;}

    public void setAtk(){atk = false;}

    public void moveLeft(){
        can.setLayoutX(can.getLayoutX() - vitesse);
        chara.getHitbox().setLayoutX(chara.getHitbox().getLayoutX() -vitesse);
    }

    public void moveRight(){
        can.setLayoutX(can.getLayoutX() + vitesse);
        chara.getHitbox().setLayoutX(chara.getHitbox().getLayoutX() +vitesse);
    }

    public void moveUp(){
        can.setLayoutY(can.getLayoutY() - vitesse);
        chara.getHitbox().setLayoutY(chara.getHitbox().getLayoutY() -vitesse);
    }

    public void moveDown(){
        can.setLayoutY(can.getLayoutY() + vitesse);
        chara.getHitbox().setLayoutY(chara.getHitbox().getLayoutY() +vitesse);
    }


    /**
     * Listen any keyboard statement
     */
    public void addKeyHandler() {
        scene.setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();

            if (keyCode.equals(KeyCode.SHIFT)) {
                vitesse = 1;
            }

            if (keyCode.equals(KeyCode.Z)) {
                up = true;
            }

            if (keyCode.equals(KeyCode.S)) {
                down = true;
            }

            if (keyCode.equals(KeyCode.Q)) {
                left = true;
            }
            if (keyCode.equals(KeyCode.D)) {
                right = true;
            }
            if (keyCode.equals(KeyCode.SPACE)) {
                validate1 = true;
            }
            if (keyCode.equals(KeyCode.ENTER)) {
                boolean tmp = attacon.attack(chara.getRecovery());
                if(tmp){atk = true;}
            }
            return;
        });

        scene.setOnKeyReleased(ke->{
            KeyCode keyCode = ke.getCode();

            if (keyCode.equals(KeyCode.SHIFT)) {
                vitesse = 3;
            }
            if (keyCode.equals(KeyCode.Z)) {
                up = false;
            }

            if (keyCode.equals(KeyCode.S)) {
                down = false;
            }

            if (keyCode.equals(KeyCode.Q)) {
                left = false;
            }
            if (keyCode.equals(KeyCode.D)) {
                right = false;
            }
            if (keyCode.equals(KeyCode.SPACE)) {
                validate1 = false;
            }
            if (keyCode.equals(KeyCode.ENTER)) {
                atk = false;
            }
        });
    }
}
