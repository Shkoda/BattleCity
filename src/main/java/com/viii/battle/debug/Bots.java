package com.viii.battle.debug;

import com.viii.battle.controller.RoomManager;
import com.viii.battle.entity.game.Room;
import com.viii.battle.entity.player.Player;
import com.viii.battle.utils.Loggers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nightingale on 04.11.2014.
 */
public class Bots {

    private static Map<Integer, Player> bots = new HashMap<>();
    public static void startBots(int botNumber){
        for (int i = 1; i <= botNumber; i++){
            Player bot = new Player("Bot#"+i);
            bots.put(bot.getId(), bot);
            RoomManager.createRoom(bot, 5);
        }
        RoomManager.getRooms().forEach(Loggers.debugLogger::debug);

    }

}
