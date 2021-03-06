package com.viii.battle.net.handlers.lobby;

import com.viii.battle.controller.RoomManager;
import com.viii.battle.entity.session.SessionWorker;
import com.viii.battle.net.handlers.IncomingMessageHandler;
import com.viii.battle.net.protocol.Protocol;

import java.io.IOException;

/**
 * Created by Nightingale on 14.10.2014.
 */
public class SLeaveRoomHandler extends IncomingMessageHandler {
    private Protocol.SLeaveRoom packet;

    public SLeaveRoomHandler(byte[] data) {
        try {
            packet = Protocol.SLeaveRoom.parse(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(SessionWorker sessionWorker) throws Exception {
        sessionWorker.write(Protocol.CLeaveRoom.Builder.create().setSuccessful(RoomManager.leaveRoom(sessionWorker.getPlayer())));
    }
}
