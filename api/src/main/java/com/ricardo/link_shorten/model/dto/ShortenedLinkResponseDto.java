package com.ricardo.link_shorten.model.dto;

import com.ricardo.link_shorten.model.enums.LinkStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShortenedLinkResponseDto {
    private String shortCode;
    private String shortUrl;
    private String originalUrl;
    private Integer clicks;
}
