package com.sync.sysodontologico.model;

import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.DentistDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationModel {
    public static  ClientDto clientAuthentication;
    public static DentistDto dentistAuthentication;

    private ClientDto client;


    public AuthenticationModel() {
    }
}
