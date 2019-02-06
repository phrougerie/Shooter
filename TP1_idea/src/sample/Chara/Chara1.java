package sample.Chara;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import sample.AnimatedImage;
import sample.Main;

/**
 * First character of the game.
 * @author kevalente
 */
public class Chara1 extends Chara{
    private String nom="Jet";
    private AnimatedImage sprite = new AnimatedImage();
    private Image[] anim = new Image[6];
    private Rectangle hitbox = new Rectangle(15,15);
    //Image which will be display on the menu
    private Image charaView = new Image( Main.class.getResource("image")+"/Characters/JET/jetDesign.png" );
    //time between 2 attacks
    private int atkRecovery = 1;
    private Arc hitZone = new Arc();

    private ImageView atk = new ImageView(new Image( Main.class.getResource("image")+"/Characters/JET/JETatk.gif" ));


    public Chara1(){
        for (int i = 0; i < 4; i++)
            anim[i] = new Image( AnimatedImage.class.getResource("image")+"/Characters/JET/jet"+(i+1)+".png" );
        anim[4] = new Image( AnimatedImage.class.getResource("image")+"/Characters/JET/jet3.png" );
        anim[5] = new Image( AnimatedImage.class.getResource("image")+"/Characters/JET/jet2.png" );
        sprite.setFrames(anim);
        sprite.setDuration(0.200);
        hitbox.setOpacity(0);

        hitZone.setCenterX(0);
        hitZone.setCenterY(0);
        hitZone.setRadiusX(55.0f);
        hitZone.setRadiusY(55.0f);
        hitZone.setStartAngle(45.0f);
        hitZone.setLength(90.0f);
        hitZone.setType(ArcType.ROUND);
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
    public ImageView getAtk(){return atk;}
    public int getRecovery() {
        return atkRecovery;
    }
    public Arc getHitZone() {
        return hitZone;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

}
