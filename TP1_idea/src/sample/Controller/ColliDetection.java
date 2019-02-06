package sample.Controller;

import javafx.scene.shape.Shape;


/**
 * @author kevalente
 */
public class ColliDetection {

    public ColliDetection() { }

    /**
     * Testing if two shapes fit in.
     * Return true if it is.
     */
    public boolean isBoum(Shape hero, Shape vilain){
        return hero.getBoundsInParent().intersects(vilain.getBoundsInParent());
    }
}
