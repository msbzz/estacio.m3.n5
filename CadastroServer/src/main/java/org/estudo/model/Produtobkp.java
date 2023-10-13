package org.estudo.model;

import javax.persistence.*;

@Entity
@Table(name = "produto")
public class Produtobkp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduto;

    @Column(name = "nome")
    private String nome;
    @Column(name = "quantidade")
    private int quantidade;
    @Column(name = "precoVenda")
    private Double precoVenda;

    // getters, setters, equals, hashCode e toString

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + idProduto +
                ", nome='" + nome + '\'' +
                ", precoVenda=" + precoVenda +
                ", quantidade=" + quantidade +
                '}';
    }

}
