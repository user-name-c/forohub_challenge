package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import com.alura.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgregarTopicoService {


    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarTopico(RequestTopicoDTO datos){

        if(!usuarioRepository.findById(datos.usuarioId()).isPresent()){
            throw new ValidacionDeIntegridad("este id para el usuario no fue encontrado");
        }

        Usuario usuario = usuarioRepository.getReferenceById(datos.usuarioId());
        Topico topico = new Topico(
                datos.titulo(),
                datos.mensaje(),
                usuario,
                datos.curso()
        );
        topicoRepository.save(topico);
    }


    //    public Topico(RequestTopicoDTO datos) {
//        this.titulo = datos.titulo();
//        this.mensaje = datos.mensaje();
//        this.fechaCreacion = LocalDateTime.now();
//        this.status = datos.status();
//        this.usuario = datos.autor();
//        this.curso = datos.curso();
//        this.respuesta = datos.respuesta();
//        this.activo = true;
//    }

    //    public void actualizarTopico(ActualizarTopicoDTO datos) {
//        this.titulo = datos.titulo();
//        this.mensaje = datos.mensaje();
//        this.status = datos.status();
//        this.usuario = datos.autor();
//        this.curso = datos.curso();
//    }
}
