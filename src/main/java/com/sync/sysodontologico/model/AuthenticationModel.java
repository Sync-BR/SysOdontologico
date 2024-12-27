package com.sync.sysodontologico.model;

import com.sync.sysodontologico.dto.ClientDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationModel {
    public static ClientDto clientAuthentication;

    public AuthenticationModel() {
    }
}
