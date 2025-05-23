package com.ricardo.link_shorten.service;

import com.ricardo.link_shorten.mapper.LinkAccessMapper;
import com.ricardo.link_shorten.model.dto.LinkAccessResponseDto;
import com.ricardo.link_shorten.model.entity.LinkAccess;
import com.ricardo.link_shorten.model.entity.ShortenedLink;
import com.ricardo.link_shorten.repository.LinkAccessRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LinkAccessService {


    private final LinkAccessRepository linkAccessRepository;
    private final LinkAccessMapper linkAccessMapper;

    @Autowired
    public LinkAccessService(LinkAccessRepository linkAccessRepository, LinkAccessMapper linkAccessMapper) {
        this.linkAccessRepository = linkAccessRepository;
        this.linkAccessMapper = linkAccessMapper;
    }

    public LinkAccessResponseDto createLinkAccess(ShortenedLink link, HttpServletRequest request) throws BadRequestException {
        String ip = extractClientIp(request);
        String userAgent = request.getHeader("User-Agent");
        LocalDateTime accessTime = LocalDateTime.now();

        LinkAccess access = new LinkAccess(ip, userAgent, accessTime, link);
        LinkAccess result = linkAccessRepository.save(access);

        return linkAccessMapper.toDto(result);
    }
    private String extractClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
