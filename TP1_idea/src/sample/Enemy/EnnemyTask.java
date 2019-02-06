package sample.Enemy;

import javafx.scene.Group;

import java.util.*;

/**
 * Launches the enemies with a determinate time gap
 * @author phrougerie
 */
public class EnnemyTask extends TimerTask {
    private Set<Map.Entry<Integer, Ennemy>> setHm;
    private Iterator<Map.Entry<Integer, Ennemy>> it;
    private Group root;
    private Timer timer;

    public EnnemyTask(Set<Map.Entry<Integer, Ennemy>> setHm, Iterator<Map.Entry<Integer, Ennemy>> it, Group root, Timer timer) {
        this.setHm = setHm;
        this.it = it;
        this.root = root;
        this.timer = timer;
    }

    /**
     * Launches the enemies with a determinate time gap
     */
    @Override
    public void run() {
        if(it.hasNext()) {
            Map.Entry<Integer, Ennemy> e = it.next();
            e.getValue().getPathTransition().play();
            e.getValue().getPathTransitionS().play();
        }
        else {
            timer.cancel();
        }
    }
}


