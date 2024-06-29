package com.alura.forohub.domain.topico;

import java.time.LocalDateTime;

public record ResponseDetallarTopicoDTO(
       Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        String autor,
        String curso,
        String respuesta

) {

    public ResponseDetallarTopicoDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getRespuesta()
        );
    }
}
