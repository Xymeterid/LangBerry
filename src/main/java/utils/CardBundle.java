package utils;

import entities.WordCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ListResourceBundle;

//ListResourceBundle для передачі карток між різними сценами
public class CardBundle extends ListResourceBundle {

    private WordCard card;
    private requestType type;

    public CardBundle(WordCard card, requestType type) {
        this.card = card;
        this.type = type;
    }

    public WordCard getCard() {
        return this.card;
    }

    public requestType getType() {
        return this.type;
    }

    public void setCard(WordCard card) {
        this.card = card;
    }

    public void setType(requestType type) {
        this.type = type;
    }

    public enum requestType{
        NO_TYPE,
        ADD_NEW,
        SHOW_EXISTING
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"card", card},
                {"request_type", type}
        };
    }
}
