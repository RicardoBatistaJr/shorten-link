package com.ricardo.link_shorten.model.entity;

import com.ricardo.link_shorten.model.enums.LinkStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class ShortenedLink {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String shortUrl;

    @Column(nullable = false)
    private String originalUrl;

    @Column(nullable = false)
    private Integer clicks;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LinkStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
