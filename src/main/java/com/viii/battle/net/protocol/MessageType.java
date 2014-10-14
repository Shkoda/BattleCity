package com.viii.battle.net.protocol;

import com.viii.battle.net.handlers.IncomingMessageHandler;
import com.viii.battle.net.handlers.UnknownMessageHandler;
import com.viii.battle.net.handlers.lobby.*;
import com.viii.battle.net.handlers.login.SNewPlayerHandler;
import com.viii.battle.net.handlers.system.SPingHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Artem
 * Date: 3/9/14 3:32 PM.
 */
public enum MessageType {

    S_PING((byte) 0x01, SPingHandler::new),

    S_NEW_PLAYER((byte) 0x10, SNewPlayerHandler::new),

    S_GET_PLAYERS((byte) 0x20, SGetPlayersHandler::new),
    S_GET_ROOMS((byte) 0x21, SGetRoomsHandler::new),
    S_CREATE_ROOM((byte) 0x22, SCreateRoomHandler::new),
    S_JOIN_ROOM((byte) 0x23, SJoinRoomHandler::new),
    S_LEAVE_ROOM((byte) 0x24, SLeaveRoomHandler::new),

    C_PING((byte) 0x01, Protocol.CPing.Builder.class),

    C_NEW_PLAYER((byte) 0x10, Protocol.CNewPlayer.Builder.class),

    C_GET_PLAYERS((byte) 0x20,  Protocol.CGetPlayers.Builder.class),
    C_GET_ROOMS((byte) 0x21,  Protocol.CGetRooms.Builder.class),
    C_CREATE_ROOM((byte) 0x22, Protocol.CCreateRoom.Builder.class),
    C_JOIN_ROOM((byte) 0x23,  Protocol.CJoinRoom.Builder.class),
    C_LEAVE_ROOM((byte) 0x24, Protocol.CLeaveRoom.Builder.class),

    UNKNOWN((byte) 0x00, UnknownMessageHandler::new);


    private final int code;
    private Class protoClass;

    private final static Map<Class, MessageType> classMap;
    private final static Map<Integer, MessageType> byteMap;
    private Function<byte[], IncomingMessageHandler> handlerCreator;


    static {
        classMap = new HashMap<>(16);
        byteMap = new HashMap<>(16);
        for (MessageType type : values()) {
            classMap.put(type.protoClass, type);
            if (type.protoClass == null) byteMap.put(type.code, type);
        }
    }

    private MessageType(int code, Function<byte[], IncomingMessageHandler> function) {
        this.code = code;
        this.handlerCreator = function;

    }

    private MessageType(int code, Class protoClass) {
        this.code = code;
        this.protoClass = protoClass;
    }


    public static MessageType fromByte(int b) {
        MessageType messageType = byteMap.get(b);
        return messageType != null ? messageType : UNKNOWN;
    }

    public static MessageType fromClass(Class c) {
        MessageType messageType = classMap.get(c);
        return messageType != null ? messageType : UNKNOWN;
    }

    public int getByteValue() {
        return code;
    }


    public IncomingMessageHandler createHandler(byte[] data) {
        return handlerCreator.apply(data);
    }
}
