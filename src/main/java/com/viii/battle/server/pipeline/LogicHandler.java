package com.viii.battle.server.pipeline;

import com.viii.battle.entity.session.SessionWorker;
import com.viii.battle.net.handlers.IncomingMessageHandler;
import com.viii.battle.utils.Loggers;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by ARTEM on 7/26/2014.
 */
public class LogicHandler extends ChannelInboundHandlerAdapter {

    private SessionWorker sessionWorker;

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        this.sessionWorker = new SessionWorker(ctx.channel());
        Loggers.debugLogger.debug("channel connected: "+ctx.channel());

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        Loggers.debugLogger.debug("channel disconnected: " + ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Loggers.debugLogger.debug("exception on: " + ctx.channel());
        cause.printStackTrace();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ((IncomingMessageHandler) msg).handle(sessionWorker);
    }
}
