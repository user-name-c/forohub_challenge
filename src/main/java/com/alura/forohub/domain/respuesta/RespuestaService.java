package com.alura.forohub.domain.respuesta;

import com.alura.forohub.domain.topico.Topico;
import com.alura.forohub.domain.topico.TopicoRepository;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {
    @Autowired
    RespuestaRepository respuestaRepository;
    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public Respuesta registrarRespuesta(RequestRespuestaDTO respuestaDTO){

        Topico topico = topicoRepository.getReferenceById(respuestaDTO.topicoId());
        Usuario autor = usuarioRepository.getReferenceById(respuestaDTO.usuarioId());
        Respuesta respuesta = new Respuesta(
                respuestaDTO.mensaje(),
                topico,
                autor
        );
        respuestaRepository.save(respuesta);
        return respuesta;
    }
}
