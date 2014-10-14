package com.viii.battle.net.handlers;


import com.viii.battle.entity.session.SessionWorker;

/**
 * Created by Artem
 * Date: 3/9/14 3:31 PM.
 */
public final class UnknownMessageHandler extends IncomingMessageHandler {
    public UnknownMessageHandler(byte[] data) {
    }

    @Override
    public void handle(SessionWorker sessionWorker) {
//        sessionWorker.write(HttpResponseStatus.BAD_REQUEST);
    }

}
