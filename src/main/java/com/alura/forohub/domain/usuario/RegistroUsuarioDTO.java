package com.alura.forohub.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record RegistroUsuarioDTO(
        @NotNull
        String nombre,
        @NotNull
        String correoElectronico,
        @NotNull
        String contrasena
) {
}
