package com.eduardo.websocket.chatroom;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String>{

    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId); // Find a chat room with the given senderId and recipientId

}
