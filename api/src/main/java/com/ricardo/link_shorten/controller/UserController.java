package com.ricardo.link_shorten.controller;

import com.ricardo.link_shorten.model.dto.UserRequestDto;
import com.ricardo.link_shorten.model.dto.UserResponseDto;
import com.ricardo.link_shorten.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Validated UserRequestDto request){
      UserResponseDto response = userService.createUser(request);
      return ResponseEntity.status(HttpStatus.CREATED).body(response);
    };

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDto> getById(@PathVariable("id") UUID id){
        UserResponseDto response = userService.getUserDtoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    };
}
