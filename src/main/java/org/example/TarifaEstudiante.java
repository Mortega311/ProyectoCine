package org.example;

public class TarifaEstudiante implements Tarifa {
    private double descuento;

    public TarifaEstudiante() {
        this.descuento = 0.20;
    }

    @Override
    public double calcularValor(double valorBase) {
        return valorBase * (1 - descuento);
    }
}
