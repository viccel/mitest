package com.mitest.service.impl;

import com.mitest.model.Usuario;
import com.mitest.repository.UsuarioRepository;
import com.mitest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Override
    public List<Usuario> getAll() {
        return repository.findAll();
    }

    @Override
    public Usuario getById(long id) {

        Optional<Usuario> optionalUsuario = repository.findById(id);

        if (optionalUsuario.isPresent()) {
            return optionalUsuario.get();
        }

        return null;
    }

    @Override
    public Usuario create(Usuario usuario) {
        Usuario newUsuario = repository.save(usuario);
        return newUsuario;
    }

    @Override
    public Usuario update(long id, Usuario usuario) {

        Optional<Usuario> optionalUsuario = repository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario updatedUsuario = optionalUsuario.get();

            updatedUsuario.setNombre(usuario.getNombre());
            updatedUsuario.setApellidoPaterno(usuario.getApellidoPaterno());
            updatedUsuario.setFechaNac(usuario.getFechaNac());

            Usuario myUpdatedUsuario = repository.save(updatedUsuario);
            return myUpdatedUsuario;
        }

        return null;
    }

    @Override
    public Usuario deleteById(long id) {

        Optional<Usuario> optionalUsuario = repository.findById(id);

        if (optionalUsuario.isPresent()) {
            repository.deleteById(id);

            Optional<Usuario> fndUsuario = repository.findById(id);

            if (fndUsuario.isPresent()) {
                System.out.println("No se borró");
                return fndUsuario.get();
            } else {
                System.out.println("Borrado.");
            }
        }

        return null;
    }
}
