package org.estudo.servers.runnable;

import org.estudo.controller.ProdutoJpaController;
import org.estudo.controller.UsuarioJpaController;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CadastroThread implements Runnable {
    private final ProdutoJpaController ctrl;
    private final UsuarioJpaController ctrlUsu;
    private final Socket s1;

    public CadastroThread(ProdutoJpaController ctrl, UsuarioJpaController ctrlUsu, Socket s1) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.s1 = s1;
    }

    @Override
    public void run() {

        System.out.println("_________Dentro de CadastroThread RUN" );
        try (ObjectOutputStream out = new ObjectOutputStream(s1.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(s1.getInputStream())) {

            // d.2.Obter o login e a senha a partir da entrada.
            String login = (String) in.readObject();
            String senha = (String) in.readObject();

            // d.3.Utilizar ctrlUsu para verificar o login...
            // (Supondo que a classe UsuarioJpaController tenha um método findUsuario)
            // Caso não tenha esse método, você precisará implementá-lo.
            if (ctrlUsu.findUsuario(login, senha) == null) {
                // ...terminando a conexão caso o retorno seja nulo.
                System.out.println("_________Dentro de CadastroThread / " +
                        "ctrlUsu.findUsuario(login, senha) == null / SAINDO " +login +"/"+senha );
                s1.close();
                return;
            }

            // d.4.Com o usuário válido, iniciar o loop de resposta...
            while (true) {
                // ...que deve obter o comando a partir da entrada.
                String command = (String) in.readObject();

                // d.5.Caso o comando seja L...

                System.out.println("+++++++++++++++++++Dentro de CadastroThread / cmd => "+command);

                if ("L".equalsIgnoreCase(command)) {
                    // ...utilizar ctrl para retornar o conjunto de produtos através da saída.
                    // (Você precisará ter um método em ProdutoJpaController que recupera a lista de produtos)
                    out.writeObject(ctrl.getAllProducts());
                }
                // Você pode adicionar mais comandos conforme necessário
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
