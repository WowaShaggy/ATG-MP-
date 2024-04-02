package tests.ui;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JunitBaseTest {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uladzimir_Papeka\\Desktop\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);

        //set NUMBER_OF_THREADS=5
        int numberOfThreads = Integer.parseInt(System.getenv("NUMBER_OF_THREADS"));
        System.setProperty("junit.jupiter.execution.threads.max", String.valueOf(numberOfThreads));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
