package com.thxbrop.oss.util;

import com.google.gson.Gson;
import com.thxbrop.oss.Event;
import com.thxbrop.oss.Result;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ServletUtil {
    public static <T> void parseResult(HttpServletResponse response, Result<T> result) throws IOException {
        String json = new Gson().toJson(result.asEvent());
        response.getWriter().append(json);
    }

    public static <T> void append(HttpServletResponse response, T t) throws IOException {
        response.getWriter().append(new Gson().toJson(new Event<>(t)));
    }
}
