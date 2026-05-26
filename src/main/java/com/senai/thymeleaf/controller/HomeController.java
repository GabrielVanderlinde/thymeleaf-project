package com.senai.thymeleaf.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio(HttpSession session, Model model) {
        model.addAttribute("mensagem", "Bem-vindo ao Thymeleaf!");
        String usuario = (String) session.getAttribute("usuarioLogado");
        if (usuario == null) {
            // Se não estiver logado, redireciona para a tela de login
            return "redirect:/login";
        }

        model.addAttribute("mensagem", "Bem-vindo ao Thymeleaf!");
        model.addAttribute("usuario", usuario);
        return "home";
    }

    @GetMapping("/login")
    public String telaLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String fazerLogin(String email, String senha, HttpSession session, Model model) {
        if (email.equals("admin@senai.com") && senha.equals("123")) {
            session.setAttribute("usuarioLogado", email);
            return "redirect:/";
        }

        model.addAttribute("erro", "E-mail ou senha inválidos");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
