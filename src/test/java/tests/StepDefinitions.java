package tests;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.List;

public class StepDefinitions {
    private String greeting;

    @Given("I have a greeting")
    public void iHaveAGreeting() {
        ScenarioContext.setContext("greeting", "Hello World");
    }

    @When("I print the greeting")
    public void iPrintTheGreeting() {
        Object greeting = ScenarioContext.getContext("greeting");
        System.out.println(greeting);
    }

    @Then("I see {string}")
    public void iSee(String expectedGreeting) {
        Object greeting = ScenarioContext.getContext("greeting");
        Assert.assertEquals(expectedGreeting, greeting);
    }

    @Given("I am on the Launch page")
    public void iAmOnTheLaunchPage() {
    }

    @When("I enter the Launch name {string}")
    public void iEnterTheLaunchName(String launchName) {
        System.out.println("Entered Launch name: " + launchName);
        ScenarioContext.setContext("launchName", launchName);
    }

    @Then("the Launch with name {string} should be created")
    public void theLaunchWithNameShouldBeCreated(String launchName) {
        Object actualLaunchName = ScenarioContext.getContext("launchName");
        Assert.assertEquals(launchName, actualLaunchName);
    }

    @When("I enter the following Launch names:")
    public void iEnterTheFollowingLaunchNames(List<String> launchNames) {
        for (String launchName : launchNames) {
            System.out.println("Entered Launch name: " + launchName);
        }
        ScenarioContext.setContext("launchNames", launchNames);
    }

    @Then("the Launches should be created")
    public void theLaunchesShouldBeCreated() {
        Object launchNames = ScenarioContext.getContext("launchNames");
        Assert.assertNotNull(launchNames);
    }

    @Given("I am logged in")
    public void iAmLoggedIn() {
    }

    @Given("I am on the Filters page")
    public void iAmOnTheFiltersPage() {
    }

    @When("I navigate to the Launchers section")
    public void iNavigateToTheLaunchersSection() {
    }

    @Then("I should see a list of available launchers")
    public void iShouldSeeAListOfAvailableLaunchers() {
    }

    @When("I navigate to the Filters section")
    public void iNavigateToTheFiltersSection() {
    }

    @And("I select {string} status")
    public void iSelectStatus(String status) {
    }

    @Then("I should see only upcoming launchers")
    public void iShouldSeeOnlyUpcomingLaunchers() {
    }

    @But("I should not see past launchers")
    public void iShouldNotSeePastLaunchers() {
    }
}
