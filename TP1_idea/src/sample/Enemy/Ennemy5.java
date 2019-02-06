package sample.Enemy;

import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.shape.*;
import javafx.util.Duration;
import sample.Main;
/**
 * 5th ennemy.
 * @author phrougerie
 *
 */
public class Ennemy5 extends Ennemy {
    Image img = new Image(Main.class.getResource("image")+"/Ennemys/e06.png");
    private EnnemyCursor cursor;

    private Path path;
    private Path path2;

    private MoveTo mt ;

    private PathTransition pathTransitionS;
    private PathTransition pathTransition;

    double xmax;
    double ymax;

    public Ennemy5(double xmax,double ymax ) {
        this.xmax = xmax;
        this.ymax = ymax;
        cursor =  new EnnemyCursor(img, xmax, img.getHeight(), img.getWidth());
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
        if(cursor.getHitbox().getLayoutX()>=xmax/4){
            cursor.getHitbox().setLayoutX(cursor.getHitbox().getLayoutX()-50);
            cursor.getSprite().setLayoutX(cursor.getSprite().getLayoutX()-50);
        }
        p.getElements().add(new LineTo(cursor.getHitbox().getTranslateX(),0));
        p.getElements().add(new CubicCurveTo(100, 300, 100, 320, 120, ymax+60));
        //p.getElements().add(new LineTo(cursor.getHitbox().getTranslateX(),400));
        p.setOpacity(0);
        root.getChildren().add(p);
    }

    public void setPathTransition() {

        pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(5.0));
        pathTransition.setDelay(Duration.seconds(.2));
        pathTransition.setPath(path);
        pathTransition.setNode(cursor.getHitbox());
    }

    public void setPathTransitionS() {

        pathTransitionS = new PathTransition();
        pathTransitionS.setDuration(Duration.seconds(5.0));
        pathTransitionS.setDelay(Duration.seconds(.2));
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
}