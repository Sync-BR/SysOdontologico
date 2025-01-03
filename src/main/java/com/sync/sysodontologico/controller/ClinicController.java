package com.sync.sysodontologico.controller;

import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.ClinicDto;
import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.dto.PatientsDto;
import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.service.ClinicService;
import com.sync.sysodontologico.service.PatientsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClinicController {
    @Autowired
    private ClinicService clinicService;
    @Autowired
    private PatientsService patientsService;

    @PostMapping("/U2FsdGVkX19Y581RvR1+mhL3rDNLJx5/2earo049mttj80LIjLj+zIs8S77+bTeF")
    public String register(ClinicDto newClinic, RedirectAttributes redirectAttributes, HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        if (clientAuthentication != null ) {
            if (clientAuthentication.getClinic() != null) {
                redirectAttributes.addFlashAttribute("message", "Clinic já registrada!");
                return "redirect:/user/home";
            } else {
                if (clinicService.register(newClinic)) {
                    clientAuthentication.setClinic(newClinic);
                    redirectAttributes.addFlashAttribute("messageSucess", "Clinic criado com sucesso!");
                    return "redirect:/user/home";
                } else {
                    redirectAttributes.addFlashAttribute("message", "Houve um erro ao registrar sua clinica!!");
                }
            }
            return "redirect:/clinic/register";
        }
        return "redirect:/";
    }

    @PostMapping("/WEr09VEBRA1rcRXkk10CyhRs5oNmekpCzPLNC1f1qqnplCVOQT8Cr")
    public String registerPatients(PatientsDto patient, RedirectAttributes redirectAttributes, HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        if(clientAuthentication != null || dentistAuthentication != null) {
            if (!patientsService.patientExists(patient)) {
                if (patientsService.register(patient)) {
                    redirectAttributes.addFlashAttribute("messageSucess", "Paciente criado com sucesso!");
                    return "redirect:/user/home";
                }
            } else {
                redirectAttributes.addFlashAttribute("message", "Cpf ou Email existente!");
            }
            return "redirect:/registrar/pacientes";
        }
        return "redirect:/";
    }


}
