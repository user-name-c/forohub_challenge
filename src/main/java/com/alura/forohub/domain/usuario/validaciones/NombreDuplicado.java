package com.alura.forohub.domain.usuario.validaciones;

import com.alura.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NombreDuplicado implements ValidadorDeUsuarios{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void validarUsuario(String nombre, String correoElectronico){
        if (repository.existsByNombre(nombre)){
            throw new ValidationException("Ya hay un usuario con ese nombre.");
        }
    }
}
