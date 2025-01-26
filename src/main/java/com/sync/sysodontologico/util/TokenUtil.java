package com.sync.sysodontologico.util;

import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class TokenUtil {

    public String generateToken() {
       final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        int alphabetSize = 18;
        StringBuilder generateCharacters = new StringBuilder();
        for (int i = 0; i < alphabetSize; i++) {
            char letraAleatoria = alphabet.charAt(random.nextInt(alphabet.length()));
            generateCharacters.append(letraAleatoria);
        }
        return generateCharacters.toString();

    }

    public static void main(String[] args) {
        TokenUtil tokenUtil = new TokenUtil();
        tokenUtil.generateToken();
    }

}
