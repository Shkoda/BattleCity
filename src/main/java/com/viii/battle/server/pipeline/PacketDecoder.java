package com.viii.battle.server.pipeline;

import com.viii.battle.net.protocol.MessageType;
import com.viii.battle.net.protocol.PacketStructure;
import com.viii.battle.utils.Loggers;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * Created by Artem
 * Date: 7/18/2014 2:55 PM.
 */
public class PacketDecoder extends ReplayingDecoder<PacketStructure> {

    private byte[] data;
    private MessageType type;

    public PacketDecoder() {
        checkpoint(PacketStructure.TYPE);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        switch (state()) {
            case TYPE:
                type = MessageType.fromByte(in.readByte());
                checkpoint(PacketStructure.LENGTH);
            case LENGTH:
                int size = in.readInt();
                if (size < 0) throw new Exception("Invalid content size");
                data = new byte[size];
                checkpoint(PacketStructure.DATA);
            case DATA:
                in.readBytes(data, 0, data.length);
                try {
                    out.add(type.createHandler(data));
                    Loggers.debugLogger.debug("Request >> "+type);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    this.reset();
                }
            default:
                throw new Exception("Unknown decoding state");
        }
    }

    private void reset() {
        checkpoint(PacketStructure.TYPE);
        data = null;
        type = null;
    }

}
