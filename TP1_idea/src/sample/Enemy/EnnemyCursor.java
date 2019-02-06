package sample.Enemy;


import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Initialise hit box and sprite of the enemy, and his initial placement
 * @author phrougerie and kevalente
 *
 */
public class EnnemyCursor extends Parent {
    private Shape hitbox;
    private ImageView sprite;

    /**
     * Generate a circular villain in a random place on x axe and to the position 0 in y axe.
     * @param spr the image of the enemy
     * @param xmax the position of left border.
     * @param height the radius of the enemy
     */
    public EnnemyCursor(Image spr, double xmax, double height) {
        hitbox = new Circle(height);
        hitbox.setOpacity(0);
        sprite = new ImageView(spr);
        rdmTemporaire(xmax);

    }

    /**
     * Generate a rectangle villain in a random place on x axe and to the position 0 in y axe.
     * @param spr the image of the enemy
     * @param xmax the position of left border.
     * @param height the height of the enemy
     * @param width the width of the enemy
     */
    public EnnemyCursor(Image spr, double xmax, double height, double width) {
        hitbox  = new Rectangle(width,height);
        hitbox.setOpacity(0);
        sprite = new ImageView(spr);
        rdmTemporaire(xmax);
    }

    /**
     * Place an enemy somewhere between left and right border, at the top of the stage.
     * @param xmax the position of left border.
     */
    private void rdmTemporaire(double xmax){
        double range = xmax-75;
        double ran=(Math.random() *range)+25;
        this.hitbox.setLayoutY(-80);
        this.hitbox.setLayoutX(ran);
        this.sprite.setLayoutY(this.hitbox.getLayoutY());
        this.sprite.setLayoutX(this.hitbox.getLayoutX());
        this.getChildren().add(hitbox);
        this.getChildren().add(sprite);


    }

    /**
     * @return a shape corresponding to the enemy's Hit Box
     */
    public Shape getHitbox() {
        return hitbox;
    }
    /**
     * @return the sprite of the villain.
     */
    public ImageView getSprite() {
        return sprite;
    }
}