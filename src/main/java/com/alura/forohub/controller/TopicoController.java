package com.alura.forohub.controller;

import com.alura.forohub.domain.topico.RequestTopicoDTO;
import com.alura.forohub.domain.topico.ResponseTopicoDTO;
import com.alura.forohub.domain.topico.Topico;
import com.alura.forohub.domain.topico.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@ResponseBody
@RestController
@RequestMapping("/topicos")

public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @GetMapping
    public ResponseEntity<Page<ResponseTopicoDTO>>  listarTopicos(@PageableDefault(size = 10, sort = {"titulo"})Pageable paginacion){
        var page = repository.findByActivoTrue(paginacion).map(ResponseTopicoDTO::new);
        return ResponseEntity.ok(page);
    } //falta modificar para que solo mueste topicos activos

    @PostMapping
    public void registrarTopico(@RequestBody @Valid RequestTopicoDTO datos){
        Topico topico = new Topico(datos);
        repository.save(topico);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        if (repository.existsById(id) && repository.findActivoById(id)){
            var topico = repository.getReferenceById(id);
            return ResponseEntity.ok(new ResponseTopicoDTO(topico));
        } return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el tópico solicitado o fue borrado");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        if (repository.existsById(id) && repository.findActivoById(id)){
            //Delete logico
            Topico topico = repository.getReferenceById(id);
            topico.desactivarTopico();
            return ResponseEntity.noContent().build();
        } return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el tópico solicitado o fue borrado");
    }

}
