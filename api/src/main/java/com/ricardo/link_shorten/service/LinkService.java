package com.ricardo.link_shorten.service;

import com.ricardo.link_shorten.config.AppProperties;
import com.ricardo.link_shorten.mapper.UserMapper;
import com.ricardo.link_shorten.model.dto.ShortenedLinkResponseDto;
import com.ricardo.link_shorten.model.dto.UserResponseDto;
import com.ricardo.link_shorten.model.entity.ShortenedLink;
import com.ricardo.link_shorten.model.entity.User;
import com.ricardo.link_shorten.model.enums.LinkStatus;
import com.ricardo.link_shorten.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final UserService userService;
    private final AppProperties appProperties;

    @Autowired
    public LinkService(LinkRepository linkRepository, UserService userService, AppProperties appProperties) {
        this.linkRepository = linkRepository;
        this.appProperties = appProperties;
        this.userService = userService;
    }

    public ShortenedLinkResponseDto shortenLink(UUID userId, String url){
        String shortCode = CodeGeneratorService.generateCode();
        User user = userService.getUserEntityById(userId);

        Optional<ShortenedLink> shortenedLink = linkRepository.findByOriginalUrlAndUser(url, user);

        if(shortenedLink.isPresent()){
            return convertToResponse(shortenedLink.get());
        }


        ShortenedLink link = new ShortenedLink(shortCode,url,0, LinkStatus.AVAILABLE,user);
        linkRepository.save(link);

        return convertToResponse(link);
    }

    public ShortenedLinkResponseDto convertToResponse(ShortenedLink link){
        UserResponseDto userDto = UserMapper.toDto(link.getUser());
        String shortenedUrl = appProperties.getBaseUrl() + link.getShortCode();
        return new ShortenedLinkResponseDto(shortenedUrl,link.getOriginalUrl(),0,LinkStatus.AVAILABLE,userDto);
    }
}
