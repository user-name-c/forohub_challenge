package com.alura.forohub.domain.usuario;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario(RegistroUsuarioDTO datos) {
        String contrasenaEncriptada = passwordEncoder.encode(datos.contrasena());
        Usuario nuevoUsuario = new Usuario(
                datos.nombre(),
                datos.correoElectronico(),
                contrasenaEncriptada
        );
        return usuarioRepository.save(nuevoUsuario);
    }
}