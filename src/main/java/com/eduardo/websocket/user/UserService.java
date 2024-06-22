package com.eduardo.websocket.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    // Injecting UserRepository dependency using constructor injection ( in order to be able to save and retrieve users from the database)
    private final UserRepository userRepository;

    // Method to save a user with ONLINE status
    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);  // Set user status to ONLINE
        userRepository.save(user);  // Save the user to the database
    }

    // Method to disconnect a user by setting their status to OFFLINE
    public void disconnect(User user) {
        // Retrieve the user by nickname from the repository
        var storedUser = userRepository.findById(user.getNickName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Check if user exists
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);  // Set user status to OFFLINE
            userRepository.save(storedUser);  // Save the updated user to the database
        }
    }

    // Method to find all users with ONLINE status
    public List<User> findConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);  // Retrieve users with ONLINE status
    }
}
