package com.ricardo.link_shorten.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "linkaccess")
public class LinkAccess {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String ipAddress;

    @Column(nullable = false)
    private String userAgent;

    @Column(nullable = false)
    private LocalDateTime accessTime;

    @ManyToOne()
    @JoinColumn(name="short_id", nullable = false)
    private ShortenedLink shortenedLink;

    public LinkAccess(String ipAddress, String userAgent, LocalDateTime accessTime, ShortenedLink shortenedLink) {
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.accessTime = accessTime;
        this.shortenedLink = shortenedLink;
    }
}
