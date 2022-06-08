package com.joebrooks.mapshotimageapi.task;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.joebrooks.mapshotimageapi.driver.DriverService;
import com.joebrooks.mapshotimageapi.global.sns.SlackClient;
import com.joebrooks.mapshotimageapi.global.util.UriGenerator;
import com.joebrooks.mapshotimageapi.global.util.WidthExtractor;
import com.joebrooks.mapshotimageapi.map.UserMapRequest;
import com.joebrooks.mapshotimageapi.map.UserMapResponse;
import com.joebrooks.mapshotimageapi.websocket.WebSocketSessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final static Map<String, ByteArrayResource> imageMap = new HashMap<>();
    private final DriverService driverService;
    private final SlackClient slackClient;
    private final WebSocketSessionManager webSocketSessionManager;
    private final ObjectMapper mapper = new ObjectMapper();


    @Async
    public void execute(UserMapRequest request, WebSocketSession session){

        if(!session.isOpen()){
            webSocketSessionManager.removeSession(session);
            return;
        }

        try{
            driverService.loadPage(UriGenerator.getUri(request));
            int width = WidthExtractor.extract(request);

            for(int y = 0; y < width; y+= 1000){
                for(int x = 0; x < width; x+= 1000){
                    try {
                        driverService.scrollPage(x, y);
                        ByteArrayResource byteArrayResource = driverService.capturePage();
                        String uuid = UUID.randomUUID().toString();
                        imageMap.put(uuid, byteArrayResource);
                        UserMapResponse response = UserMapResponse.builder()
                                .index(0)
                                .x(x)
                                .y(y)
                                .uuid(uuid)
                                .build();

                        if(session.isOpen()){
                            session.sendMessage(new TextMessage(mapper.writeValueAsString(response)));
                        } else {
                            popImage(response.getUuid());
                            return;
                        }
                    } catch (Exception e){
                        log.error(e.getMessage(), e);
                        slackClient.sendMessage(e.getMessage(), e);
                    }
                }
            }


        } catch (Exception e){
            log.error(e.getMessage(), e);
            slackClient.sendMessage(e.getMessage(), e);
        }

    }


    public Optional<ByteArrayResource> popImage(String uuid){
        Optional<ByteArrayResource> data = Optional.ofNullable(imageMap.get(uuid));
        imageMap.remove(uuid);

        return data;
    }
}