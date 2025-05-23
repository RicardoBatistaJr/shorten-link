package com.ricardo.link_shorten.service;

import com.ricardo.link_shorten.config.exceptions.ShortenedLinkNotFoundException;
import com.ricardo.link_shorten.mapper.ShortenedLinkMapper;
import com.ricardo.link_shorten.model.dto.ShortenedLinkResponseDto;
import com.ricardo.link_shorten.model.entity.ShortenedLink;
import com.ricardo.link_shorten.model.enums.LinkStatusEnum;
import com.ricardo.link_shorten.repository.LinkRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShortenedLinkService {

    private final LinkRepository linkRepository;
    private final ShortenedLinkMapper shortenedLinkMapper;
    private final String PROTOCOL = "https://";

    @Autowired
    public ShortenedLinkService(LinkRepository linkRepository, ShortenedLinkMapper shortenedLinkMapper) {
        this.linkRepository = linkRepository;
        this.shortenedLinkMapper = shortenedLinkMapper;
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
        Optional<ShortenedLink> shortenedLinkOpt = linkRepository.findByShortCode(shortCode);

        if(shortenedLinkOpt.isEmpty()){
            throw new ShortenedLinkNotFoundException();
        }

        return shortenedLinkOpt.get();
    }

    public ShortenedLink increaseClickCount(ShortenedLink link) throws BadRequestException {
        if (link == null) {
            throw new BadRequestException("Link não pode ser nulo");
        }
        ShortenedLink increasedLink = new ShortenedLink(link.getId(), link.getShortCode(), link.getOriginalUrl(), link.getClicks() + 1, link.getStatus(), link.getLinkAccessList());
        return linkRepository.save(increasedLink);
    }

    public String getOriginalUrl(String shortCode){
        Optional<ShortenedLink> link = linkRepository.findByShortCode(shortCode);

        if(link.isEmpty()){
            throw new ShortenedLinkNotFoundException();
        }
        return link.get().getOriginalUrl();
    }

    public List<ShortenedLinkResponseDto> getAllLinks(){
        List<ShortenedLink> links = linkRepository.findByStatus(LinkStatusEnum.AVAILABLE);
        return links.stream().map(link -> new ShortenedLinkResponseDto(link.getShortCode(), link.getOriginalUrl(), link.getClicks())).toList();
    }
}
