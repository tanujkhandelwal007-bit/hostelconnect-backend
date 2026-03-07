package com.hostelconnect.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.hostelconnect.model.Announcement;

public interface AnnouncementRepository
        extends MongoRepository<Announcement, String> {
}