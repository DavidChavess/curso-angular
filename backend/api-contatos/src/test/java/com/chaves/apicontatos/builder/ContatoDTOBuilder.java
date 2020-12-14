package com.chaves.apicontatos.builder;
import com.chaves.apicontatos.dto.ContatoDTO;
import com.chaves.apicontatos.entity.Operadora;

public class ContatoDTOBuilder {

    private final ContatoDTO contatoDTO;

    private ContatoDTOBuilder(){
        contatoDTO = new ContatoDTO();
        contatoDTO.setId(1L);
        contatoDTO.setNome("Fulano");
        contatoDTO.setTelefone("16 99760-9338");
        contatoDTO.setCor("#FFF");
        contatoDTO.setData(null);
        contatoDTO.setOperadora(Operadora.VIVO);
    }

    public static ContatoDTOBuilder umContatoDTO(){
        return new ContatoDTOBuilder();
    }

    public ContatoDTOBuilder comId(Long id){
        contatoDTO.setId(id);
        return this;
    }

    public ContatoDTOBuilder comNome(String nome) {
        contatoDTO.setNome(nome);
        return this;
    }

    public ContatoDTO agora(){
        return contatoDTO;
    }

}
