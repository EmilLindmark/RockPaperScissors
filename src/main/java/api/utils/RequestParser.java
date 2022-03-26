package api.utils;

import org.json.JSONObject;

public class RequestParser {
    public static String getPlayerName(String body) {
        return getValue("name", body);
    }

    public static String getMove(String body){
        return getValue("move", body);
    }

    private static String getValue(String param, String body){
        JSONObject o = new JSONObject(body);
        String value = o.get(param).toString();

        if(value.isEmpty()){
            throw new IllegalArgumentException(
                    "Value for needed parameter '" + param + "' is missing in request body.");
        }

        return value;
    }
}
