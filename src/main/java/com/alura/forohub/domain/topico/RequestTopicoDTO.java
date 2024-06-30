package com.alura.forohub.domain.topico;

import java.time.LocalDateTime;

public record RequestTopicoDTO(
        String titulo,
        String mensaje,
        String status,
        Long usuarioId,
        String curso,
        String respuesta
) {

}
