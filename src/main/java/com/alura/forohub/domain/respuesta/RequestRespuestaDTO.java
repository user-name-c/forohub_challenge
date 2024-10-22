package com.alura.forohub.domain.respuesta;

import com.alura.forohub.domain.topico.Topico;
import com.alura.forohub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotNull;

public record RequestRespuestaDTO(
        @NotNull
        String mensaje,
        @NotNull
        Long topicoId,
        @NotNull
        Long usuarioId
) {
}
