package com.pelican.battle.server.config;

import com.google.gson.annotations.SerializedName;
import lombok.ToString;

/**
 * Created by Nightingale on 19.03.14.
 */
@ToString
public class Configuration {
    public Server server;

    @ToString
    public static class Server {
        public final int port;
        public final String version;

        public Server(int port, String version) {
            this.port = port;
            this.version = version;
        }
    }

    @ToString
    public static class DataBase {
        public final String host;
        public final String user;
        public final String pass;

        public DataBase(String host, String user, String pass) {
            this.host = host;
            this.user = user;
            this.pass = pass;
        }
    }
}




















































