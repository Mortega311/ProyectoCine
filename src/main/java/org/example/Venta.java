package org.example;

import java.time.LocalDateTime;

public class Venta {
    private String codigo;
    private LocalDateTime fechaHora;
    private double valoPagado;

    //RELACIONES
    private EstadoVenta estadoVenta;

    public Venta(String codigo, LocalDateTime fechaHora, double valoPagado, EstadoVenta estadoVenta) {
        this.codigo = codigo;
        this.fechaHora = fechaHora;
        this.valoPagado = valoPagado;
        this.estadoVenta = estadoVenta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getValoPagado() {
        return valoPagado;
    }

    public void setValoPagado(double valoPagado) {
        this.valoPagado = valoPagado;
    }

    public EstadoVenta getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(EstadoVenta estadoVenta) {
        this.estadoVenta = estadoVenta;
    }
}
