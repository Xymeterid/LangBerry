import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/card.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("LangBerry");
        primaryStage.setScene(new Scene(root, 350.0D, 600.0D));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
