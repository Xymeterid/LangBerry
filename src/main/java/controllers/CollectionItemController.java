package controllers;

import entities.WordCard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utils.CardBundle;
import utils.FXUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CollectionItemController implements Initializable {

    @FXML
    private Label question;
    @FXML
    private Label answer;

    private WordCard card;

    public void setQuestion(String newQuestion){
        question.setText(newQuestion);
    }

    public void setAnswer(String newAnswer){
        answer.setText(newAnswer);
    }

    public String getQuestion(){
        return question.getText();
    }

    public String getAnswer(){
        return answer.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WordCard card = (WordCard) resources.getObject("card");
        setAnswer(card.getAnswer());
        setQuestion(card.getQuestion());
        this.card = card;
    }

    public void itemClicked() {
        FXUtils.loadScene(getClass().getResource("../fxml/card.fxml"),
                new CardBundle(card, CardBundle.requestType.SHOW_EXISTING));
    }
}
