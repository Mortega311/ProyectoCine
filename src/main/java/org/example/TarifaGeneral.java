package org.example;

public class TarifaGeneral implements Tarifa {
    private double descuento;

    public TarifaGeneral() {
        this.descuento = 0.00;
    }

    @Override
    public double calcularValor(double valorBase) {
        return valorBase * (1 - descuento);
    }
}
