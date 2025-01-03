package com.sync.sysodontologico.controller;


import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.service.*;
import com.sync.sysodontologico.token.repository.TokenRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static com.sync.sysodontologico.model.AuthenticationModel.clientAuthentication;
import static com.sync.sysodontologico.model.AuthenticationModel.dentistAuthentication;

@Controller
public class HomeController {

    @Autowired
    private PatientsService patientsService;
    @Autowired
    private DentistService dentistService;
    @Autowired
    private ConsultService consultService;
    @Autowired
    private ExamService examService;
    @Autowired
    private HistoryService historyService;


    //Controller e paginas inicias
    @GetMapping("/")
    public String home(HttpSession session) {
        session.invalidate();
        return "index";
    }


    //Controller de registro
    @GetMapping("/user/register")
    public String userRegister() {
        return "user/register";

    }

    // Controller de pagina de usuários
    @GetMapping("/user/home")
    public String userHome(Model model, HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        if (clientAuthentication != null) {
            if (clientAuthentication.getClinic() != null) {
                model.addAttribute("clientAuthentication", clientAuthentication);
                return "user/index";
            } else {
                return "redirect:/clinic/register";
            }
        } else if (dentistAuthentication != null) {
            if (dentistAuthentication.getClinic() != null) {
                return "user/index";
            }
        }
        return "redirect:/";
    }


    @GetMapping("/clinic/register")
    public String clinicRegister(HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        if (clientAuthentication != null || dentistAuthentication != null) {
            if (clientAuthentication.getClinic() != null || dentistAuthentication != null) {
                return "redirect:/user/home";
            }
        } else {
            return "redirect:/";
        }
        return "user/clinic/registerclinic";
    }

    //Paginação pacientes
    @GetMapping("/registrar/pacientes")
    public String registerPatient(HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        if (clientAuthentication != null || dentistAuthentication != null) {
            return "user/clinic/patients";
        }
        return "redirect:/";
    }

    @GetMapping("/visualizar/pacientes")
    public String viewerPatient(Model model, HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        if (clientAuthentication != null || dentistAuthentication != null) {
            model.addAttribute("pacientes", patientsService.getAllPatients());
            return "user/patients/viwpatients";
        }
        return "redirect:/";
    }


    // Pagina para controller de dentista
    @GetMapping("/dentista/registrar")
    public String registerDentist(HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        if (clientAuthentication != null) {
            return "user/dentist/register";
        }
        return "redirect:/";

    }

    @GetMapping("/visualizar/dentista")
    public String viwerDentista(Model model, HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        if (clientAuthentication != null) {
            model.addAttribute("dentist", dentistService.getAllDentist());
            return "user/dentist/viwdentist";
        }
        return "redirect:/";

    }

    @GetMapping("/hisotory/dentist/{idDentist}")
    public String viwerHistoryDentista(@PathVariable int idDentist, Model model, HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        if (clientAuthentication != null) {
            model.addAttribute("dentist", historyService.getHistoryByDentis(idDentist));
            return "user/history/dentist";
        }
        return "redirect:/";
    }

    //Controle de exames
    @GetMapping("/patient/exam/add/{idPatient}")
    public String addPatientExam(@PathVariable int idPatient, Model model, HttpSession session) {
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        if (clientAuthentication != null || dentistAuthentication != null) {
            model.addAttribute("clientAuthentication", clientAuthentication);
            model.addAttribute("dentistAuthentication", dentistAuthentication);
            model.addAttribute("patients", patientsService.getPatientById(idPatient));
            model.addAttribute("dentists", dentistService.getAllDentist());
            return "user/exam/addexam";
        }
        return "redirect:/";
    }

    @GetMapping("/patient/viwer/exam/{idPatient}")
    public String getExamPatient(@PathVariable int idPatient, Model model, HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        if (clientAuthentication != null || dentistAuthentication != null) {
            model.addAttribute("patients", examService.getAllExamsById(idPatient));
            return "user/exam/viwexam";
        }
        return "redirect:/";
    }

    //Controle de consultas
    @GetMapping("/patient/consult/add/{idPatient}")
    public String addConsult(Model model, @PathVariable int idPatient, HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        if (clientAuthentication != null || dentistAuthentication != null) {
            model.addAttribute("patients", patientsService.getPatientById(idPatient));
            model.addAttribute("dentists", dentistService.getAllDentist());
            model.addAttribute("clientAuthentication", clientAuthentication);
            model.addAttribute("dentistAuthentication", AuthenticationModel.dentistAuthentication);
            return "user/consult/addconsult";
        }
        return "redirect:/";
    }

    @GetMapping("/patient/consult/viw")
    public String getAllConsult(Model model, HttpSession session) {
        ClientDto clientAuthentication = (ClientDto) session.getAttribute("client");
        DentistDto dentistAuthentication = (DentistDto) session.getAttribute("dentist");
        if (clientAuthentication != null || dentistAuthentication != null) {
            model.addAttribute("consults", consultService.getAllConsults());
            return "user/consult/viwconsults";
        }
        return "redirect:/";

    }

}
