package com.hostelconnect.dtorequest;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

import com.hostelconnect.model.Priority;

@Data
public class ComplaintRequest {

    @Email(message = "Invalid email format")
    @NotBlank(message = "Student email is required")
    private String studentEmail;

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @NotBlank(message = "Issue description is required")
    private String issue;

    // ⭐ ADD THIS
    private Priority priority;

}