package com.ricardo.link_shorten.mapper;

import com.ricardo.link_shorten.model.dto.LinkAccessResponseDto;
import com.ricardo.link_shorten.model.entity.LinkAccess;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LinkAccessMapper {
    private ShortenedLinkMapper shortenedLinkMapper;

    @Autowired
    public LinkAccessMapper(ShortenedLinkMapper shortenedLinkMapper) {
        this.shortenedLinkMapper = shortenedLinkMapper;
    }

    public LinkAccessResponseDto toDto(LinkAccess access) throws BadRequestException{
        if(access == null){
            throw new BadRequestException();
        }

        String shortUrl = shortenedLinkMapper.getShortUrl(access.getShortenedLink());
        return new LinkAccessResponseDto(access.getId(), access.getIpAddress(), access.getUserAgent(), access.getAccessTime(), shortUrl);
    }
}
