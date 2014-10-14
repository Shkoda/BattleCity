package com.viii.battle.server;

import com.viii.battle.entity.game.Room;
import com.viii.battle.entity.player.Player;
import com.viii.battle.net.protocol.Protocol;
import lombok.ToString;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by Nightingale on 14.10.2014.
 */
@ToString
public class SessionManager {
    private static List<Player> onlinePlayers = new CopyOnWriteArrayList<>();
    private static List<Room> rooms = new CopyOnWriteArrayList<>();


    public static void addPlayer(Player player) {
        onlinePlayers.add(player);
    }

    public static void removePlayer(Player player) {
        onlinePlayers.remove(player);
    }

    public static void removeRoom(Room room) {
        rooms.remove(room);
    }

    public static List<Protocol.PlayerInfo.Builder> listPlayers() {
        return onlinePlayers.stream()
                .map(Player::info)
                .collect(Collectors.toList());
    }

    public static List<Protocol.RoomInfo.Builder> listRooms() {
        return rooms.stream()
                .map(Room::info)
                .collect(Collectors.toList());
    }

    public static Protocol.RoomInfo.Builder createRoom(Player owner, int capacity) {
        if (owner.getState() != Protocol.PlayerState.IN_LOBBY)
            return null;
        Room room = new Room(owner, capacity);
        rooms.add(room);
        return room.info();
    }
}



















