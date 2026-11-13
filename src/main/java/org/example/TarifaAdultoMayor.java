package org.example;

public class TarifaAdultoMayor implements Tarifa {
    private double descuento;

    public TarifaAdultoMayor() {
        this.descuento = 0.27;
    }

    @Override
    public double calcularValor(double valorBase) {
        return valorBase * (1 - descuento);
    }
}
