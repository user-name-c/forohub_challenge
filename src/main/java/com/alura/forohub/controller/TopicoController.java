package com.alura.forohub.controller;

import com.alura.forohub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")

public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @GetMapping
    public String listarTopicos() {
        return "Aca ira el listado de los topicos";
    }


}
