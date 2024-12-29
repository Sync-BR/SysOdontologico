package com.sync.sysodontologico.controller;

import com.sync.sysodontologico.dto.ConsultDto;
import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.dto.PatientsDto;
import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.service.ConsultService;
import com.sync.sysodontologico.service.DentistService;
import com.sync.sysodontologico.service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ConsultController {
    @Autowired
    private ConsultService consultService;
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private DentistService dentistService;


    @PostMapping("/dentist/consult/add")
    public String registerConsult(ConsultDto newConsult, @RequestParam("patientsDto.id") Long patientId, @RequestParam("dentist") Long dentistId, RedirectAttributes redirectAttributes) {
        if (AuthenticationModel.clientAuthentication != null || AuthenticationModel.dentistAuthentication != null) {
            if (newConsult != null) {
                PatientsDto datePatient = patientsService.getPatientById(patientId);
                DentistDto dateDentist = dentistService.getDentistById(dentistId);
                newConsult.setPatientName(datePatient.getName());
                newConsult.setPatientCpf(datePatient.getCpf());
                newConsult.setDentistName(dateDentist.getName());
                newConsult.setDentistCpf(dateDentist.getCpf());
                if (AuthenticationModel.clientAuthentication != null) {
                    newConsult.setClinicId(AuthenticationModel.clientAuthentication.getClinic().getId());
                } else if (AuthenticationModel.dentistAuthentication != null) {
                    newConsult.setClinicId(AuthenticationModel.dentistAuthentication.getClinic().getId());
                }
                if (consultService.addConsult(newConsult)) {
                    redirectAttributes.addFlashAttribute("messageSucess", "Consulta marcado com sucesso!");
                } else {
                    redirectAttributes.addFlashAttribute("message", "Falhou ao marcar uma consulta!!");
                }

            } else {
                redirectAttributes.addFlashAttribute("message", "Dados inexistente!");
            }
            return "redirect:/user/home";
        }
        return "redirect:/";
    }
}
