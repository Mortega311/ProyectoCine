package org.example;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Cinema {
    private String nombre;
    private String nit;
    private String direccion;

    //RELACIONES
    private List<Sala> listaSalas;
    private List<Pelicula> listaPeliculas;
    private List<Cliente> listaClientes;
    private List<Funcion> listaFunciones;
    private List<Venta> listaVentas;

    public Cinema(String nombre, String nit, String direccion, List<Sala> listaSalas,
                  List<Pelicula> listaPeliculas, List<Cliente> listaClientes) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.listaSalas= listaSalas;
        this.listaPeliculas= listaPeliculas;
        this.listaClientes= listaClientes;
        this.listaFunciones = new ArrayList<>();
        this.listaVentas = new ArrayList<>();
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

    // Devuelve las peliculas registradas en el cine.
    public List<Pelicula> consultarPeliculas() {
        return listaPeliculas;
    }

    // Devuelve las salas registradas en el cine.
    public List<Sala> consultarSalas() {
        return listaSalas;
    }

    public void registrarPelicula(Pelicula pelicula) {
    }

    public void modificarPelicula(String codigo, Pelicula pelicula) {
    }

    public void deshabilitarPelicula(String codigo) {
    }

    public void registrarSala(Sala sala) {
    }

    public void modificarSala(String codigo, Sala sala) {
    }

    public void deshabilitarSala(String codigo) {
    }

    public void programarFuncion(Funcion funcion) {
    }

    public List<Funcion> consultarFunciones() {
        return listaFunciones;
    }

    public Pelicula buscarPelicula(String codigo) {
        for (Pelicula pelicula : listaPeliculas) {
            if (pelicula.getCodigo().equalsIgnoreCase(codigo)) {
                return pelicula;
            }
        }
        return null;
    }

    public Sala buscarSala(String codigo) {
        for (Sala sala : listaSalas) {
            if (sala.getCodigo().equalsIgnoreCase(codigo)) {
                return sala;
            }
        }
        return null;
    }

    public Funcion buscarFuncion(String codigo) {
        for (Funcion funcion : listaFunciones) {
            if (funcion.getCodigo().equalsIgnoreCase(codigo)) {
                return funcion;
            }
        }
        return null;
    }

    public List<Asiento> consultarDisponibilidad(String codigoFuncion) {
        Funcion funcion = buscarFuncion(codigoFuncion);
        if (funcion != null && funcion.getSala() != null) {
            return funcion.getSala().getListaAsientos();
        }
        return new ArrayList<>();
    }

    public void venderEntrada(String codigoFuncion, Cliente cliente, TipoCliente tipoEntrada,
                              String codigoAsiento) {
    }

    public void cancelarVenta(String codigoVenta) {
    }

    public Venta buscarVenta(String codigo) {
        for (Venta venta : listaVentas) {
            if (venta.getCodigo().equalsIgnoreCase(codigo)) {
                return venta;
            }
        }
        return null;
    }

    public void totalEntradasVendidas() {
    }

    public void totalIngresos() {
    }

    public void peliculaMasVendida() {
    }

    public String ocupacionFuncion(String codigoFuncion) {
        Funcion funcion = buscarFuncion(codigoFuncion);
        if (funcion == null) {
            return "Funcion no encontrada";
        }
        int ocupados = funcion.contarAsientosNoDisponibles();
        int total = funcion.contarAsientosDisponibles() + ocupados;
        return "Ocupados: " + ocupados + " de " + total;
    }

    public List<Funcion> funcionesProgramadas(LocalDate fecha) {
        List<Funcion> funcionesDeFecha = new ArrayList<>();
        for (Funcion funcion : listaFunciones) {
            if (funcion.getFecha().equals(fecha)) {
                funcionesDeFecha.add(funcion);
            }
        }
        return funcionesDeFecha;
    }
    
}
