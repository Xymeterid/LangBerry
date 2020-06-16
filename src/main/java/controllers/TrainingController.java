package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.FXUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class TrainingController implements Initializable {

    @FXML
    Button badButton, unsureButton, goodButton, excellentButton, showAnswerButton;

    @FXML
    Label answer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        badButton.setVisible(false);
        unsureButton.setVisible(false);
        goodButton.setVisible(false);
        excellentButton.setVisible(false);
        answer.setVisible(false);
    }

    public void showAnswerButtonPressed() {
        answer.setVisible(true);
        badButton.setVisible(true);
        unsureButton.setVisible(true);
        goodButton.setVisible(true);
        excellentButton.setVisible(true);

        showAnswerButton.setVisible(false);
    }

    public void backButtonPressed() {
        FXUtils.loadScene(getClass().getResource("../fxml/new_menu.fxml"));
    }
}
