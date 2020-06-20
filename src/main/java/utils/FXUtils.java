package utils;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;

public class FXUtils {

    @Setter
    private static Stage stage;

    public static Scene loadScene(URL path){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(path);
        try {
            Parent root = loader.load();
            Scene res = new Scene(root);
            stage.setScene(res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Scene loadScene(URL path, ListResourceBundle bundle){
        try {
            Parent root = FXMLLoader.load(path, bundle);
            Scene res = new Scene(root);
            stage.setScene(res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
