package com.hostelconnect.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String name;

    private String email;   // 👈 THIS MUST EXIST

    private String password;

    private Role role;

    private String roomNumber;
}