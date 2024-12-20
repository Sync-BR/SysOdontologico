package com.sync.sysodontologico.controller;

import com.sync.sysodontologico.service.PatientsService;
import com.sync.sysodontologico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/")
    public String home() {
        return "index";
    }


    //Controller de pagina de usuarios
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

    @GetMapping("/user/home")
    public String userHome(Model model) {
        if (Authentication.clientAuthentication != null) {
            if (Authentication.clientAuthentication.getClinic() != null) {
                return "user/index";
            } else {
                return "redirect:/clinic/register";
            }
        }
        return "redirect:/";
    }


    @GetMapping("/clinic/register")
    public String clinicRegister() {
        if (Authentication.clientAuthentication != null) {
            if (Authentication.clientAuthentication.getClinic() != null) {
                return "redirect:/user/home";
            }
        } else {
            return "redirect:/";
        }
        return "user/clinic/registerclinic";
    }
    //Paginação pacientes
    @GetMapping("/registrar/pacientes")
    public String registerPatient(){
        System.out.println(Authentication.clientAuthentication);
        return "user/clinic/patients";
    }

    @GetMapping("/visualizar/pacientes")
    public String visualizarPatient(Model model) {
        model.addAttribute("pacientes", patientsService.getAllPatients());
        return "user/viwpatients";
    }
}
