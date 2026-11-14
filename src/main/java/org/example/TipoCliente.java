package org.example;

public enum TipoCliente {
    GENERAL(new TarifaGeneral()),
    ESTUDIANTE(new TarifaEstudiante()),
    NINO(new TarifaNino()),
    ADULTO_MAYOR(new TarifaAdultoMayor());

    private final Tarifa tarifa;

    TipoCliente(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public double getTarifa(double valorBase) {
        return tarifa.calcularValor(valorBase);
    }
}
