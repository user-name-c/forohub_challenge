package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.curso.CursoRepository;
import com.alura.forohub.domain.topico.validaciones.ValidadorDeTopicos;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import com.alura.forohub.infra.errores.ValidacionDeIntegridad;
import com.alura.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TopicoService {


    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TokenService tokenService;

    @Autowired
    List<ValidadorDeTopicos> validaciones;

    public Page<ResponseTopicoDTO> listarTopicos(Pageable paginacion) {
        return topicoRepository.findByActivoTrue(paginacion).map(ResponseTopicoDTO::new);
    }

    public Topico registrarTopico(RequestTopicoDTO datos){

        if(!usuarioRepository.findById(datos.usuarioId()).isPresent()){
            throw new ValidacionDeIntegridad("este id para el usuario no fue encontrado");
        }
        if(!cursoRepository.existsByNombre(datos.curso())){
            throw new ValidacionDeIntegridad("este curso no fue encontrado");
        }

        for (ValidadorDeTopicos validador : validaciones) {
            validador.validarTopico(datos.titulo(), datos.mensaje());
        }

        Curso curso = cursoRepository.findByNombre(datos.curso());
        Usuario usuario = usuarioRepository.getReferenceById(datos.usuarioId());
        Topico topico = new Topico(
                datos.titulo(),
                datos.mensaje(),
                usuario,
                curso
        );
        topicoRepository.save(topico);

        return topico;
    }

    public Topico actualizarTopico(@PathVariable Long id, @RequestBody @Valid ActualizarTopicoDTO datos, String token){

        // Obtener el ID del usuario desde el token
        Long usuarioId = tokenService.obtenerIdUsuario(token);

        Topico topico = topicoRepository.getReferenceById(id);
        if (!topico.getUsuario().getId().equals(usuarioId)) {
            throw new SecurityException("No tienes permiso para modificar este tópico.");
        }
        if(!cursoRepository.existsByNombre(datos.curso())){
            throw new ValidacionDeIntegridad("este curso no fue encontrado");
        }
        for (ValidadorDeTopicos validador : validaciones) {
            validador.validarTopico(datos.titulo(), datos.mensaje());
        }
        Curso curso = cursoRepository.findByNombre(datos.curso());
        topico.actualizarTopico(
                datos.titulo(),
                datos.mensaje(),
                datos.status(),
                curso
        );
        return topico;
    }

    public ResponseEntity<Object> detallarTopico(Long id) {
        if (topicoRepository.existsById(id) && topicoRepository.findActivoById(id)) {
            var topico = topicoRepository.getReferenceById(id);
            return ResponseEntity.ok(new ResponseDetallarTopicoDTO(topico));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el tópico solicitado o fue borrado");
    }

    public ResponseEntity<Object> eliminarTopico(Long id) {
        if (topicoRepository.existsById(id) && topicoRepository.findActivoById(id)) {
            Topico topico = topicoRepository.getReferenceById(id);
            topico.desactivarTopico();
            topicoRepository.save(topico);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el tópico solicitado o fue borrado");
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
