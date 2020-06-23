package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ListResourceBundle;

//ListResourceBundle для передачі інформації про тип тренування
public class TypeBundle extends ListResourceBundle {

    private command_type type;

    public TypeBundle(command_type type) {
        this.type = type;
    }

    public command_type getType() {
        return this.type;
    }

    public void setType(command_type type) {
        this.type = type;
    }

    public enum command_type{
        TRAINING,
        CRAM
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"command", type}
        };
    }
}
