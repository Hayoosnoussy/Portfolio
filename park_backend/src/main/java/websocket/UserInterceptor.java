package websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.messaging.support.NativeMessageHeaderAccessor;
import org.springframework.util.MultiValueMap;

import websocket.data.User;

import static com.schiedtandbachmann.ParkApplication.LOGGER_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kubanek.peter on 29. 3. 2022 for project AsynPoC.
 */
public class UserInterceptor implements ChannelInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ":UserInterceptor");

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        MessageHeaders headers = message.getHeaders();

        logger.info("MESSAGE preSend: preSend-StompHeaderAccessor: {} preSend-MessageHeaders: {} preSend-Message: {} ,preSend-MessageChannel: {}",accessor , headers, message, channel);

        if (accessor != null) {
            logger.info("MESSAGE preSend: StompCommand: {}", accessor.getCommand());

            try {
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    stompConnection(accessor, message);
                } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
                    stompDisconnection(message);
                }
            } catch (Exception exception) {
                logger.error("MESSAGE preSend: There was an exception: ", exception);
            }
        }

        MultiValueMap<String, String> multiValueMap = headers.get(NativeMessageHeaderAccessor.NATIVE_HEADERS,MultiValueMap.class);
        if (multiValueMap != null) {
            for (Map.Entry<String, List<String>> head : multiValueMap.entrySet()) {
                logger.debug("{}#{}",head.getKey() , head.getValue());
            }
        }
        return message;
    }

    private void stompConnection(StompHeaderAccessor accessor, Message<?> message) {
        Object raw = message.getHeaders().get(NativeMessageHeaderAccessor.NATIVE_HEADERS);

        if (raw instanceof Map) {
            Object name = ((Map<?, ?>) raw).get("username");
            logger.info("MESSAGE preSend: Connected, username:{}", name);

            if (name instanceof ArrayList) {
                accessor.setUser(new User(((ArrayList<String>) name).get(0)));
            }

            logger.debug("MESSAGE preSend: Connect WebSocket client: {}", accessor.getUser());
        }
    }

    private void stompDisconnection(Message<?> message) {
        Object raw = message.getHeaders().get(NativeMessageHeaderAccessor.NATIVE_HEADERS);

        if (raw instanceof Map) {
            Object name = ((Map<?, ?>) raw).get("username");
            logger.info("MESSAGE preSend: Disconnected, username:{}", name);
        }
    }
}
