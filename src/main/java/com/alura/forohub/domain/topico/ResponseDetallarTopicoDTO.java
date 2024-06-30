package com.alura.forohub.domain.topico;

import java.time.LocalDateTime;

public record ResponseDetallarTopicoDTO(
       Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        Long idUsuario,
        String curso,
        String respuesta

) {

    public ResponseDetallarTopicoDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getUsuario().getId(),
                topico.getCurso(),
                topico.getRespuesta()
        );
    }
}
