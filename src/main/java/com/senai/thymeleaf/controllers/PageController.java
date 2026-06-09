package com.senai.thymeleaf.controllers;

import com.senai.thymeleaf.dtos.UsuarioDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @GetMapping("/")
    public String getIndex() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/usuariolista")
    public String getUsuarioLista(Model model) {
        List<UsuarioDto> usuarioDtoLista = new ArrayList<>();

        UsuarioDto usuario1 = new UsuarioDto();

        usuario1.setId(1L);
        usuario1.setNome("Paulo");
        usuario1.setSenha("paulo");
        usuario1.setEmail("paulo@gmail.com");

        UsuarioDto usuario2 = new UsuarioDto();

        usuario2.setId(2L);
        usuario2.setNome("Bento");
        usuario2.setSenha("bento");
        usuario2.setEmail("bento@gmail.com");

        usuarioDtoLista.add(usuario1);
        usuarioDtoLista.add(usuario2);

        model.addAttribute("usuarios", usuarioDtoLista);

        return "usuariolista";
    }
}