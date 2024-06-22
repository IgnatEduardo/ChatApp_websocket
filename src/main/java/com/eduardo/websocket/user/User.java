package com.eduardo.websocket.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document// Marks this class as a MongoDB document
public class User {
    @Id
    private String nickName;
    private String fullName;
    private Status status;
}
