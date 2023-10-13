package org.estudo.cadastroServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) {
        int port = 4321;
        int timeoutMillis = 600 * 1000; // 10 minutos em milissegundos

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server iniciado. Esperando conexões na porta " + port + " por 10 minutos...");

            // Configura um timeout no socket do servidor.
            serverSocket.setSoTimeout(timeoutMillis);

            Socket clientSocket = serverSocket.accept(); // Isso bloqueará por até 10 minutos
            System.out.println("Conexão recebida de: " + clientSocket.getRemoteSocketAddress());

            clientSocket.close();
        } catch (IOException e) {
            if (e.getMessage().contains("timed out")) {
                System.out.println("Tempo esgotado. Nenhuma conexão recebida em 10 minutos.");
            } else {
                e.printStackTrace();
            }
        }
    }
}
