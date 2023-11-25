package org.estudo.servers;

import org.estudo.model.Movimento;
import org.estudo.util.ComandoMovimento;

import java.io.*;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CadastroClient {
    private static final int PORTA_LISTAGEM = 4321;
    private static final int PORTA_CADASTRO = 4322;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean usuarioLogado = false;
        Boolean formInicializado = false;
        ThreadClient threadClient = null;

        Socket socketListagem = null;
        ObjectOutputStream outListagem = null;
        ObjectInputStream inListagem = null;

        Socket socketCadastro = null;
        ObjectOutputStream outCadastro = null;
        ObjectInputStream inCadastro = null;

        try {
            socketListagem = new Socket("localhost", PORTA_LISTAGEM);
            outListagem = new ObjectOutputStream(socketListagem.getOutputStream());
            inListagem = new ObjectInputStream(socketListagem.getInputStream());

            socketCadastro = new Socket("localhost", PORTA_CADASTRO);
            outCadastro = new ObjectOutputStream(socketCadastro.getOutputStream());
            inCadastro = new ObjectInputStream(socketCadastro.getInputStream());

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            if (!formInicializado) {
                threadClient = new ThreadClient(inListagem); // Supondo que ThreadClient é uma classe existente
                threadClient.start();
                threadClient.addMessage(gerarMensagemComunicacao());
                formInicializado = true;
            }

            while (true) {
                if (!usuarioLogado) {
                    System.out.print("Digite o login: ");
                    String login = scanner.nextLine();
                    System.out.print("Digite a senha: ");
                    String senha = scanner.nextLine();

                    outListagem.writeObject(login);
                    outListagem.flush();
                    outListagem.writeObject(senha);
                    outListagem.flush();

                    Object response = inListagem.readObject();
                    if ("Usuário inválido.".equals(response)) {
                        threadClient.addMessage("Usuário inválido.");
                        System.out.print("Deseja tentar novamente? (S/N): ");
                        if (!"S".equalsIgnoreCase(scanner.nextLine())) {
                            threadClient.addMessage("Encerrando...");
                            threadClient.terminate();
                            System.exit(0);
                        }
                    } else {
                        threadClient.addMessage("Usuário conectado com sucesso.");
                        usuarioLogado = true;
                    }
                }

                if (usuarioLogado) {
                    System.out.println("Menu: L - Listar, X - Finalizar, E - Entrada, S - Saída");
                    String menuChoice = keyboard.readLine();

                    switch (menuChoice.toUpperCase()) {
                        case "L":
                            outListagem.writeObject("L");
                            outListagem.flush();
                            threadClient.addMessage("Lista de produtos.");
                            Object responseListagem = inListagem.readObject();
                            if (responseListagem instanceof List) {
                                List<?> receivedList = (List<?>) responseListagem;
                                for (Object obj : receivedList) {
                                    Object[] valores = (Object[]) obj;
                                    int Id = (int) valores[0];
                                    String nomeProduto = (String) valores[1];
                                    Integer Quantidade = (Integer) valores[2];
                                    BigDecimal precoVenda = (BigDecimal) valores[3];
                                    String tipo = (String) valores[4];
                                    String linha="ID: " + Id +", Nome: " + nomeProduto +", Quantidade: " + Quantidade +", Preço: " + precoVenda +
                                            ", Tipo Mov: " + tipo + "\n";
                                    threadClient.addMessage(linha);
                                }
                            } else {
                                threadClient.addMessage("Erro ao receber lista.");
                            }
                            break;

                        case "E":
                        case "S":

                            System.out.print("Id da pessoa: ");
                            int idPessoa = Integer.parseInt(keyboard.readLine());
                            System.out.print("Id do produto: ");
                            int idProduto = Integer.parseInt(keyboard.readLine());
                            System.out.print("Quantidade: ");
                            int quantidade = Integer.parseInt(keyboard.readLine());
                            System.out.print("Valor unitário: ");
                            String valorUnitarioStr = keyboard.readLine();
                            BigDecimal valorUnitario = new BigDecimal(valorUnitarioStr);

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
                            movimento.setValorUnitario(valorUnitario);


                            ComandoMovimento cm = new ComandoMovimento(tipo, movimento);
                            outCadastro.writeObject(cm);
                            outCadastro.flush();
                            threadClient.addMessage("movimento do tipo '" +tipo + "' adicionado" );
                            break;

                        case "X":
                            outListagem.writeObject("X");
                            outCadastro.writeObject("X");
                            System.exit(0);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outListagem != null) outListagem.close();
                if (inListagem != null) inListagem.close();
                if (socketListagem != null && !socketListagem.isClosed()) socketListagem.close();

                if (outCadastro != null) outCadastro.close();
                if (inCadastro != null) inCadastro.close();
                if (socketCadastro != null && !socketCadastro.isClosed()) socketCadastro.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String gerarMensagemComunicacao() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return ">>Nova comunicação em " + dateFormat.format(new Date());
    }
}
