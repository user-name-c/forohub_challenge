package com.alura.forohub.domain.topico.validaciones;

import com.alura.forohub.domain.topico.RequestTopicoDTO;
import com.alura.forohub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TopicoDuplicado implements ValidadorDeTopicos{

    @Autowired
    private TopicoRepository repositorio;

    @Override
    public void validarCrearTopico(RequestTopicoDTO datos) {

        if (repositorio.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())){
            throw new IllegalArgumentException("Este topico ya existe");
        }

    }

}
