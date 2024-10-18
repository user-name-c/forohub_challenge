package com.alura.forohub.domain.usuario;


import com.alura.forohub.domain.usuario.validaciones.ValidadorDeUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    List<ValidadorDeUsuarios> validaciones;

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario(RegistroUsuarioDTO datos) {

        for (ValidadorDeUsuarios validador : validaciones) {
            validador.validarUsuario(datos.nombre(), datos.correoElectronico());
        }

        String contrasenaEncriptada = passwordEncoder.encode(datos.contrasena());
        Usuario nuevoUsuario = new Usuario(
                datos.nombre(),
                datos.correoElectronico(),
                contrasenaEncriptada
        );
        return usuarioRepository.save(nuevoUsuario);
    }
}