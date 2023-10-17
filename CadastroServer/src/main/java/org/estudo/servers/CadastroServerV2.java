package org.estudo.servers;

import org.estudo.controller.*;

import org.estudo.servers.MovimentoThread;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CadastroServerV2 {
    public static void main(String[] args) {
        // Verifica a presença do arquivo persistence.xml
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

        // Instancia um objeto do tipo EntityManagerFactory a partir da unidade de persistência.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jdbc/loja");

        MovimentoJpaController ctrlMov = new MovimentoJpaController(emf);
        PessoaJpaController ctrlPessoa = new PessoaJpaController(emf);

        System.out.println(" abaixo  Declaração das variáveis JPAs");

        try {
            ServerSocket serverSocket = new ServerSocket(4321);
            System.out.println("ServerSocket iniciado na porta 4321");

            while (true) {
                //System.out.println("Conectado ao servidor ANTES...serverSocket.accept();");
                Socket clientSocket = serverSocket.accept();
                //System.out.println("Conectado ao servidor...serverSocket.accept();");

                ObjectOutputStream outServer = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inServer = new ObjectInputStream(clientSocket.getInputStream());

                // Inicie diretamente a MovimentoThread
                Thread clientThread = new Thread(new MovimentoThread(ctrlMov, ctrlPessoa, outServer, inServer));
                //System.out.println("Antes clientThread.start() MOVIMENTO");
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
