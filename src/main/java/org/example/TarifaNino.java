package org.example;

public class TarifaNino implements Tarifa {
    private double descuento;

    public TarifaNino() {
        this.descuento = 0.33;
    }

    @Override
    public double calcularValor(double valorBase) {
        return valorBase * (1 - descuento);
    }
}
