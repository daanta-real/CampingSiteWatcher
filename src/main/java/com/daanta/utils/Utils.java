package com.daanta.utils;

import com.google.gson.GsonBuilder;

public class Utils {

    public static String getPrettyJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

}
