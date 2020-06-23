package entities;

import java.time.LocalDateTime;

//Клас навчальної картки
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

    public WordCard(int id, String question, String answer, Integer nextReviewDay, Integer nextReviewYear, Integer lastDayIncrease) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.nextReviewDay = nextReviewDay;
        this.nextReviewYear = nextReviewYear;
        this.lastDayIncrease = lastDayIncrease;
    }

    @Override
    public int compareTo(WordCard o) {
        return answer.toLowerCase().compareTo(o.getAnswer().toLowerCase());
    }

    public int getId() {
        return this.id;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public Integer getNextReviewDay() {
        return this.nextReviewDay;
    }

    public Integer getNextReviewYear() {
        return this.nextReviewYear;
    }

    public Integer getLastDayIncrease() {
        return this.lastDayIncrease;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setNextReviewDay(Integer nextReviewDay) {
        this.nextReviewDay = nextReviewDay;
    }

    public void setNextReviewYear(Integer nextReviewYear) {
        this.nextReviewYear = nextReviewYear;
    }

    public void setLastDayIncrease(Integer lastDayIncrease) {
        this.lastDayIncrease = lastDayIncrease;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof WordCard)) return false;
        final WordCard other = (WordCard) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$question = this.getQuestion();
        final Object other$question = other.getQuestion();
        if (this$question == null ? other$question != null : !this$question.equals(other$question)) return false;
        final Object this$answer = this.getAnswer();
        final Object other$answer = other.getAnswer();
        if (this$answer == null ? other$answer != null : !this$answer.equals(other$answer)) return false;
        final Object this$nextReviewDay = this.getNextReviewDay();
        final Object other$nextReviewDay = other.getNextReviewDay();
        if (this$nextReviewDay == null ? other$nextReviewDay != null : !this$nextReviewDay.equals(other$nextReviewDay))
            return false;
        final Object this$nextReviewYear = this.getNextReviewYear();
        final Object other$nextReviewYear = other.getNextReviewYear();
        if (this$nextReviewYear == null ? other$nextReviewYear != null : !this$nextReviewYear.equals(other$nextReviewYear))
            return false;
        final Object this$lastDayIncrease = this.getLastDayIncrease();
        final Object other$lastDayIncrease = other.getLastDayIncrease();
        if (this$lastDayIncrease == null ? other$lastDayIncrease != null : !this$lastDayIncrease.equals(other$lastDayIncrease))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof WordCard;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $question = this.getQuestion();
        result = result * PRIME + ($question == null ? 43 : $question.hashCode());
        final Object $answer = this.getAnswer();
        result = result * PRIME + ($answer == null ? 43 : $answer.hashCode());
        final Object $nextReviewDay = this.getNextReviewDay();
        result = result * PRIME + ($nextReviewDay == null ? 43 : $nextReviewDay.hashCode());
        final Object $nextReviewYear = this.getNextReviewYear();
        result = result * PRIME + ($nextReviewYear == null ? 43 : $nextReviewYear.hashCode());
        final Object $lastDayIncrease = this.getLastDayIncrease();
        result = result * PRIME + ($lastDayIncrease == null ? 43 : $lastDayIncrease.hashCode());
        return result;
    }

    public String toString() {
        return "WordCard(id=" + this.getId() + ", question=" + this.getQuestion() + ", answer=" + this.getAnswer() + ", nextReviewDay=" + this.getNextReviewDay() + ", nextReviewYear=" + this.getNextReviewYear() + ", lastDayIncrease=" + this.getLastDayIncrease() + ")";
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
