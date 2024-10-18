package com.alura.forohub.domain.usuario.validaciones;


import com.alura.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioYaExiste implements ValidadorDeUsuarios{

    @Autowired
    private UsuarioRepository repositorio;

    @Override
    public void validarUsuario(String nombre, String correoElectronico){
        if (repositorio.existsByNombreAndCorreoElectronico(nombre, correoElectronico)){
            throw new ValidationException("Este Usuario ya existe");
        }
    }
}
