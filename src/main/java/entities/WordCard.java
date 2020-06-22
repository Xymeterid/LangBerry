package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

//Клас навчальної картки
@Data
@AllArgsConstructor
public class WordCard implements Comparable<WordCard>{

    //Унікальний id картки
    private int id;
    //Питання
    private String question;
    //Відповідь
    private String answer;
    //День наступного ревью
    private Integer nextReviewDay;
    //Рік наступного ревью
    private Integer nextReviewYear;
    //Останнє збільшення кількості днів до наступного ревью (необхідно що б єффективно збільшувати кількість в залежності від відповіді користувача)
    private Integer lastDayIncrease;

    @Override
    public int compareTo(WordCard o) {
        return answer.toLowerCase().compareTo(o.getAnswer().toLowerCase());
    }

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

    //Копіювальний конструктор
    public WordCard(WordCard parent){
        this.id = parent.id;
        this.question = parent.question;
        this.answer = parent.answer;
        this.lastDayIncrease = parent.lastDayIncrease;
        this.nextReviewYear = parent.nextReviewYear;
        this.nextReviewDay = parent.nextReviewDay;
    }

    //Виставляє наступний день ревью в залежності від рівня знань
    public void solve(levelOfKnowledge level){
        switch (level){
            case BAD:
                lastDayIncrease = lastDayIncrease / 4 + 1;
                break;
            case UNSURE:
                //Do nothing
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
