package org.estudo.gui;

import javax.swing.*;

public class SaidaFrame extends JFrame { // Alterado de JDialog para JFrame

    public JTextArea texto;

    public SaidaFrame() {
        texto = new JTextArea(20, 50);
        JScrollPane scrollPane = new JScrollPane(texto);
        add(scrollPane);

        setTitle("SaidaFrame");  // Definir um título para o JFrame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define o comportamento de fechamento

        setBounds(100, 100, 600, 700);
        setVisible(true);
    }
}
