package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Funcion {
    private String codigo;
    private LocalDate fecha;
    private LocalTime hora;
    private double valorBase;
    private EstadoFuncion estadoFuncion;


    //RELACIONES
    private Sala sala;
    private Pelicula pelicula;


    public Funcion(String codigo, LocalDate fecha, LocalTime hora, double valorBase, EstadoFuncion disponible, Pelicula pelicula) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.valorBase = valorBase;
        this.estadoFuncion = disponible;
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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public EstadoFuncion getEstadoFuncion() {
        return estadoFuncion;
    }

    public void setEstadoFuncion(EstadoFuncion estadoFuncion) {
        this.estadoFuncion = estadoFuncion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Asiento buscarAsiento (String codigo) {
        List<Asiento> listaAsientos = sala.getListaAsientos();
        for (Asiento asiento : listaAsientos) {
            if (asiento.getCodigo().equalsIgnoreCase(codigo)) {
                return asiento;
            }
        }
        return null;
    }
    public boolean ocuparAsiento (String codigo) {
        Asiento asiento = buscarAsiento(codigo);
        if (asiento != null) {
            if (asiento.isDisponible()) {
                asiento.setDisponible(false);
                return true;
            }
        }
        return false;
    }
    public boolean liberarAsiento (String codigo){
        Asiento asiento = buscarAsiento(codigo);
            if (asiento != null) {
                if (!asiento.isDisponible()) {
                    asiento.setDisponible(true);
                    return true;
                }
            }
        return false;
    }
    public List<Asiento> obtenerAsientosDisponibles (){
        List<Asiento> listaAsientosDisponibles = new ArrayList<>();
        List<Asiento> listaAsientos= sala.getListaAsientos();
        for (Asiento asiento: listaAsientos){
            if (asiento.isDisponible()){
                listaAsientosDisponibles.add(asiento);
            }
        }
        return listaAsientosDisponibles;
    }
    public int contarAsientosDisponibles() {
        return obtenerAsientosDisponibles().size();
    }

    public int contarAsientosNoDisponibles() {
        return sala.getListaAsientos().size() - contarAsientosDisponibles();
    }
}
