package com.viii.battle.net.handlers.lobby;

import com.viii.battle.entity.session.SessionWorker;
import com.viii.battle.net.handlers.IncomingMessageHandler;
import com.viii.battle.net.protocol.Protocol;
import com.viii.battle.server.SessionManager;

import java.io.IOException;

/**
 * Created by Nightingale on 14.10.2014.
 */
public class SGetRoomsHandler extends IncomingMessageHandler {
    private Protocol.SGetRooms packet;

    public SGetRoomsHandler(byte[] data) {
        try {
            packet = Protocol.SGetRooms.parse(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(SessionWorker sessionWorker) throws Exception {
        sessionWorker.write(Protocol.CGetRooms.Builder.create().addAllRooms(SessionManager.listRooms()));
    }
}
