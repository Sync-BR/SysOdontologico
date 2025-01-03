package com.sync.sysodontologico.controller;

import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.dto.ExamDto;
import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.service.ExamService;
import com.sync.sysodontologico.service.UploadService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private UploadService serviceUpload;

    @PostMapping("/dentist/exam/add")
    public String register(ExamDto newExam, MultipartFile mediaFile, RedirectAttributes redirectAttributes, HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        if (newExam != null && mediaFile != null && !mediaFile.isEmpty()) {
            String uploadedFilePath = serviceUpload.uploadExam(mediaFile);
            if (uploadedFilePath != null) {
                newExam.setMediaPatch(uploadedFilePath);
                if (clientAuthentication != null) {
                    newExam.setClinic(clientAuthentication.getClinic());
                } else if (dentistAuthentication != null) {
                    newExam.setClinic(dentistAuthentication.getClinic());
                } else {
                    return "redirect:/";
                }
                if (examService.register(newExam)) {
                    redirectAttributes.addFlashAttribute("messageSucess", "Exame adicionado com sucesso!");
                } else {
                    redirectAttributes.addFlashAttribute("message", "Erro ao registrar o exame.");
                }
            } else {
                redirectAttributes.addFlashAttribute("message", "Erro ao fazer upload do arquivo.");
            }
        } else if (newExam != null) {
            newExam.setMediaPatch(null);
            if (clientAuthentication != null) {
                newExam.setClinic(clientAuthentication.getClinic());
            } else if (dentistAuthentication != null) {
                newExam.setClinic(dentistAuthentication.getClinic());
            } else {
                return "redirect:/";
            }
            if (examService.register(newExam)) {
                redirectAttributes.addFlashAttribute("messageSucess", "Exame adicionado com sucesso!");
            } else {
                redirectAttributes.addFlashAttribute("message", "Erro ao registrar o exame.");
            }

        }


        return "redirect:/user/home";
    }
}
