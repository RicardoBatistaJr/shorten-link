package com.ricardo.link_shorten.service;

import com.ricardo.link_shorten.config.AppProperties;
import com.ricardo.link_shorten.config.exceptions.ShortenedLinkNotFoundException;
import com.ricardo.link_shorten.mapper.ShortenedLinkMapper;
import com.ricardo.link_shorten.mapper.UserMapper;
import com.ricardo.link_shorten.model.dto.ShortenedLinkResponseDto;
import com.ricardo.link_shorten.model.dto.UserResponseDto;
import com.ricardo.link_shorten.model.entity.ShortenedLink;
import com.ricardo.link_shorten.model.entity.User;
import com.ricardo.link_shorten.model.enums.LinkStatus;
import com.ricardo.link_shorten.repository.LinkRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ShortenedLinkService {

    private final LinkRepository linkRepository;
    private final UserService userService;
    private final ShortenedLinkMapper shortenedLinkMapper;

    @Autowired
    public ShortenedLinkService(LinkRepository linkRepository, UserService userService, ShortenedLinkMapper shortenedLinkMapper) {
        this.linkRepository = linkRepository;
        this.userService = userService;
        this.shortenedLinkMapper = shortenedLinkMapper;
    }

    public ShortenedLinkResponseDto shortenLink(UUID userId, String url){
        String shortCode = CodeGeneratorService.generateCode();
        User user = userService.getUserEntityById(userId);

        Optional<ShortenedLink> shortenedLink = linkRepository.findByOriginalUrlAndUser(url, user);

        if(shortenedLink.isPresent()){
            return shortenedLinkMapper.toDto(shortenedLink.get());
        }


        ShortenedLink link = new ShortenedLink(shortCode,url,0, LinkStatus.AVAILABLE,user);
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

    public ShortenedLink updateShortenedLink(ShortenedLink link) throws BadRequestException {
        if (link == null) {
            throw new BadRequestException("Link não pode ser nulo");
        }
        return linkRepository.save(link);
    }

    public ShortenedLink increaseClickCount(ShortenedLink link) throws BadRequestException {
        if (link == null) {
            throw new BadRequestException("Link não pode ser nulo");
        }
        ShortenedLink increasedLink = new ShortenedLink(link.getId(), link.getShortCode(), link.getOriginalUrl(), link.getClicks() + 1, link.getStatus(), link.getUser(), link.getLinkAccessList());
        return linkRepository.save(increasedLink);
    }
}
