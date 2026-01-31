package com.tests.product;

import com.framework.base.BaseTest;
import com.framework.endpoints.Routes;
import com.framework.utils.PayloadGenerator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class ProductTests extends BaseTest {

    @Test
    public void validateProducts() {

        Response response = given().get(Routes.PRODUCTS);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void createProduct() throws Exception {

        Map<String,Object> updates = new HashMap<>();
        updates.put("title","Test Phone");
        updates.put("price",999);
        updates.put("category","smartphones");

        String payload = PayloadGenerator.generatePayload("src/test/resources/product.json",updates);

        Response response = given()
                .contentType("application/json")
                .body(payload)
                .post(Routes.ADD_PRODUCT);

        Assert.assertEquals(response.getStatusCode(),201);
    }
}
