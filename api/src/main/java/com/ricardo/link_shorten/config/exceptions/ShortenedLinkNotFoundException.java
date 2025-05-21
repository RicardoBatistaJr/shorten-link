package com.ricardo.link_shorten.config.exceptions;

import com.ricardo.link_shorten.model.entity.ShortenedLink;

public class ShortenedLinkNotFoundException extends RuntimeException {
    public ShortenedLinkNotFoundException(){
        super("Link não encontrado");
    }
}
