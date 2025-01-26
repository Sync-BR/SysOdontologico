package com.sync.sysodontologico.controller;

import com.sync.sysodontologico.dto.ClientDto;
import com.sync.sysodontologico.dto.UserDto;
import com.sync.sysodontologico.service.UserService;
import com.sync.sysodontologico.enums.SubscriptionsType;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/U2FsdGVkX1+SVZbYQbDGn8Lli+o6A2wE8SiLvyMX29w=")
    public String login(UserDto user, RedirectAttributes redirectAttributes, HttpSession session) {
        UserDto userAuthentication = userService.login(user);
        if (userAuthentication != null ) {
            if(!userAuthentication.getClient().isActive()){
                redirectAttributes.addFlashAttribute("message", "Acesso expirado");
                return "redirect:/";

            }
            if (userAuthentication.getClient() != null) {
                session.setAttribute("client", userAuthentication.getClient());
            } else if (userAuthentication.getDentist() != null) {
                session.setAttribute("dentist", userAuthentication.getDentist());
            }
            return "redirect:/user/home";
        }
        redirectAttributes.addFlashAttribute("message", "Login ou senha incorretar");
        return "redirect:/";
    }

    @PostMapping("/U2FsdGVkX1+DPaWRyKqYEA4snJJ3m3KtWCI8fKigd5s=")
    public String register(ClientDto client, RedirectAttributes redirectAttributes) {
        SubscriptionsType subscriptions = SubscriptionsType.basic;
        client.setSubscriptionsType(subscriptions);
        client.setActive(true);
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
