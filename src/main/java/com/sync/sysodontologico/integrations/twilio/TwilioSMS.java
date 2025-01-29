package com.sync.sysodontologico.integrations.twilio;

import com.sync.sysodontologico.config;
import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.dto.ExamDto;
import com.sync.sysodontologico.dto.PatientsDto;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioSMS {
    public static final String ACCOUNT_SID = "AC6cf904c6e190170a98e9c67729dc0e3f";
    public static final String AUTH_TOKEN = "f7888f749c578387d9393a22e1cb972d";
    private String phoneCompany = "+18124873182";
    @Autowired
    private HttpSession session;

    public void sendSmsMessage(PatientsDto patient) {
        config getConfigDate = new config();
        ClientDto client = new ClientDto();
        DentistDto dentist = new DentistDto();
        if (session.getAttribute("client") != null) {
            client = (ClientDto) session.getAttribute("client");
        } else if (session.getAttribute("dentist") != null) {
            dentist = (DentistDto) session.getAttribute("dentist");
        }
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        String numberFormat = patient.getTelephone();
        if (numberFormat != null && numberFormat.contains("+55")) {
            numberFormat = numberFormat.substring(3); // Remove o prefixo "+55"
        } else if (numberFormat == null) {
            // Trate o caso de número de telefone ser nulo, por exemplo, atribuindo um valor padrão
            numberFormat = ""; // Ou qualquer valor padrão que você queira usar
        }
        if (client != null) {
            try {
                System.out.println("Starting testing client");
                Message preparedMessages = Message.creator(

                                new PhoneNumber("+55" + numberFormat),
                                new PhoneNumber(getPhoneCompany()),
                                getConfigDate.phoneTextPresentation +
                                        patient.getName() + getConfigDate.phoneTextReminder +
                                        client.getClinic().getClinicName() +
                                        getConfigDate.phoneTextDate + " hoje. " +
                                        getConfigDate.phoneTextAttend

                        )
                        .create();
                System.out.println("Enviado : " +preparedMessages.getSid());
                System.out.println("Number send : " +numberFormat);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (dentist != null) {
            try {
                System.out.println("Starting testing dentist");
                Message preparedMessages = Message.creator(

                                new PhoneNumber("+55" + numberFormat),
                                new PhoneNumber(getPhoneCompany()),
                                getConfigDate.phoneTextPresentation +
                                        patient.getName() + getConfigDate.phoneTextReminder +
                                        dentist.getClinic().getClinicName() +
                                        getConfigDate.phoneTextDate + "24/04/1998. " +
                                        getConfigDate.phoneTextAttend

                        )
                        .create();
                System.out.println("Enviado : " +preparedMessages.getSid());
                System.out.println("Number send : " +numberFormat);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String getPhoneCompany() {
        return phoneCompany;
    }

}
