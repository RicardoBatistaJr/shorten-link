package com.ricardo.link_shorten.controller;

import com.ricardo.link_shorten.model.dto.ShortenedLinkResponseDto;
import com.ricardo.link_shorten.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/links")
public class LinkController {

    private final LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService){
        this.linkService = linkService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenedLinkResponseDto> shortenLink(@RequestParam String url, @RequestParam UUID userId){
        ShortenedLinkResponseDto response = linkService.shortenLink(userId, url);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
