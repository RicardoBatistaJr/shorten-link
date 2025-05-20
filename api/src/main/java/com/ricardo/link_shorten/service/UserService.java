package com.ricardo.link_shorten.service;

import com.ricardo.link_shorten.config.exceptions.UserNotFoundException;
import com.ricardo.link_shorten.mapper.UserMapper;
import com.ricardo.link_shorten.model.dto.UserRequestDto;
import com.ricardo.link_shorten.model.dto.UserResponseDto;
import com.ricardo.link_shorten.model.entity.User;
import com.ricardo.link_shorten.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserEntityById(UUID id){
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException();
        }

        return user.get();
    }

    public UserResponseDto getUserDtoById(UUID id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }

        return UserMapper.toDto(user.get());
    }

    public UserResponseDto createUser(UserRequestDto request){
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        User newUser = new User(request.getEmail(), encryptedPassword, request.getUsername());
        return UserMapper.toDto(userRepository.save(newUser));
    }
}
