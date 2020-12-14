package com.chaves.apicontatos.exception;

public class ContatoNotFoundException extends RuntimeException{

    public ContatoNotFoundException() {
        super("Contato não encontrado");
    }
}
