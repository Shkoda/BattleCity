package com.viii.battle.net.handlers.lobby;

import com.viii.battle.entity.session.SessionWorker;
import com.viii.battle.net.handlers.IncomingMessageHandler;
import com.viii.battle.net.protocol.Protocol;

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

    }
}
