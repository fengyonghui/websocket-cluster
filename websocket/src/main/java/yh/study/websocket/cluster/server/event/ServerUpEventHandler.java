package yh.study.websocket.cluster.server.event;

import yh.study.websocket.cluster.common.constant.GlobalConstant;
import yh.study.websocket.cluster.common.model.MessageType;
import yh.study.websocket.cluster.common.model.WebSocketMessage;
import yh.study.websocket.cluster.common.utils.JSON;
import yh.study.websocket.cluster.server.server.WebSocketEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * 服务上线事件处理
 *
 * @author lawrence
 * @since 2021/3/21
 */
@Component
public class ServerUpEventHandler implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ServerUpEventHandler.class);

    final StringRedisTemplate stringRedisTemplate;

    public ServerUpEventHandler(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void onApplicationEvent(@Nonnull ApplicationReadyEvent applicationReadyEvent) {
        logger.debug("当前 WebSocket 实例 - 准备就绪，即将发布上线消息. {}", applicationReadyEvent);
        try {
            // Sleep 是为了确保该实例 100% 准备好了
            Thread.sleep(5000);
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }
        logger.info("WebSocket 实例通过 Redis 发布服务上线消息：通知网关更新哈希环");
        // 这里消息内容复用面向客户端的实体类
        stringRedisTemplate.convertAndSend(GlobalConstant.REDIS_TOPIC_CHANNEL,
                JSON.toJSONString(WebSocketMessage.toUserOrServerMessage(
                        MessageType.FOR_SERVER, GlobalConstant.SERVER_UP_MESSAGE, WebSocketEndpoint.sessionMap.size()))
        );
    }
}
