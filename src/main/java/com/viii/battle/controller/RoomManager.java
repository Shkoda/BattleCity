package com.viii.battle.controller;

import com.viii.battle.entity.game.Room;
import com.viii.battle.entity.player.Player;
import com.viii.battle.net.protocol.Protocol;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by Nightingale on 14.10.2014.
 */
public class RoomManager {
    private static List<Room> rooms = new CopyOnWriteArrayList<>();

    public static void removeRoom(Room room) {
        rooms.remove(room);
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

    private static Optional<Room> getRoom(int roomId) {
        return rooms.stream().filter(r -> r.getId() == roomId).findAny();
    }


    public static Protocol.RoomInfo.Builder joinRoom(int roomId, Player player) {
        Optional<Room> room = getRoom(roomId);
        if (!room.isPresent())
            return null;
        boolean joined = room.get().join(player);
        if (!joined)
            return null;
        return room.get().info();
    }

    private static Optional<Room> find(Player player) {
        return rooms.stream().filter(room -> room.inRoom(player)).findAny();
    }

    public static boolean leaveRoom(Player player) {
        switch (player.getState()) {
            case IN_LOBBY:
                return false;
            case IN_ROOM:
                return find(player).get().leave(player);
            case IN_COMBAT:
                //todo
                return false;
            default:
                return false;

        }
    }


}





















