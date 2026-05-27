package com.senai.thymeleaf.controllers;

import com.senai.thymeleaf.dtos.LoginDto;
import com.senai.thymeleaf.entities.UsuarioEntity;
import com.senai.thymeleaf.services.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class HomeController {

    private final LoginService loginService;

    // Injeção de dependência do Service oficial
    public HomeController(LoginService loginService) {
        this.loginService = loginService;
    }

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
    public String fazerLogin(LoginDto loginDto, HttpSession session, Model model) {
        //Aciona serviço passando pelo Dto
        Optional<UsuarioEntity> usuarioAutenticacao = loginService.autenticar(loginDto);

        if (usuarioAutenticacao.isPresent()) {
            //Guarda na sessão e faz o redirect
            session.setAttribute("usuarioLogado", usuarioAutenticacao.get().getEmail());
            return "redirect:/";
        }
        //Se falhar retorna erro
        model.addAttribute("erro", "Email ou Senha inválidos");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
