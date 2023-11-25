package org.estudo.model;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Movimento implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idMovimento")
    private int idMovimento;
    @Basic
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @Basic
    @Column(name = "idPessoa")
    private Integer idPessoa;
    @Basic
    @Column(name = "idProduto")
    private Integer idProduto;
    @Basic
    @Column(name = "quantidade")
    private Integer quantidade;
    @Basic
    @Column(name = "valorUnitario")
    private BigDecimal valorUnitario;
    @Basic
    @Column(name = "tipo")
    private String tipo;

    public int getIdMovimento() {
        return idMovimento;
    }

    public void setIdMovimento(int idMovimento) {
        this.idMovimento = idMovimento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movimento movimento = (Movimento) o;
        return idMovimento == movimento.idMovimento && Objects.equals(idUsuario, movimento.idUsuario) && Objects.equals(idPessoa, movimento.idPessoa) && Objects.equals(idProduto, movimento.idProduto) && Objects.equals(quantidade, movimento.quantidade) && Objects.equals(valorUnitario, movimento.valorUnitario) && Objects.equals(tipo, movimento.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMovimento, idUsuario, idPessoa, idProduto, quantidade, valorUnitario, tipo);
    }
}
