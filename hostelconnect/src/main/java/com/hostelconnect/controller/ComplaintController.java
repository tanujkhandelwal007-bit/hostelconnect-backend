package com.hostelconnect.controller;

import com.hostelconnect.dtorequest.ComplaintRequest;
import com.hostelconnect.model.Complaint;
import com.hostelconnect.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping
    public Complaint createComplaint(@RequestBody ComplaintRequest request) {
        return complaintService.createComplaint(request);
    }
}