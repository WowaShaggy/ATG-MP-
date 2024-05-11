package tests;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class StepDefinitions {
    private static final Logger logger = LogManager.getLogger(LoggerTest.class);

    private Response response;
    private String projectName;
    private String createFilterRQ;
    private String accessToken = "Uladzimir-Papeka_tJsTdWqdRluJdZ9vYlK8vY9_GXpTWb3jjD-7ycdeVg4gzspOFqj8a7Fwi__U5B2k";

    @ParameterType(".*")
    public String projectName(String projectName) {
        return projectName;
    }

    @Given("the API is available")
    public void theAPIIsAvailable() {
        // No additional setup needed
    }

    @When("I request the project filter for project {string}")
    public void iRequestTheProjectFilter(String projectName) {
        this.projectName = projectName;
        response = given()
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .when()
                .get("http://localhost:8080/api/v1/{projectName}/filter");
    }

    @When("I send a POST request to create a filter for project {string}")
    public void iSendPostRequest(String projectName) {
        this.projectName = projectName;
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .body(createFilterRQ)
                .when()
                .post("http://localhost:8080/api/v1/{projectName}/filter");
    }

    public void theResponseBodyShouldContainId() {
        assertTrue(response.getBody().asString().contains("id"), "Response body does not contain 'id' for project: " + projectName);
    }

    public void setCreateFilterRQ(String createFilterRQ) {
        this.createFilterRQ = createFilterRQ;
    }

    @Then("the response status code should be 200")
    public void theResponseStatusCodeShouldBe200() {
        assertEquals(response.getStatusCode(), 200,  "Failed for project: " + projectName);
    }

    @Given("I have a greeting")
    public void iHaveAGreeting() {
        ScenarioContext.setContext("greeting", "Hello World");
    }

    @When("I print the greeting")
    public void iPrintTheGreeting() {
        Object greeting = ScenarioContext.getContext("greeting");
        logger.info(greeting);
    }

    @Then("I see {string}")
    public void iSee(String expectedGreeting) {
        Object greeting = ScenarioContext.getContext("greeting");
        assertEquals(expectedGreeting, greeting);
    }

    @Given("I am on the Launch page")
    public void iAmOnTheLaunchPage() {
    }

    @When("I enter the Launch name {string}")
    public void iEnterTheLaunchName(String launchName) {
        logger.info("Entered Launch name: " + launchName);
        ScenarioContext.setContext("launchName", launchName);
    }

    @Then("the Launch with name {string} should be created")
    public void theLaunchWithNameShouldBeCreated(String launchName) {
        Object actualLaunchName = ScenarioContext.getContext("launchName");
        assertEquals(launchName, actualLaunchName);
    }

    @When("I enter the following Launch names:")
    public void iEnterTheFollowingLaunchNames(List<String> launchNames) {
        for (String launchName : launchNames) {
            logger.info("Entered Launch name: " + launchName);
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
