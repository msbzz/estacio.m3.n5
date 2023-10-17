package org.estudo.servers;

import org.estudo.controller.MovimentoJpaController;
import org.estudo.model.Movimento;

import org.estudo.gui.SaidaFrame;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;


public class CadastroClientV2 {

    public static void main(String[] args) {
        try {
            System.out.println("Iniciando CadastroClient...");

            Socket socket = new Socket("localhost", 4321);

            if (socket.isConnected()) {
                System.out.println("Conectado ao servidor.");
                System.out.println("Socket conectado: " + socket.isConnected());
            } else {
                System.out.println("Falha ao conectar ao servidor.");
            }

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            //System.out.println("Output Stream criado.");
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            SaidaFrame saidaFrame = new SaidaFrame();

            ThreadClient clientRunnable = new ThreadClient(in, saidaFrame.texto);
            Thread clientThread = new Thread(clientRunnable);
            clientThread.start();

            while (true) {
                System.out.println("Menu: L - Listar, X - Finalizar, E - Entrada, S - Saída");
                String menuChoice = keyboard.readLine();
                switch (menuChoice.toUpperCase()) {
                    case "L":

                        System.out.println("Solicitando lista ao servidor.");
                        out.reset();
                        out.writeObject("L");
                        clientRunnable.requestList(); // Solicita uma nova lista
                        menuChoice="";
                        break;
                    case "E":
                    case "S":

                        System.out.print("Id da pessoa: ");
                        int idPessoa = Integer.parseInt(keyboard.readLine());
                        System.out.print("Id do produto: ");
                        int idProduto = Integer.parseInt(keyboard.readLine());
                        System.out.print("Quantidade: ");
                        int quantidade = Integer.parseInt(keyboard.readLine());


                        String tipo="";

                        if ("E".equals(menuChoice.toUpperCase())) {
                            tipo="E";
                         } else if ("S".equals(menuChoice.toUpperCase())) {
                            tipo="S";
                        }

                        Movimento movimento = new Movimento();
                        movimento.setTipo(tipo);
                        movimento.setIdUsuario(1);
                        movimento.setIdPessoa(idPessoa);
                        movimento.setIdProduto(idProduto);
                        movimento.setQuantidade(quantidade);

                        MovimentoJpaController movimentoController = new MovimentoJpaController();
                        movimentoController.create(movimento);
                        menuChoice="";
                        break;
                    case "X":
                        out.writeObject("X");
                        in.close();
                        out.close();
                        socket.close();
                        System.exit(0);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro CadastroClient "+ e.getMessage());
        }
    }
}
