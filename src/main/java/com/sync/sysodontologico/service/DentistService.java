package com.sync.sysodontologico.service;

import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.repository.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DentistService {
    @Autowired
    private DentistRepository dentistRepository;

    private boolean checkExistence(DentistDto dentist) {
        DentistDto checkCpf = dentistRepository.findByClinicIdAndCpf((long) AuthenticationModel.clientAuthentication.getClinic().getId(), dentist.getCpf());
        if (checkCpf != null) {
            return true;
        }
        DentistDto checkEmail = dentistRepository.findByClinicIdAndEmail((long) AuthenticationModel.clientAuthentication.getClinic().getId(), dentist.getEmail());
        if(checkEmail != null) {
            return true;
        }
        return false;
    }
    public DentistDto getDentistById(Long id) {
        return dentistRepository.findById(id).get();
    }
    public List<DentistDto> getAllDentist(){
       return dentistRepository.getDentistById(AuthenticationModel.clientAuthentication.getClinic().getId());
    }
    @Transactional
    public DentistDto register(DentistDto dentistDto) {
        dentistDto.setClinic(AuthenticationModel.clientAuthentication.getClinic());
        if (!checkExistence(dentistDto)) {
            return dentistRepository.save(dentistDto);
        }
        return null;
    }
}
