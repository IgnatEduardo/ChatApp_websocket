package com.eduardo.websocket.chatroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

// Service class for managing ChatRoom operations
@Service
@RequiredArgsConstructor // Lombok annotation to generate a constructor with required arguments
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository; // Injecting ChatRoomRepository dependency

    // Method to get or create a chat room ID between two users
    public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists) {
        // Try to find an existing chat room by senderId and recipientId
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId) // Find a chat room with the given senderId and recipientId
                .map(ChatRoom::getChatId) // If found, return the chatId of the existing room
                .or(() -> {
                    if (createNewRoomIfNotExists) {
                        // If createNewRoomIfNotExists is true, create a new chat room
                        var chatId = createChatId(senderId, recipientId);
                        return Optional.of(chatId); // Return the newly created chatId as Optional
                    }

                    return Optional.empty(); // Otherwise, return empty Optional
                });
    }

    // Method to create a new chat room and save it to the repository
    private String createChatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId); // Generate chatId based on sender and recipient ( e.g. senderId_recipientId )

        // Create ChatRoom instances for both sender to recipient and recipient to sender
        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId) // Reverse the sender and recipient for the second ChatRoom instance
                .recipientId(senderId)
                .build();

        // Save both ChatRoom instances to the repository
        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);

        return chatId; // Return the generated chatId
    }
}
