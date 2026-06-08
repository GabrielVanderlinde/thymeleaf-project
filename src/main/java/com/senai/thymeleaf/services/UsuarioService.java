package com.senai.thymeleaf.services;

import com.senai.thymeleaf.dtos.UsuarioDto;
import com.senai.thymeleaf.entities.UsuarioEntity;
import com.senai.thymeleaf.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Camada de regras de negócio — fica entre o Controller e o Repositório
@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    // Spring injeta o repositório automaticamente (injeção de dependência)
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioDto realizarLogin(UsuarioDto usuarioDto) {
        // Busca no banco um usuário com esse email E senha exatos
        // Optional = pode vir um usuário, ou pode não vir nada
        Optional<UsuarioEntity> usuarioOpt = repository.findByEmailAndSenha(usuarioDto.getEmail(), usuarioDto.getSenha());

        // Se encontrou → converte para DTO e retorna (login ok)
        // Se não encontrou → retorna null (login falhou)
        if (usuarioOpt.isPresent()) {
            return converterEntityParaDto(usuarioOpt.get());
        }

        return null;
    }

    // Traduz os dados do banco (Entity) para o formato que o sistema usa (DTO)
    private UsuarioDto converterEntityParaDto(UsuarioEntity usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        return usuarioDto;
    }

    // Traduz os dados do formulário (DTO) para o formato do banco (Entity)
    private UsuarioEntity converterDtoParaEntity(UsuarioDto usuarioDto) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(usuarioDto.getSenha());
        return usuario;
    }
}
