package com.viii.battle.entity.game;

import com.viii.battle.controller.RoomManager;
import com.viii.battle.entity.player.Player;
import com.viii.battle.net.protocol.Protocol;
import com.viii.battle.server.SessionManager;
import com.viii.battle.utils.ProtocolObject;
import lombok.ToString;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Nightingale on 14.10.2014.
 */
@ToString
public class Room implements ProtocolObject<Protocol.RoomInfo.Builder> {
    private static final AtomicInteger sequence = new AtomicInteger();
    private int id;
    private int capacity;
    private Protocol.RoomState state;
    private List<Player> players;
    private long startTimeUTC;

    public Room(Player owner, int capacity) {
        this.id = sequence.incrementAndGet();
        this.capacity = capacity;
        state = Protocol.RoomState.WAITING;
        players = new CopyOnWriteArrayList<>();
        players.add(owner);
    }

    public boolean join(Player player) {
        if (isFull() || inRoom(player))
            return false;
        players.add(player);
        return true;
    }

    public boolean leave(Player player){
        if (!inRoom(player))
            return false;
        players.remove(player);
        if (players.isEmpty())
            RoomManager.removeRoom(this);
        return true;
    }

    public boolean isFull() {
        return players.size() == capacity;
    }

    public boolean inRoom(Player player) {
        return players.contains(player);
    }

    public int getId() {
        return id;
    }

    @Override
    public Protocol.RoomInfo.Builder info() {
        return Protocol.RoomInfo.Builder.create()
                .setId(id)
                .setCapacity(capacity)
                .setState(state)
                .addAllPlayers(players.stream().map(Player::info).collect(Collectors.toList()));
    }
}

































