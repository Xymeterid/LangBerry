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

//Контреллер-клас для головного меню - відповідає за усі дії користувача на цьому екрані
public class MenuController implements Initializable {

    @FXML
    Button trainingButton, settingsButton;

    //Відкриває сцену колекції карток
    @FXML
    public void cardCollectionClicked() {
        FXUtils.loadScene(getClass().getResource("../fxml/card_collection.fxml"));
    }

    //Відкриває сцену навчання у варіації training
    @FXML
    public void trainingButtonPressed() {
        FXUtils.loadScene(getClass().getResource("../fxml/training_view.fxml"), new TypeBundle(TypeBundle.command_type.TRAINING));
    }

    //Відкриває сцену навчання у варіації cram
    @FXML
    public void cramButtonPressed() {
        FXUtils.loadScene(getClass().getResource("../fxml/training_view.fxml"), new TypeBundle(TypeBundle.command_type.CRAM));
    }

    //Закриває програму
    public void exitButtonPressed() {
        Platform.exit();
    }

    //Ініціалізація сцени - позначаємо на клавіші training кількість доступних карток для вивчення
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
