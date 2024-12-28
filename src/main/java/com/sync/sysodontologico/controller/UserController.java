package com.sync.sysodontologico.controller;

import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.dto.UserDto;
import com.sync.sysodontologico.model.AuthenticationModel;
import com.sync.sysodontologico.service.UserService;
import com.sync.sysodontologico.enums.SubscriptionsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/logout")
    public String logout() {
        AuthenticationModel.clientAuthentication = null;
        return "redirect:/";
    }

    @PostMapping("/U2FsdGVkX1+SVZbYQbDGn8Lli+o6A2wE8SiLvyMX29w=")
    public String login(UserDto user, RedirectAttributes redirectAttributes) {
        UserDto userAuthentication = userService.login(user);
        if (userAuthentication != null) {
            if (userAuthentication.getClient() != null) {
                AuthenticationModel.clientAuthentication = userAuthentication.getClient();

            } else if(userAuthentication.getDentist() != null) {
                AuthenticationModel.dentistAuthentication = userAuthentication.getDentist();

            }
            return "redirect:/user/home";
        }
//        if (userService.login(user)) {
//            if (clientOpt.isPresent()) {
//                Optional<ClientDto> clientOpt = userService.getClientByUsername(user.getUsername());
//                AuthenticationModel.clientAuthentication = clientOpt.get();
//            }
//            return "redirect:/user/index/" + user.getUsername() + "/" + user.getPassword();
//        }
        redirectAttributes.addFlashAttribute("message", "Login ou senha incorretar");
        return "redirect:/";
    }

    @PostMapping("/U2FsdGVkX1+DPaWRyKqYEA4snJJ3m3KtWCI8fKigd5s=")
    public String register(ClientDto client, RedirectAttributes redirectAttributes) {
        SubscriptionsType subscriptions = SubscriptionsType.basic;
        client.setSubscriptionsType(subscriptions);
        switch (userService.register(client)) {
            case 200:
                redirectAttributes.addFlashAttribute("messageSucess", "Cadastrado com sucesso");
                break;
            case 230:
                redirectAttributes.addFlashAttribute("message", "Cpf existente");
                break;
            case 235:
                redirectAttributes.addFlashAttribute("message", "Email existente");
                break;
            case 238:
                redirectAttributes.addFlashAttribute("message", "usuário existente");
                break;
            default:
                redirectAttributes.addFlashAttribute("message", "Erro de processamento");
                break;
        }


        return "redirect:/";
    }
}
