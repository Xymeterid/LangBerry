package controllers;

import database.card.CardDao;
import entities.WordCard;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import utils.FXUtils;
import utils.TypeBundle;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    Button trainingButton, settingsButton;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CardDao cardDao = new CardDao();
        LocalDateTime timeNow = LocalDateTime.now();
        HashSet<WordCard> cardsToComplete = (HashSet<WordCard>) cardDao.getAlThatMatch("WHERE nextReviewYear<" + timeNow.getYear() + " OR " +
                "(nextReviewYear=" + timeNow.getYear() + " AND nextReviewDay<=" + timeNow.getDayOfYear() + ")");
        if (cardsToComplete.size() > 0){
            trainingButton.setText("Training ("+cardsToComplete.size()+")");
        }
        settingsButton.setVisible(false);
    }
}
