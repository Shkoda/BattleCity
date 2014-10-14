package com.viii.battle.server;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;

import java.io.IOException;

import static io.netty.buffer.Unpooled.directBuffer;


public class HttpHandler extends ChannelInboundHandlerAdapter {

    private boolean secure;

    public HttpHandler(boolean secure) {
        this.secure = secure;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        if (secure) {
            ctx.pipeline().get(SslHandler.class).handshakeFuture();
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            try {
                handleHttpRequest((DefaultFullHttpRequest) msg, ctx.channel());
            } catch (Exception exc) {
                exc.printStackTrace();
            }

        }
    }

    private void handleHttpRequest(DefaultFullHttpRequest request, Channel channel) throws IOException {
        System.out.println(" >> handling stub ...");

    }
}
