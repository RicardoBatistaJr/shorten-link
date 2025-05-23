package com.ricardo.link_shorten.repository;

import com.ricardo.link_shorten.model.entity.ShortenedLink;
import com.ricardo.link_shorten.model.enums.LinkStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LinkRepository extends JpaRepository<ShortenedLink, UUID> {
    Optional<ShortenedLink> findByShortCode(String shortCode);
    Optional<ShortenedLink> findByOriginalUrl(String url);
    List<ShortenedLink> findByStatus(LinkStatusEnum status);
}
