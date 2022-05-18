package com.thxbrop.oss.util;

import com.google.gson.reflect.TypeToken;

public class GsonUtil {
    public static <T> TypeToken<T> getTypeToken() {
        return new TypeToken<T>() {
        };
    }
}
