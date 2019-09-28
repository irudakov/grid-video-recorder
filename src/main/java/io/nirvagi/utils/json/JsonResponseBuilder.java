package io.nirvagi.utils.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.List;
import java.util.Map;

public class JsonResponseBuilder {

    public static final String ERROR_RECEIVED_DURING_EXECUTION_OF_COMMAND = "Error received during execution of command";
    public static final String EXIT_CODE_FOR_OPERATION = "Exit code for operation";
    public static final String ALL_OF_THE_STANDARD_OUT_RECEIVED_FROM_THE_SYSTEM = "All of the StandardOut received from the system";
    private JsonObject keyDescriptions;
    private JsonObject keyValues;

    public JsonResponseBuilder() {
        keyDescriptions = new JsonObject();
        keyDescriptions.addProperty(JsonCodec.ERROR, ERROR_RECEIVED_DURING_EXECUTION_OF_COMMAND);
        keyDescriptions.addProperty(JsonCodec.EXIT_CODE, EXIT_CODE_FOR_OPERATION);
        keyDescriptions.addProperty(JsonCodec.OUT, ALL_OF_THE_STANDARD_OUT_RECEIVED_FROM_THE_SYSTEM);
        clearValues();
    }

    private void clearValues() {

        keyValues = new JsonObject();

        JsonArray out = new JsonArray();
        JsonArray error = new JsonArray();

        addKeyValues(JsonCodec.EXIT_CODE, 0);
        addKeyValues(JsonCodec.OUT, out);
        addKeyValues(JsonCodec.ERROR, error);
    }

    public void addKeyDescriptions(String key, String description) {
        keyDescriptions.addProperty(key, description);
        addKeyValues(key, "");
    }

    public void addKeyValues(String key, String value) {
        addKeyValues(key, value, true);
    }

    public void addKeyValues(String key, String value, Boolean splitLineToArray) {
        checkIfKeyDescriptionExist(key);
        JsonArray valueArray = new JsonArray();
        if (splitLineToArray) {
            valueArray = convertLineToArray(value);
        } else {
            valueArray.add(new JsonPrimitive(value));
        }

        if (key.equals(JsonCodec.OUT)) {
            addKeyValues(key, valueArray);
        } else if (key.equals(JsonCodec.ERROR)) {
            addKeyValues(JsonCodec.EXIT_CODE, 1);
            addKeyValues(key, valueArray);
        } else {
            keyValues.add(key, valueArray);
        }

    }

    public void addKeyValues(String key, Boolean value) {
        checkIfKeyDescriptionExist(key);
        keyValues.addProperty(key, value);
    }

    public void addKeyValues(String key, JsonArray value) {
        checkIfKeyDescriptionExist(key);
        keyValues.add(key, value);
    }

    public void addKeyValues(String key, JsonObject value) {
        checkIfKeyDescriptionExist(key);
        keyValues.add(key, value);
    }

    public void addKeyValues(String key, Map<String, String> value) {
        checkIfKeyDescriptionExist(key);

        keyValues.add(key, hashMapToJsonObject(value));
    }

    public void addNestedMapValues(String key, Map<String, List> value) {
        checkIfKeyDescriptionExist(key);
        keyValues.add(key, JsonParserWrapper.toJsonObject(value));
    }

    public void addKeyValues(String key, int value) {
        checkIfKeyDescriptionExist(key);
        keyValues.addProperty(key, value);
    }

    @Deprecated
    private JsonObject hashMapToJsonObject(Map<String, String> hashMap) {
        //TODO replace this with JsonParserWrapper.toJsonObject(); but need to test how the api will change completely
        //That is some Arrays will become strings etc..
        JsonObject hashToJsonObjectConverter = new JsonObject();
        for (String hashKey : hashMap.keySet()) {
            hashToJsonObjectConverter.addProperty(hashKey, hashMap.get(hashKey));
        }

        return hashToJsonObjectConverter;
    }

    public void addListOfHashes(String key, List<Map<String, String>> value) {
        checkIfKeyDescriptionExist(key);

        JsonArray valueArray = new JsonArray();
        for (Map<String, String> hashMap : value) {
            valueArray.add(hashMapToJsonObject(hashMap));
        }
        keyValues.add(key, valueArray);
    }

    public void addKeyValues(String key, List<String> value) {
        checkIfKeyDescriptionExist(key);

        JsonArray valueArray = new JsonArray();
        for (String item : value) {
            valueArray.add(new JsonPrimitive(item));
        }
        keyValues.add(key, valueArray);
    }

    public String toString() {
        String values = keyValues.toString();
        clearValues();
        return values;
    }

    public JsonObject getJson() {
        JsonObject values = keyValues;
        clearValues();
        return values;
    }

    public JsonObject getKeyDescriptions() {
        return keyDescriptions;
    }

    private void checkIfKeyDescriptionExist(String key) {
        if (!keyDescriptions.has(key)) {
            throw new RuntimeException(
                    "You cannot add an entry to Json Response without adding description for it first");
        }
    }

    private JsonArray convertLineToArray(String input) {
        JsonArray output = new JsonArray();

        String stdOutLines[] = input.split("\n");
        for (String line : stdOutLines) {
            output.add(new JsonPrimitive(line));
        }
        return output;
    }


}
