package com.sync.sysodontologico.service;

import com.sync.sysodontologico.dto.ExamDto;
import com.sync.sysodontologico.dto.HistoryDto;
import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.repository.ExamRepository;
import com.sync.sysodontologico.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService extends HistoryService{
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private HistoryRepository historyRepository;

    public List<ExamDto> getAllExamsById(long id){
        return examRepository.findExamsByPatientAndClinic(id, (long)AuthenticationModel.clientAuthentication.getClinic().getId());
    }

    public boolean register(ExamDto examDto) {
        ExamDto exam = examRepository.save(examDto);
        if(exam != null) {
            HistoryDto historyLog = new HistoryDto(exam.getId(), exam.getPatients().getId(), exam.getDentist().getId(), exam.getExamType().getDescription(), exam.getClinic().getId());
            historyRepository.save(historyLog);
            return true;
        }
        return false;
    }
}
