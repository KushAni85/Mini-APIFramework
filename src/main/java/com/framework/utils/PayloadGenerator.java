package com.framework.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class PayloadGenerator {

    private static final Gson gson = new Gson();

    public static String generatePayload(String jsonFilePath, Map<String, Object> updates) throws Exception {

        String jsonTemplate = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        Type mapType = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> payloadMap = gson.fromJson(jsonTemplate, mapType);

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            payloadMap.put(entry.getKey(), entry.getValue());
        }

        return gson.toJson(payloadMap);
    }
}
