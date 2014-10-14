package com.viii.battle.net.handlers.lobby;

import com.viii.battle.entity.session.SessionWorker;
import com.viii.battle.net.handlers.IncomingMessageHandler;
import com.viii.battle.net.protocol.Protocol;
import com.viii.battle.server.SessionManager;

import java.io.IOException;

/**
 * Created by Nightingale on 14.10.2014.
 */
public class SGetPlayersHandler extends IncomingMessageHandler {
    private Protocol.SGetPlayers packet;

    public SGetPlayersHandler(byte[] data) {
        try {
            packet = Protocol.SGetPlayers.parse(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(SessionWorker sessionWorker) throws Exception {
        sessionWorker.write(Protocol.CGetPlayers.Builder.create().addAllPlayers(SessionManager.listPlayers()));
    }
}
