package tests;

import core.Configuration;
import org.testng.annotations.Test;

public class ConfigurationTest {
    @Test
    public void testExample() {
        String url = Configuration.getProperty("url");
        String username = Configuration.getProperty("username");
        String password = Configuration.getProperty("password");

        // Используйте параметры конфигурации в вашем тесте
        System.out.println("URL: " + url);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
    }
}
