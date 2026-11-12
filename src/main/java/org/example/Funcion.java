package org.example;

import java.time.LocalDate;
import java.time.LocalTime;

public class Funcion {
    private String codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private double valorBase;

    //RELACIONES
    private EstadoFuncion estadoFuncion;


    public Funcion(String codigo, LocalDate fecha, LocalTime hora, double valorBase,
    EstadoFuncion estadoFuncion) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.valorBase = valorBase;
        this.estadoFuncion= estadoFuncion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public EstadoFuncion getEstadoFuncion() {
        return estadoFuncion;
    }

    public void setEstadoFuncion(EstadoFuncion estadoFuncion) {
        this.estadoFuncion = estadoFuncion;
    }
}
