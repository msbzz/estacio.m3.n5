package org.estudo.controller;

import org.estudo.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UsuarioJpaController {

    private EntityManagerFactory emf = null;

    public UsuarioJpaController(EntityManagerFactory emf) {
        System.out.println("construtor usuario c/parametro EntityManagerFactory emf");
        this.emf =emf; //Persistence.createEntityManagerFactory("jdbc/loja");
    }

    public EntityManager getEntityManager() {
        System.out.println("construtor usuario s/parametro EntityManagerFactory emf");
        return emf.createEntityManager();
    }

    public Usuario findUsuario(String login, String senha) {
        EntityManager em = getEntityManager();
        try {

            //System.out.println("||||||===>> Dentro de Usuario jpa controler login "+login+", senha "+ senha);


            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha", Usuario.class);
            query.setParameter("login", login);
            query.setParameter("senha", senha);


            return query.getSingleResult();
        } catch (Exception e) {
            //System.out.println("XXXX===>> Dentro de Usuario jpa controler ERRO ==>> "+e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
}
