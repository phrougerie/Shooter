package sample.Enemy;

import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import javafx.scene.image.Image;
import sample.Main;

/**
 * First ennemy.
 * @author kevalente and phrougerie
 *
 */
public class Ennemy1 extends Ennemy {
    Image img = new Image(Main.class.getResource("image")+"/Ennemys/ball.png");
    private EnnemyCursor cursor;

    private Path path;
    private Path path2;

    private MoveTo mt ;
    private LineTo lt ;

    private PathTransition pathTransitionS;
    private PathTransition pathTransition;

    private double ymax;
    private double xmax;


    public Ennemy1(double xmax,double ymax ) {
        this.xmax = xmax;
        cursor =  new EnnemyCursor(img, xmax, img.getHeight()/2);
        this.ymax=ymax;
        lt = new LineTo(0,ymax+100);
        mt = new MoveTo(0,0);
    }




    public void setPath(Group root){
        path = new Path();
        SetterPath(path, root);
        root.getChildren().add(cursor.getHitbox());
    }
    public void setPath2(Group root) {
        path2 = new Path();
        SetterPath(path2, root);
        root.getChildren().add(cursor.getSprite());
    }

    private void SetterPath(Path p, Group root){
        p.getElements().add(mt);
        p.getElements().add(lt);
        p.setOpacity(0);
        root.getChildren().add(p);
    }

    public void setPathTransition() {

        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(5.0));
        pathTransition.setDelay(Duration.seconds(.5));
        pathTransition.setPath(path);
        pathTransition.setNode(cursor.getHitbox());
    }

    public void setPathTransitionS() {

        pathTransitionS = new PathTransition();
        pathTransitionS.setDuration(Duration.seconds(5.0));
        pathTransitionS.setDelay(Duration.seconds(.5));
        pathTransitionS.setPath(path2);
        pathTransitionS.setNode(cursor.getSprite());

    }

    public PathTransition getPathTransition() {
        return pathTransition;
    }
    public PathTransition getPathTransitionS() {
        return pathTransitionS;
    }
    public  EnnemyCursor getCursor(){return cursor;}
    public Shape getHitbox(){return cursor.getHitbox();}

    //public Image getSprite() { return sprite; }
}

