package com.senai.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("mensagem", "Bem-vindo ao Thymeleaf!");
        return "home";
    }

    @GetMapping("/login")
    public String telaLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String fazerLogin(String email, String senha, Model model) {
        if (email.equals("admin@senai.com") && senha.equals("123")) {
            return "redirect:/";
        }

        model.addAttribute("erro", "E-mail ou senha inválidos");
        return "login";
    }
}
