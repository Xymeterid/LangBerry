package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//Клас для зручної роботи з файлом config.properties
public class ProjProperties {

    static String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
    static Properties properties;

    //Отримання property за наданим ключем
    public static String getProperty(String key){
        properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (properties.containsKey(key)){
            return properties.getProperty(key);
        }
        return null;
    }
}
