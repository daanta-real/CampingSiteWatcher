package com.daanta.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;

public class Utils {

    public static Gson gson = new Gson();
    public static OkHttpClient okHttp = new OkHttpClient();
    public static String getPrettyJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

}
