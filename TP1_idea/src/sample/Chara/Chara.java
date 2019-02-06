/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.Chara;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import sample.AnimatedImage;

/**
 * Structure de base d'un personnage.
 * @author kevalente
 *
 */

public abstract class Chara{
    /**
     * Name of the character
     * @return String
     */
    public  abstract String getNom();
    /**
     * Hitbox of the character
     * @return Shape
     */
    public abstract Rectangle getHitbox();
    /**
     * Sprite of the character
     * @return AnimatedImage
     */
    public abstract AnimatedImage getSprite() ;
    /**
     * Return the battler of the character
     * @return Image
     */
    public abstract Image getCharaView();
    /**
     * Return time between two attacks
     * @return int
     */
    public abstract int getRecovery();
    /**
     * Return zone of attack
     * @return Shape
     */
    public abstract Shape getHitZone();
    /**
     * Return gif with attack's animation
     * @return ImageView
     */
    public abstract ImageView getAtk();


    /**
     * Active character animation.
     * @see AnimatedImage
     */
    public void setAnimationTimer(GraphicsContext gc, AnimatedImage sprite){
        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                gc.clearRect(0,0,1000,1000);
                gc.drawImage( sprite.getFrame(t), 200, 200 );
            }
        }.start();
    }



}
