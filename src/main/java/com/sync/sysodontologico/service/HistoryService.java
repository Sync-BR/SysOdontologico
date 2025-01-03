package com.sync.sysodontologico.service;

import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.dto.HistoryDto;
import com.sync.sysodontologico.dto.PatientsDto;
import com.sync.sysodontologico.repository.ClientRepository;
import com.sync.sysodontologico.repository.DentistRepository;
import com.sync.sysodontologico.repository.HistoryRepository;
import com.sync.sysodontologico.repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DentistRepository dentistRepository;
    @Autowired
    private PatientsRepository patientsRepository;

    public List<PatientsDto> getHistoryByDentis(int dentistId) {
        DentistDto dentistDto = dentistRepository.findById((long) dentistId).get();
        List<HistoryDto> histories = historyRepository.findByDoctorId(dentistDto.getId());
        List<PatientsDto> patientData = new ArrayList<>();
        for (HistoryDto historyDto : histories) {
            List<PatientsDto> patients = patientsRepository.findByClinicIdAndPatientID((long) historyDto.getIdClinic(), historyDto.getIdPatient());
            patientData.addAll(patients);
        }
        return patientData;
    }

}
