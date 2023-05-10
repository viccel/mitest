package com.mitest.service;

import com.mitest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioService {
    List<Usuario> getAll();
    Usuario getById(long id);
    Usuario create(Usuario usuario);
    Usuario update(long id, Usuario usuario);
    Usuario deleteById(long id);
}
