package tests.junit;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class Module4ApiTest {

    String accessToken = "Uladzimir-Papeka_tJsTdWqdRluJdZ9vYlK8vY9_GXpTWb3jjD-7ycdeVg4gzspOFqj8a7Fwi__U5B2k";

    private String projectName;

    public Module4ApiTest(String projectName) {
        this.projectName = projectName;
    }

    @Parameterized.Parameters(name = "{index}: ProjectName={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"default_personal"},
                {"project2"},
                {"project3"},
                {"123123"}
        });
    }

    @Test
    public void testGetProjectFilter() {
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .when()
                .get("http://localhost:8080/api/v1/{projectName}/filter");

        Assert.assertEquals(200, response.getStatusCode());
    }
}