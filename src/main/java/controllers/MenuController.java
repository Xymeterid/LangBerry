package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import utils.FXUtils;
import utils.TypeBundle;

public class MenuController {

    @FXML
    public void cardCollectionClicked() {
        FXUtils.loadScene(getClass().getResource("../fxml/card_collection.fxml"));
    }

    @FXML
    public void trainingButtonPressed() {
        FXUtils.loadScene(getClass().getResource("../fxml/training_view.fxml"), new TypeBundle(TypeBundle.command_type.TRAINING));
    }

    @FXML
    public void cramButtonPressed() {
        FXUtils.loadScene(getClass().getResource("../fxml/training_view.fxml"), new TypeBundle(TypeBundle.command_type.CRAM));
    }

    public void exitButtonPressed() {
        Platform.exit();
    }
}
