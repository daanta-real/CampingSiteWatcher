package com.daanta.camp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.NonNull;
import okhttp3.OkHttpClient;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static String keyValMapToString(Map<String, String> map) {
        return map.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }

}
