package org.example;

import java.time.LocalTime;
import java.util.List;

public class Pelicula {
    private String codigo;
    private String nombre;
    private String genero;
    private LocalTime duracion;
    private boolean disponible;

    //RELACIONES
    private ClasificacionPelicula clasificacionPelicula;
    private List<Funcion> listaFunciones;


    public Pelicula(String codigo, String nombre, String genero, LocalTime duracion,
                    boolean disponible, ClasificacionPelicula clasificacionPelicula,
                    List<Funcion> listaFunciones) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.duracion = duracion;
        this.disponible = disponible;
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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public ClasificacionPelicula getClasificacionPelicula() {
        return clasificacionPelicula;
    }

    public void setClasificacionPelicula(ClasificacionPelicula clasificacionPelicula) {
        this.clasificacionPelicula = clasificacionPelicula;
    }

    public List<Funcion> getListaFunciones() {
        return listaFunciones;
    }

    public void setListaFunciones(List<Funcion> listaFunciones) {
        this.listaFunciones = listaFunciones;
    }


}
