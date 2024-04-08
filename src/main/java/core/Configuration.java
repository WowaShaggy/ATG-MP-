package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            // Обработайте ошибку чтения файла конфигурации
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}