package com.project.shorty.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "url_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UrlData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_url", nullable = false,unique=true)
    private String originalUrl;

    @Column(name = "short_code", unique = true)
    private String encodedUrl;

    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}