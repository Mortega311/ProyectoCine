package org.example;

public class Asiento {
    private String codigo;
    private int fila;
    private int numero;
    private boolean disponible;

    public Asiento(String codigo, int fila, int numero, boolean disponible) {
        this.codigo = codigo;
        this.fila = fila;
        this.numero = numero;
        this.disponible = disponible;
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

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
