package org.example;

import java.time.LocalDateTime;

public class Venta {
    private String codigo;
    private LocalDateTime fechaHora;
    private double valoPagado;
    private boolean estadoVenta;

    public Venta(String codigo, LocalDateTime fechaHora, double valoPagado, boolean estadoVenta) {
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

    public boolean isEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(boolean estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public void cancelar() {
        if (this.estadoVenta == true) {
            this.estadoVenta = false; // Cambia el estado a cancelada
            System.out.println("La venta con código " + this.codigo + " ha sido cancelada correctamente.");
        } else {
            System.out.println("Error: La venta con código " + this.codigo + " ya se encuentra cancelada.");
        }
    }
}
