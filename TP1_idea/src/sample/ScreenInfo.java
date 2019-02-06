package sample;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.Chara.Chara;

/**
 * Generate a bandeau with game information
 * @author phrougerie and kevalente
 */
public class ScreenInfo extends Parent {

    /**
     * Image for the life counter
     */
    private Image img = new Image(Main.class.getResource("image")+"/coeur.png");


    /**
     * Generate the ScreenInfo.
     * @param c chara for getting name
     * @param height Height of the bandeau
     * @param width Width of the bandeau
     * @param life Number of life
     * @param time Game's timer actual statement
     * @param nbKill Number of victims
     */
    public ScreenInfo(Chara c, double height, double width, int life , double time, int nbKill){

        Rectangle fond = new Rectangle();
        fond.setWidth(width);
        fond.setHeight(height/7);


        fond.setFill(Color.rgb(120,50,60));
        this.getChildren().add(fond);

        Group container = new Group();
        container.setLayoutX(20);
        this.getChildren().add(container);

        Text nom = new Text(10, 25, c.getNom());
        nom.setFill(Color.CORAL);
        nom.setFont(Font.font("Bodoni MT",25));
        ImageView heart = new ImageView(img);
        heart.setFitHeight(50);
        heart.setFitWidth(80);
        heart.setLayoutX(0);
        heart.setLayoutY(40);
        Text life2 = new Text (80 ,80, Integer.toString(life));
        life2.setFill(Color.CORAL);
        Text printTime = new Text(width-120,30,Double.toString(time));
        Text kills = new Text(width-260,75, "Ennemis vaincus : " + nbKill);

        kills.setFill(Color.CORAL);
        kills.setFont(Font.font("Bodoni MT",25));
        printTime.setFill(Color.CORAL);
        printTime.setFont(Font.font("Webdings",25));
        life2.setFont(Font.font(36));
        container.getChildren().add(nom);
        container.getChildren().add(heart);
        container.getChildren().add(life2);
        container.getChildren().add(printTime);
        container.getChildren().add(kills);
    }

}