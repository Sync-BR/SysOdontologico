package com.sync.sysodontologico.service;

import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.UserDto;
import com.sync.sysodontologico.repository.ClientRepository;
import com.sync.sysodontologico.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;

    public boolean getClinicById(Long id) {
        Optional<ClientDto> client = clientRepository.findByClinicId(id);
        if(client.isPresent()) {
            ClientDto clientDto = client.get();
            if(clientDto.getClinic().getId() == 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public Optional<ClientDto> getClientByUsername(String username) {
        UserDto userVerification = userRepository.findByUsername(username);
        if (userVerification != null) {
            return clientRepository.findByUserId(userVerification.getId());
        }
        return null;
    }

    public boolean login(UserDto user) {
        UserDto verificationUser = userRepository.findByUsername(user.getUsername());
        if (verificationUser != null) {
            return verificationUser.getPassword().equals(user.getPassword());
        }
        return false;
    }


    /*
    / Error values
    / value: 0 - Nothing processed
    / value: 200 - OK
    / value: 230 - cpf existing
    / value: 235 - email existing
    / value: 238 - username existing
    */
    public int register(ClientDto newClient) {
        ClientDto verificationClientCpf = clientRepository.findByCpf(newClient.getCpf());
        if (verificationClientCpf != null) {
            return 230;
        }
        ClientDto verificationClientEmail = clientRepository.findByEmail(newClient.getEmail());
        if (verificationClientEmail != null) {
            return 235;
        }
        UserDto verificationUser = userRepository.findByUsername(newClient.getUser().getUsername());
        if (verificationUser != null) {
            return 238;
        }

        ClientDto addNewClient = clientRepository.save(newClient);
        if (addNewClient != null) {
            return 200;
        }
        return 0;
    }

}
