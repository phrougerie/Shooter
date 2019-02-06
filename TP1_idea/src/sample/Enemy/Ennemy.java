package sample.Enemy;

import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.shape.Shape;
/**
 * Base structure for ennemy.
 * @author kevalente and phrougerie
 *
 */
public abstract class Ennemy {


    /**
     * @see EnnemyCursor
     */
    public abstract EnnemyCursor getCursor();

    /**
     * update the path of hitbox in the group.
     */
    public abstract void setPath(Group root);
    /**
     * update the pathTransition of hitbox in the group.
     */
    public abstract void setPathTransition();
    /**
     * update the path of sprite in the group.
     */
    public abstract void setPath2(Group root);
    /**
     * update the pathTransition of sprite in the group.
     */
    public abstract void setPathTransitionS();
    /**
     * Get pathTransistion of the enemy
     */
    public abstract PathTransition getPathTransitionS();
    /**
     * Get pathTransistion of the enemy
     */
    public abstract PathTransition getPathTransition();
    /**
     * Get hitbox of the enemy
     */
    public abstract Shape getHitbox();
}
