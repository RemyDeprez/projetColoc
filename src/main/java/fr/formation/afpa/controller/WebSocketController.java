package fr.formation.afpa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.formation.afpa.domain.Chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import static java.lang.String.format; 

@Controller
public class WebSocketController {
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
 
 @Autowired 
private SimpMessageSendingOperations simpleMessaging;
 
 @Autowired
 public WebSocketController(SimpMessagingTemplate template) {
   this.simpleMessaging = template;
 }
 
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public Chat sendMessage(@Payload Chat chatMessage) {
    	System.out.println("in the public message");
        return chatMessage;
    }
 
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public Chat addUser(@Payload Chat chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
    	System.out.println("in the addPublic");
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    
    @MessageMapping("/chat/{roomId}/sendPrivateMessage")
    public void sendPrivateMessage(@DestinationVariable String roomId, @Payload Chat chatMessage) {
    	System.out.println("in the sendPrivate");
        logger.info(roomId+" Chat message recieved is "+chatMessage.getContent());
        
    	simpleMessaging.convertAndSend(format("/chat-room/%s", roomId), chatMessage);
    }
    

    @MessageMapping("/chat/{roomId}/addPrivateUser")
    public void addPrivateUser(@DestinationVariable String roomId, @Payload Chat chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {
        String currentRoomId = (String) headerAccessor.getSessionAttributes().put("room_id", roomId);
        System.out.println("in the addPrivate");
        logger.info("Current room id is : " + roomId);
        if (currentRoomId != null) {
            Chat leaveMessage = new Chat();
            leaveMessage.setType(Chat.MessageType.LEAVE);
            leaveMessage.setSender(chatMessage.getSender());
            simpleMessaging.convertAndSend(format("/chat-room/%s", currentRoomId), leaveMessage);
        }
        headerAccessor.getSessionAttributes().put("name", chatMessage.getSender());
        simpleMessaging.convertAndSend(format("/chat-room/%s", roomId), chatMessage);
    }
}
    
    
    
 
