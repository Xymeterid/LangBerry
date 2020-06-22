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

//Контреллер-клас для окремого елементу в коллекції карток - відповідає за усі дії користувача з цим елементом
public class CollectionItemController implements Initializable {

    @FXML
    private Label question;
    @FXML
    private Label answer;

    private WordCard card;

    //Встановлює задане питання, зменшуючи його довжину за потреби
    public void setQuestion(String newQuestion){
        if (newQuestion.length() > 10)
            question.setText(newQuestion.substring(0, 7) + "...");
        else question.setText(newQuestion);
    }

    //Встановлює задану відповідь, зменшуючи її довжину за потреби
    public void setAnswer(String newAnswer){
        if (newAnswer.length() > 20)
            answer.setText(newAnswer.substring(0, 17) + "...");
        else answer.setText(newAnswer);
    }

    //Ініціалізація елементу - отримуємо картку з ResourceBundle, встановлюємо питання і відповідь
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WordCard card = (WordCard) resources.getObject("card");
        setAnswer(card.getAnswer());
        setQuestion(card.getQuestion());
        this.card = card;
    }

    //Відкриває екран картки з режимом модифікації
    public void itemClicked() {
        FXUtils.loadScene(getClass().getResource("../fxml/card.fxml"),
                new CardBundle(card, CardBundle.requestType.SHOW_EXISTING));
    }
}
