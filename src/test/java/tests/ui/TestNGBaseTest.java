package tests.ui;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestNGBaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Uladzimir_Papeka\\Desktop\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
