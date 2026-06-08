package com.senai.thymeleaf.controllers;

import com.senai.thymeleaf.dtos.UsuarioDto;
import com.senai.thymeleaf.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // Processa o formulário de login (POST = enviar os dados)
    @PostMapping("/login")
    public String realizarLogin(String email, String senha,
                                Model model, RedirectAttributes redirectAttributes) {

        System.out.println("email=" + email + " senha=" + senha);

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setEmail(email);
        usuarioDto.setSenha(senha);

        UsuarioDto retorno = service.realizarLogin(usuarioDto);

        if (retorno != null) {
            redirectAttributes.addFlashAttribute("usuario", "Bem-vindo, " + retorno.getNome());
            return "redirect:/home";
        }

        model.addAttribute("erro", "E-mail ou senha inválidos.");
        return "login";
    }
}