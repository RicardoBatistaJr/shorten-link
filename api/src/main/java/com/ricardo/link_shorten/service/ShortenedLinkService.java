package com.ricardo.link_shorten.service;

import com.ricardo.link_shorten.config.exceptions.ShortenedLinkNotFoundException;
import com.ricardo.link_shorten.mapper.ShortenedLinkMapper;
import com.ricardo.link_shorten.model.dto.ShortenedLinkResponseDto;
import com.ricardo.link_shorten.model.entity.LinkAccess;
import com.ricardo.link_shorten.model.entity.ShortenedLink;
import com.ricardo.link_shorten.model.enums.LinkStatusEnum;
import com.ricardo.link_shorten.repository.LinkRepository;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShortenedLinkService {

    private final LinkAccessService linkAccessService;
    private final LinkRepository linkRepository;
    private final ShortenedLinkMapper shortenedLinkMapper;
    private final String PROTOCOL = "https://";

    @Autowired
    public ShortenedLinkService(LinkRepository linkRepository, LinkAccessService linkAccessService, ShortenedLinkMapper shortenedLinkMapper) {
        this.linkRepository = linkRepository;
        this.shortenedLinkMapper = shortenedLinkMapper;
        this.linkAccessService = linkAccessService;
    }

    public ShortenedLinkResponseDto shortenLink(String url){
        String shortCode = CodeGeneratorService.generateCode();

        Optional<ShortenedLink> shortenedLink = linkRepository.findByOriginalUrl(url);

        if(shortenedLink.isPresent()){
            return shortenedLinkMapper.toDto(shortenedLink.get());
        }

        ShortenedLink link = new ShortenedLink(shortCode,PROTOCOL + url,0, LinkStatusEnum.AVAILABLE);
        linkRepository.save(link);

        return shortenedLinkMapper.toDto(link);
    }

    public ShortenedLink getEntityByShortcode(String shortCode){
        return linkRepository.findByShortCodeAndStatus(shortCode, LinkStatusEnum.AVAILABLE).orElseThrow(ShortenedLinkNotFoundException::new);
    }

    public void increaseClickCount(ShortenedLink link) throws BadRequestException {
        if (link == null) {
            throw new BadRequestException("Link não pode ser nulo");
        }
        link.setClicks(link.getClicks()+1);
        linkRepository.save(link);
    }

    public String getOriginalUrl(String shortCode){
        ShortenedLink link = linkRepository.findByShortCodeAndStatus(shortCode, LinkStatusEnum.AVAILABLE).orElseThrow(ShortenedLinkNotFoundException::new);
        return link.getOriginalUrl();
    }

    public String redirectUrl(String shortCode, HttpServletRequest request) throws BadRequestException {
        ShortenedLink link = linkRepository.findByShortCodeAndStatus(shortCode, LinkStatusEnum.AVAILABLE).orElseThrow(ShortenedLinkNotFoundException::new);
        linkAccessService.createLinkAccess(link, request);
        increaseClickCount(link);
        return this.getOriginalUrl(shortCode);
    }

    public List<ShortenedLinkResponseDto> getAllLinks(){
        List<ShortenedLink> links = linkRepository.findByStatus(LinkStatusEnum.AVAILABLE);
        return links.stream().map(link -> new ShortenedLinkResponseDto(link.getShortCode(), link.getOriginalUrl(), link.getClicks())).toList();
    }

    public void cancelLink(String shortCode){
        ShortenedLink link = linkRepository.findByShortCodeAndStatus(shortCode, LinkStatusEnum.AVAILABLE).orElseThrow(ShortenedLinkNotFoundException::new);
        link.setStatus(LinkStatusEnum.CANCELLED);
        linkRepository.save(link);
    }
}