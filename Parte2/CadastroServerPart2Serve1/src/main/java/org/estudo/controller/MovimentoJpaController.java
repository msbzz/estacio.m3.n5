package org.estudo.controller;

import org.estudo.model.Movimento;
import org.estudo.model.MovimentoDTO;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import java.util.List;

public class MovimentoJpaController {
    private EntityManagerFactory emf = null;

    public MovimentoJpaController(EntityManagerFactory emf){

        this.emf = Persistence.createEntityManagerFactory("jdbc/loja");
        System.out.println("abaixo this.emf = Persistence.createEntityManagerFactory");
    }
    public MovimentoJpaController(){
        System.out.println("construtor Movimento c/parametro EntityManagerFactory emf");
        this.emf = Persistence.createEntityManagerFactory("jdbc/loja");
        System.out.println("abaixo this.emf = Persistence.createEntityManagerFactory");
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimento movimento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(movimento);
            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MovimentoDTO> findAllMovimentos() {
        return getEntityManager().createQuery(
                "SELECT m.idMovimento,p.nome,m.quantidade,m.valorUnitario,m.tipo FROM Movimento m INNER JOIN Produto p ON m.idProduto = p.idProduto"
                , MovimentoDTO.class).getResultList();
    }



}
