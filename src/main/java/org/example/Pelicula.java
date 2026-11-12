package org.example;

import java.time.LocalTime;
import java.util.List;

public class Pelicula {
    private String codigo;
    private String nombre;
    private String genero;
    private LocalTime duracion;

    //RELACIONES
    private EstadoPelicula estadoPelicula;
    private ClasificacionPelicula clasificacionPelicula;
    private List<Funcion> listaFunciones;


    public Pelicula(String codigo, String nombre, String genero, LocalTime duracion,
                    EstadoPelicula estadoPelicula, ClasificacionPelicula clasificacionPelicula,
                    List<Funcion> listaFunciones) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.duracion = duracion;
        this.estadoPelicula = estadoPelicula;
        this.clasificacionPelicula= clasificacionPelicula;
        this.listaFunciones= listaFunciones;

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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public EstadoPelicula getEstadoPelicula() {
        return estadoPelicula;
    }

    public void setEstadoPelicula(EstadoPelicula estadoPelicula) {
        this.estadoPelicula = estadoPelicula;
    }
}
