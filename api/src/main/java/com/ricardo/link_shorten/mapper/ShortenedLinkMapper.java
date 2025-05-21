package com.ricardo.link_shorten.mapper;

import com.ricardo.link_shorten.config.AppProperties;
import com.ricardo.link_shorten.model.dto.ShortenedLinkResponseDto;
import com.ricardo.link_shorten.model.dto.UserResponseDto;
import com.ricardo.link_shorten.model.entity.ShortenedLink;
import com.ricardo.link_shorten.model.enums.LinkStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShortenedLinkMapper {
    private final AppProperties appProperties;

    @Autowired
    public ShortenedLinkMapper(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public ShortenedLinkResponseDto toDto(ShortenedLink link){
        UserResponseDto userDto = UserMapper.toDto(link.getUser());
        String shortenedUrl = appProperties.getBaseUrl() + link.getShortCode();
        return new ShortenedLinkResponseDto(shortenedUrl,link.getOriginalUrl(),0, LinkStatus.AVAILABLE,userDto);
    }

    public String getShortUrl(ShortenedLink link){
        return appProperties.getBaseUrl() + link.getShortCode();
    }
}
