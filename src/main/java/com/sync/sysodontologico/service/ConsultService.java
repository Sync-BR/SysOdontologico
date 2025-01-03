package com.sync.sysodontologico.service;

import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.ConsultDto;
import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.repository.ConsultRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultService {
    @Autowired
    private ConsultRepository consultRepository;
    @Autowired
    private HttpSession session;

    public List<ConsultDto> getAllConsults() {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        if (clientAuthentication != null) {
            return consultRepository.getConsultByClinicId((long) clientAuthentication.getClinic().getId());
        } else if (dentistAuthentication != null) {
            return consultRepository.getConsultByClinicId((long) dentistAuthentication.getClinic().getId());
        }
        return null ;
    }

    public boolean addConsult(ConsultDto newConsult) {
        ConsultDto verification = consultRepository.save(newConsult);
        if (verification != null) {
            return true;
        }
        return false;
    }
}
