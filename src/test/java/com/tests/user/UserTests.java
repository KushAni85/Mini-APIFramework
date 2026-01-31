package com.tests.user;

import com.framework.base.BaseTest;
import com.framework.endpoints.Routes;
import com.framework.utils.PayloadGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UserTests extends BaseTest {

    static int userId;

    @Test
    public void createUser() throws Exception {

        Map<String,Object> updates = new HashMap<>();
        updates.put("firstName","Animesh");
        updates.put("lastName","Choudhary");

        String payload = PayloadGenerator.generatePayload("src/test/resources/request.json",updates);

        Response response = given()
                .contentType("application/json")
                .body(payload)
                .post(Routes.ADD_USER);

        Assert.assertEquals(response.getStatusCode(),201);
        userId = response.jsonPath().getInt("id");
    }

    @Test(dependsOnMethods="createUser")
    public void getUser(){

        Response response = given().get(Routes.USERS + "/" + userId);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
