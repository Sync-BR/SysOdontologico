package com.sync.sysodontologico.controller;

import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.service.DentistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DentistController {
    @Autowired
    private DentistService dentistService;

    @PostMapping("/U2FsdGVkX19mav0FVJsLPN1KpfZuMpGKl0u3mINTdlzr9kppF4BXEZr9")
    public String registerDentist(DentistDto newDentist, RedirectAttributes redirectAttributes, HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        if (clientAuthentication != null) {
            newDentist.setClinic(clientAuthentication.getClinic());
            DentistDto verificationDentist = dentistService.register(newDentist);
            if (verificationDentist != null) {
                redirectAttributes.addFlashAttribute("messageSucess", "Dentista cadastrado com sucesso");
                return "redirect:/user/home";
            }
            redirectAttributes.addFlashAttribute("message", "Cpf ou Email já cadastrado.");
            return "redirect:/dentista/registrar";
        }
        return "redirect:/";
    }


}
