package com.sync.sysodontologico.service;

import com.sync.sysodontologico.model.ApplicationModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadService {
//    public static String uploadDir = "E:/Java/Projetos SpringBoot/SysOdontologico/src/main/resources/static/src/img/exam";
    public static String uploadDir = "sysondotologicoimg.com/img";

    public String uploadExam(MultipartFile file) {
        String archiveName = null;
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            archiveName = "https://sysondotologicoimg.com.eduar4959.c44.integrator.host/img/" + fileName;

        } catch (IOException e) {
            System.out.println("Erro ao salvar exame: " + e.getMessage());
        }
        return archiveName;
    }

}
