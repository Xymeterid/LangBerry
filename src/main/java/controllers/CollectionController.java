package controllers;

import database.card.CardDao;
import entities.WordCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import utils.CardBundle;
import utils.FXUtils;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CollectionController implements Initializable {

    @FXML
    VBox cards_container;

    public void backButtonClicked() {
        FXUtils.loadScene(getClass().getResource("../fxml/new_menu.fxml"));
    }

    public void addButtonPressed() {
        FXUtils.loadScene(getClass().getResource("../fxml/card.fxml"),
                new CardBundle(new WordCard(), CardBundle.requestType.ADD_NEW));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CardDao cardDao = new CardDao();
        HashSet<WordCard> allCards = (HashSet<WordCard>) cardDao.getAll();
        List<WordCard> list = new ArrayList<>(allCards);
        Collections.sort(list);
        for (WordCard card : list) {
            try {
                Pane pane = FXMLLoader.load(getClass().getResource("../fxml/collection_item.fxml"),
                        new CardBundle(card, CardBundle.requestType.NO_TYPE));
                cards_container.getChildren().add(pane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
