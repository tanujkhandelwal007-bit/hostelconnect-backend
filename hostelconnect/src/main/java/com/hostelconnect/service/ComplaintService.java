package com.hostelconnect.service;

import com.hostelconnect.model.Complaint;
import com.hostelconnect.model.ComplaintStatus;
import com.hostelconnect.model.Priority;
import com.hostelconnect.repository.ComplaintRepository;
import com.hostelconnect.dtoresponse.ComplaintAnalyticsResponse;
import com.hostelconnect.dtorequest.ComplaintRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    // ⭐ CREATE COMPLAINT (Student)
    public Complaint createComplaint(ComplaintRequest request) {

        Complaint complaint = new Complaint();

        complaint.setStudentEmail(request.getStudentEmail());
        complaint.setRoomNumber(request.getRoomNumber());
        complaint.setIssue(request.getIssue());

        complaint.setPriority(request.getPriority());
        complaint.setStatus(ComplaintStatus.OPEN);

        complaint.setCreatedAt(LocalDateTime.now());

        return complaintRepository.save(complaint);
    }

    // ⭐ Complaint Analytics
    public ComplaintAnalyticsResponse getAnalytics() {

        long total = complaintRepository.count();
        long open = complaintRepository.countByStatus(ComplaintStatus.OPEN);
        long inProgress = complaintRepository.countByStatus(ComplaintStatus.IN_PROGRESS);
        long resolved = complaintRepository.countByStatus(ComplaintStatus.RESOLVED);

        return new ComplaintAnalyticsResponse(total, open, inProgress, resolved);
    }

    // ⭐ Smart Complaint Search
    public List<Complaint> searchComplaints(
            ComplaintStatus status,
            Priority priority,
            String roomNumber,
            String studentEmail) {

        if (status != null) {
            return complaintRepository.findByStatus(status);
        }

        if (priority != null) {
            return complaintRepository.findByPriority(priority);
        }

        if (roomNumber != null) {
            return complaintRepository.findByRoomNumber(roomNumber);
        }

        if (studentEmail != null) {
            return complaintRepository.findByStudentEmail(studentEmail);
        }

        return complaintRepository.findAll();
    }
}