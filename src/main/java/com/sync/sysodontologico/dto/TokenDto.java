package com.sync.sysodontologico.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "TOKEN")
public class TokenDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
    private boolean isActive;

    public TokenDto() {
    }

    public TokenDto(String token, boolean isActive) {
        this.token = token;
        this.isActive = isActive;
    }
}
