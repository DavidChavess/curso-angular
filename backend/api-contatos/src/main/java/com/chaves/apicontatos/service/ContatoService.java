package com.chaves.apicontatos.service;

import com.chaves.apicontatos.dto.ContatoDTO;
import com.chaves.apicontatos.dto.NewContatoDTO;

import java.util.List;

public interface ContatoService {

    ContatoDTO save(NewContatoDTO contatoDTO);
    List<ContatoDTO> get();
    ContatoDTO getById(Long cod);
    void deleteById(Long cod);

}
