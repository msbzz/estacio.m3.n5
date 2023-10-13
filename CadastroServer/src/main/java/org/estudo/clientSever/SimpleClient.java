package org.estudo.clientSever;
import java.io.IOException;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 4321;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Conectado ao servidor em " + socket.getRemoteSocketAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
