package com.hotel.agency.booking.model.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<RoomRequest> roomRequests;
}
