package com.hotel.agency.booking.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;

@EntityListeners(AuditingEntityListener.class)
public class AuditEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;
}
