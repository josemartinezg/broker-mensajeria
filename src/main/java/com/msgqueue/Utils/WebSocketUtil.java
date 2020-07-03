package com.msgqueue.Utils;


import com.msgqueue.Controllers.ClienteController;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import org.eclipse.jetty.websocket.api.Session;

@WebSocket
public class WebSocketUtil {
    @OnWebSocketConnect
    public void addSession(Session session) {
        System.out.println("Conectando Usuario: " + session.getLocalAddress().getAddress().toString());
        ClienteController.sessions.add(session);
    }

    @OnWebSocketClose
    public void removeSession(Session session, int statusCode, String reason) {
        ClienteController.sessions.remove(session);
    }
}
