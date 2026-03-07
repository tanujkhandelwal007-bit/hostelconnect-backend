package com.hostelconnect.dtoresponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComplaintAnalyticsResponse {

    private long totalComplaints;
    private long openComplaints;
    private long inProgressComplaints;
    private long resolvedComplaints;

}