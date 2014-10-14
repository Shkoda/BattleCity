package com.viii.battle.net.handlers.system;

import com.viii.battle.entity.session.SessionWorker;
import com.viii.battle.net.handlers.IncomingMessageHandler;
import com.viii.battle.net.protocol.Protocol;
import com.viii.battle.utils.Loggers;

import static com.viii.battle.utils.Loggers.*;

/**
 * Created by Nightingale on 14.10.2014.
 */
public class SPingHandler extends IncomingMessageHandler {

    public SPingHandler(byte[] data) {
    }

    @Override
    public void handle(SessionWorker sessionWorker) throws Exception {
        debugLogger.debug("SPing...");
        sessionWorker.write(Protocol.CPing.Builder.create().setInfo("hello sunshine"));
    }
}
