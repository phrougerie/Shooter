package sample.Chara;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import sample.AnimatedImage;
import sample.Main;
/**
 * Second character of the game.
 * @author kevalente
 */
public class Chara2 extends Chara{
    private String nom="Lilith";
    private AnimatedImage sprite = new AnimatedImage();
    private Image[] anim = new Image[6];
    private Rectangle hitbox = new Rectangle(15,15);
    //Image which will be display on the menu
    private Image charaView = new Image( Main.class.getResource("image")+"/Characters/Lilith/lilithDesign.png" );
    //time between 2 attacks
    private int atkRecovery = 2;
    private Ellipse hitZone = new Ellipse();
    private ImageView atk = new ImageView(new Image( Main.class.getResource("image")+"/Characters/Lilith/LilithAtk.gif" ));

    public Chara2(){
        for (int i = 0; i < 6; i++)
            anim[i] = new Image( AnimatedImage.class.getResource("image")+"/Characters/Lilith/lilith"+(i+1)+".png" );
        sprite.setFrames(anim);
        sprite.setDuration(0.200);
        hitbox.setOpacity(0);
        hitZone.setRadiusX(45);
        hitZone.setRadiusY(70);
        hitZone.setOpacity(0);
    }



    public String getNom() {
        return nom;
    }
    public Image getCharaView() {
        return charaView;
    }
    public AnimatedImage getSprite() {
        return sprite;
    }
    public int getRecovery() {
        return atkRecovery;
    }
    public ImageView getAtk(){return atk;}
    public Ellipse getHitZone() {
        return hitZone;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }






}
