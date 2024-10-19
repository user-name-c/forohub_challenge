package com.alura.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;

public record RegistroUsuarioDTO(
        @NotNull
        String nombre,
        @NotNull
        @Email
        String correoElectronico,
        @NotBlank
        String contrasena
) {
}
