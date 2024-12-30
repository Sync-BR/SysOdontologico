package com.sync.sysodontologico.service;

import com.sync.sysodontologico.dto.DentistDto;
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

    public PatientsDto getPatientById(long id) {
        return patientsRepository.findById(id);
    }

    public List<PatientsDto> getAllPatients() {
        if (AuthenticationModel.clientAuthentication != null) {
            return patientsRepository.getPatientsById(AuthenticationModel.clientAuthentication.getClinic().getId());
        } else if (AuthenticationModel.dentistAuthentication != null) {
            return patientsRepository.getPatientsById(AuthenticationModel.dentistAuthentication.getClinic().getId());

        }
        return null;
    }

    public PatientsDto getPatientById(Long id) {
        return patientsRepository.findById(id).get();
    }

    public boolean patientExists(PatientsDto patient) {
        //Fazer uma condição para verificar o id do paciente para saber se existte na quela clinica!!!
        PatientsDto existsCpf = new PatientsDto();
        PatientsDto existsEmail = new PatientsDto();
        if (AuthenticationModel.clientAuthentication != null) {
            existsCpf = patientsRepository.findByClinicIdAndCpf((long) AuthenticationModel.clientAuthentication.getClinic().getId(), patient.getCpf());
            existsEmail = patientsRepository.findByClinicIdAndEmail((long) AuthenticationModel.clientAuthentication.getClinic().getId(), patient.getEmail());
        } else if (AuthenticationModel.dentistAuthentication != null) {
            existsCpf = patientsRepository.findByClinicIdAndCpf((long) AuthenticationModel.dentistAuthentication.getClinic().getId(), patient.getCpf());
            existsEmail = patientsRepository.findByClinicIdAndEmail((long) AuthenticationModel.dentistAuthentication.getClinic().getId(), patient.getEmail());
        }
        if (existsCpf != null) {
            return true;
        }
        if (existsEmail != null) {
            return true;
        }
        return false;
    }

    public boolean register(PatientsDto patient) {
        List<PatientsDto> patients = new ArrayList<>();
        ClientDto clientDate = new ClientDto();
        ClinicDto clinicDate = new ClinicDto();
        if (AuthenticationModel.clientAuthentication != null) {
            patient.setClinic(AuthenticationModel.clientAuthentication.getClinic());
            clientDate = AuthenticationModel.clientAuthentication;
            clientDate.setClinic(AuthenticationModel.clientAuthentication.getClinic());
            clinicDate = clientDate.getClinic();

        } else if (AuthenticationModel.dentistAuthentication != null) {
            patient.setClinic(AuthenticationModel.dentistAuthentication.getClinic());
            clientDate = AuthenticationModel.dentistAuthentication.getClinic().getClient();
            clientDate.setClinic(AuthenticationModel.dentistAuthentication.getClinic());
            clinicDate = clientDate.getClinic();
        } else {
            return false;
        }
        clinicDate.setClient(clientDate);
        patients.add(patient);
        clinicDate.setPatients(patients);
        ClientDto isSuccess = clientRepository.save(clientDate);
        if (isSuccess != null) {
            return true;
        }
        return false;

    }
}
