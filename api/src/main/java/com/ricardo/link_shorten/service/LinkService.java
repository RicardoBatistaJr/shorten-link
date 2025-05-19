package com.ricardo.link_shorten.service;

import com.ricardo.link_shorten.model.dto.ShortenedLinkDto;
import com.ricardo.link_shorten.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public void shortenLink(UUID userId, String link){
    }
}
