package com.alura.forohub.controller;


import com.alura.forohub.domain.respuesta.RequestRespuestaDTO;
import com.alura.forohub.domain.respuesta.RespuestaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ResponseBody
@RestController
@RequestMapping("/respuesta")
public class RespuestaController {
    @Autowired
    RespuestaService service;

    @PostMapping
    public ResponseEntity registrarRespuesta(@RequestBody @Valid RequestRespuestaDTO datos){
        var Respuesta = service.registrarRespuesta(datos);
        return (ResponseEntity.ok("Respuesta registrada"));
    }
}
