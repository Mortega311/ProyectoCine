package org.example;

import java.time.LocalDateTime;

public class Venta{
    private String codigo;
    private Cliente cliente;
    private Funcion funcion;
    private Asiento asiento;
    private LocalDateTime fechaHora;
    private double valorPagado;
    private boolean activa;

    public Venta(String codigo, Cliente cliente, Funcion funcion, Asiento asiento, LocalDateTime fechaHora, double valorPagado, boolean activa) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.funcion = funcion;
        this.asiento = asiento;
        this.fechaHora = fechaHora;
        this.valorPagado = valorPagado;
        this.activa = activa;
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

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public void setAsiento(Asiento asiento) {
        this.asiento = asiento;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }



    public void cancelar() {
        if (this.activa == true) {
            this.activa = false; // Cambia el estado a cancelada
            System.out.println("La venta con código " + this.codigo + " ha sido cancelada correctamente.");
        } else {
            System.out.println("Error: La venta con código " + this.codigo + " ya se encuentra cancelada.");
        }
    }
}
