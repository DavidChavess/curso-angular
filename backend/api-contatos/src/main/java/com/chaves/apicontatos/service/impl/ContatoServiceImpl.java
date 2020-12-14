package com.chaves.apicontatos.service.impl;

import com.chaves.apicontatos.dto.ContatoDTO;
import com.chaves.apicontatos.dto.NewContatoDTO;
import com.chaves.apicontatos.entity.Contato;
import com.chaves.apicontatos.exception.ContatoNotFoundException;
import com.chaves.apicontatos.repository.ContatoRepository;
import com.chaves.apicontatos.service.ContatoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContatoServiceImpl implements ContatoService {

    private final ContatoRepository contatoRepository;
    private final ModelMapper modelMapper;

    public ContatoServiceImpl(ContatoRepository contatoRepository, ModelMapper modelMapper){
        this.contatoRepository = contatoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ContatoDTO save(NewContatoDTO contatoDTO) {
        Contato contato = modelMapper.map(contatoDTO, Contato.class);
        contato.setData(LocalDate.now());
        contato = contatoRepository.save(contato);
        return modelMapper.map(contato, ContatoDTO.class);
    }

    @Override
    public List<ContatoDTO> get() {
        return contatoRepository.findAll()
                .stream().map( c -> modelMapper.map(c, ContatoDTO.class) )
                .collect(Collectors.toList());
    }

    @Override
    public ContatoDTO getById(Long cod) {
        Optional<Contato> c = contatoRepository.findById( cod );
        if( c.isPresent() ){
            return modelMapper.map(c.get(), ContatoDTO.class);
        }
        throw new ContatoNotFoundException();
    }

    @Override
    public void deleteById(Long cod) {
        contatoRepository.deleteById(cod);
    }
}
