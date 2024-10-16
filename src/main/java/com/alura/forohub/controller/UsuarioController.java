package com.alura.forohub.controller;

import com.alura.forohub.domain.usuario.*;
import com.alura.forohub.domain.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping
    public String helloWorld() {
        return "Sin problemas en la configuracion inicial!";
    }

    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody @Valid RegistroUsuarioDTO datos) {
        Usuario usuarioRegistrado = service.registrarUsuario(datos);
        return ResponseEntity.ok(usuarioRegistrado);
    }
}
