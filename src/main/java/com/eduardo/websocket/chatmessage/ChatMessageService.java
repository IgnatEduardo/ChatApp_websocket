package com.eduardo.websocket.chatmessage;

import com.eduardo.websocket.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Service class for managing ChatMessage operations
@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository; // Injecting ChatMessageRepository dependency
    private final ChatRoomService chatRoomService; // Injecting ChatRoomService dependency

    // Method to save a chat message and associate it with a chat room
    public ChatMessage save(ChatMessage chatMessage) {
        // Get or create a chat room ID based on senderId and recipientId
        var chatId = chatRoomService
                .getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow(  ); // You can create your own dedicated exception here if needed

        chatMessage.setChatId(chatId); // Set the chat room ID for the chat message
        chatMessageRepository.save(chatMessage); // Save the chat message to the repository
        return chatMessage; // Return the saved chat message
    }

    // Method to find all chat messages between a sender and recipient
    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        // Retrieve the chat room ID for the sender and recipient
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false); // false to not create a new room if it doesn't exist because we only want to retrieve messages

        // If chatId is present, find all messages by chatId; otherwise, return an empty list
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }

}
