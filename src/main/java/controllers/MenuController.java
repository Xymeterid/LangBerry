package controllers;

import javafx.fxml.FXML;
import utils.FXUtils;

public class MenuController {

    @FXML
    public void cardCollectionClicked() {
        FXUtils.loadScene(getClass().getResource("../fxml/card_collection.fxml"));
    }

    @FXML
    public void trainingButtonPressed() {
        FXUtils.loadScene(getClass().getResource("../fxml/training_view.fxml"));
    }

    @FXML
    public void cramButtonPressed() {
        FXUtils.loadScene(getClass().getResource("../fxml/training_view.fxml"));
    }
}
