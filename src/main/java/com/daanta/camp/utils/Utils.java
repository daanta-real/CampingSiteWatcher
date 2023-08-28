package com.daanta.camp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.NonNull;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

public class Utils {

    public static Gson gson = new Gson();
    public static OkHttpClient okHttp = new OkHttpClient();
    public static String getPrettyJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public static Map<String, String> castToMapOfString(Object obj) {
        if (obj instanceof Map) {
            Type type = ((ParameterizedType) obj.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            if (type.equals(String.class)) {
                return (Map<String, String>) obj;
            }
        }
        return Collections.emptyMap();
    }

}
