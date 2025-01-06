package com.sync.sysodontologico.service;

import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.dto.ExamDto;
import com.sync.sysodontologico.dto.HistoryDto;
import com.sync.sysodontologico.repository.ExamRepository;
import com.sync.sysodontologico.repository.HistoryRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService extends HistoryService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private HttpSession session;
    public List<ExamDto> getAllExamsById(long id) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        if (clientAuthentication != null)
            return examRepository.findExamsByPatientAndClinic(id, (long) clientAuthentication.getClinic().getId());
        if (dentistAuthentication != null)
            return examRepository.findExamsByPatientAndClinic(id, (long) dentistAuthentication.getClinic().getId());
        return null;
    }

    public boolean register(ExamDto examDto) {
        ExamDto exam = examRepository.save(examDto);
        if (exam != null) {
            HistoryDto historyLog = new HistoryDto(exam.getId(), exam.getPatients().getId(), exam.getDentist().getId(), exam.getExamType().getDescription(), exam.getClinic().getId());
            historyRepository.save(historyLog);
            return true;
        }
        return false;
    }
}
