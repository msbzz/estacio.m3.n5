package org.estudo.servers;

import org.estudo.model.Movimento;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ThreadClient implements Runnable {
    private final ObjectInputStream in;
    private final JTextArea texto;

    private volatile boolean isListRequest = false;

    public ThreadClient(ObjectInputStream in, JTextArea texto) {
        this.in = in;
        this.texto = texto;
    }

    @Override
    public void run() {
        // Primeira comunicação ao iniciar a thread
        SwingUtilities.invokeLater(() -> {
            texto.append(gerarMensagemComunicacao() + "\n");
            texto.append("Usuário conectado com sucesso\n");
        });

        while (true) {
            if (isListRequest) {
                // Nova comunicação ao solicitar a lista
                SwingUtilities.invokeLater(() -> {
                    texto.append(gerarMensagemComunicacao() + "\n");
                });

                try {
                    processList();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                isListRequest = false;
            } else {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void requestList() {
        isListRequest = true;
        synchronized (this) {
            notify();
        }
    }

    private void processList() throws IOException, ClassNotFoundException {

        try{

            Object receivedObject = in.readObject();
            if (receivedObject instanceof String) {
                SwingUtilities.invokeLater(() -> texto.append((String) receivedObject + "\n"));
            } else if (receivedObject instanceof List) {
                List<?> receivedList = (List<?>) receivedObject;

                processReceivedList(receivedList);
            } else {
                System.out.println("Nenhuma das opções do while");
            }
        }catch (Exception  e){
            System.out.println("Erro in processList() ==>> "+ e.getMessage());
        }


    }

    private void processReceivedList(List<?> receivedList) {
        if (!receivedList.isEmpty()) {
            if (receivedList.get(0) instanceof Object[]) {
                for (Object obj : receivedList) {
                    Object[] valores = (Object[]) obj;
                    int Id = (int) valores[0];
                    String nomeProduto = (String) valores[1];
                    Integer Quantidade = (Integer) valores[2];
                    BigDecimal precoVenda = (BigDecimal) valores[3];
                    String tipo = (String) valores[4];

                    SwingUtilities.invokeLater(() -> texto.append("ID: " + Id +", Nome: " + nomeProduto +", Quantidade: " + Quantidade +", Preço: " + precoVenda + ", Tipo Mov: " + tipo + "\n"));
                }
            } else if (receivedList.get(0) instanceof Movimento) {
                for (Object obj : receivedList) {
                    Movimento mov = (Movimento) obj;
                    SwingUtilities.invokeLater(() -> texto.append("Movimento: " + mov.getIdMovimento() + ", " + mov.getIdUsuario() + ", " + mov.getTipo() + ", " + mov.getIdPessoa() + ", " + mov.getIdProduto() + ", " + mov.getQuantidade() + ", " + mov.getValorUnitario() + "\n"));
                }
            } else {
                System.out.println("Tipo de resultado não esperado: " + receivedList.get(0).getClass().getName());
            }
        } else {
            System.out.println("A lista recebida está vazia.");
        }
    }

    private String gerarMensagemComunicacao() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataHoraAtual = dateFormat.format(new Date());
        return ">>Nova comunicação em " + dataHoraAtual;
    }
}
