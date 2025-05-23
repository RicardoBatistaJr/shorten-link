package com.ricardo.link_shorten.model.entity;

import com.ricardo.link_shorten.model.enums.LinkStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "links")
public class ShortenedLink {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String shortCode;

    @Column(nullable = false)
    private String originalUrl;

    @Column(nullable = false)
    private Integer clicks;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LinkStatusEnum status;

    @OneToMany(mappedBy = "shortenedLink", cascade = CascadeType.ALL, orphanRemoval = true)
    List<LinkAccess> linkAccessList;

    public ShortenedLink(String shortCode, String originalUrl, Integer clicks, LinkStatusEnum status) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.clicks = clicks;
        this.status = status;
    }
}
