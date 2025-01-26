package com.sync.sysodontologico.service;

import com.sync.sysodontologico.dto.TokenDto;
import com.sync.sysodontologico.repository.TokenRepository;
import com.sync.sysodontologico.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private TokenUtil tokenUtil;

    public boolean checkToken(String token) {
        TokenDto checkTokenDate = tokenRepository.findByToken(token);
        if (checkTokenDate != null) {
            if (checkTokenDate.isActive() == true) {
                checkTokenDate.setActive(false);
                tokenRepository.save(checkTokenDate);
                return true;
            } else {
                return false;
            }
        } else {

            return false;
        }
    }

    public String registerToken() {
        String token = null;
        do {
            token = tokenUtil.generateToken();
        } while (tokenRepository.findByToken(token) != null);
        TokenDto saveToken = new TokenDto(token, true);
        tokenRepository.save(saveToken);
        return saveToken.getToken();
    }
}
