package com.sync.sysodontologico.service;

import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.ClinicDto;
import com.sync.sysodontologico.dto.PatientsDto;
import com.sync.sysodontologico.repository.ClientRepository;
import com.sync.sysodontologico.repository.PatientsRepository;
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
    public PatientsDto getPatientById(long id){
        return patientsRepository.findById(id);
    }
    public List<PatientsDto> getAllPatients() {
        if(AuthenticationModel.clientAuthentication != null){

            return patientsRepository.getPatientsById(AuthenticationModel.clientAuthentication.getClinic().getId());
        } else if (AuthenticationModel.dentistAuthentication != null){
            return patientsRepository.getPatientsById(AuthenticationModel.dentistAuthentication.getClinic().getId());

        }
        return null;
    }
    public PatientsDto getPatientById(Long id) {
        return patientsRepository.findById(id).get();
    }
    public boolean patientExists(PatientsDto patient) {
        //Fazer uma condição para verificar o id do paciente para saber se existte na quela clinica!!!

        PatientsDto existsCpf = patientsRepository.findByClinicIdAndCpf((long) AuthenticationModel.clientAuthentication.getClinic().getId(), patient.getCpf());
        if(existsCpf != null) {
            return true;
        }
        PatientsDto existsEmail = patientsRepository.findByClinicIdAndEmail((long) AuthenticationModel.clientAuthentication.getClinic().getId(), patient.getEmail());
        if(existsEmail != null) {
            return true;
        }
        return false;
    }

    public boolean register(PatientsDto patient) {
        patient.setClinic(AuthenticationModel.clientAuthentication.getClinic());
        List<PatientsDto> listPatients = new ArrayList<>();
        listPatients.add(patient);
        ClientDto clientDto = AuthenticationModel.clientAuthentication;
        ClinicDto clinicDto = clientDto.getClinic();
        clinicDto.setPatients(listPatients);
        clientDto.setClinic(clinicDto);
        ClientDto coppy = clientRepository.save(clientDto);
        if(coppy != null) {
            return true;
        }

        return false;
    }
}
