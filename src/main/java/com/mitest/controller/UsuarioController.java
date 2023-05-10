package com.mitest.controller;

import com.mitest.model.Usuario;
import com.mitest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> getAll() {
        List<Usuario> usuarios = service.getAll();

        if (usuarios == null || usuarios.size() == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> getById(@PathVariable(name = "id") final long id) {

        Usuario usuario = service.getById(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Usuario newUsuario = service.create(usuario);

        if (newUsuario == null) {
            return ResponseEntity.accepted().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newUsuario);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> update(@PathVariable(name = "id") final long id,
                                          @RequestBody Usuario usuario){
        Usuario updatedUsuario = service.update(id, usuario);

        if (updatedUsuario == null) {
            return ResponseEntity.accepted().build();
        }

        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Usuario> deleteById(long id) {
        Usuario deletedUsuario = service.deleteById(id);

        if (deletedUsuario == null) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
