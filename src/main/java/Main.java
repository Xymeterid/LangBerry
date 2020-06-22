import database.SqlManager;
import database.TableManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.FXUtils;

//Головний клас для запуску програми
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SqlManager.connect();
        TableManager.initiateTables();

        FXUtils.setStage(primaryStage);
        Scene mainMenu = FXUtils.loadScene(getClass().getResource("fxml/new_menu.fxml"));
        mainMenu.getStylesheets().add(getClass().getResource("css/common_stylesheet.css").toExternalForm());
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
