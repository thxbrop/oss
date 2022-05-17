package com.thxbrop.oss.util;

import com.google.gson.Gson;
import com.thxbrop.oss.Result;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ServletUtil {
    public static <T> void parseResult(HttpServletResponse response, Result<T> result) throws IOException {
        if (StringUtil.isNotNull(result.message)) {
            response.sendError(1, result.message);
        }
        if (result.value != null) {
            String json = new Gson().toJson(result.value);
            response.getWriter().append(json);
        }
    }
}
