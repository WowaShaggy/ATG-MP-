package tests;

import core.Configuration;
import org.testng.annotations.Test;
import tests.ui.TestNGBaseTest;

public class ConfigurationTest extends TestNGBaseTest {
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
