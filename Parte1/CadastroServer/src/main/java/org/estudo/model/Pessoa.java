package org.estudo.model;

import javax.persistence.*;

import java.util.Objects;

@Entity
public class Pessoa {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @Basic
    @Column(name = "logradouro")
    private String logradouro;
    @Basic
    @Column(name = "cidade")
    private String cidade;
    @Basic
    @Column(name = "estado")
    private String estado;
    @Basic
    @Column(name = "telefone")
    private String telefone;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "tipo")
    private String tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Pessoa pessoa = (Pessoa) o;
        return id == pessoa.id && Objects.equals(nome, pessoa.nome) && Objects.equals(logradouro, pessoa.logradouro) && Objects.equals(cidade, pessoa.cidade) && Objects.equals(estado, pessoa.estado) && Objects.equals(telefone, pessoa.telefone) && Objects.equals(email, pessoa.email) && Objects.equals(tipo, pessoa.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, logradouro, cidade, estado, telefone, email, tipo);
    }
}
