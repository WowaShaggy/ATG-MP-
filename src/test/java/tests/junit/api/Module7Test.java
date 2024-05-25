package tests.junit.api;

import business.ConfigUtil;
import business.models.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Module7Test {
    private final String accessToken = ConfigUtil.getProperty("accessToken");
    private final String baseUrl = ConfigUtil.getProperty("baseUrl");

    @Test
    public void testGetProjectFilter() {
        String projectName = "superadmin_personal";
        Response response = given()
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .when()
                .get(baseUrl + "/api/v1/{projectName}/filter");

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
                .get(baseUrl + "/api/v1/{projectName}/filter");

        assertEquals(404, response.getStatusCode());

    }

    @Test
    public void testPostFilter() {
        String projectName = "superadmin_personal";
        String name = UUID.randomUUID().toString().substring(0, 10);

        // Create request body
        CreateFilterRQ createFilterRQ = new CreateFilterRQ();
        createFilterRQ.setDescription("description test");
        createFilterRQ.setName(name);
        createFilterRQ.setType("launch");
        createFilterRQ.getConditions().add(new Condition("has", "compositeAttribute", "demo"));
        createFilterRQ.getOrders().add(new Order(true, "id"));

        // Execute API call
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .body(createFilterRQ)
                .when()
                .post(baseUrl + "/api/v1/{projectName}/filter");

        // Verify response
        assertEquals(201, response.getStatusCode());

        // Deserialize response body
        EntryCreatedRS createFilterResponse = response.getBody().as(EntryCreatedRS.class);
        Assertions.assertNotNull(createFilterResponse);
        Assertions.assertNotNull(createFilterResponse.getId());
    }

    @Test
    @Description("invalid body")
    public void testPostNegative() {
        String projectName = "superadmin_personal";
        String name = UUID.randomUUID().toString().substring(0, 10);

        // Create request body
        CreateFilterRQ createFilterRQ = new CreateFilterRQ();
        createFilterRQ.setDescription("description test");
        createFilterRQ.setName(name);
        createFilterRQ.setType("launch");
        createFilterRQ.getConditions().add(new Condition("has", "compositeAttribute", "demo"));

        // Execute API call
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .body(createFilterRQ)
                .when()
                .post(baseUrl + "/api/v1/{projectName}/filter");

        // Verify response
        assertEquals(400, response.getStatusCode());

    }

    @Test
    public void testPutFilter() {
        String projectName = "superadmin_personal";

        // Step 1: Perform GET request
        Response getResponse = given()
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .when()
                .get(baseUrl + "/api/v1/{projectName}/filter");

        assertEquals(200, getResponse.getStatusCode());

        Content content = getResponse.getBody().as(Content.class);
        UserFilterResource firstFilter = content.getContent().stream().findFirst().orElseThrow(() ->
                new RuntimeException("No filters found"));

        // Step 2: Modify the first filter's description
        String newDescription = "New description";
        UpdateUserFilterRQ updateUserFilterRQ = new UpdateUserFilterRQ();
        updateUserFilterRQ.setConditions(firstFilter.getConditions());
        updateUserFilterRQ.setDescription(newDescription);
        updateUserFilterRQ.setName(firstFilter.getName());
        updateUserFilterRQ.setOrders(firstFilter.getOrders());
        updateUserFilterRQ.setType(firstFilter.getType());

        // Step 3: Perform PUT request with modified body
        given()
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .pathParam("filterId", firstFilter.getId())
                .contentType("application/json")
                .body(updateUserFilterRQ)
                .when()
                .put(baseUrl + "/api/v1/{projectName}/filter/{filterId}")
                .then()
                .statusCode(200);

        // Step 4: Perform GET request again to verify the change
        Response getResponseAfterPut = given()
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .when()
                .get(baseUrl + "/api/v1/{projectName}/filter");

        assertEquals(200, getResponseAfterPut.getStatusCode());

        Content contentAfterPut = getResponseAfterPut.getBody().as(Content.class);
        Assertions.assertTrue(contentAfterPut.getContent().stream()
                        .anyMatch(filter -> filter.getId() ==
                                (firstFilter.getId()) && filter.getDescription().equals(newDescription)),
                "The filter description was not updated correctly");
    }

    @Test
    @Description("id is 0")
    public void testPutFilterNegative() {
        String projectName = "superadmin_personal";

        // Step 1: Perform GET request
        Response getResponse = given()
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .when()
                .get(baseUrl + "/api/v1/{projectName}/filter");

        assertEquals(200, getResponse.getStatusCode());

        Content content = getResponse.getBody().as(Content.class);
        UserFilterResource firstFilter = content.getContent().stream().findFirst().orElseThrow(() ->
                new RuntimeException("No filters found"));

        // Step 2: Modify the first filter's description
        String newDescription = "New description";
        UpdateUserFilterRQ updateUserFilterRQ = new UpdateUserFilterRQ();
        updateUserFilterRQ.setConditions(firstFilter.getConditions());
        updateUserFilterRQ.setDescription(newDescription);
        updateUserFilterRQ.setName(firstFilter.getName());
        updateUserFilterRQ.setOrders(firstFilter.getOrders());
        updateUserFilterRQ.setType(firstFilter.getType());

        // Step 3: Perform PUT request with modified body
        given()
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .pathParam("filterId", 0)
                .contentType("application/json")
                .body(updateUserFilterRQ)
                .when()
                .put(baseUrl + "/api/v1/{projectName}/filter/{filterId}")
                .then()
                .statusCode(404);

    }

    // NO PATCH API FOR FILTERS, WHY IT IS REQUIRED IN HOMETASK THEN

    @Test
    public void testCreateAndDeleteFilter() {
        String projectName = "superadmin_personal";
        String name = UUID.randomUUID().toString().substring(0, 10);

        // Create request body
        CreateFilterRQ createFilterRQ = new CreateFilterRQ();
        createFilterRQ.setDescription("description test");
        createFilterRQ.setName(name);
        createFilterRQ.setType("launch");
        createFilterRQ.getConditions().add(new Condition("has", "compositeAttribute", "demo"));
        createFilterRQ.getOrders().add(new Order(true, "id"));

        // Execute API call to create filter
        Response createResponse = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .body(createFilterRQ)
                .when()
                .post(baseUrl + "/api/v1/{projectName}/filter");

        // Verify response status code
        assertEquals(201, createResponse.getStatusCode());

        // Deserialize response body
        EntryCreatedRS createFilterResponse = createResponse.getBody().as(EntryCreatedRS.class);
        Assertions.assertNotNull(createFilterResponse);
        Assertions.assertNotNull(createFilterResponse.getId());

        // Perform DELETE operation
        int filterId = createFilterResponse.getId();
        Response deleteResponse = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .pathParam("filterId", filterId)
                .when()
                .delete(baseUrl + "/api/v1/{projectName}/filter/{filterId}");

        // Verify response status code
        assertEquals(200, deleteResponse.getStatusCode());

        // Verify response body
        OperationCompletionRS messageResponse = deleteResponse.getBody().as(OperationCompletionRS.class);
        Assertions.assertNotNull(messageResponse);
        Assertions.assertTrue(messageResponse.getMessage().contains("successfully deleted"));
    }

    @Test
    public void testDeleteFilterNegative() {

        String projectName = "superadmin_personal";

        // Perform DELETE operation
        int filterId = RandomUtils.nextInt() + 1000;
        Response deleteResponse = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .pathParam("projectName", projectName)
                .pathParam("filterId", filterId)
                .when()
                .delete(baseUrl + "/api/v1/{projectName}/filter/{filterId}");

        // Verify response status code
        assertEquals(404, deleteResponse.getStatusCode());

    }
}

