package yh.study.websocket.cluster.common.test.utils;

import yh.study.websocket.cluster.common.model.MessageType;
import yh.study.websocket.cluster.common.model.WebSocketMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yh.study.websocket.cluster.common.utils.JSON;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lawrence
 * @since 2021/8/15
 */
class JSONTest {

    private WebSocketMessage testMessage;

    @BeforeEach
    void setUp() {
        WebSocketMessage webSocketMessage = new WebSocketMessage();
        webSocketMessage.setContent("content");
        webSocketMessage.setTimestamp(new Date());
        webSocketMessage.setType(MessageType.FOR_USER.code());
        webSocketMessage.setServerIp("0.0.0.0");
        webSocketMessage.setServerUserCount(1);
        testMessage = webSocketMessage;
    }

    @Test
    void toJSONString() {
        String message = JSON.toJSONString(testMessage);
        Field[] declaredFields = WebSocketMessage.class.getDeclaredFields();
        for (Field field : declaredFields) {
            int modifier = field.getModifiers();
            if (!Modifier.isStatic(modifier)) {
                assertTrue(message.contains(field.getName()));
            }
        }
    }

    @Test
    void parseJSON() {
        final String json = "{\"type\":1,\"content\":\"content\",\"serverUserCount\":1,\"serverIp\":\"0.0.0.0\",\"timestamp\":1628997609392}";
        WebSocketMessage message = JSON.parseJSON(json, WebSocketMessage.class);
        assertEquals(message.getType(), testMessage.getType());
        assertEquals(message.getContent(), testMessage.getContent());
        assertEquals(message.getServerIp(), testMessage.getServerIp());
        assertEquals(message.getServerUserCount(), testMessage.getServerUserCount());
    }
}
