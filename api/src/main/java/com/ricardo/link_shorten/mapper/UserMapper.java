package com.ricardo.link_shorten.mapper;

import com.ricardo.link_shorten.config.AppProperties;
import com.ricardo.link_shorten.model.dto.UserRequestDto;
import com.ricardo.link_shorten.model.dto.UserResponseDto;
import com.ricardo.link_shorten.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toDto(User user){
        return new UserResponseDto(user.getId(), user.getEmail(), user.getUsername());
    };
}
