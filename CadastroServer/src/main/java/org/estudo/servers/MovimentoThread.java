package org.estudo.servers;

import org.estudo.controller.MovimentoJpaController;
import org.estudo.controller.PessoaJpaController;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.util.List;

public class MovimentoThread implements Runnable {
    private MovimentoJpaController ctrlMov;
    private PessoaJpaController ctrlPessoa;

    private ObjectOutputStream out;
    private ObjectInputStream in;


    // Construtor
    public MovimentoThread(MovimentoJpaController ctrlMov, PessoaJpaController ctrlPessoa, ObjectOutputStream out, ObjectInputStream in) {
        this.ctrlMov = ctrlMov;
        this.ctrlPessoa = ctrlPessoa;
        this.out = out;
        this.in = in;

    }

    @Override
    public void run() {

        System.out.println("MovimentoThread String command RUN ");
        try {

            String command = (String) in.readObject();
            System.out.println("String command = (String) in.readObject(); "+ command);

            // Listar movimentos
            if ("L".equals(command)) {
                System.out.println("Opção Movimento List<?> ");
                List<?> movimentos = ctrlMov.findAllMovimentos();
                out.writeObject(movimentos);
            }
            // Aqui você pode adicionar mais comandos relacionados a movimentos
            else if ("E".equals(command) || "S".equals(command)) {
                //... (restante do código existente)
            }
            else if (("X".equals(command))||("x".equals(command))) {
                //s1.close();
                System.exit(0);
            } else {
                System.out.println("Comando desconhecido: " + command);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
