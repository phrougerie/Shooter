package sample.Enemy;

/**
 * call the fabric
 * @author phrougerie
 */
public class AbstractEnnemyFactory {

    private SimpleEnnemyFactory simpleFactory;// Attribut contenant la fabrique simple.

    // Le constructeur permet de sélectionner la fabrique à utiliser.
    public AbstractEnnemyFactory()
    {
        this.simpleFactory = new SimpleEnnemyFactory();
    }

    // Méthode qui permet de construire l'ensemble des unités.
    public Ennemy createEnnemy(TypeEnnemi type, double x , double y)
    {
        Ennemy ennemy = this.simpleFactory.createEnnemy(type, x ,y);
        return ennemy;
    }
}
