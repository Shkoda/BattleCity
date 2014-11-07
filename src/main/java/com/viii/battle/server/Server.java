package com.viii.battle.server;

import com.viii.battle.config.Config;
import com.viii.battle.debug.Bots;
import com.viii.battle.server.pipeline.LogicHandler;
import com.viii.battle.server.pipeline.PacketDecoder;
import com.viii.battle.server.pipeline.PacketEncoder;
import com.viii.battle.utils.Loggers;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * Created by Nightingale on 14.10.2014.
 */
public class Server {
    public static void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        PacketEncoder ENCODER = new PacketEncoder();

        try{
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("decoder", new PacketDecoder());
                            pipeline.addLast("encoder", ENCODER);
                            pipeline.addLast("handler", new LogicHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 512)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)// (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // Bind and start to accept incoming connections.
//            int port = Config.DEBUG_MODE ? Config.server.port : Config.server.portSSL;
            int port = Config.server.port;
            ChannelFuture f = b.bind(port).sync(); // (7)

            System.out.println("Server was started successfully on port " + port);
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void init() throws Exception {
        Config.load();
        Bots.startBots(10);
    }


    public static void main(String[] args) {
        try {
            if (args != null && args.length > 0 && args[0].equals("-release")) {
                Config.DEBUG_MODE = false;
            }
            init();
        } catch (Exception e) {
            Loggers.exceptionLogger.error("Error during initialization:", e);
            return;
        }
        try {
            Loggers.debugLogger.debug("Starting sever...");
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
