package com.senai.thymeleaf.services;

import com.senai.thymeleaf.dtos.UsuarioDto;
import com.senai.thymeleaf.entities.UsuarioEntity;
import com.senai.thymeleaf.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioDto realizarLogin(UsuarioDto usuarioDto) {
        Optional<UsuarioEntity> usuarioOP = repository.findByEmailAndSenha(usuarioDto.getEmail(), usuarioDto.getSenha());

        UsuarioDto usuarioDtoRetorno = new UsuarioDto();

        if (usuarioOP.isPresent()) {
            usuarioDtoRetorno = converterEntityParaDto(usuarioOP.get());
            return usuarioDtoRetorno;
        }

        return usuarioDtoRetorno;
    }

    public List<UsuarioDto> obterListaUsuarios() {
        List<UsuarioEntity> listaUsuario = repository.findAll();
        List<UsuarioDto> listaDtos = new ArrayList<>();

        for (UsuarioEntity usuario : listaUsuario) {
            listaDtos.add(converterEntityParaDto(usuario));
        }

        return listaDtos;
    }

    public void usuarioInserir(UsuarioDto usuarioDto) {
        repository.save(converterDtoParaEntity(usuarioDto));
    }

    public UsuarioDto obterUsuarioPorId(Long id) {
        UsuarioDto usuarioDto = new UsuarioDto();

        Optional<UsuarioEntity> usuarioOP = repository.findById(id);

        if (usuarioOP.isPresent()) {
            usuarioDto = converterEntityParaDto(usuarioOP.get());
        }

        return usuarioDto;
    }

    public void atualizarUsuario(UsuarioDto usuarioDto) {
        Optional<UsuarioEntity> usuarioOP = repository.findById(usuarioDto.getId());

        if (usuarioOP.isPresent()) {
            UsuarioEntity usuario = usuarioOP.get();

            usuario.setNome(usuarioDto.getNome());
            usuario.setEmail(usuarioDto.getEmail());

            if (usuarioDto.getSenha() != null && !usuarioDto.getSenha().isEmpty()) {
                usuario.setSenha(usuarioDto.getSenha());
            }

            repository.save(usuario);
        }
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    private UsuarioDto converterEntityParaDto(UsuarioEntity usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        return usuarioDto;
    }

    private UsuarioEntity converterDtoParaEntity(UsuarioDto usuarioDto) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(usuarioDto.getId());
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(usuarioDto.getSenha());
        return usuario;
    }
}