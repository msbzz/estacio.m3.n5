package org.estudo.servers;

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
                //s1.close();
                return;
            }else{
                out.writeObject("Usuário válido.");
            }

            // Iniciar o loop de resposta
            while (true) {
                String command = (String) in.readObject();
                if ("L".equals(command)) {
                    // Suponho que o método em ctrl retorne uma lista de produtos
                    out.writeObject(ctrl.getAllProducts());
                }

                if ("QUIT".equals(command)) {
                    break;  // Sair do loop se o comando de término for recebido
                }

            }

            s1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
