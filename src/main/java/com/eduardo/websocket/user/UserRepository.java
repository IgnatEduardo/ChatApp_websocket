package com.eduardo.websocket.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository  extends MongoRepository<User, String>{

    // Custom query method to find all users by status
    List<User> findAllByStatus(Status status);
}
