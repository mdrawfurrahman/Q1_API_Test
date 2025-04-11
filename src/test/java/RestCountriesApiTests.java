package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.testng.Assert.*;

public class RestCountriesApiTests {


    @Test
    public void AllCountries() {
        Response response = get("https://restcountries.com/v3.1/all");

        System.out.println("Response String: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.getBody().asString().contains("Bangladesh"));

    }


    @Test
    public void CountryByName() {
        Response response = get("https://restcountries.com/v3.1/name/bangladesh");

        System.out.println("Response String: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.getBody().asString().contains("Bangladesh"));

    }


    @Test
    public void CountryByCurrency() {
        Response response = get("https://restcountries.com/v3.1/currency/bdt");

        System.out.println("Response String: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.getBody().asString().contains("Bangladesh"));

    }


    @Test
    public void InvalidCountry() {
        Response response = get("https://restcountries.com/v3.1/name/unknownland");

        System.out.println("Response String: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 404);

    }


    @Test
    public void CountryByCapital() {
        Response response = get("https://restcountries.com/v3.1/capital/dhaka");

        System.out.println("Response String: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());

        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.getBody().asString().contains("Dhaka"));

    }


    @Test
    public void CountryByRegion() {
        Response response = get("https://restcountries.com/v3.1/region/asia");
        System.out.println("Response String: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.getBody().asString().contains("Asia"));
    }


    @Test
    public void CountryByLanguage() {
        Response response = get("https://restcountries.com/v3.1/lang/bengali");
        System.out.println("Response String: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.getBody().asString().contains("Bengali"));

    }


    @Test
    public void CountryByAlphaCode() {
        Response response = get("https://restcountries.com/v3.1/alpha/bd");
        System.out.println("Response String: " + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.getBody().asString().contains("Bangladesh"));

    }

}
