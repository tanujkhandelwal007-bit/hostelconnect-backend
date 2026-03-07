package com.hostelconnect.service;

import com.hostelconnect.model.Announcement;
import com.hostelconnect.repository.AnnouncementRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    // CREATE
    public Announcement createAnnouncement(Announcement announcement) {
        announcement.setCreatedAt(LocalDateTime.now());
        return announcementRepository.save(announcement);
    }

    // READ ALL
    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    // READ BY ID
    public Announcement getAnnouncementById(String id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Announcement not found"));
    }

    // UPDATE
    public Announcement updateAnnouncement(String id, Announcement updated) {
        Announcement existing = getAnnouncementById(id);
        existing.setTitle(updated.getTitle());
        existing.setMessage(updated.getMessage());
        existing.setCreatedBy(updated.getCreatedBy());
        return announcementRepository.save(existing);
    }

    // DELETE
    public void deleteAnnouncement(String id) {
        announcementRepository.deleteById(id);
    }
}