package com.ricardo.link_shorten.mapper;

import com.ricardo.link_shorten.config.AppProperties;
import com.ricardo.link_shorten.model.dto.ShortenedLinkResponseDto;
import com.ricardo.link_shorten.model.entity.ShortenedLink;
import com.ricardo.link_shorten.model.enums.LinkStatusEnum;
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
        String shortenedUrl = appProperties.getBaseUrl() + link.getShortCode();
        return new ShortenedLinkResponseDto(link.getShortCode(), shortenedUrl,link.getOriginalUrl(),0);
    }

    public String getShortUrl(ShortenedLink link){
        return appProperties.getBaseUrl() + link.getShortCode();
    }
}
