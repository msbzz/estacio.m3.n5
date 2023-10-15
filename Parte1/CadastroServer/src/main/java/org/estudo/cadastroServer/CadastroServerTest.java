package org.estudo.cadastroServer;

import org.estudo.controller.ProdutoJpaController;
import org.estudo.controller.UsuarioJpaController;
import org.estudo.servers.runnable.CadastroThread;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CadastroServerTest {

    private static EntityManagerFactory emf;

    public static void main(String[] args) {

        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/persistence.xml");
            if (is == null) {
                System.out.println("Não foi possível encontrar o arquivo persistence.xml no classpath!");
            } else {
                System.out.println("Arquivo persistence.xml encontrado com sucesso no classpath.");
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        emf = Persistence.createEntityManagerFactory("jdbc/loja");

        // Testa a conexão com o banco de dados
        testDatabaseConnection();

        ProdutoJpaController ctrl = new ProdutoJpaController(emf);
        UsuarioJpaController ctrlUsu = new UsuarioJpaController(emf);

        try {
            ServerSocket serverSocket = new ServerSocket(4321);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread clientThread = new Thread(new CadastroThread(ctrl, ctrlUsu, clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testDatabaseConnection() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            // Testa a conexão
            em.getTransaction().begin();
            em.getTransaction().commit();
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (Exception e) {
            System.err.println("Falha ao se conectar ao banco de dados: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
