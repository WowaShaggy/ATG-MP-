package tests.junit.api;

import business.ConfigUtil;
import business.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class Module7AnotherApiClientTest {
    private final String accessToken = ConfigUtil.getProperty("accessToken");
    private final String baseUrl = ConfigUtil.getProperty("baseUrl");
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetProjectFilter() throws Exception {
        String projectName = "superadmin_personal";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(baseUrl + "/api/v1/" + projectName + "/filter");
        request.setHeader("Authorization", "Bearer " + accessToken);

        HttpResponse response = httpClient.execute(request);
        assertEquals(200, response.getStatusLine().getStatusCode());

        String responseBody = EntityUtils.toString(response.getEntity());
        Content content = objectMapper.readValue(responseBody, Content.class);
        Assertions.assertTrue(content.getContent().stream().anyMatch(filter -> filter.getName().equals("DEMO_FILTER")));

        httpClient.close();
    }

    @Test
    public void testGetProjectFilterNegative() throws Exception {
        String projectName = "non_existent_project";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(baseUrl + "/api/v1/" + projectName + "/filter");
        request.setHeader("Authorization", "Bearer " + accessToken);

        HttpResponse response = httpClient.execute(request);
        assertEquals(404, response.getStatusLine().getStatusCode());

        httpClient.close();
    }

    @Test
    public void testPostFilter() throws Exception {
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
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(baseUrl + "/api/v1/" + projectName + "/filter");
        request.setHeader("Authorization", "Bearer " + accessToken);
        request.setHeader("Content-Type", "application/json");

        String jsonRequest = objectMapper.writeValueAsString(createFilterRQ);
        request.setEntity(new StringEntity(jsonRequest));

        HttpResponse response = httpClient.execute(request);
        assertEquals(201, response.getStatusLine().getStatusCode());

        String responseBody = EntityUtils.toString(response.getEntity());
        EntryCreatedRS createFilterResponse = objectMapper.readValue(responseBody, EntryCreatedRS.class);
        Assertions.assertNotNull(createFilterResponse);
        Assertions.assertNotNull(createFilterResponse.getId());

        httpClient.close();
    }

    @Test
    @Description("invalid body")
    public void testPostNegative() throws Exception {
        String projectName = "superadmin_personal";
        String name = UUID.randomUUID().toString().substring(0, 10);

        // Create request body
        CreateFilterRQ createFilterRQ = new CreateFilterRQ();
        createFilterRQ.setDescription("description test");
        createFilterRQ.setName(name);
        createFilterRQ.setType("launch");
        createFilterRQ.getConditions().add(new Condition("has", "compositeAttribute", "demo"));

        // Execute API call
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(baseUrl + "/api/v1/" + projectName + "/filter");
        request.setHeader("Authorization", "Bearer " + accessToken);
        request.setHeader("Content-Type", "application/json");

        String jsonRequest = objectMapper.writeValueAsString(createFilterRQ);
        request.setEntity(new StringEntity(jsonRequest));

        HttpResponse response = httpClient.execute(request);
        assertEquals(400, response.getStatusLine().getStatusCode());

        httpClient.close();
    }

    @Test
    public void testPutFilter() throws Exception {
        String projectName = "superadmin_personal";

        // Step 1: Perform GET request
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet(baseUrl + "/api/v1/" + projectName + "/filter");
        getRequest.setHeader("Authorization", "Bearer " + accessToken);

        HttpResponse getResponse = httpClient.execute(getRequest);
        assertEquals(200, getResponse.getStatusLine().getStatusCode());

        String getResponseBody = EntityUtils.toString(getResponse.getEntity());
        Content content = objectMapper.readValue(getResponseBody, Content.class);
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
        HttpPut putRequest = new HttpPut(baseUrl + "/api/v1/" + projectName + "/filter/" + firstFilter.getId());
        putRequest.setHeader("Authorization", "Bearer " + accessToken);
        putRequest.setHeader("Content-Type", "application/json");

        String jsonPutRequest = objectMapper.writeValueAsString(updateUserFilterRQ);
        putRequest.setEntity(new StringEntity(jsonPutRequest));

        HttpResponse putResponse = httpClient.execute(putRequest);
        assertEquals(200, putResponse.getStatusLine().getStatusCode());

        // Step 4: Perform GET request again to verify the change
        HttpResponse getResponseAfterPut = httpClient.execute(getRequest);
        assertEquals(200, getResponseAfterPut.getStatusLine().getStatusCode());

        String getResponseBodyAfterPut = EntityUtils.toString(getResponseAfterPut.getEntity());
        Content contentAfterPut = objectMapper.readValue(getResponseBodyAfterPut, Content.class);
        Assertions.assertTrue(contentAfterPut.getContent().stream()
                        .anyMatch(filter -> filter.getId() ==
                                (firstFilter.getId()) && filter.getDescription().equals(newDescription)),
                "The filter description was not updated correctly");

        httpClient.close();
    }

    @Test
    @Description("id is 0")
    public void testPutFilterNegative() throws Exception {
        String projectName = "superadmin_personal";

        // Step 1: Perform GET request
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet(baseUrl + "/api/v1/" + projectName + "/filter");
        getRequest.setHeader("Authorization", "Bearer " + accessToken);

        HttpResponse getResponse = httpClient.execute(getRequest);
        assertEquals(200, getResponse.getStatusLine().getStatusCode());

        String getResponseBody = EntityUtils.toString(getResponse.getEntity());
        Content content = objectMapper.readValue(getResponseBody, Content.class);
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
        HttpPut putRequest = new HttpPut(baseUrl + "/api/v1/" + projectName + "/filter/0");
        putRequest.setHeader("Authorization", "Bearer " + accessToken);
        putRequest.setHeader("Content-Type", "application/json");

        String jsonPutRequest = objectMapper.writeValueAsString(updateUserFilterRQ);
        putRequest.setEntity(new StringEntity(jsonPutRequest));

        HttpResponse putResponse = httpClient.execute(putRequest);
        assertEquals(404, putResponse.getStatusLine().getStatusCode());

        httpClient.close();
    }

    @Test
    public void testCreateAndDeleteFilter() throws Exception {
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
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost postRequest = new HttpPost(baseUrl + "/api/v1/" + projectName + "/filter");
        postRequest.setHeader("Authorization", "Bearer " + accessToken);
        postRequest.setHeader("Content-Type", "application/json");

        String jsonPostRequest = objectMapper.writeValueAsString(createFilterRQ);
        postRequest.setEntity(new StringEntity(jsonPostRequest));

        HttpResponse postResponse = httpClient.execute(postRequest);
        assertEquals(201, postResponse.getStatusLine().getStatusCode());

        String postResponseBody = EntityUtils.toString(postResponse.getEntity());
        EntryCreatedRS createFilterResponse = objectMapper.readValue(postResponseBody, EntryCreatedRS.class);
        Assertions.assertNotNull(createFilterResponse);
        Assertions.assertNotNull(createFilterResponse.getId());

        // Perform DELETE operation
        int filterId = createFilterResponse.getId();
        HttpDelete deleteRequest = new HttpDelete(baseUrl + "/api/v1/" + projectName + "/filter/" + filterId);
        deleteRequest.setHeader("Authorization", "Bearer " + accessToken);

        HttpResponse deleteResponse = httpClient.execute(deleteRequest);
        assertEquals(200, deleteResponse.getStatusLine().getStatusCode());

        String deleteResponseBody = EntityUtils.toString(deleteResponse.getEntity());
        OperationCompletionRS messageResponse = objectMapper.readValue(deleteResponseBody, OperationCompletionRS.class);
        Assertions.assertNotNull(messageResponse);
        Assertions.assertTrue(messageResponse.getMessage().contains("successfully deleted"));

        httpClient.close();
    }

    @Test
    public void testDeleteFilterNegative() throws Exception {
        String projectName = "superadmin_personal";

        // Perform DELETE operation
        int filterId = RandomUtils.nextInt() + 1000;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete deleteRequest = new HttpDelete(baseUrl + "/api/v1/" + projectName + "/filter/" + filterId);
        deleteRequest.setHeader("Authorization", "Bearer " + accessToken);

        HttpResponse deleteResponse = httpClient.execute(deleteRequest);
        assertEquals(404, deleteResponse.getStatusLine().getStatusCode());

        httpClient.close();
    }
}