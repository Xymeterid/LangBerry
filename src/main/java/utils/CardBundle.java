package utils;

import entities.WordCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ListResourceBundle;

//ListResourceBundle для передачі карток між різними сценами
@AllArgsConstructor
public class CardBundle extends ListResourceBundle {

    @Getter @Setter
    private WordCard card;
    @Getter @Setter
    private requestType type;

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
