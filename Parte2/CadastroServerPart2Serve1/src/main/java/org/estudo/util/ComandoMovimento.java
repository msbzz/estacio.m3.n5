package org.estudo.util;

import org.estudo.model.Movimento;

import java.io.Serializable;

public class ComandoMovimento implements Serializable {
    private String comando;
    private Movimento movimento;

    public ComandoMovimento(String comando, Movimento movimento) {
        this.comando = comando;
        this.movimento = movimento;
    }

    // Getters e setters
    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public Movimento getMovimento() {
        return movimento;
    }

    public void setMovimento(Movimento movimento) {
        this.movimento = movimento;
    }
}
