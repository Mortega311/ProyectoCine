package org.example;

public enum TipoCliente {
    GENERAL,
    ESTUDIANTE,
    NIÑO,
    ADULTO_MAYOR;


    public double calcularValor(double valorBase) {
        double valorFinal = valorBase;
        if (this == GENERAL) {
            valorFinal = valorBase;
        } else {
            if (this == ESTUDIANTE) {
                valorFinal = valorBase * 0.80; // 20% de descuento
            } else {
                if (this == NIÑO) {
                    valorFinal = valorBase * 0.70; // 30% de descuento
                } else {
                    if (this == ADULTO_MAYOR) {
                        valorFinal = valorBase * 0.75; // 25% de descuento
                    }
                }
            }
        }
        return valorFinal;
    }
    }
