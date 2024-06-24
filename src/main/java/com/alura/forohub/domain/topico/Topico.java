package com.alura.forohub.domain.topico;

//import com.alura.forohub.domain.curso.Curso;
//import com.alura.forohub.domain.respuesta.Respuesta;
//import com.alura.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String status;
    private String autor;
    private String curso;
    private String respuesta;
//    private Usuario autor;
//    private Curso curso;
//    private List<Respuesta> respuestas;


    public Topico(RequestTopicoDTO datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = datos.status();
        this.autor = datos.autor();
        this.curso = datos.curso();
        this.respuesta = datos.respuesta();
    }
}
