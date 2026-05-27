package com.senai.thymeleaf.services;

import com.senai.thymeleaf.dtos.LoginDto;
import com.senai.thymeleaf.entities.UsuarioEntity;
import com.senai.thymeleaf.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UsuarioRepository repository;

    public LoginService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Optional<UsuarioEntity> autenticar(LoginDto login) {
        //Buscar no banco através do Repository utilizando o email
        Optional<UsuarioEntity> usuarioOpt = repository.findByEmail(login.getEmail());

        //Se existir valida regra
        if (usuarioOpt.isPresent()) {
            UsuarioEntity usuario = usuarioOpt.get();
            if (usuario.getSenha().equals(login.getSenha())) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty(); //Falha na autenticação
    }
}
