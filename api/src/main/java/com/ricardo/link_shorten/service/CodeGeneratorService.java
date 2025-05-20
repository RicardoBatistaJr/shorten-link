package com.ricardo.link_shorten.service;

import java.security.SecureRandom;

public class CodeGeneratorService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int DEFAULT_LENGTH = 8;
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateCode(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(secureRandom.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public static String generateCode() {
        return generateCode(DEFAULT_LENGTH);
    }
}
