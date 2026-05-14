package com.linxs.automate.utils;

import com.linxs.automate.models.AbstractCommunicatorResult;
import tools.jackson.databind.ObjectMapper;


public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T extends AbstractCommunicatorResult> String toJson(T result) {
        return mapper.writeValueAsString(result);
    }
}
