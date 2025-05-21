package com.ricardo.link_shorten.controller;

import com.ricardo.link_shorten.model.dto.LinkAccessResponseDto;
import com.ricardo.link_shorten.service.LinkAccessService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/access")
public class LinkAccessController {

    private final LinkAccessService linkAccessService;

    @Autowired
    public LinkAccessController(LinkAccessService linkAccessService) {
        this.linkAccessService = linkAccessService;
    }

    @PostMapping()
    public ResponseEntity<LinkAccessResponseDto> createLinkAccess(@RequestParam String shortCode, HttpServletRequest request) throws BadRequestException {
        LinkAccessResponseDto response = linkAccessService.createLinkAccess(shortCode, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
