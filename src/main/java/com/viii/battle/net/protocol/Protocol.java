package com.viii.battle.net.protocol;

import ortobuf.core.CodedInputStream;
import ortobuf.core.CodedOutputStream;
import ortobuf.core.InvalidProtocolBufferException;
import ortobuf.core.ProtoEnum;
import ortobuf.core.AbstractMessageBuilder;

import java.io.IOException;

public final class Protocol {

    public static final class SPing {

        private Builder builder;
        public String greeting;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static SPing parse(byte[] data) throws IOException {
            return SPing.parse(new CodedInputStream(data));
        }

        public static SPing parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            SPing instance = new SPing();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 10:
                        bitMask |= 0x1;
                        instance.greeting = is.readString();
                        break;
                }
            }
            if ((bitMask & 0x0) != 0x0) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (greeting != null) {
                builder.setGreeting(greeting);
            }
            return builder;
        }

        @Override
        public String toString() {
            return "SPing{" +
                    "greeting = '" + greeting + '\'' +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setGreeting(String greeting) {
                bitMask |= 1;
                os.writeInt32NoTag(10);
                os.writeString(greeting);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class CPing {

        private Builder builder;
        public String info;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static CPing parse(byte[] data) throws IOException {
            return CPing.parse(new CodedInputStream(data));
        }

        public static CPing parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            CPing instance = new CPing();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 10:
                        bitMask |= 0x1;
                        instance.info = is.readString();
                        break;
                }
            }
            if ((bitMask & 0x0) != 0x0) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (info != null) {
                builder.setInfo(info);
            }
            return builder;
        }

        @Override
        public String toString() {
            return "CPing{" +
                    "info = '" + info + '\'' +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setInfo(String info) {
                bitMask |= 1;
                os.writeInt32NoTag(10);
                os.writeString(info);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class SNewPlayer {

        private Builder builder;
        public String name;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static SNewPlayer parse(byte[] data) throws IOException {
            return SNewPlayer.parse(new CodedInputStream(data));
        }

        public static SNewPlayer parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            SNewPlayer instance = new SNewPlayer();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 10:
                        bitMask |= 0x1;
                        instance.name = is.readString();
                        break;
                }
            }
            if ((bitMask & 0x1) != 0x1) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (name != null) {
                builder.setName(name);
            }
            return builder;
        }

        @Override
        public String toString() {
            return "SNewPlayer{" +
                    "name = '" + name + '\'' +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setName(String name) {
                bitMask |= 1;
                os.writeInt32NoTag(10);
                os.writeString(name);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class CNewPlayer {

        private Builder builder;
        public PlayerInfo playerInfo;
        public java.util.List<PlayerInfo> onlinePlayers;
        public java.util.List<RoomInfo> rooms;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static CNewPlayer parse(byte[] data) throws IOException {
            return CNewPlayer.parse(new CodedInputStream(data));
        }

        public static CNewPlayer parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            CNewPlayer instance = new CNewPlayer();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 10:
                        bitMask |= 0x1;
                        int l = is.limit;
                        is.readLimit();
                        instance.playerInfo = PlayerInfo.parse(is);
                        is.limit = l;
                        break;
                    case 18:
                        bitMask |= 0x2;
                        if (instance.onlinePlayers == null) instance.onlinePlayers = new java.util.ArrayList<>();
                        l = is.limit;
                        is.readLimit();
                        instance.onlinePlayers.add(PlayerInfo.parse(is));
                        is.limit = l;
                        break;
                    case 26:
                        bitMask |= 0x4;
                        if (instance.rooms == null) instance.rooms = new java.util.ArrayList<>();
                        l = is.limit;
                        is.readLimit();
                        instance.rooms.add(RoomInfo.parse(is));
                        is.limit = l;
                        break;
                }
            }
            if ((bitMask & 0x1) != 0x1) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            result.onlinePlayersCount = 0;
            result.roomsCount = 0;
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (playerInfo != null) {
                builder.setPlayerInfo(playerInfo.serialize(false));
            }
            if (onlinePlayers != null) {
                for (int i = 0, size = onlinePlayers.size(); i < size; i++) {
                    builder.addOnlinePlayers(onlinePlayers.get(i).serialize(false));
                }
            }
            if (rooms != null) {
                for (int i = 0, size = rooms.size(); i < size; i++) {
                    builder.addRooms(rooms.get(i).serialize(false));
                }
            }
            return builder;
        }

        @Override
        public String toString() {
            return "CNewPlayer{" +
                    "playerInfo = " + playerInfo +
                    ", onlinePlayers = " + onlinePlayers +
                    ", rooms = " + rooms +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public int onlinePlayersCount = 0;
            public int roomsCount = 0;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setPlayerInfo(PlayerInfo.Builder playerInfo) {
                bitMask |= 1;
                os.writeInt32NoTag(10);
                os.writeInt32NoTag(playerInfo.getOutputStream().pointer);
                os.merge(playerInfo.getOutputStream());
                return this;
            }

            public Builder addOnlinePlayers(PlayerInfo.Builder onlinePlayers) {
                bitMask |= 2;
                os.writeInt32NoTag(18);
                os.writeInt32NoTag(onlinePlayers.getOutputStream().pointer);
                os.merge(onlinePlayers.getOutputStream());
                onlinePlayersCount++;
                return this;
            }

            public Builder addAllOnlinePlayers(java.util.List<PlayerInfo.Builder> list) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    addOnlinePlayers(list.get(i));
                }
                return this;
            }

            public Builder addRooms(RoomInfo.Builder rooms) {
                bitMask |= 4;
                os.writeInt32NoTag(26);
                os.writeInt32NoTag(rooms.getOutputStream().pointer);
                os.merge(rooms.getOutputStream());
                roomsCount++;
                return this;
            }

            public Builder addAllRooms(java.util.List<RoomInfo.Builder> list) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    addRooms(list.get(i));
                }
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class SGetPlayers {

        private Builder builder;
        public boolean nothing;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static SGetPlayers parse(byte[] data) throws IOException {
            return SGetPlayers.parse(new CodedInputStream(data));
        }

        public static SGetPlayers parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            SGetPlayers instance = new SGetPlayers();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.nothing = is.readBoolean();
                        break;
                }
            }
            if ((bitMask & 0x0) != 0x0) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            builder.setNothing(nothing);
            return builder;
        }

        @Override
        public String toString() {
            return "SGetPlayers{" +
                    "nothing = " + nothing +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setNothing(boolean nothing) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeBoolean(nothing);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class CGetPlayers {

        private Builder builder;
        public java.util.List<PlayerInfo> players;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static CGetPlayers parse(byte[] data) throws IOException {
            return CGetPlayers.parse(new CodedInputStream(data));
        }

        public static CGetPlayers parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            CGetPlayers instance = new CGetPlayers();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 10:
                        bitMask |= 0x1;
                        if (instance.players == null) instance.players = new java.util.ArrayList<>();
                        int l = is.limit;
                        is.readLimit();
                        instance.players.add(PlayerInfo.parse(is));
                        is.limit = l;
                        break;
                }
            }
            if ((bitMask & 0x0) != 0x0) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            result.playersCount = 0;
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (players != null) {
                for (int i = 0, size = players.size(); i < size; i++) {
                    builder.addPlayers(players.get(i).serialize(false));
                }
            }
            return builder;
        }

        @Override
        public String toString() {
            return "CGetPlayers{" +
                    "players = " + players +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public int playersCount = 0;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder addPlayers(PlayerInfo.Builder players) {
                bitMask |= 1;
                os.writeInt32NoTag(10);
                os.writeInt32NoTag(players.getOutputStream().pointer);
                os.merge(players.getOutputStream());
                playersCount++;
                return this;
            }

            public Builder addAllPlayers(java.util.List<PlayerInfo.Builder> list) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    addPlayers(list.get(i));
                }
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class PlayerInfo {

        private Builder builder;
        public int id;
        public String name;
        public PlayerState state;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static PlayerInfo parse(byte[] data) throws IOException {
            return PlayerInfo.parse(new CodedInputStream(data));
        }

        public static PlayerInfo parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            PlayerInfo instance = new PlayerInfo();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.id = is.readRawVarint32();
                        break;
                    case 18:
                        bitMask |= 0x2;
                        instance.name = is.readString();
                        break;
                    case 24:
                        bitMask |= 0x4;
                        instance.state = PlayerState.valueOf(is.readRawVarint32());
                        break;
                }
            }
            if ((bitMask & 0x3) != 0x3) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (id != 0) {
                builder.setId(id);
            }
            if (name != null) {
                builder.setName(name);
            }
            if (state != null) {
                builder.setState(state);
            }
            return builder;
        }

        @Override
        public String toString() {
            return "PlayerInfo{" +
                    "id = " + id +
                    ", name = '" + name + '\'' +
                    ", state = " + state +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setId(int id) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeInt32NoTag(id);
                return this;
            }

            public Builder setName(String name) {
                bitMask |= 2;
                os.writeInt32NoTag(18);
                os.writeString(name);
                return this;
            }

            public Builder setState(PlayerState state) {
                bitMask |= 4;
                os.writeInt32NoTag(24);
                os.writeInt32NoTag(state.getNumber());
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class SGetRooms {

        private Builder builder;
        public boolean nothing;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static SGetRooms parse(byte[] data) throws IOException {
            return SGetRooms.parse(new CodedInputStream(data));
        }

        public static SGetRooms parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            SGetRooms instance = new SGetRooms();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.nothing = is.readBoolean();
                        break;
                }
            }
            if ((bitMask & 0x0) != 0x0) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            builder.setNothing(nothing);
            return builder;
        }

        @Override
        public String toString() {
            return "SGetRooms{" +
                    "nothing = " + nothing +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setNothing(boolean nothing) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeBoolean(nothing);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class CGetRooms {

        private Builder builder;
        public java.util.List<RoomInfo> rooms;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static CGetRooms parse(byte[] data) throws IOException {
            return CGetRooms.parse(new CodedInputStream(data));
        }

        public static CGetRooms parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            CGetRooms instance = new CGetRooms();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 10:
                        bitMask |= 0x1;
                        if (instance.rooms == null) instance.rooms = new java.util.ArrayList<>();
                        int l = is.limit;
                        is.readLimit();
                        instance.rooms.add(RoomInfo.parse(is));
                        is.limit = l;
                        break;
                }
            }
            if ((bitMask & 0x0) != 0x0) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            result.roomsCount = 0;
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (rooms != null) {
                for (int i = 0, size = rooms.size(); i < size; i++) {
                    builder.addRooms(rooms.get(i).serialize(false));
                }
            }
            return builder;
        }

        @Override
        public String toString() {
            return "CGetRooms{" +
                    "rooms = " + rooms +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public int roomsCount = 0;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder addRooms(RoomInfo.Builder rooms) {
                bitMask |= 1;
                os.writeInt32NoTag(10);
                os.writeInt32NoTag(rooms.getOutputStream().pointer);
                os.merge(rooms.getOutputStream());
                roomsCount++;
                return this;
            }

            public Builder addAllRooms(java.util.List<RoomInfo.Builder> list) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    addRooms(list.get(i));
                }
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class RoomInfo {

        private Builder builder;
        public int id;
        public int capacity;
        public java.util.List<PlayerInfo> players;
        public RoomState state;
        public long startTimeUTC;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static RoomInfo parse(byte[] data) throws IOException {
            return RoomInfo.parse(new CodedInputStream(data));
        }

        public static RoomInfo parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            RoomInfo instance = new RoomInfo();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.id = is.readRawVarint32();
                        break;
                    case 16:
                        bitMask |= 0x2;
                        instance.capacity = is.readRawVarint32();
                        break;
                    case 26:
                        bitMask |= 0x4;
                        if (instance.players == null) instance.players = new java.util.ArrayList<>();
                        int l = is.limit;
                        is.readLimit();
                        instance.players.add(PlayerInfo.parse(is));
                        is.limit = l;
                        break;
                    case 32:
                        bitMask |= 0x8;
                        instance.state = RoomState.valueOf(is.readRawVarint32());
                        break;
                    case 40:
                        bitMask |= 0x10;
                        instance.startTimeUTC = is.readRawVarint64();
                        break;
                }
            }
            if ((bitMask & 0xb) != 0xb) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            result.playersCount = 0;
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (id != 0) {
                builder.setId(id);
            }
            if (capacity != 0) {
                builder.setCapacity(capacity);
            }
            if (players != null) {
                for (int i = 0, size = players.size(); i < size; i++) {
                    builder.addPlayers(players.get(i).serialize(false));
                }
            }
            if (state != null) {
                builder.setState(state);
            }
            if (startTimeUTC != 0) {
                builder.setStartTimeUTC(startTimeUTC);
            }
            return builder;
        }

        @Override
        public String toString() {
            return "RoomInfo{" +
                    "id = " + id +
                    ", capacity = " + capacity +
                    ", players = " + players +
                    ", state = " + state +
                    ", startTimeUTC = " + startTimeUTC +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public int playersCount = 0;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setId(int id) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeInt32NoTag(id);
                return this;
            }

            public Builder setCapacity(int capacity) {
                bitMask |= 2;
                os.writeInt32NoTag(16);
                os.writeInt32NoTag(capacity);
                return this;
            }

            public Builder addPlayers(PlayerInfo.Builder players) {
                bitMask |= 4;
                os.writeInt32NoTag(26);
                os.writeInt32NoTag(players.getOutputStream().pointer);
                os.merge(players.getOutputStream());
                playersCount++;
                return this;
            }

            public Builder addAllPlayers(java.util.List<PlayerInfo.Builder> list) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    addPlayers(list.get(i));
                }
                return this;
            }

            public Builder setState(RoomState state) {
                bitMask |= 8;
                os.writeInt32NoTag(32);
                os.writeInt32NoTag(state.getNumber());
                return this;
            }

            public Builder setStartTimeUTC(long startTimeUTC) {
                bitMask |= 16;
                os.writeInt32NoTag(40);
                os.writeRawVarint64(startTimeUTC);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class SCreateRoom {

        private Builder builder;
        public int capacity;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static SCreateRoom parse(byte[] data) throws IOException {
            return SCreateRoom.parse(new CodedInputStream(data));
        }

        public static SCreateRoom parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            SCreateRoom instance = new SCreateRoom();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.capacity = is.readRawVarint32();
                        break;
                }
            }
            if ((bitMask & 0x1) != 0x1) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (capacity != 0) {
                builder.setCapacity(capacity);
            }
            return builder;
        }

        @Override
        public String toString() {
            return "SCreateRoom{" +
                    "capacity = " + capacity +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setCapacity(int capacity) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeInt32NoTag(capacity);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class CCreateRoom {

        private Builder builder;
        public boolean successful;
        public RoomInfo room;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static CCreateRoom parse(byte[] data) throws IOException {
            return CCreateRoom.parse(new CodedInputStream(data));
        }

        public static CCreateRoom parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            CCreateRoom instance = new CCreateRoom();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.successful = is.readBoolean();
                        break;
                    case 18:
                        bitMask |= 0x2;
                        int l = is.limit;
                        is.readLimit();
                        instance.room = RoomInfo.parse(is);
                        is.limit = l;
                        break;
                }
            }
            if ((bitMask & 0x1) != 0x1) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            builder.setSuccessful(successful);
            if (room != null) {
                builder.setRoom(room.serialize(false));
            }
            return builder;
        }

        @Override
        public String toString() {
            return "CCreateRoom{" +
                    "successful = " + successful +
                    ", room = " + room +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setSuccessful(boolean successful) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeBoolean(successful);
                return this;
            }

            public Builder setRoom(RoomInfo.Builder room) {
                bitMask |= 2;
                os.writeInt32NoTag(18);
                os.writeInt32NoTag(room.getOutputStream().pointer);
                os.merge(room.getOutputStream());
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class SJoinRoom {

        private Builder builder;
        public int roomId;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static SJoinRoom parse(byte[] data) throws IOException {
            return SJoinRoom.parse(new CodedInputStream(data));
        }

        public static SJoinRoom parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            SJoinRoom instance = new SJoinRoom();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.roomId = is.readRawVarint32();
                        break;
                }
            }
            if ((bitMask & 0x1) != 0x1) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (roomId != 0) {
                builder.setRoomId(roomId);
            }
            return builder;
        }

        @Override
        public String toString() {
            return "SJoinRoom{" +
                    "roomId = " + roomId +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setRoomId(int roomId) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeInt32NoTag(roomId);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class CJoinRoom {

        private Builder builder;
        public boolean successful;
        public RoomInfo room;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static CJoinRoom parse(byte[] data) throws IOException {
            return CJoinRoom.parse(new CodedInputStream(data));
        }

        public static CJoinRoom parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            CJoinRoom instance = new CJoinRoom();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.successful = is.readBoolean();
                        break;
                    case 18:
                        bitMask |= 0x2;
                        int l = is.limit;
                        is.readLimit();
                        instance.room = RoomInfo.parse(is);
                        is.limit = l;
                        break;
                }
            }
            if ((bitMask & 0x1) != 0x1) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            builder.setSuccessful(successful);
            if (room != null) {
                builder.setRoom(room.serialize(false));
            }
            return builder;
        }

        @Override
        public String toString() {
            return "CJoinRoom{" +
                    "successful = " + successful +
                    ", room = " + room +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setSuccessful(boolean successful) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeBoolean(successful);
                return this;
            }

            public Builder setRoom(RoomInfo.Builder room) {
                bitMask |= 2;
                os.writeInt32NoTag(18);
                os.writeInt32NoTag(room.getOutputStream().pointer);
                os.merge(room.getOutputStream());
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class SLeaveRoom {

        private Builder builder;
        public int roomId;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static SLeaveRoom parse(byte[] data) throws IOException {
            return SLeaveRoom.parse(new CodedInputStream(data));
        }

        public static SLeaveRoom parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            SLeaveRoom instance = new SLeaveRoom();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.roomId = is.readRawVarint32();
                        break;
                }
            }
            if ((bitMask & 0x1) != 0x1) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (roomId != 0) {
                builder.setRoomId(roomId);
            }
            return builder;
        }

        @Override
        public String toString() {
            return "SLeaveRoom{" +
                    "roomId = " + roomId +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setRoomId(int roomId) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeInt32NoTag(roomId);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class CLeaveRoom {

        private Builder builder;
        public boolean successful;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static CLeaveRoom parse(byte[] data) throws IOException {
            return CLeaveRoom.parse(new CodedInputStream(data));
        }

        public static CLeaveRoom parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            CLeaveRoom instance = new CLeaveRoom();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.successful = is.readBoolean();
                        break;
                }
            }
            if ((bitMask & 0x1) != 0x1) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            builder.setSuccessful(successful);
            return builder;
        }

        @Override
        public String toString() {
            return "CLeaveRoom{" +
                    "successful = " + successful +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setSuccessful(boolean successful) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeBoolean(successful);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class SField {

        private Builder builder;
        public boolean nothing;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static SField parse(byte[] data) throws IOException {
            return SField.parse(new CodedInputStream(data));
        }

        public static SField parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            SField instance = new SField();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.nothing = is.readBoolean();
                        break;
                }
            }
            if ((bitMask & 0x0) != 0x0) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            builder.setNothing(nothing);
            return builder;
        }

        @Override
        public String toString() {
            return "SField{" +
                    "nothing = " + nothing +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setNothing(boolean nothing) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeBoolean(nothing);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class CField {

        private Builder builder;
        public Field field;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static CField parse(byte[] data) throws IOException {
            return CField.parse(new CodedInputStream(data));
        }

        public static CField parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            CField instance = new CField();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 10:
                        bitMask |= 0x1;
                        int l = is.limit;
                        is.readLimit();
                        instance.field = Field.parse(is);
                        is.limit = l;
                        break;
                }
            }
            if ((bitMask & 0x0) != 0x0) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (field != null) {
                builder.setField(field.serialize(false));
            }
            return builder;
        }

        @Override
        public String toString() {
            return "CField{" +
                    "field = " + field +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setField(Field.Builder field) {
                bitMask |= 1;
                os.writeInt32NoTag(10);
                os.writeInt32NoTag(field.getOutputStream().pointer);
                os.merge(field.getOutputStream());
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class Field {

        private Builder builder;
        public java.util.List<Cell> cells;
        public java.util.List<Panzer> panzers;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static Field parse(byte[] data) throws IOException {
            return Field.parse(new CodedInputStream(data));
        }

        public static Field parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            Field instance = new Field();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 10:
                        bitMask |= 0x1;
                        if (instance.cells == null) instance.cells = new java.util.ArrayList<>();
                        int l = is.limit;
                        is.readLimit();
                        instance.cells.add(Cell.parse(is));
                        is.limit = l;
                        break;
                    case 18:
                        bitMask |= 0x2;
                        if (instance.panzers == null) instance.panzers = new java.util.ArrayList<>();
                        l = is.limit;
                        is.readLimit();
                        instance.panzers.add(Panzer.parse(is));
                        is.limit = l;
                        break;
                }
            }
            if ((bitMask & 0x0) != 0x0) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            result.cellsCount = 0;
            result.panzersCount = 0;
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (cells != null) {
                for (int i = 0, size = cells.size(); i < size; i++) {
                    builder.addCells(cells.get(i).serialize(false));
                }
            }
            if (panzers != null) {
                for (int i = 0, size = panzers.size(); i < size; i++) {
                    builder.addPanzers(panzers.get(i).serialize(false));
                }
            }
            return builder;
        }

        @Override
        public String toString() {
            return "Field{" +
                    "cells = " + cells +
                    ", panzers = " + panzers +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public int cellsCount = 0;
            public int panzersCount = 0;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder addCells(Cell.Builder cells) {
                bitMask |= 1;
                os.writeInt32NoTag(10);
                os.writeInt32NoTag(cells.getOutputStream().pointer);
                os.merge(cells.getOutputStream());
                cellsCount++;
                return this;
            }

            public Builder addAllCells(java.util.List<Cell.Builder> list) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    addCells(list.get(i));
                }
                return this;
            }

            public Builder addPanzers(Panzer.Builder panzers) {
                bitMask |= 2;
                os.writeInt32NoTag(18);
                os.writeInt32NoTag(panzers.getOutputStream().pointer);
                os.merge(panzers.getOutputStream());
                panzersCount++;
                return this;
            }

            public Builder addAllPanzers(java.util.List<Panzer.Builder> list) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    addPanzers(list.get(i));
                }
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class Panzer {

        private Builder builder;
        public PlayerInfo owner;
        public Position position;
        public PanzerProperties properties;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static Panzer parse(byte[] data) throws IOException {
            return Panzer.parse(new CodedInputStream(data));
        }

        public static Panzer parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            Panzer instance = new Panzer();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 10:
                        bitMask |= 0x1;
                        int l = is.limit;
                        is.readLimit();
                        instance.owner = PlayerInfo.parse(is);
                        is.limit = l;
                        break;
                    case 18:
                        bitMask |= 0x2;
                        l = is.limit;
                        is.readLimit();
                        instance.position = Position.parse(is);
                        is.limit = l;
                        break;
                    case 26:
                        bitMask |= 0x4;
                        l = is.limit;
                        is.readLimit();
                        instance.properties = PanzerProperties.parse(is);
                        is.limit = l;
                        break;
                }
            }
            if ((bitMask & 0x3) != 0x3) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (owner != null) {
                builder.setOwner(owner.serialize(false));
            }
            if (position != null) {
                builder.setPosition(position.serialize(false));
            }
            if (properties != null) {
                builder.setProperties(properties.serialize(false));
            }
            return builder;
        }

        @Override
        public String toString() {
            return "Panzer{" +
                    "owner = " + owner +
                    ", position = " + position +
                    ", properties = " + properties +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setOwner(PlayerInfo.Builder owner) {
                bitMask |= 1;
                os.writeInt32NoTag(10);
                os.writeInt32NoTag(owner.getOutputStream().pointer);
                os.merge(owner.getOutputStream());
                return this;
            }

            public Builder setPosition(Position.Builder position) {
                bitMask |= 2;
                os.writeInt32NoTag(18);
                os.writeInt32NoTag(position.getOutputStream().pointer);
                os.merge(position.getOutputStream());
                return this;
            }

            public Builder setProperties(PanzerProperties.Builder properties) {
                bitMask |= 4;
                os.writeInt32NoTag(26);
                os.writeInt32NoTag(properties.getOutputStream().pointer);
                os.merge(properties.getOutputStream());
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class PanzerProperties {

        private Builder builder;
        public int speed;
        public int power;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static PanzerProperties parse(byte[] data) throws IOException {
            return PanzerProperties.parse(new CodedInputStream(data));
        }

        public static PanzerProperties parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            PanzerProperties instance = new PanzerProperties();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.speed = is.readRawVarint32();
                        break;
                    case 16:
                        bitMask |= 0x2;
                        instance.power = is.readRawVarint32();
                        break;
                }
            }
            if ((bitMask & 0x3) != 0x3) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (speed != 0) {
                builder.setSpeed(speed);
            }
            if (power != 0) {
                builder.setPower(power);
            }
            return builder;
        }

        @Override
        public String toString() {
            return "PanzerProperties{" +
                    "speed = " + speed +
                    ", power = " + power +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setSpeed(int speed) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeInt32NoTag(speed);
                return this;
            }

            public Builder setPower(int power) {
                bitMask |= 2;
                os.writeInt32NoTag(16);
                os.writeInt32NoTag(power);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class Cell {

        private Builder builder;
        public Position position;
        public Dimension dimension;
        public CellProperties properties;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static Cell parse(byte[] data) throws IOException {
            return Cell.parse(new CodedInputStream(data));
        }

        public static Cell parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            Cell instance = new Cell();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 10:
                        bitMask |= 0x1;
                        int l = is.limit;
                        is.readLimit();
                        instance.position = Position.parse(is);
                        is.limit = l;
                        break;
                    case 18:
                        bitMask |= 0x2;
                        l = is.limit;
                        is.readLimit();
                        instance.dimension = Dimension.parse(is);
                        is.limit = l;
                        break;
                    case 26:
                        bitMask |= 0x4;
                        l = is.limit;
                        is.readLimit();
                        instance.properties = CellProperties.parse(is);
                        is.limit = l;
                        break;
                }
            }
            if ((bitMask & 0x5) != 0x5) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (position != null) {
                builder.setPosition(position.serialize(false));
            }
            if (dimension != null) {
                builder.setDimension(dimension.serialize(false));
            }
            if (properties != null) {
                builder.setProperties(properties.serialize(false));
            }
            return builder;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "position = " + position +
                    ", dimension = " + dimension +
                    ", properties = " + properties +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setPosition(Position.Builder position) {
                bitMask |= 1;
                os.writeInt32NoTag(10);
                os.writeInt32NoTag(position.getOutputStream().pointer);
                os.merge(position.getOutputStream());
                return this;
            }

            public Builder setDimension(Dimension.Builder dimension) {
                bitMask |= 2;
                os.writeInt32NoTag(18);
                os.writeInt32NoTag(dimension.getOutputStream().pointer);
                os.merge(dimension.getOutputStream());
                return this;
            }

            public Builder setProperties(CellProperties.Builder properties) {
                bitMask |= 4;
                os.writeInt32NoTag(26);
                os.writeInt32NoTag(properties.getOutputStream().pointer);
                os.merge(properties.getOutputStream());
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class CellProperties {

        private Builder builder;
        public CellType type;
        public boolean canBeDestroyed;
        public int health;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static CellProperties parse(byte[] data) throws IOException {
            return CellProperties.parse(new CodedInputStream(data));
        }

        public static CellProperties parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            CellProperties instance = new CellProperties();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.type = CellType.valueOf(is.readRawVarint32());
                        break;
                    case 16:
                        bitMask |= 0x2;
                        instance.canBeDestroyed = is.readBoolean();
                        break;
                    case 24:
                        bitMask |= 0x4;
                        instance.health = is.readRawVarint32();
                        break;
                }
            }
            if ((bitMask & 0x3) != 0x3) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (type != null) {
                builder.setType(type);
            }
            builder.setCanBeDestroyed(canBeDestroyed);
            if (health != 0) {
                builder.setHealth(health);
            }
            return builder;
        }

        @Override
        public String toString() {
            return "CellProperties{" +
                    "type = " + type +
                    ", canBeDestroyed = " + canBeDestroyed +
                    ", health = " + health +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setType(CellType type) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeInt32NoTag(type.getNumber());
                return this;
            }

            public Builder setCanBeDestroyed(boolean canBeDestroyed) {
                bitMask |= 2;
                os.writeInt32NoTag(16);
                os.writeBoolean(canBeDestroyed);
                return this;
            }

            public Builder setHealth(int health) {
                bitMask |= 4;
                os.writeInt32NoTag(24);
                os.writeInt32NoTag(health);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class Position {

        private Builder builder;
        public int x;
        public int y;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static Position parse(byte[] data) throws IOException {
            return Position.parse(new CodedInputStream(data));
        }

        public static Position parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            Position instance = new Position();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.x = is.readRawVarint32();
                        break;
                    case 16:
                        bitMask |= 0x2;
                        instance.y = is.readRawVarint32();
                        break;
                }
            }
            if ((bitMask & 0x3) != 0x3) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (x != 0) {
                builder.setX(x);
            }
            if (y != 0) {
                builder.setY(y);
            }
            return builder;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x = " + x +
                    ", y = " + y +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setX(int x) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeInt32NoTag(x);
                return this;
            }

            public Builder setY(int y) {
                bitMask |= 2;
                os.writeInt32NoTag(16);
                os.writeInt32NoTag(y);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public static final class Dimension {

        private Builder builder;
        public int height;
        public int width;

        private static final ThreadLocal<Builder> threadLocal = new ThreadLocal<>();

        public static Dimension parse(byte[] data) throws IOException {
            return Dimension.parse(new CodedInputStream(data));
        }

        public static Dimension parse(CodedInputStream is) throws IOException {
            boolean done = false;
            int bitMask = 0x0000;
            Dimension instance = new Dimension();
            while (!done) {
                int tag = is.readTag();
                switch (tag) {
                    default:
                        throw InvalidProtocolBufferException.invalidTag();
                    case 0:
                        done = true;
                        break;
                    case 8:
                        bitMask |= 0x1;
                        instance.height = is.readRawVarint32();
                        break;
                    case 16:
                        bitMask |= 0x2;
                        instance.width = is.readRawVarint32();
                        break;
                }
            }
            if ((bitMask & 0x3) != 0x3) {
                throw new IOException("Message missing required fields");
            }
            return instance;
        }

        /**
         * @return thread-local builder. Be aware that if you use recursive nested message obtaining new builder will
         * return the same one, use {@link Builder#create()} instead,
         * or, better not use recursive nested messages at all
         */
        public static Builder obtainBuilder() {
            Builder result = threadLocal.get();
            if (result == null) {
                result = Builder.create();
                threadLocal.set(result);
            }
            result.getOutputStream().reset();
            return result;
        }

        public Builder serialize() {
            return serialize(true);
        }

        public Builder serialize(boolean createBuilder) {
            if (builder != null) return builder;
            builder = createBuilder ? Builder.create() : obtainBuilder();
            if (height != 0) {
                builder.setHeight(height);
            }
            if (width != 0) {
                builder.setWidth(width);
            }
            return builder;
        }

        @Override
        public String toString() {
            return "Dimension{" +
                    "height = " + height +
                    ", width = " + width +
                    '}';
        }

        public static final class Builder extends AbstractMessageBuilder {

            private int bitMask;

            public static Builder create() {
                return new Builder();
            }

            public Builder() {
                super(50);
            }

            public Builder setHeight(int height) {
                bitMask |= 1;
                os.writeInt32NoTag(8);
                os.writeInt32NoTag(height);
                return this;
            }

            public Builder setWidth(int width) {
                bitMask |= 2;
                os.writeInt32NoTag(16);
                os.writeInt32NoTag(width);
                return this;
            }

            public void initDefaults() {
            }
        }

    }

    public enum PlayerState implements ProtoEnum {
        IN_LOBBY(1),
        IN_ROOM(2),
        IN_COMBAT(3);

        private int number;

        private PlayerState(int number) {
            this.number = number;
        }

        @Override
        public int getNumber() {
            return number;
        }

        public static PlayerState valueOf(int value) {
            switch (value) {
                case 1:
                    return IN_LOBBY;
                case 2:
                    return IN_ROOM;
                case 3:
                    return IN_COMBAT;
                default:
                    return null;
            }
        }
    }

    public enum RoomState implements ProtoEnum {
        WAITING(1),
        READY(2),
        COMBAT(3);

        private int number;

        private RoomState(int number) {
            this.number = number;
        }

        @Override
        public int getNumber() {
            return number;
        }

        public static RoomState valueOf(int value) {
            switch (value) {
                case 1:
                    return WAITING;
                case 2:
                    return READY;
                case 3:
                    return COMBAT;
                default:
                    return null;
            }
        }
    }

    public enum Status implements ProtoEnum {
        OK(1),
        PLAYER_NOT_FOUND(2),
        NOT_IN_COMBAT(3);

        private int number;

        private Status(int number) {
            this.number = number;
        }

        @Override
        public int getNumber() {
            return number;
        }

        public static Status valueOf(int value) {
            switch (value) {
                case 1:
                    return OK;
                case 2:
                    return PLAYER_NOT_FOUND;
                case 3:
                    return NOT_IN_COMBAT;
                default:
                    return null;
            }
        }
    }

    public enum CellType implements ProtoEnum {
        BRICK(1),
        STEEL(2);

        private int number;

        private CellType(int number) {
            this.number = number;
        }

        @Override
        public int getNumber() {
            return number;
        }

        public static CellType valueOf(int value) {
            switch (value) {
                case 1:
                    return BRICK;
                case 2:
                    return STEEL;
                default:
                    return null;
            }
        }
    }
}

