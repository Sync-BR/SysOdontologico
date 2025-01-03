package com.sync.sysodontologico.token.service;

import com.sync.sysodontologico.token.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    // Injeção de dependência do TokenRepository
    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    // Método para gerar o token único e armazená-lo
    public String generateToken() {
        String token = UUID.randomUUID().toString(); // Gera um token único
        tokenRepository.saveToken(token); // Armazena o token
        return token;
    }
}
