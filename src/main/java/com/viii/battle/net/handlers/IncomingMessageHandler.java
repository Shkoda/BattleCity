package com.viii.battle.net.handlers;
import com.viii.battle.entity.session.SessionWorker;

/**
 * Created by Artem
 * Date: 3/9/14 3:30 PM.
 */
public abstract class IncomingMessageHandler {
    public abstract void handle(SessionWorker sessionWorker) throws Exception;
}
