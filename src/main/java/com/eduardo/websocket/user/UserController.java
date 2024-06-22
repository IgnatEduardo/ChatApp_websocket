package com.eduardo.websocket.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

// Controller class to handle user-related HTTP and WebSocket messages
@Controller
@RequiredArgsConstructor
public class UserController {

    // Injecting UserService dependency using constructor injection ( in order to be able to save and retrieve users from the database)
    private final UserService userService;

    // WebSocket endpoint for adding a user
    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User addUser(
            @Payload User user // Extract the user from the message payload using @Payload
    ) {
        userService.saveUser(user);  // Call UserService to save the user
        return user;  // Return the saved user in the response body
    }

    // WebSocket endpoint for disconnecting a user
    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public User disconnect(@Payload User user) {
        userService.disconnect(user);  // Call UserService to disconnect the user
        return user;  // Return the disconnected user in the response body
    }

    // HTTP endpoint to get all connected users
    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        List<User> connectedUsers = userService.findConnectedUsers();  // Call UserService to get connected users
        return ResponseEntity.ok(connectedUsers);  // Return the list of connected users in the response body
    }
}
