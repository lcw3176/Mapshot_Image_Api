package com.joebrooks.mapshotimageapi.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebsocketInfo {

    private int index;
    private int x;
    private int y;
    private String uuid;
    private boolean error;
    private WebSocketSession session;
    private COMMAND command;

    public enum COMMAND{
        SEND,
        CLOSE
    }
}
