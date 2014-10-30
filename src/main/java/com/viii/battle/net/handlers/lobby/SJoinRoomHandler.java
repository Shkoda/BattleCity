package com.viii.battle.net.handlers.lobby;

import com.viii.battle.controller.RoomManager;
import com.viii.battle.entity.session.SessionWorker;
import com.viii.battle.net.handlers.IncomingMessageHandler;
import com.viii.battle.net.protocol.Protocol;
import com.viii.battle.server.SessionManager;

import java.io.IOException;

/**
 * Created by Nightingale on 14.10.2014.
 */
public class SJoinRoomHandler extends IncomingMessageHandler {
    private Protocol.SJoinRoom packet;

    public SJoinRoomHandler(byte[] data) {
        try {
            packet = Protocol.SJoinRoom.parse(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(SessionWorker sessionWorker) throws Exception {
        Protocol.RoomInfo.Builder room = RoomManager.joinRoom(packet.roomId, sessionWorker.getPlayer());
        Protocol.CJoinRoom.Builder response = Protocol.CJoinRoom.Builder.create();
        if (room != null)
            sessionWorker.write(response.setSuccessful(true).setRoom(room));
        else
            sessionWorker.write(response.setSuccessful(false));
    }
}
