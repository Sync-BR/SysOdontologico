package com.sync.sysodontologico.service;

import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.ClinicDto;
import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.repository.ClinicRepository;
import com.sync.sysodontologico.repository.DentistRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DentistService {
    @Autowired
    private DentistRepository dentistRepository;
    @Autowired
    private ClinicRepository clinicRepository;
    @Autowired
    private HttpSession session;

    private boolean checkExistence(DentistDto dentist) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto checkCpf = dentistRepository.findByClinicIdAndCpf((long) clientAuthentication.getClinic().getId(), dentist.getCpf());
        if (checkCpf != null) {
            return true;
        }
        DentistDto checkEmail = dentistRepository.findByClinicIdAndEmail((long) clientAuthentication.getClinic().getId(), dentist.getEmail());
        if (checkEmail != null) {
            return true;
        }
        return false;
    }

    public DentistDto getDentistById(Long id) {
        return dentistRepository.findById(id).get();
    }

    public List<DentistDto> getAllDentist() {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        if (clientAuthentication != null) {
            return dentistRepository.getDentistById(clientAuthentication.getClinic().getId());
        } else if (dentistAuthentication != null) {
            List<DentistDto> dentist = new ArrayList<>();
            dentist.add(dentistAuthentication);
            return dentist;
        }
        return null;
    }

    @Transactional
    public DentistDto register(DentistDto dentistDto) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        ClinicDto clinic = clientAuthentication.getClinic();
        if (!clinicRepository.existsById((long) clinic.getId())) {
            clinic = clinicRepository.save(clinic);
            dentistDto.setClinic(clinic);
        }

        if (!checkExistence(dentistDto)) {
            return dentistRepository.save(dentistDto);
        }
        return null;

    }
}
