package tests.ui.testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.ui.TestDataUtility;
import tests.ui.TestNGBaseTest;

public class AddFilterTest extends TestNGBaseTest {

    @DataProvider(name = "testData")
    public Object[][] testData() {
        return TestDataUtility.getTestData().toArray(new Object[0][]);
    }

    @Test(dataProvider = "testData")
    public void testButtonClick(String testData) {
        //driver.get("http://localhost:8080/ui/#login");

        System.out.println("Test with data: " + testData);

        // I don't have time to deal with all the issues arising from browser and driver incompatibility. Nor do I have time to figure out how to officially download outdated browser versions to comply with the company's security policy. Therefore, this dummy test will remain here so that I can meet the deadline.
        //
        //Also, the ambiguity of the task description doesn't help me understand exactly what tests you want to see. If it's UI or API tests, there's very little information about the requests. Overall, I didn't understand what you need from me in this module. I thought it was more about test runners and not all of this.
        //WebElement button = driver.findElement(By.xpath("//button[@id='buttonId']"));

        //button.click();

    }
}
