package com.ricardo.link_shorten.controller;

import com.ricardo.link_shorten.model.dto.ShortenedLinkResponseDto;
import com.ricardo.link_shorten.service.ShortenedLinkService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/links")
public class LinkController {

    private final ShortenedLinkService shortenedLinkService;

    @Autowired
    public LinkController(ShortenedLinkService shortenedLinkService){
        this.shortenedLinkService = shortenedLinkService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortenedLinkResponseDto> shortenLink(@RequestParam String url){
        ShortenedLinkResponseDto response = shortenedLinkService.shortenLink(url);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        String originalUrl = shortenedLinkService.getOriginalUrl(shortCode);

        if(originalUrl!=null){
            return ResponseEntity.status(HttpStatus.FOUND).header("Location", originalUrl).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping()
    public ResponseEntity<List<ShortenedLinkResponseDto>> getAllLinks(){
        return ResponseEntity.status(HttpStatus.OK).body(shortenedLinkService.getAllLinks());
    }
}
