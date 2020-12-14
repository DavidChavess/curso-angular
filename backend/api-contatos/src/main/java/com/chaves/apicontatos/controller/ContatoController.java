package com.chaves.apicontatos.controller;

import com.chaves.apicontatos.dto.ContatoDTO;
import com.chaves.apicontatos.dto.NewContatoDTO;
import com.chaves.apicontatos.entity.Operadora;
import com.chaves.apicontatos.service.ContatoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService service;

    public ContatoController(ContatoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ContatoDTO> get(){
        return service.get();
    }

    @GetMapping("/{id}")
    public ContatoDTO getById( @PathVariable long id){
        return service.getById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        service.deleteById(id);
    }

    @GetMapping("/operadoras")
    public Operadora[] getOp(){
        return Operadora.values();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ContatoDTO save(@RequestBody NewContatoDTO dto){
        return service.save(dto);
    }
}
