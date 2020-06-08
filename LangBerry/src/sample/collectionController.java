package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class collectionController implements Initializable, ControlledScreen{
    ScreenController myController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent;
    }
    @FXML
    private void goToScreen1(ActionEvent event){
        myController.setScreen(Main.screen1ID);
    }


}
