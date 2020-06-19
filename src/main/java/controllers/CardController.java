package controllers;

import database.card.CardDao;
import entities.WordCard;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import utils.CardBundle;
import utils.FXUtils;

import javax.smartcardio.Card;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class CardController implements Initializable {

    CardBundle.requestType type;
    WordCard card;

    @FXML
    TextField questionInput, answerInput;

    @FXML
    Label nextReviewIn, lastReviewWas, statistics, nextReviewInLabel, lastReviewWasLabel;

    @FXML
    Button deleteButton;

    @FXML
    TextField importCodeArea;

    public void backButtonPressed() {
        FXUtils.loadScene(getClass().getResource("../fxml/card_collection.fxml"));
    }

    public void saveButtonPressed() {
        CardDao cardDao = new CardDao();
        if (type == CardBundle.requestType.ADD_NEW){
            cardDao.save(new WordCard(questionInput.getText(), answerInput.getText()));
        }
        else if (type == CardBundle.requestType.SHOW_EXISTING){
            card.setAnswer(answerInput.getText());
            card.setQuestion(questionInput.getText());
            cardDao.update(card);
        }
        FXUtils.loadScene(getClass().getResource("../fxml/card_collection.fxml"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        card = (WordCard) resources.getObject("card");
        type = (CardBundle.requestType) resources.getObject("request_type");
        if (type == CardBundle.requestType.SHOW_EXISTING){
            questionInput.setText(card.getQuestion());
            answerInput.setText(card.getAnswer());

            LocalDateTime timeNow = LocalDateTime.of(LocalDate.now(), LocalTime.NOON);
            LocalDateTime nextReview = LocalDateTime.of(LocalDate.ofYearDay(card.getNextReviewYear(), card.getNextReviewDay()), LocalTime.NOON);
            LocalDateTime lastReview = nextReview.minusDays(card.getLastDayIncrease());
            lastReviewWas.setText(lastReview.toLocalDate().toString());
            if (nextReview.isBefore(timeNow) || nextReview.equals(timeNow)){
                nextReviewIn.setText("Now");
            }
            else {
                int temp = nextReview.getDayOfYear() - timeNow.getDayOfYear();
                while (temp < 0) temp += 365;
                if (temp == 1){
                    nextReviewIn.setText(nextReview.toLocalDate().toString() + " (" + temp + " day)");
                }
                else {
                    nextReviewIn.setText(nextReview.toLocalDate().toString() + " (" + temp + " days)");
                }
            }
            System.out.println(card.toString());
        }
        else {
            statistics.setVisible(false);
            nextReviewIn.setVisible(false);
            lastReviewWas.setVisible(false);
            nextReviewInLabel.setVisible(false);
            lastReviewWasLabel.setVisible(false);
            deleteButton.setVisible(false);
        }

        importCodeArea.textProperty().addListener((observable, oldValue, newValue) -> {
            String[] splits = checkIfValidCode(newValue);
            if (splits != null){
                questionInput.setText(splits[0]);
                answerInput.setText(splits[1]);
            }
        });
    }

    private String[] checkIfValidCode(String newValue) {
        String[] splits = newValue.split("#");
        if (splits.length != 2){
            splits = new String[2];
            splits[0] = "";
            splits[1] = "";
        }
        return splits;
    }

    public void onDeleteButtonPressed(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText("Are you sure you want to delete this card?");
        alert.setContentText("This process is irreversible");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            CardDao cardDao = new CardDao();
            cardDao.delete(card);
            FXUtils.loadScene(getClass().getResource("../fxml/card_collection.fxml"));
        }
    }
}
