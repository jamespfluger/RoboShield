package com.jamespfluger.roboshield.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jamespfluger.roboshield.lists.Item;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonConverter {
    private static final Type listType = new TypeToken<ArrayList<Item>>() {
    }.getType();
    private static final Gson gson = new Gson();

    public static String fromObject(Object object) {
        return gson.toJson(object);
    }

    public static <T> String fromList(List<T> object) {
        return gson.toJson(object);
    }

    public static Object toObject(String jsonString, Type type) {
        return gson.fromJson(jsonString, type);
    }

    public static <T> List<T> toList(String jsonString) {
        return gson.fromJson(jsonString, listType);
    }
}
