package tests.junit.api;

import business.models.Content;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Module7Test {
    private final String accessToken = "wowatest1_PqhGRBVoR32rUFr_YMsiFY0hxOKKe5_y6VMzacA7bV17dJEQPNRU5yWxD6Xnc0XG";

    @Test
    public void testGetProjectFilter() {
        String projectName = "superadmin_personal";
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .when()
                .get("http://localhost:8080/api/v1/{projectName}/filter");

        assertEquals(200, response.getStatusCode());

        Content content = response.getBody().as(Content.class);
        Assertions.assertTrue(content.getContent().stream().anyMatch(filter -> filter.getName().equals("DEMO_FILTER")));
    }

    @Test
    public void testGetProjectFilterNegative() {
        String projectName = "non_existent_project";
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .when()
                .get("http://localhost:8080/api/v1/{projectName}/filter");

        assertEquals(404, response.getStatusCode());

    }
}
