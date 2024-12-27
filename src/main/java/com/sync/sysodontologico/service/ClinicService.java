package com.sync.sysodontologico.service;

import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.ClinicDto;
import com.sync.sysodontologico.repository.ClientRepository;
import com.sync.sysodontologico.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicService {
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private ClientRepository clientRepository;

    public boolean register(ClinicDto newClinic) {
        ClientDto updateClient = AuthenticationModel.clientAuthentication;
        updateClient.setClinic(newClinic);
        ClientDto verificationUpdate = clientRepository.save(updateClient);
        if(verificationUpdate != null) {
            AuthenticationModel.clientAuthentication.setClinic(verificationUpdate.getClinic());
            return true;
        }
        return false;
    }
}
