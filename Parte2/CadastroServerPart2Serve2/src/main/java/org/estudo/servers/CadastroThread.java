package org.estudo.servers;

import org.estudo.controller.MovimentoJpaController;
import org.estudo.controller.ProdutoJpaController;
import org.estudo.controller.UsuarioJpaController;
import org.estudo.model.Movimento;
import org.estudo.model.Usuario;
import org.estudo.util.ComandoMovimento;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class CadastroThread extends Thread {
    private ProdutoJpaController ctrl;
    private UsuarioJpaController ctrlUsu;
    private MovimentoJpaController ctrlMov;
    private Socket clientSocket;

    private Boolean usuarioLogado= false;

    // Construtor
    public CadastroThread(ProdutoJpaController ctrl, UsuarioJpaController ctrlUsu, Socket clientSocket,
                          MovimentoJpaController ctrlMov) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.clientSocket = clientSocket;
        this.ctrlMov = ctrlMov;
    }

    @Override
    public void run() {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;

        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            /*
            while(!usuarioLogado) {

                // Obter o login e a senha
                String login = (String) in.readObject();
                String senha = (String) in.readObject();

                // Verificar o login
                Usuario user = ctrlUsu.findUsuario(login, senha);
                if (user == null) {
                    out.writeObject("Usuário inválido.");
                    //return;
                } else {
                    out.writeObject("Usuário válido.");
                    usuarioLogado=true;
                }
            }

             */

            // Iniciar o loop de resposta
            while (true) {
                Object receivedObject = in.readObject();
                String command;
                Movimento movimento=null;

                if (receivedObject instanceof String) {
                    command = (String) receivedObject;
                } else {
                    ComandoMovimento cm = (ComandoMovimento) receivedObject;
                    command = cm.getComando();
                    movimento = cm.getMovimento();
                }

                if ("P".equals(command)) {
                    out.writeObject(ctrl.getAllProducts());
                } else if ("L".equals(command) || "l".equals(command)) {
                    List<?> movimentos = ctrlMov.findAllMovimentos();
                    out.writeObject(movimentos);
                    out.flush();
                } else if ("E".equals(command) || "e".equals(command) || "S".equals(command) || "s".equals(command)) {
                    ctrlMov.create(movimento);

                    if ("E".equals(command)) {
                        out.writeObject("Entrada de produto.");
                    } else {
                        out.writeObject("Saída de produto.");
                    }
                }

                if ("X".equals(command) || "x".equals(command)) {
                    System.exit(0);
                    break; // Sair do loop se o comando de término for recebido
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fechando recursos
            try {
                if (out != null) {
                    //out.close();
                }
                if (in != null) {
                    //in.close();
                }
                if (clientSocket != null && !clientSocket.isClosed()) {
                    //clientSocket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
