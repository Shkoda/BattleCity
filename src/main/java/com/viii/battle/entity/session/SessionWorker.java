package com.viii.battle.entity.session;

import com.viii.battle.entity.player.Player;
import com.viii.battle.utils.Loggers;
import io.netty.channel.Channel;
import ortobuf.core.AbstractMessageBuilder;

/**
 * Created by Artem
 * Date: 7/18/2014 3:08 PM.
 */
public class SessionWorker {

    private Player player;
    private Channel channel;

    public SessionWorker(Channel channel) {
        this.channel = channel;
    }


    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void disconnectChannel(){
        channel.disconnect();
    }

    public void write(AbstractMessageBuilder packet){
        Loggers.debugLogger.debug("Writing message... "+packet );
        channel.write(packet);
        channel.flush();
    }
}
