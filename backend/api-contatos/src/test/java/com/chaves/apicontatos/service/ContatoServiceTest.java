package com.chaves.apicontatos.service;

import com.chaves.apicontatos.dto.ContatoDTO;
import com.chaves.apicontatos.dto.NewContatoDTO;
import com.chaves.apicontatos.entity.Contato;
import com.chaves.apicontatos.repository.ContatoRepository;
import com.chaves.apicontatos.service.impl.ContatoServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.chaves.apicontatos.builder.ContatoBuilder.umContato;
import static com.chaves.apicontatos.builder.ContatoDTOBuilder.umContatoDTO;
import static com.chaves.apicontatos.builder.NewContatoDTOBuilder.umNovoContatoDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class ContatoServiceTest {

    @MockBean
    ContatoRepository repository;

    @MockBean
    ModelMapper mapper;

    ContatoService service;

    @BeforeEach
    public void setUp(){
        service = new ContatoServiceImpl(repository, mapper);
    }

    @Test
    public void get(){
        // inicialização
        List<Contato> list = Arrays.asList(
                umContato().comId(1L).agora(),
                umContato().comId(2L).agora()
        );

        when(repository.findAll()).thenReturn(list);
        when(mapper.map(any(Contato.class), ContatoDTO.class )).thenReturn(umContatoDTO().agora());

        // chamada
        List< ContatoDTO > listDTO =  service.get();

        // verificações
        assertThat(listDTO).isNotEmpty();
        assertThat(listDTO).hasSize(2);
    }

    @Test
    public void getById(){
        // inicialização
        final long ID = 1L;
        Contato contato = umContato().comId(ID).comNome("fulano").agora();

        when(repository.findById(ID)).thenReturn(Optional.of(contato));
        when(mapper.map(contato, ContatoDTO.class )).thenReturn(umContatoDTO().comId(ID).comNome("fulano").agora());

        // chamada
        ContatoDTO contatoDTO =  service.getById(ID);

        // verificações
        assertThat(contatoDTO.getId()).isEqualTo(ID);
        assertThat(contatoDTO.getNome()).isEqualTo("fulano");
    }

    @Test
    public void deleteById(){
        // chamada
        service.deleteById(1L);

        // verificações
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void save(){
        NewContatoDTO dto  = umNovoContatoDTO().agora();
        Contato contato = umContato().agora();
        Contato contatoSaving = umContato().comId(1L).agora();

        when(mapper.map(dto, Contato.class)).thenReturn(contato);
        when(repository.save(contato)).thenReturn(contatoSaving);
        when(mapper.map(contatoSaving, ContatoDTO.class)).thenReturn(umContatoDTO().comId(1L).agora());

        ContatoDTO contatoDTOSaved = service.save(dto);

        Assertions.assertThat(contatoDTOSaved.getId()).isNotNull();

    }
}
