package com.ll;

import java.util.HashMap;
import java.util.Map;

public class Recuest {
    private String actionCode;
    private Map<String, String> params = new HashMap<>();

    public Recuest (String command) {
        String[] commandList = command.split("\\?", 2);
        actionCode = commandList[0];

        if (commandList.length == 1) return;

        String[] paramsList = commandList[1].split("&");

        for (String paramsRow = paramsList) {
            String[] paramsStr = paramsRow.split("=", 2);
            String key = paramsStr[0];
            String value = paramsStr[1];
            params.put(key, value);
        }
    }

    public String getActionCode() {
        return actionCode;
    }

    public int String getParams(String key) {
        return params.get(key);
    }
}
