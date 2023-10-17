package org.estudo.controller;

import org.estudo.model.Pessoa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PessoaJpaController {
    private EntityManagerFactory emf = null;

    public PessoaJpaController(EntityManagerFactory emf){
        this.emf= Persistence.createEntityManagerFactory("jdbc/loja");;
    }
    public Pessoa findPessoaById(Integer id) {
        // Lógica para buscar e retornar a pessoa pelo ID no banco de dados.
        return new Pessoa(); // Retorno temporário, só para fins de ilustração.
    }
}
