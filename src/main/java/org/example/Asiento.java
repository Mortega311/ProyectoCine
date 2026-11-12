package org.example;

public class Asiento {
    private String codigo;
    private int fila;
    private int numero;
    private boolean estadoAsiento;

    public Asiento(String codigo, int fila, int numero, boolean estadoAsiento) {
        this.codigo = codigo;
        this.fila = fila;
        this.numero = numero;
        this.estadoAsiento = estadoAsiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isEstadoAsiento() {
        return estadoAsiento;
    }

    public void setEstadoAsiento(boolean estadoAsiento) {
        this.estadoAsiento = estadoAsiento;
    }
}
