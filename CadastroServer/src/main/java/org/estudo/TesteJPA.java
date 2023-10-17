package org.estudo;

import org.estudo.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TesteJPA {

    public static void main(String[] args) {
        // Criando um EntityManagerFactory e EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jdbc/loja");
        EntityManager em = emf.createEntityManager();

        try {
            // Criando uma query para buscar todos os produtos
            TypedQuery<Produto> query = em.createQuery("SELECT p FROM Produto p", Produto.class);

            // Executando a query
            List<Produto> produtos = query.getResultList();

            // Imprimindo os produtos
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fechando o EntityManager e EntityManagerFactory
            em.close();
            emf.close();
        }
    }
}
