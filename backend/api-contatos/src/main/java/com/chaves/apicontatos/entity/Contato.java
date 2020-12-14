package com.chaves.apicontatos.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private LocalDate data;
    private String cor;
    private Integer operadora;

    public Contato(){}

    public Contato(Long id, String nome,String telefone, LocalDate data, String cor, Operadora operadora) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.data = data;
        this.cor = cor;
        this.operadora= operadora.getCodigo();
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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
