package tests.junit;


import business.models.Condition;
import business.models.CreateFilterRQ;
import business.models.Order;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class Module4ApiPostTest {

    String accessToken = "Uladzimir-Papeka_tJsTdWqdRluJdZ9vYlK8vY9_GXpTWb3jjD-7ycdeVg4gzspOFqj8a7Fwi__U5B2k";

    private String projectName;
    private CreateFilterRQ createFilterRQ;

    public Module4ApiPostTest(String projectName, CreateFilterRQ createFilterRQ) {
        this.projectName = projectName;
        this.createFilterRQ = createFilterRQ;
    }

    @Parameterized.Parameters(name = "{index}: ProjectName={0}, CreateFilterRQ={1}")
    public static Collection<Object[]> data() {
        CreateFilterRQ createFilterRQ = new CreateFilterRQ();
        createFilterRQ.setDescription("description123");
        createFilterRQ.setName("name123");
        createFilterRQ.setType("launch123");
        createFilterRQ.getConditions().add(new Condition("string123", "string123", "string123"));
        createFilterRQ.getOrders().add(new Order(true, "string123"));

        return Arrays.asList(new Object[][] {
                {"default_personal", createFilterRQ},
                {"12", createFilterRQ}
        });
    }

    @Test
    public void testPostFilter() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .body(createFilterRQ)
                .when()
                .post("http://localhost:8080/api/v1/{projectName}/filter");

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(response.getBody().asString().contains("id"));
    }
}