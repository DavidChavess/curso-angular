package com.chaves.apicontatos.entity;

public enum Operadora {
    VIVO(15, "VIVO", "CELULAR"),
    OI(14, "OI", "CELULAR"),
    TIM(41, "TIM", "CELULAR"),
    GNV(35, "GNV","TELEFONE_FIXO"),
    EMBRATEL(21, "EMBRATEL", "TELEFONE_FIXO");

    private final Integer codigo;
    private final String nome;
    private final String categoria;

    private Operadora(Integer codigo, String nome, String categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public static Operadora toEnum(Integer cod) {
        for (Operadora op : Operadora.values()){
            if (cod.equals(op.getCodigo())) {
                return op;
            }
        }
        return null;
    }

}
