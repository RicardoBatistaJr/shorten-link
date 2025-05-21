package com.ricardo.link_shorten.model.dto;

import com.ricardo.link_shorten.model.entity.ShortenedLink;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class LinkAccessResponseDto {
    private UUID id;
    private String ipAddress;
    private String userAgent;
    private LocalDateTime accessTime;
    private String shortUrl;
}
