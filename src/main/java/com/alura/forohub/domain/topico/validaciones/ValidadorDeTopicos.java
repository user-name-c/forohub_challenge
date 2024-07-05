package com.alura.forohub.domain.topico.validaciones;

import com.alura.forohub.domain.topico.ActualizarTopicoDTO;
import com.alura.forohub.domain.topico.RequestTopicoDTO;

public interface ValidadorDeTopicos {
    public void validarCrearTopico(RequestTopicoDTO datos);

//    public void validarActualizarTopico(ActualizarTopicoDTO datos);
}
