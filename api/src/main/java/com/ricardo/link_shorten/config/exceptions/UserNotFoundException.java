package com.ricardo.link_shorten.config.exceptions;


public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User not found with this parameters");
    }
}
