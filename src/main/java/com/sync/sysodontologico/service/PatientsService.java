package com.sync.sysodontologico.service;

import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.ClinicDto;
import com.sync.sysodontologico.dto.PatientsDto;
import com.sync.sysodontologico.repository.ClientRepository;
import com.sync.sysodontologico.repository.PatientsRepository;
import jakarta.servlet.http.HttpSession;
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
    @Autowired
    private HttpSession session;
    public PatientsDto getPatientById(long id) {
        return patientsRepository.findById(id);
    }

    public List<PatientsDto> getAllPatients() {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        if (clientAuthentication != null) {
            return patientsRepository.getPatientsById(clientAuthentication.getClinic().getId());
        } else if (dentistAuthentication != null) {
            return patientsRepository.getPatientsById(dentistAuthentication.getClinic().getId());

        }
        return null;
    }

    public PatientsDto getPatientById(Long id) {
        return patientsRepository.findById(id).get();
    }

    public boolean patientExists(PatientsDto patient) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        //Fazer uma condição para verificar o id do paciente para saber se existte na quela clinica!!!
        PatientsDto existsCpf = new PatientsDto();
        PatientsDto existsEmail = new PatientsDto();
        if (clientAuthentication != null) {
            existsCpf = patientsRepository.findByClinicIdAndCpf((long) clientAuthentication.getClinic().getId(), patient.getCpf());
            existsEmail = patientsRepository.findByClinicIdAndEmail((long) clientAuthentication.getClinic().getId(), patient.getEmail());
        } else if (dentistAuthentication != null) {
            existsCpf = patientsRepository.findByClinicIdAndCpf((long) dentistAuthentication.getClinic().getId(), patient.getCpf());
            existsEmail = patientsRepository.findByClinicIdAndEmail((long) dentistAuthentication.getClinic().getId(), patient.getEmail());
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
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        List<PatientsDto> patients = new ArrayList<>();
        ClientDto clientDate = new ClientDto();
        ClinicDto clinicDate = new ClinicDto();
        if (clientAuthentication != null) {
            patient.setClinic(clientAuthentication.getClinic());
            clientDate = clientAuthentication;
            clientDate.setClinic(clientAuthentication.getClinic());
            clinicDate = clientDate.getClinic();

        } else if (dentistAuthentication != null) {
            patient.setClinic(dentistAuthentication.getClinic());
            clientDate = dentistAuthentication.getClinic().getClient();
            clientDate.setClinic(dentistAuthentication.getClinic());
            clinicDate = clientDate.getClinic();
        } else {
            return false;
        }
        clinicDate.setClient(clientDate);
        patients.add(patient);
        clinicDate.setPatients(patients);
        ClientDto isSuccess = clientRepository.save(clientDate);

        if (isSuccess != null) {
            if(clientAuthentication != null){
                clientAuthentication.setClinic(isSuccess.getClinic());
            } else if(dentistAuthentication != null){
                dentistAuthentication.setClinic(isSuccess.getClinic());

            }
            return true;
        }
        return false;

    }
}
