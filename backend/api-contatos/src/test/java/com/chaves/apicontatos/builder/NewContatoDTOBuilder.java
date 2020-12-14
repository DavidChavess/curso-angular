package com.chaves.apicontatos.builder;
import com.chaves.apicontatos.dto.ContatoDTO;
import com.chaves.apicontatos.dto.NewContatoDTO;
import com.chaves.apicontatos.entity.Operadora;

public class NewContatoDTOBuilder {

    private final NewContatoDTO contatoDTO;

    private NewContatoDTOBuilder(){
        contatoDTO = new NewContatoDTO();
        contatoDTO.setId(1L);
        contatoDTO.setNome("Fulano");
        contatoDTO.setTelefone("16 99760-9338");
        contatoDTO.setCor("#FFF");
        contatoDTO.setOperadora(Operadora.VIVO);
    }

    public static NewContatoDTOBuilder umNovoContatoDTO(){
        return new NewContatoDTOBuilder();
    }

    public NewContatoDTOBuilder comId(Long id){
        contatoDTO.setId(id);
        return this;
    }

    public NewContatoDTOBuilder comNome(String nome) {
        contatoDTO.setNome(nome);
        return this;
    }

    public NewContatoDTO agora(){
        return contatoDTO;
    }

}
