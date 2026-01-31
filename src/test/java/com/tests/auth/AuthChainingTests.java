package com.tests.auth;

import com.framework.base.BaseTest;
import com.framework.endpoints.Routes;
import com.framework.utils.PayloadGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class AuthChainingTests extends BaseTest {

    static String token;

    @Test
    public void login() throws Exception {

        Map<String,Object> updates = new HashMap<>();
        updates.put("username","emilys");
        updates.put("password","emilyspass");

        String payload = PayloadGenerator.generatePayload("src/test/resources/login.json",updates);

        Response response = given()
                .contentType("application/json")
                .body(payload)
                .post(Routes.LOGIN);

        Assert.assertEquals(response.getStatusCode(),200);
        token = response.jsonPath().getString("accessToken");
    }

    @Test(dependsOnMethods="login")
    public void getProfile(){

        Response response = given()
                .header("Authorization","Bearer " + token)
                .get(Routes.AUTH_ME);

        Assert.assertEquals(response.getStatusCode(),200);
    }
}
