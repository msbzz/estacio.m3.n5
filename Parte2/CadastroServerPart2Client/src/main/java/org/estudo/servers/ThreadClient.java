package org.estudo.servers;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadClient extends Thread {
    private ObjectInputStream in;
    private final JTextArea textArea;
    private JFrame frame;
    private BlockingQueue<String> messageQueue;
    private volatile boolean running = true; // Variável para controlar a execução da thread

    public ThreadClient(ObjectInputStream in) {
        this.in = in;
        this.messageQueue = new LinkedBlockingQueue<>();
        frame = new JFrame("Mensagens do Servidor");
        textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void addMessage(String message) {
        messageQueue.add(message);
    }

    public void terminate() {
        running= false;
        interrupt(); // Interrompe a thread
    }

    @Override
    public void run() {
        try {
            while (running) { // Verifica a variável running a cada iteração
                String message = messageQueue.take(); // Espera até que uma mensagem esteja disponível

                SwingUtilities.invokeLater(() -> {
                    textArea.append(message + "\n");
                    textArea.setCaretPosition(textArea.getDocument().getLength());
                });
            }
        }catch (InterruptedException e) {
            // Thread foi interrompida, sair do loop
            System.out.println("Thread foi interrompida, sair do loop ");

        } catch (Exception e) {
            // Lidar com exceções, se necessário
        }finally {

        }
    }
}
