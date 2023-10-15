package org.estudo.cadastroServer;

import org.estudo.controller.ProdutoJpaController;
import org.estudo.controller.UsuarioJpaController;
import org.estudo.servers.runnable.CadastroThread;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CadastroServer {
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

        // a. Instanciar um objeto do tipo EntityManagerFactory a partir da unidade de persistência.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jdbc/loja");


        // b. Instanciar o objeto ctrl, do tipo ProdutoJpaController.


        ProdutoJpaController ctrl = new ProdutoJpaController(emf);

        // c. Instanciar o objeto ctrlUsu do tipo UsuarioJpaController.
        UsuarioJpaController ctrlUsu = new UsuarioJpaController(emf);

        try {
            // d. Instanciar um objeto do tipo ServerSocket, escutando a porta 4321.
            ServerSocket serverSocket = new ServerSocket(4321);

            // e. Dentro de um loop infinito...
            while (true) {
                // ...obter a requisição de conexão do cliente...
                Socket clientSocket = serverSocket.accept();

                System.out.println("=======>>> Dentro de CadastroServer/ ctrl => "+ctrl);
                // ...instanciar uma Thread...
                Thread clientThread = new Thread(new CadastroThread(ctrl, ctrlUsu, clientSocket));

                // ...iniciando-a em seguida.
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
