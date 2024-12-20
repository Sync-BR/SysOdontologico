package com.sync.sysodontologico.service;

import com.sync.sysodontologico.controller.Authentication;
import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.ClinicDto;
import com.sync.sysodontologico.dto.PatientsDto;
import com.sync.sysodontologico.repository.ClientRepository;
import com.sync.sysodontologico.repository.PatientsRepository;
import com.sync.sysodontologico.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientsService {
    @Autowired
    private PatientsRepository patientsRepository;
    @Autowired
    private ClientRepository clientRepository;

    public List<PatientsDto> getAllPatients() {

        return patientsRepository.getPatientsById(Authentication.clientAuthentication.getClinic().getId());
    }

    public boolean register(PatientsDto patient) {
        List<PatientsDto> listPatients = new ArrayList<>();
        listPatients.add(patient);
        ClientDto clientDto = Authentication.clientAuthentication;
        System.out.println("Valor do clientDto: " +clientDto);
        ClinicDto clinicDto = clientDto.getClinic();
        clinicDto.setPatients(listPatients);
        clientDto.setClinic(clinicDto);
        ClientDto coppy = clientRepository.save(clientDto);
        System.out.println("Log coppy: " + coppy);
        if(coppy != null) {
            return true;
        }

        return false;
    }
}
