package org.example;

import java.util.List;

public class Sala {
    private String codigo;
    private String nombre;
    private int capacidad;

   //RELACIONES
    private EstadoSala estadoSala;
    private List<Asiento> listaAsientos;


    public Sala(String codigo, String nombre, int capacidad, EstadoSala estadoSala,
                List<Asiento> listaAsientos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.estadoSala = estadoSala;
        this.listaAsientos= listaAsientos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public EstadoSala getEstadoSala() {
        return estadoSala;
    }

    public void setEstadoSala(EstadoSala estadoSala) {
        this.estadoSala = estadoSala;
    }
}
