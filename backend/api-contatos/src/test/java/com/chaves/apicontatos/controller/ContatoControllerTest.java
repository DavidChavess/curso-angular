package com.chaves.apicontatos.controller;

import com.chaves.apicontatos.dto.ContatoDTO;
import com.chaves.apicontatos.dto.NewContatoDTO;
import com.chaves.apicontatos.entity.Operadora;
import com.chaves.apicontatos.service.ContatoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static com.chaves.apicontatos.builder.ContatoDTOBuilder.umContatoDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
public class ContatoControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ContatoService service;

    @Test
    @DisplayName("Deve obter informações dos contatos")
    public void getContatos() throws Exception {
        // inicio
        // instancio dois contatos dto e simulo o retorno do endpoint get de contatos
        List<ContatoDTO> contatos = Arrays.asList(
                umContatoDTO().comId(1L).agora(),
                umContatoDTO().comId(2L).agora()
        );
        given(service.get()).willReturn(contatos);

        // chamada
        // faço a chamada ao endpoint
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/contatos")
                .contentType(MediaType.APPLICATION_JSON);

        // verificaçao
        // verifico se o retorno do endpoint foi como o esperado
        mvc.perform(request)
                .andExpect(status().isOk()) // o status da requisição deve ser 200
                .andExpect(jsonPath("[0].id").value(1L)) // o id do 1º contato deve ser 1
                .andExpect(jsonPath("[1].id").value(2L)); // o id do 2º contato deve ser 2
    }

    @Test
    @DisplayName("Deve recuperar um contato por id informado pelo usuario")
    public void getById() throws Exception {
        final long  ID = 1L;
        ContatoDTO contatoDTO = umContatoDTO().comId(ID).agora();
        given(service.getById(ID)).willReturn(contatoDTO);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/contatos".concat("/"+ID))
                .contentType(MediaType.APPLICATION_JSON);

        ResultActions resultActions = mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id"            ).value(ID))
                .andExpect(jsonPath("nome"          ).value(contatoDTO.getNome()))
                .andExpect(jsonPath("cor"           ).value(contatoDTO.getCor()))
                .andExpect(jsonPath("telefone"      ).value(contatoDTO.getTelefone()))
                .andExpect(jsonPath("codigoOperadora"     ).value(Operadora.VIVO.getCodigo()))
                .andExpect(jsonPath("nomeOperadora"       ).value(Operadora.VIVO.getNome()))
                .andExpect(jsonPath("categoriaOperadora"  ).value(Operadora.VIVO.getCategoria()));
    }

    @Test
    @DisplayName("Deve deletar um contato")
    public void delete() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete("/contatos".concat("/"+1));

        ResultActions resultActions = mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve salvar um contato")
    public void save() throws Exception {
        ContatoDTO contatoDTO = umContatoDTO().agora();
        String json = new ObjectMapper().writeValueAsString(contatoDTO);
        given(service.save(any(NewContatoDTO.class))).willReturn( umContatoDTO().comId(1L).agora() );

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/contatos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);


        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id"                  ).value(1))
                .andExpect(jsonPath("nome"                ).value(contatoDTO.getNome()))
                .andExpect(jsonPath("cor"                 ).value(contatoDTO.getCor()))
                .andExpect(jsonPath("telefone"            ).value(contatoDTO.getTelefone()))
                .andExpect(jsonPath("codigoOperadora"     ).value(contatoDTO.getOperadora().getCodigo()))
                .andExpect(jsonPath("nomeOperadora"       ).value(contatoDTO.getOperadora().getNome()))
                .andExpect(jsonPath("categoriaOperadora"  ).value(contatoDTO.getOperadora().getCategoria()));
    }
}
