package com.alura.forohub.domain.topico;

import java.time.LocalDateTime;

public record ResponseTopicoDTO(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        String autor,
        String curso,
        String respuesta

) {

    public ResponseTopicoDTO(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getRespuesta()
        );
    }
}
