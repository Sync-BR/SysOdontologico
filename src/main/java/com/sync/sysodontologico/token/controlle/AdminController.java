package com.sync.sysodontologico.token.controlle;

import com.sync.sysodontologico.token.repository.TokenRepository;
import com.sync.sysodontologico.token.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/")
public class AdminController {
    private TokenRepository tokenRepository = new TokenRepository();
    private final TokenService tokenService;

    // Injeção de dependência do TokenService
    @Autowired
    public AdminController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    // Endpoint para o administrador gerar um token
    @GetMapping("admin/generateToken")
    public String generateToken() {
        String token = tokenService.generateToken();
        return "Token gerado: " + token;
    }
}
