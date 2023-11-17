package org.estudo.servers;

import org.estudo.model.Produto;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class CadastroClient {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);  // Criar um Scanner para ler a entrada do usuário

       try {
            // a. Instanciar um Socket apontando para localhost, na porta 4321.
            Socket socket = new Socket("localhost", 4321);

            // b. Encapsular os canais de entrada e saída do Socket
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // Ler o login e a senha do usuário
            System.out.print("Digite o login: ");
            String login = scanner.nextLine();  // Ler o login do usuário
            System.out.print("Digite a senha: ");
            String senha = scanner.nextLine();  // Ler a senha do usuário

            // Enviar o login e a senha para o servidor
            out.writeObject(login);
            out.writeObject(senha);


           // Ler a resposta do servidor
           Object response = in.readObject();

           // Verificar se a resposta é "Usuário inválido."
           if (response instanceof String && "Usuário inválido.".equals(response)) {
               System.out.println("Usuário inválido. Encerrando...");
               socket.close();
               return;
           }
            // d. Enviar o comando L no canal de saída.
            out.writeObject("L");

             System.out.println("Usuario conectado com sucesso");

            List<Produto> produtos = (List<Produto>) in.readObject();

             for (Produto produto : produtos) {
                System.out.println(produto.getNome());
            }
            out.writeObject("QUIT");  // Enviar comando de término

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
