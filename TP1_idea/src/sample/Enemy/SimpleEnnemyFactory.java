package sample.Enemy;



/**
 * Create an enemy
 * @author phrougerie
 */

public class SimpleEnnemyFactory
{
    // La création d'un ennemi en fonction de son type est encapsulée dans la fabrique.

    /**
     * Create an enemy of the selected type
     * @param type type of the enemy who will be created
     * @param x position of apparition
     * @param y position of apparition
     */
    public Ennemy createEnnemy(TypeEnnemi type, double x, double y)
    {
        Ennemy ennemy = null;;
        switch(type)
        {
            case ENNEMI1:ennemy = new Ennemy1(x,y);break;
            case ENNEMI2:ennemy = new Ennemy2(x,y);break;
            case ENNEMI3:ennemy = new Ennemy3(x,y);break;
            case ENNEMI4:ennemy = new Ennemy4(x,y);break;
            case ENNEMI5:ennemy = new Ennemy5(x,y);break;
        }
        return ennemy;
    }
}

