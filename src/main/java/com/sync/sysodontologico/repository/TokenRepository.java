package com.sync.sysodontologico.repository;

import com.sync.sysodontologico.dto.TokenDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenDto, Long> {
    TokenDto findByToken(String token);
}
