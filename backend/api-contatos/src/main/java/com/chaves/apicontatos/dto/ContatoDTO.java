package com.chaves.apicontatos.dto;

import com.chaves.apicontatos.entity.Operadora;
import java.time.LocalDate;

public class ContatoDTO {

    private Long id;
    private String nome;
    private String telefone;
    private LocalDate data;
    private String cor;
    private Integer operadora;
    private Integer codigoOperadora;
    private String nomeOperadora;
    private String categoriaOperadora;

    public ContatoDTO(){}

    public ContatoDTO(Long id, String nome, String telefone, LocalDate data, String cor, Operadora operadora) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.data = data;
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
        Operadora op = Operadora.toEnum(operadora);

        setCodigoOperadora(op.getCodigo());
        setNomeOperadora(op.getNome());
        setCategoriaOperadora(op.getCategoria());

        return op;
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

    public Integer getCodigoOperadora() {
        return codigoOperadora;
    }

    public void setCodigoOperadora(Integer codigoOperadora) {
        this.codigoOperadora = codigoOperadora;
    }

    public String getNomeOperadora() {
        return nomeOperadora;
    }

    public void setNomeOperadora(String nomeOperadora) {
        this.nomeOperadora = nomeOperadora;
    }

    public String getCategoriaOperadora() {
        return categoriaOperadora;
    }

    public void setCategoriaOperadora(String categoriaOperadora) {
        this.categoriaOperadora = categoriaOperadora;
    }
}
