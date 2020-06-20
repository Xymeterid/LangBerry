package controllers;

import database.card.CardDao;
import entities.WordCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import utils.FXUtils;
import utils.TypeBundle;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;

public class TrainingController implements Initializable {

    @FXML
    Button badButton, unsureButton, goodButton, excellentButton, showAnswerButton;

    @FXML
    Label question, answer;

    private TypeBundle.command_type command_type;
    private HashSet<WordCard> cardsToComplete;
    private Iterator<WordCard> iterator;
    private WordCard currentCard;
    private CardDao cardDao = new CardDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        command_type = (TypeBundle.command_type) resources.getObject("command");
        generateReviewList();

        iterator = cardsToComplete.iterator();

        showNextCard();
    }

    private void showNextCard() {
        badButton.setVisible(false);
        unsureButton.setVisible(false);
        goodButton.setVisible(false);
        excellentButton.setVisible(false);
        answer.setVisible(false);
        if (iterator.hasNext()){
            currentCard = iterator.next();
            question.setText(currentCard.getQuestion());
            answer.setText(currentCard.getAnswer());
            showAnswerButton.setVisible(true);
        }
        else {
            showAnswerButton.setVisible(false);
            if (command_type == TypeBundle.command_type.TRAINING) {
                question.setText("That's it, there are no more cards left today");
            }
            else if (command_type == TypeBundle.command_type.CRAM){
                question.setText("Cram session is over");
            }
        }
    }

    private void generateReviewList() {
        if (command_type == TypeBundle.command_type.TRAINING){
            LocalDateTime timeNow = LocalDateTime.now();
            cardsToComplete = (HashSet<WordCard>) cardDao.getAlThatMatch("WHERE nextReviewYear<"+timeNow.getYear()+" OR " +
                    "(nextReviewYear="+timeNow.getYear()+" AND nextReviewDay<="+timeNow.getDayOfYear()+")");
        }
        else if (command_type == TypeBundle.command_type.CRAM){
            CardDao cardDao = new CardDao();
            cardsToComplete = (HashSet<WordCard>) cardDao.getAlThatMatch("ORDER BY lastDayIncrease LIMIT 20");
        }
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

    public void badButtonPressed() {
        if (command_type == TypeBundle.command_type.TRAINING) {
            currentCard.solve(WordCard.levelOfKnowledge.BAD);
            cardDao.update(currentCard);
        }
        showNextCard();
    }

    public void unsureButtonPressed() {
        if (command_type == TypeBundle.command_type.TRAINING) {
            currentCard.solve(WordCard.levelOfKnowledge.UNSURE);
            cardDao.update(currentCard);
        }
        showNextCard();
    }

    public void goodButtonPressed() {
        if (command_type == TypeBundle.command_type.TRAINING) {
            currentCard.solve(WordCard.levelOfKnowledge.GOOD);
            cardDao.update(currentCard);
        }
        showNextCard();
    }

    public void excellentButtonPressed() {
        if (command_type == TypeBundle.command_type.TRAINING) {
            currentCard.solve(WordCard.levelOfKnowledge.EXCELLENT);
            cardDao.update(currentCard);
        }
        showNextCard();
    }
}
