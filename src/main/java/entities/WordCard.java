package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WordCard {

    private int id;
    private String question;
    private String answer;
    private Integer nextReviewDay;
    private Integer nextReviewYear;
    private Integer lastDayIncrease;

    public enum levelOfKnowledge{
        BAD,
        UNSURE,
        GOOD,
        EXCELLENT
    }

    public WordCard(String question, String answer){
        this.question = question;
        this.answer = answer;
        LocalDateTime timeNow = LocalDateTime.now();
        this.nextReviewDay = timeNow.getDayOfYear();
        this.nextReviewYear = timeNow.getYear();
        this.lastDayIncrease = 1;
    }

    public WordCard() {}

    public void solve(levelOfKnowledge level){
        switch (level){
            case BAD:
                lastDayIncrease = lastDayIncrease / 4 + 1;
                break;
            case UNSURE:
                lastDayIncrease = lastDayIncrease + 1;
                break;
            case GOOD:
                lastDayIncrease = lastDayIncrease * 2 + 1;
                break;
            case EXCELLENT:
                lastDayIncrease = lastDayIncrease * 4 + 1;
                break;
        }
        LocalDateTime timeNow = LocalDateTime.now();
        nextReviewDay = timeNow.plusDays(lastDayIncrease).getDayOfYear();
        nextReviewYear = timeNow.plusDays(lastDayIncrease).getYear();
    }

}
