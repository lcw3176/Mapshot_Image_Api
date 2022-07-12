package com.joebrooks.mapshotimageapi.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@RequiredArgsConstructor
public abstract class AbstractMapRequestHandler extends TextWebSocketHandler {

    protected final SessionHandler sessionHandler;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessionHandler.onConnect(session);
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        sessionHandler.onClose(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        sessionHandler.onClose(session);
    }


}