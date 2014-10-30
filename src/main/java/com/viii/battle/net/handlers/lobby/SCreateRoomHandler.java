package com.viii.battle.net.handlers.lobby;

import com.viii.battle.controller.RoomManager;
import com.viii.battle.entity.game.Room;
import com.viii.battle.entity.session.SessionWorker;
import com.viii.battle.net.handlers.IncomingMessageHandler;
import com.viii.battle.net.protocol.Protocol;
import com.viii.battle.server.SessionManager;

import java.io.IOException;

/**
 * Created by Nightingale on 14.10.2014.
 */
public class SCreateRoomHandler extends IncomingMessageHandler {
    private Protocol.SCreateRoom packet;

    public SCreateRoomHandler(byte[] data) {
        try {
            packet = Protocol.SCreateRoom.parse(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(SessionWorker sessionWorker) throws Exception {
        Protocol.CCreateRoom.Builder response = Protocol.CCreateRoom.Builder.create();
        Protocol.RoomInfo.Builder room = RoomManager.createRoom(sessionWorker.getPlayer(), packet.capacity);
        if (room != null) sessionWorker.write(response.setSuccessful(true).setRoom(room));
        else sessionWorker.write(response.setSuccessful(false));
    }
}
