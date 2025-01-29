package com.sync.sysodontologico.controller;

import com.sync.sysodontologico.dto.PatientsDto;
import com.sync.sysodontologico.integrations.twilio.TwilioSMS;
import com.sync.sysodontologico.service.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ReminderController {
    @Autowired
    private TwilioSMS serviceSms;
    @Autowired
    private PatientsService servicePatients;

    @PostMapping("/v1/twiliosms/sendSms")
    public ResponseEntity<String> reminder(@RequestBody String patientCpf) {
        try {
            patientCpf = patientCpf.replace("\"", "");
            // Aqui você coloca a lógica de envio de SMS
            PatientsDto patient = servicePatients.getPatientByCpf(patientCpf);
            if(patient != null) {
                serviceSms.sendSmsMessage(patient);
            }
            return ResponseEntity.ok("{\"message\": \"Lembrete enviado com sucesso!\"}");
        } catch (Exception e) {
            e.printStackTrace();
            // Em caso de erro, retornamos um erro 500 com a mensagem
            return ResponseEntity.status(500).body("{\"message\": \"Erro ao enviar lembrete.\"}");
        }
    }

}
