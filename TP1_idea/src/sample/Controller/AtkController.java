package sample.Controller;

import sample.Chara.Chara;


/**
 * Control the time between two attack.
 * @author kevalente
 * @see Chara
 */
public class AtkController {
    private long lastAtkTime;
    private long currentTime;

    public AtkController() {
        lastAtkTime = 0;
    }

    /**
     * Check if the time between two attacks is respected
     * @param recovery recovery time between two attacks
     * @return true if attack available
     */
    public boolean attack(int recovery){
        currentTime = System.nanoTime();
        if ((currentTime - lastAtkTime) / 1000000000.00 < recovery/2.00) {
            return false;
        }
        lastAtkTime = currentTime;
        return true;
    }




}
