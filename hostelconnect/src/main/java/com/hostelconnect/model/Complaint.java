package com.hostelconnect.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "complaints")
public class Complaint {

    @Id
    private String id;

    private String studentEmail;

    private String roomNumber;

    private String issue;

    private ComplaintStatus status;

    private Priority priority;

    private LocalDateTime createdAt;

}