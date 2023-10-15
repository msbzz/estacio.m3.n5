package org.estudo.servers.thread;

import org.estudo.controller.ProdutoJpaController;
import org.estudo.controller.UsuarioJpaController;
import org.estudo.model.Usuario;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CadastroThread extends Thread {
    private ProdutoJpaController ctrl;
    private UsuarioJpaController ctrlUsu;
    private Socket s1;

    // Construtor
    public CadastroThread(ProdutoJpaController ctrl, UsuarioJpaController ctrlUsu, Socket s1) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.s1 = s1;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(s1.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(s1.getInputStream());

            // Obter o login e a senha
            String login = (String) in.readObject();
            String senha = (String) in.readObject();

            // Verificar o login
            Usuario user = ctrlUsu.findUsuario(login, senha);
            if (user == null) {
                out.writeObject("Usuário inválido.");
                s1.close();
                return;
            }

            // Iniciar o loop de resposta
            while (true) {
                String command = (String) in.readObject();
                if ("L".equals(command)) {
                    // Suponho que o método em ctrl retorne uma lista de produtos
                    out.writeObject(ctrl.getAllProducts());
                }
                // Você pode expandir isso para outros comandos conforme necessário
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
