package org.example;

import java.util.List;

public class Cinema {
    private String nombre;
    private String nit;
    private String direccion;

    //RELACIONES
    private List<Sala> listaSalas;
    private List<Pelicula> listaPeliculas;
    private List<Cliente> listaClientes;

    public Cinema(String nombre, String nit, String direccion, List<Sala> listaSalas,
                  List<Pelicula> listaPeliculas, List<Cliente> listaClientes) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.listaSalas= listaSalas;
        this.listaPeliculas= listaPeliculas;
        this.listaClientes= listaClientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Sala> getListaSalas() {
        return listaSalas;
    }

    public void setListaSalas(List<Sala> listaSalas) {
        this.listaSalas = listaSalas;
    }

    public List<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    public void setListaPeliculas(List<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
}
