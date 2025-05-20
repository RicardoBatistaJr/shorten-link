package com.ricardo.link_shorten.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {
    private UUID id;
    private String email;
    private String username;
}
