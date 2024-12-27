package com.sync.sysodontologico.controller;

import com.sync.sysodontologico.service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PatientsController {
    @Autowired
    private PatientsService servicePatients;

    // Transformar em uma consultar personalizada para quando clicar
    @PostMapping("/consultar/{id}")
    public String getPatientsById(@PathVariable int id){
        System.out.println(servicePatients.getPatientById((long) id));
        return "index";
    }

    public String getHistoryById(@PathVariable int id){

        return "redirect:/";
    }
}
