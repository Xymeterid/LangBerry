package sample;

import java.io.File;
import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;



public class Main extends Application {


    public static String screen1ID = "main";
    public static String screen1File = "menu.fxml";
    public static String screen2ID = "list";
    public static String screen2File = "collection.fxml";
    public static String screen3ID = "card";
    public static String screen3File = "card.fxml";
  /*  public static String screen4ID = "lesson";
    public static String screen4File = "Screen4.fxml";*/

    @Override
    public void start(Stage primaryStage) {

        ScreenController mainContainer = new ScreenController();
        mainContainer.loadScreen(Main.screen1ID, Main.screen1File);
        mainContainer.loadScreen(Main.screen2ID, Main.screen2File);
        mainContainer.loadScreen(Main.screen3ID, Main.screen3File);

        mainContainer.setScreen(Main.screen1ID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
