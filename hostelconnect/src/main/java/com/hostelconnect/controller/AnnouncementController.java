package com.hostelconnect.controller;

import com.hostelconnect.model.Announcement;
import com.hostelconnect.service.AnnouncementService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
@CrossOrigin
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    // ✅ ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Announcement create(@RequestBody Announcement announcement) {
        return announcementService.createAnnouncement(announcement);
    }

    // ✅ STUDENT + ADMIN
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @GetMapping
    public List<Announcement> getAll() {
        return announcementService.getAllAnnouncements();
    }

    // ✅ STUDENT + ADMIN
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
    @GetMapping("/{id}")
    public Announcement getById(@PathVariable String id) {
        return announcementService.getAnnouncementById(id);
    }

    // ✅ ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Announcement update(@PathVariable String id,
                               @RequestBody Announcement announcement) {
        return announcementService.updateAnnouncement(id, announcement);
    }

    // ✅ ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        announcementService.deleteAnnouncement(id);
    }
}