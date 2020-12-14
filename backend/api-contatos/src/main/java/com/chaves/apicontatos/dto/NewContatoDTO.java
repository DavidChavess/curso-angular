package com.chaves.apicontatos.dto;

import com.chaves.apicontatos.entity.Operadora;

import java.time.LocalDate;

public class NewContatoDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String cor;
    private Integer operadora;

    public NewContatoDTO(){}

    public NewContatoDTO(Long id, String nome, String telefone, String cor, Operadora operadora) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cor = cor;
        this.operadora = operadora.getCodigo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Operadora getOperadora() {
        return Operadora.toEnum(operadora);
    }

    public void setOperadora(Operadora operadora) {
        this.operadora = operadora.getCodigo();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
