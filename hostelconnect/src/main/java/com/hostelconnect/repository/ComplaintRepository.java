package com.hostelconnect.repository;

import com.hostelconnect.model.Complaint;
import com.hostelconnect.model.ComplaintStatus;
import com.hostelconnect.model.Priority;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends MongoRepository<Complaint, String> {

    long countByStatus(ComplaintStatus status);

    // ⭐ Search APIs
    List<Complaint> findByStatus(ComplaintStatus status);

    List<Complaint> findByPriority(Priority priority);

    List<Complaint> findByRoomNumber(String roomNumber);

    List<Complaint> findByStudentEmail(String studentEmail);

}