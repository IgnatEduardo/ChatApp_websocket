package com.eduardo.websocket.chatmessage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate; // WebSocket messaging template for sending messages
    private final ChatMessageService chatMessageService; // Service for managing chat messages

    // WebSocket message mapping endpoint for handling chat messages
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage) {
        // Save the incoming chat message
        ChatMessage savedMsg = chatMessageService.save(chatMessage);

        // Send a notification to the recipient using WebSocket
        messagingTemplate.convertAndSendToUser( // Send a message to a specific user
                chatMessage.getRecipientId(), // Recipient's user ID for routing
                "/queue/messages", // Destination queue for the recipient
                new ChatNotification( // Create a notification object
                        savedMsg.getId(), // ID of the saved chat message
                        savedMsg.getSenderId(),
                        savedMsg.getRecipientId(),
                        savedMsg.getContent()
                )
        );
    }

    // REST API endpoint to fetch chat messages between two users
    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(@PathVariable String senderId, //PathVariable annotation to map senderId from the URL
                                                              @PathVariable String recipientId) {
        // Retrieve and return the list of chat messages between sender and recipient
        return ResponseEntity // Return a ResponseEntity object
                .ok(chatMessageService.findChatMessages(senderId, recipientId));// Return the list of chat messages
    }
}
