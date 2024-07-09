package websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/validateUserInformation")
    @SendTo("/topic/userInformationResponse")
    public String validateUserInformation(@Payload String message) {
        // Process the message here and return the response
        return "Validation successful!";
    }
}
