package com.viii.battle.net.handlers.login;

import com.viii.battle.controller.RoomManager;
import com.viii.battle.entity.player.Player;
import com.viii.battle.entity.session.SessionWorker;
import com.viii.battle.net.handlers.IncomingMessageHandler;
import com.viii.battle.net.protocol.Protocol;
import com.viii.battle.server.SessionManager;
import com.viii.battle.utils.Loggers;

import java.io.IOException;

/**
 * Created by Nightingale on 14.10.2014.
 */
public class SNewPlayerHandler extends IncomingMessageHandler {
    private Protocol.SNewPlayer packet;

    public SNewPlayerHandler(byte[] data) {
        try {
            packet = Protocol.SNewPlayer.parse(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(SessionWorker sessionWorker) throws Exception {
        Loggers.debugLogger.debug("Creating new Player "+packet.name);
        Player player = new Player(packet.name);
        sessionWorker.setPlayer(player);
        Protocol.CNewPlayer.Builder response = Protocol.CNewPlayer.Builder.create()
                .setPlayerInfo(player.info())
                .addAllOnlinePlayers(SessionManager.listPlayers())
                .addAllRooms(RoomManager.listRooms());
        sessionWorker.write(response);

    }
}

















