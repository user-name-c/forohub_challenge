package com.alura.forohub.domain.usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutenticacionUsuarioDTO(
        @NotNull
        @Email
        String email,

        @NotBlank
        String contrasena
) {
}
