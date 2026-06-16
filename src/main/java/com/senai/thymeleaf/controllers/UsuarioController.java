package com.senai.thymeleaf.controllers;

import com.senai.thymeleaf.dtos.UsuarioDto;
import com.senai.thymeleaf.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String realizarLogin(String email, String senha,
                                Model model, RedirectAttributes redirectAttributes) {

        System.out.println("email=" + email + " senha=" + senha);

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setEmail(email);
        usuarioDto.setSenha(senha);

        UsuarioDto usuarioDtoRetorno = service.realizarLogin(usuarioDto);

        if (usuarioDtoRetorno.getNome() != null) {
            redirectAttributes.addFlashAttribute("usuario", "Bem-vindo, " + usuarioDtoRetorno.getNome());
            return "redirect:/home";
        }

        model.addAttribute("erro", "E-mail ou senha invalidos.");
        return "login";
    }

    @PostMapping("/usuarioinserir")
    public String inserirUsuario(
            @Valid @ModelAttribute("usuario") UsuarioDto usuarioDto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "usuarioinserir";
        }

        service.usuarioInserir(usuarioDto);
        redirectAttributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        return "redirect:/usuariolista";
    }
}