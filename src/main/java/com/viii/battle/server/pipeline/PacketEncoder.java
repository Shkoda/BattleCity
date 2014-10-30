package com.viii.battle.server.pipeline;

import com.viii.battle.net.protocol.MessageType;
import com.viii.battle.utils.Loggers;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import ortobuf.core.AbstractMessageBuilder;

import java.util.Arrays;

/**
 * Created by Artem
 * Date: 7/18/2014 7:21 PM.
 */
@ChannelHandler.Sharable
public class PacketEncoder extends MessageToByteEncoder<AbstractMessageBuilder> {

    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractMessageBuilder msg, ByteBuf out) throws Exception {

        out.writeByte(MessageType.fromClass(msg.getClass()).getByteValue());
        byte[] data = msg.getBytes();
        out.writeInt(data.length);
        out.writeBytes(data);
    }
}
