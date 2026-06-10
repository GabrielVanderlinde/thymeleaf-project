package com.senai.thymeleaf.controllers;

import com.senai.thymeleaf.dtos.UsuarioDto;
import com.senai.thymeleaf.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {
    private final UsuarioService service;

    public PageController(UsuarioService service) {
        this.service = service;
    }

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
        List<UsuarioDto> usuarioDtoLista = service.obterListaUsuarios();

        model.addAttribute("usuarios", usuarioDtoLista);

        return "usuariolista";
    }

    @GetMapping("/usuarioinserir")
    public String exibirFormulario(Model model) {
        model.addAttribute("usuario", new UsuarioDto());
        return "usuarioinserir";
    }
}