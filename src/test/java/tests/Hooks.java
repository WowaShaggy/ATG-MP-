package tests;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void beforeScenario() {
    }

    @After
    public void afterScenario() {
    }

    @Before("@someTag")
    public void beforeScenarioWithSomeTag() {
    }

    @After("@someTag")
    public void afterScenarioWithSomeTag() {
    }

    @Before("@someOtherTag")
    public void beforeScenarioWithSomeOtherTag() {
    }

    @After("@someOtherTag")
    public void afterScenarioWithSomeOtherTag() {
    }
}
