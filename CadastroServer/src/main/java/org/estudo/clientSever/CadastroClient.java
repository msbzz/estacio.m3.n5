package org.estudo.clientSever;

import org.estudo.model.Produto;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class CadastroClient {

    public static void main(String[] args) {
        try {
            // a. Instanciar um Socket apontando para localhost, na porta 4321.
            Socket socket = new Socket("localhost", 4321);

            // b. Encapsular os canais de entrada e saída do Socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // c. Escrever o login e a senha na saída.
            out.writeObject("op1");  // Login
            out.writeObject("op1");  // Senha

            // d. Enviar o comando L no canal de saída.
            out.writeObject("L");

            // Receber a coleção de entidades no canal de entrada.
            System.out.println("Usuario conectado com sucesso");

            List<Produto> produtos = (List<Produto>) in.readObject();

            // e. Apresentar o nome de cada entidade recebida.
            for (Produto produto : produtos) {
                System.out.println(produto.getNome());
            }

            // Fechar a conexão.
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
