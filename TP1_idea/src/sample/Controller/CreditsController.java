package sample.Controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Credits menu controller
 * @author kevalente
 */
public class CreditsController {
    @FXML
    private javafx.scene.control.Button Exit;

    /**
     * Return to the main menu.
     */
    @FXML
    private void onExit(){
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }
}
