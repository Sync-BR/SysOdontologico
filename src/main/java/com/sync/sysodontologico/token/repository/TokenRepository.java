package com.sync.sysodontologico.token.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class TokenRepository {

    // Armazena os tokens com o status de uso (false = não usado, true = usado)
    private Map<String, Boolean> tokenStorage = new HashMap<>();

    // Armazena o token com o status de não usado (false)
    public void saveToken(String token) {
        tokenStorage.put(token, false); // false indica que o token não foi usado
    }

    // Verifica se o token é válido e não foi usado
    public boolean isValidToken(String token) {
        return tokenStorage.containsKey(token) && !tokenStorage.get(token);
    }

    // Marca o token como usado
    public void useToken(String token) {
        if (tokenStorage.containsKey(token)) {
            tokenStorage.put(token, true); // Marca como usado (true)
        }
    }
}
