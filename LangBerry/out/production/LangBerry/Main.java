package sample;

import java.io.File;
import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("card.fxml"));
        primaryStage.setTitle("LangBerry");
        primaryStage.setScene(new Scene(root, 350, 600));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
