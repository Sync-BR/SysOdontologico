package com.sync.sysodontologico.controller;

import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private DentistService dentistService;
    @Autowired
    private ConsultService consultService;
    @Autowired
    private ExamService examService;

    //Controller e paginas inicias
    @GetMapping("/")
    public String home() {
        return "index";
    }


    //Controller de pagina de registro & login
    @GetMapping("/user/index/{username}/{password}")
    public String userLogin(@PathVariable String username, @PathVariable String password) {
        if (username != null && password != null) {
            return "redirect:/user/home";
        }
        return "redirect:/";
    }

    @GetMapping("/user/register")
    public String userRegister() {
        return "user/register";
    }

    // Controller de pagina de usuários
    @GetMapping("/user/home")
    public String userHome(Model model) {
        if (AuthenticationModel.clientAuthentication != null) {
            if (AuthenticationModel.clientAuthentication.getClinic() != null) {
                model.addAttribute("clientAuthentication", AuthenticationModel.clientAuthentication);
                return "user/index";
            } else {
                return "redirect:/clinic/register";
            }
        } else if (AuthenticationModel.dentistAuthentication != null) {
            if (AuthenticationModel.dentistAuthentication.getClinic() != null) {
                return "user/index";
            }
        }
        return "redirect:/";
    }


    @GetMapping("/clinic/register")
    public String clinicRegister() {
        if (AuthenticationModel.clientAuthentication != null) {
            if (AuthenticationModel.clientAuthentication.getClinic() != null) {
                return "redirect:/user/home";
            }
        } else {
            return "redirect:/";
        }
        return "user/clinic/registerclinic";
    }

    //Paginação pacientes
    @GetMapping("/registrar/pacientes")
    public String registerPatient() {
        if (AuthenticationModel.clientAuthentication != null) {
            return "user/clinic/patients";
        } else if (AuthenticationModel.dentistAuthentication != null) {
            return "user/clinic/patients";
        }
        return "redirect:/";
    }

    @GetMapping("/visualizar/pacientes")
    public String visualizarPatient(Model model) {
        if (AuthenticationModel.clientAuthentication != null) {
            model.addAttribute("pacientes", patientsService.getAllPatients());
            return "user/patients/viwpatients";
        } else if (AuthenticationModel.dentistAuthentication != null) {
            model.addAttribute("pacientes", patientsService.getAllPatients());
            return "user/patients/viwpatients";

        }
        return "redirect:/";
    }

    // Pagina para controller de dentista
    @GetMapping("/dentista/registrar")
    public String registarDentista() {
        if (AuthenticationModel.clientAuthentication != null) {
            return "user/dentist/register";
        }
        return "redirect:/";

    }

    @GetMapping("/visualizar/dentista")
    public String visualizarDentista(Model model) {
        if (AuthenticationModel.clientAuthentication != null) {
            model.addAttribute("dentist", dentistService.getAllDentist());
            return "user/dentist/viwdentist";
        }
        return "redirect:/";

    }

    //Controle de exames
    @GetMapping("/patient/exam/add/{idPatient}")
    public String addPatientExam(@PathVariable int idPatient, Model model) {
        if (AuthenticationModel.clientAuthentication != null || AuthenticationModel.dentistAuthentication != null) {
            model.addAttribute("patients", patientsService.getPatientById(idPatient));
            model.addAttribute("dentists", dentistService.getAllDentist());
            return "user/exam/addexam";
        }
        return "redirect:/";
    }

    @GetMapping("/patient/viwer/exam/{idPatient}")
    public String getExamPatient(@PathVariable int idPatient, Model model) {
        if (AuthenticationModel.clientAuthentication != null || AuthenticationModel.dentistAuthentication != null) {
            model.addAttribute("patients", examService.getAllExamsById(idPatient));
            return "user/exam/viwexam";
        }
        return "redirect:/";
    }

    //Controle de consultas
    @GetMapping("/patient/consult/add/{idPatient}")
    public String addConsult(Model model, @PathVariable int idPatient) {
        if (AuthenticationModel.clientAuthentication != null) {
            model.addAttribute("patients", patientsService.getPatientById(idPatient));
            model.addAttribute("dentists", dentistService.getAllDentist());
            return "user/consult/addconsult";
        } else if (AuthenticationModel.dentistAuthentication != null) {
            System.out.println("Dados do dentista: " + AuthenticationModel.dentistAuthentication);
            model.addAttribute("patients", patientsService.getPatientById(idPatient));
            model.addAttribute("dentists", dentistService.getAllDentist());
            return "user/consult/addconsult";

        }
        return "redirect:/";
    }

    @GetMapping("/patient/consult/viw")
    public String getAllConsult(Model model) {
        if (AuthenticationModel.clientAuthentication != null || AuthenticationModel.dentistAuthentication != null) {
            model.addAttribute("consults", consultService.getAllConsults());
            return "user/consult/viwconsults";
        }
        return "redirect:/";

    }

}
