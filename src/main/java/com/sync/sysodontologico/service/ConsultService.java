package com.sync.sysodontologico.service;

import com.sync.sysodontologico.dto.ConsultDto;
import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.repository.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultService {
    @Autowired
    private ConsultRepository consultRepository;

    public List<ConsultDto> getAllConsults() {
        if (AuthenticationModel.clientAuthentication != null) {
            return consultRepository.getConsultByClinicId((long) AuthenticationModel.clientAuthentication.getClinic().getId());
        } else if (AuthenticationModel.dentistAuthentication != null) {
            return consultRepository.getConsultByClinicId((long) AuthenticationModel.dentistAuthentication.getClinic().getId());
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
