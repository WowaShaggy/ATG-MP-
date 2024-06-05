package tests.selenium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {

    protected WebDriver webDriver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        String browser = System.getProperty("browser", "chrome");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.ANY);

        if (browser.equalsIgnoreCase("firefox")) {
            capabilities.setBrowserName("firefox");
        } else if (browser.equalsIgnoreCase("chrome")) {
            capabilities.setBrowserName("chrome");
        } else if (browser.equalsIgnoreCase("edge")) {
            capabilities.setBrowserName("MicrosoftEdge");
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        webDriver = new RemoteWebDriver(new URL(GridConfig.HUB_URL), capabilities);
        Configuration.driverManagerEnabled = false;
        Configuration.remote = GridConfig.HUB_URL;

        // Configure Selenide
        Configuration.timeout = 10000; // 10 seconds
        Configuration.pageLoadTimeout = 20000; // 20 seconds
        Configuration.screenshots = true;
        Configuration.savePageSource = false;

        // Add Allure Selenide listener for logging
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @AfterEach
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
