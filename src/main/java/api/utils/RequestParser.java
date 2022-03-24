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
        JSONObject o =new JSONObject(body);
        return o.get(param).toString();
    }
}
