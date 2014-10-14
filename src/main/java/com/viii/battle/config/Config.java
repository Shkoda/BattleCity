package com.viii.battle.config;

import com.viii.battle.utils.DiskUtils;
import com.viii.battle.utils.gson.GsonUtils;

/**
 * Created by Artem
 * Date: 3/9/14 3:35 PM.
 */
public final class Config {

    public static final long SESSION_MILLIS = 15 * 60_000 * 12 * 24;
    public static final String GENERAL_CONFIG_PATH = "conf/server.conf";
    public static final String CONF_PATH = "conf/";

    public static boolean DEBUG_MODE = true;
    public static Configuration.Server server;

    @SuppressWarnings("unchecked")
    public static void load() throws Exception {
        Configuration configuration = GsonUtils.gson.fromJson(DiskUtils.readString(GENERAL_CONFIG_PATH), Configuration.class);
        server = configuration.server;
    }
}
