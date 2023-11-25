package org.estudo.model;

import java.math.BigDecimal;

public class MovimentoDTO {
    private String nome;
    private int quantidade; // ajuste o tipo conforme necessário
    private BigDecimal precoVenda; // ajuste o tipo conforme necessário

    public MovimentoDTO(String nome, int quantidade, BigDecimal precoVenda) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    // getters, setters, etc.

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    @Override
    public String toString() {
        return "Movimento{" +
                " nome=" + nome +
                ", quantidade=" + quantidade +
                ", valorUnitario=" + precoVenda +
                '}';
    }
}
