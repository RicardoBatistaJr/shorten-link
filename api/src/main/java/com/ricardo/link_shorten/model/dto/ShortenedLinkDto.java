package com.ricardo.link_shorten.model.dto;

import com.ricardo.link_shorten.model.entity.ShortenedLink;
import com.ricardo.link_shorten.model.entity.User;
import com.ricardo.link_shorten.model.enums.LinkStatus;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@AllArgsConstructor
public class ShortenedLinkDto {
    private String shortUrl;
    private String originalUrl;
    private Integer clicks;
    private LinkStatus status;
    private User creator;

    public ShortenedLinkDto (ShortenedLink link){
        this.shortUrl = link.getShortUrl();
        this.originalUrl = link.getOriginalUrl();
        this.clicks = link.getClicks();
        this.status = link.getStatus();
    }
}
