package tests.testNG.api;

import business.models.Condition;
import business.models.CreateFilterRQ;
import business.models.Order;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Module4ApiTest {

    String accessToken = "Uladzimir-Papeka_tJsTdWqdRluJdZ9vYlK8vY9_GXpTWb3jjD-7ycdeVg4gzspOFqj8a7Fwi__U5B2k";

    @Test(dataProvider = "projectNames")
    public void testGetProjectFilter(String projectName) {
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .when()
                .get("http://localhost:8080/api/v1/{projectName}/filter");

        assertEquals(response.getStatusCode(), 200);
    }

    @Test(dataProvider = "filterData")
    public void testPostFilter(String projectName, CreateFilterRQ createFilterRQ) {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .body(createFilterRQ)
                .when()
                .post("http://localhost:8080/api/v1/{projectName}/filter");

        assertEquals(response.getStatusCode(), 200);

        assertTrue(response.getBody().asString().contains("id"));
    }

    @DataProvider(name = "projectNames")
    public Object[][] projectNames() {
        return new Object[][] {
                {"project1"},
                {"project2"},
                {"project3"},
                {"default_personal"}
        };
    }

    @DataProvider(name = "filterData")
    public Object[][] filterData() {
        CreateFilterRQ createFilterRQ = new CreateFilterRQ();
        createFilterRQ.setDescription("description");
        createFilterRQ.setName("name");
        createFilterRQ.setType("launch");
        createFilterRQ.getConditions().add(new Condition("string", "string", "string"));
        createFilterRQ.getOrders().add(new Order(true, "string"));

        return new Object[][] {
                {"default_personal", createFilterRQ},
                {"12", createFilterRQ},
        };
    }
}
