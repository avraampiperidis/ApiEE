
package com.protectsoft.apiee.core;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;

/**
 *
 */
public class JsonUtils {
    
    public static JsonObject getJsonObject(String json) {
        return getJsonReader(json).readObject();
    }
    
    public static JsonArray getJsonArray(String json) {
        return getJsonReader(json).readArray();
    }
    
    public static JsonStructure getJsonStructure(String json) {
        return getJsonReader(json).read();
    }
    
    public static boolean isJsonArray(String json) {
        return getJsonReader(json).read().getValueType().equals(JsonValue.ValueType.ARRAY);
    }
    
    
    public static JsonObjectBuilder jsonObjectBuilder(JsonObject json) {
          JsonObjectBuilder job = Json.createObjectBuilder();
          json.entrySet().forEach((entry) -> {
              job.add(entry.getKey(), entry.getValue());
          });
          return job;
     }
    
    
    
    private static JsonReader getJsonReader(String json) {
        return Json.createReader(new StringReader(json));
    }
    
}
