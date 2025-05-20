package com.ricardo.link_shorten.mapper;

import com.ricardo.link_shorten.config.AppProperties;
import com.ricardo.link_shorten.model.dto.UserRequestDto;
import com.ricardo.link_shorten.model.dto.UserResponseDto;
import com.ricardo.link_shorten.model.entity.User;

public class UserMapper {
    public static UserResponseDto toDto(User user){
        return new UserResponseDto(user.getId(), user.getEmail(), user.getUsername());
    };

    public static User requestToUser(UserRequestDto request){
        return new User(request.getEmail(), request.getPassword(), request.getUsername());
    }
}
