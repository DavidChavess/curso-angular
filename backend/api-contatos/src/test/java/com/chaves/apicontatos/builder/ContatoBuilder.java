package com.chaves.apicontatos.builder;

import com.chaves.apicontatos.entity.Operadora;
import com.chaves.apicontatos.entity.Contato;

public class ContatoBuilder {

    private final Contato contato;

    private ContatoBuilder(){
        contato = new Contato();
        contato.setId(1L);
        contato.setNome("Fulano");
        contato.setTelefone("16 99760-9338");
        contato.setData(null);
        contato.setCor("#FFF");
        contato.setOperadora(Operadora.VIVO);
    }

    public static ContatoBuilder umContato(){
      return new ContatoBuilder();
    }

    public ContatoBuilder comId(Long id){
        contato.setId(id);
        return this;
    }

    public ContatoBuilder comNome(String nome) {
        contato.setNome(nome);
        return this;
    }

    public Contato agora(){
        return contato;
    }
}
