package com.viii.battle.utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GsonUtils {

    public static final Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(SlotPrototype.class, new SlotAdapter());
        gson = gsonBuilder.create();
    }

}
