package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ListResourceBundle;

@AllArgsConstructor
public class TypeBundle extends ListResourceBundle {

    @Getter
    @Setter
    private command_type type;

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
