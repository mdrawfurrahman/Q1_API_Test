

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class RestfulApiFullCrudTest {

    String baseURI = "https://api.restful-api.dev/objects";
    String createdId = null;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseURI;
    }

    // ========== GET Requests ==========

    @Test(priority = 1)
    public void testGetAllObjects() {
        Response response = given()
                .get();

        assertEquals(response.statusCode(), 200);
        System.out.println("Get All Objects:\n" + response.asPrettyString());
    }

    @Test(priority = 2)
    public void testGetObjectsByIds() {
        Response response = given()
                .queryParam("id", "3")
                .queryParam("id", "5")
                .queryParam("id", "10")
                .get();

        assertEquals(response.statusCode(), 200);
        System.out.println("Get Objects by IDs:\n" + response.asPrettyString());
    }

    @Test(priority = 3)
    public void testGetSingleObject() {
        Response response = given()
                .get("/7");

        assertEquals(response.statusCode(), 200);
        System.out.println("Get Single Object (ID 7):\n" + response.asPrettyString());
    }

    //
//    // ========== POST Request ==========
//
    @Test(priority = 4)
    public void testCreateObject() {
        String requestBody = """
    {
       "name": "Apple MacBook Pro 16",
       "data": {
          "year": 2019,
          "price": 1849.99,
          "CPU model": "Intel Core i9",
          "Hard disk size": "1 TB"
       }
    }""";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post();

        assertEquals(response.getStatusCode(), 200);
        createdId = response.jsonPath().getString("id");
        System.out.println("Created ID: " + createdId);
    }


    //
//    // ========== PUT Request ==========
//
    @Test(priority = 5, dependsOnMethods = "testCreateObject")
    public void testUpdateWithPut() {
        String requestBody = """
    {
       "name": "Apple MacBook Pro 16",
       "data": {
          "year": 2019,
          "price": 2049.99,
          "CPU model": "Intel Core i9",
          "Hard disk size": "1 TB",
          "color": "silver"
       }
    }""";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .put("/" + createdId);

        assertEquals(response.getStatusCode(), 200);
        System.out.println("Updated:\n" + response.asPrettyString());
    }

    //
//    // ========== PATCH Request ==========
//
    @Test(priority = 6, dependsOnMethods = "testUpdateWithPut")
    public void testPatchObject() {
        String requestBody = """
    {
        "name": "Apple MacBook Pro 16 (Updated Name)"
    }""";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .patch("/" + createdId);

        assertEquals(response.statusCode(), 200);
        System.out.println("Patched:\n" + response.asPrettyString());
    }

    //
//    // ========== DELETE Request ==========
//
    @Test(priority = 7, dependsOnMethods = "testPatchObject")
    public void testDeleteObject() {
        Response response = given()
                .delete("/" + createdId);

        assertEquals(response.statusCode(), 200);
        System.out.println("Deleted ID: " + createdId);
    }


}
