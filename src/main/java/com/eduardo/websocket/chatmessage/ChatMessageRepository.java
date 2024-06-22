package com.eduardo.websocket.chatmessage;

import com.eduardo.websocket.chatroom.ChatRoomService;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByChatId(String chatId);
}
