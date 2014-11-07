package com.viii.battle.entity.player;

import com.viii.battle.net.protocol.Protocol;
import com.viii.battle.utils.ProtocolObject;
import lombok.ToString;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Nightingale on 14.10.2014.
 */
@ToString
public class Player implements ProtocolObject<Protocol.PlayerInfo.Builder> {
    private static final AtomicInteger sequence = new AtomicInteger();

    private int id;
    private String name;
    private Protocol.PlayerState state;

    public Player(String name) {
        this.id = sequence.incrementAndGet();
        this.name = name;
        state = Protocol.PlayerState.IN_LOBBY;
    }

    public Protocol.PlayerInfo.Builder info() {
        return Protocol.PlayerInfo.Builder.create()
                .setId(id)
                .setName(name)
                .setState(state);
    }

    public int getId() {
        return id;
    }

    public Protocol.PlayerState getState() {
        return state;
    }

    public void setState(Protocol.PlayerState state) {
        this.state = state;
    }
}
