package controllers;

import database.card.CardDao;
import entities.WordCard;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utils.CardBundle;
import utils.FXUtils;

import javax.smartcardio.Card;
import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable {

    CardBundle.requestType type;
    WordCard card;

    @FXML
    TextField questionInput, answerInput;

    @FXML
    Label info;

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
            info.setText(card.toString());
        }
    }
}
