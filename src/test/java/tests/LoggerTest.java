package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoggerTest {

    private static final Logger logger = LogManager.getLogger(LoggerTest.class);

    @Test
    public void testExample() {
        Reporter.log("This is a test message for TestNG Reporter");
        logger.info("This is a test message.");
        Assert.assertTrue(true);
    }
}
