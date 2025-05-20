package com.ricardo.link_shorten.model.dto;

import com.ricardo.link_shorten.model.entity.User;
import com.ricardo.link_shorten.model.enums.LinkStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShortenedLinkResponseDto {
    private String shortUrl;
    private String originalUrl;
    private Integer clicks;
    private LinkStatus status;
    private UserResponseDto creator;
}
