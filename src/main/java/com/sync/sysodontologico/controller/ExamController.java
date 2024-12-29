package com.sync.sysodontologico.controller;

import com.sync.sysodontologico.dto.ExamDto;
import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.service.ExamService;
import com.sync.sysodontologico.service.UploadService;
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
    public String register(ExamDto newExam, MultipartFile mediaFile, RedirectAttributes redirectAttributes) {
        if (newExam != null && mediaFile != null && !mediaFile.isEmpty()) {
            String uploadedFilePath = serviceUpload.uploadExam(mediaFile);
            if (uploadedFilePath != null) {
                newExam.setMediaPatch(uploadedFilePath);
                if (AuthenticationModel.clientAuthentication != null) {
                    newExam.setClinic(AuthenticationModel.clientAuthentication.getClinic());
                } else if (AuthenticationModel.dentistAuthentication != null) {
                    newExam.setClinic(AuthenticationModel.dentistAuthentication.getClinic());
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
        } else {
            redirectAttributes.addFlashAttribute("message", "Dados inválidos ou arquivo não enviado.");
        }


        return "redirect:/user/home";
    }
}
