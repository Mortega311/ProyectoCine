package org.example;

import org.example.excepcions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String nombre;
    private String nit;
    private String direccion;


    private List<Pelicula> listaPeliculas;
    private List<Sala> listaSalas;
    private List<Funcion> listaFunciones;
    private List<Venta> listaVentas;


    public Cinema(String nombre, String nit, String direcciona) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.listaPeliculas = new ArrayList<>();
        this.listaSalas = new ArrayList<>();
        this.listaFunciones = new ArrayList<>();
        this.listaVentas = new ArrayList<>();
    }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNit() { return nit; }
    public void setNit(String nit) { this.nit = nit; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public List<Pelicula> getListaPeliculas() { return listaPeliculas; }
    public void setListaPeliculas(List<Pelicula> listaPeliculas) { this.listaPeliculas = listaPeliculas; }

    public List<Sala> getListaSalas() { return listaSalas; }
    public void setListaSalas(List<Sala> listaSalas) { this.listaSalas = listaSalas; }

    public List<Funcion> getListaFunciones() { return listaFunciones; }
    public void setListaFunciones(List<Funcion> listaFunciones) { this.listaFunciones = listaFunciones; }

    public List<Venta> getListaVentas() { return listaVentas; }
    public void setListaVentas(List<Venta> listaVentas) { this.listaVentas = listaVentas; }

    public void registrarPelicula(Pelicula nuevaPelicula) throws PeliculaDuplicadaException {
        int cantidadPeliculasRegistradas = 0;
        for (Pelicula peliculaIterada : listaPeliculas) {
            if (peliculaIterada != null) {
                cantidadPeliculasRegistradas++;
            }
            if (peliculaIterada.getCodigo().equalsIgnoreCase(nuevaPelicula.getCodigo())) {
                throw new PeliculaDuplicadaException("Ya existe una película registrada con el código: " + nuevaPelicula.getCodigo());
            }
        }
        listaPeliculas.add(nuevaPelicula);
    }

    public List<Pelicula> consultarPeliculas() {
        return listaPeliculas;
    }

    public Pelicula buscarPelicula(String codigoBuscado) {
        Pelicula peliculaEncontrada = null;
        for (Pelicula peliculaIterada : listaPeliculas) {
            if (peliculaIterada.getCodigo().equalsIgnoreCase(codigoBuscado)) {
                peliculaEncontrada = peliculaIterada;
                break;
            }
        }
        return peliculaEncontrada;
    }

    public void modificarPelicula(String codigoBuscado, Pelicula datosNuevos) throws Exception {
        Pelicula peliculaExistente = buscarPelicula(codigoBuscado);
        if (peliculaExistente == null) {
            throw new Exception("La película con código " + codigoBuscado + " no existe.");
        }
        peliculaExistente.setNombre(datosNuevos.getNombre());
        peliculaExistente.setGenero(datosNuevos.getGenero());
        peliculaExistente.setDuracion(datosNuevos.getDuracion());
        peliculaExistente.setClasificacionPelicula(datosNuevos.getClasificacionPelicula());
        peliculaExistente.setDisponible(datosNuevos.isDisponible());
    }

    public void deshabilitarPelicula(String codigoBuscado) throws Exception {
        Pelicula peliculaExistente = buscarPelicula(codigoBuscado);
        if (peliculaExistente == null) {
            throw new Exception("La película con código " + codigoBuscado + " no existe.");
        }
        peliculaExistente.setDisponible(false);
    }
    public void registrarSala(Sala nuevaSala) throws SalaDuplicadaException {
        for (Sala salaIterada : listaSalas) {
            if (salaIterada.getCodigo().equalsIgnoreCase(nuevaSala.getCodigo())) {
                throw new SalaDuplicadaException("Ya existe una sala registrada con el código: " + nuevaSala.getCodigo());
            }
        }
        listaSalas.add(nuevaSala);
    }

    public List<Sala> consultarSalas() {
        return listaSalas;
    }

    public Sala buscarSala(String codigoBuscado) {
        Sala salaEncontrada = null;
        for (Sala salaIterada : listaSalas) {
            if (salaIterada.getCodigo().equalsIgnoreCase(codigoBuscado)) {
                salaEncontrada = salaIterada;
                break;
            }
        }
        return salaEncontrada;
    }

    public void modificarSala(String codigoBuscado, Sala datosNuevos) throws Exception {
        Sala salaExistente = buscarSala(codigoBuscado);
        if (salaExistente == null) {
            throw new Exception("La sala con código " + codigoBuscado + " no existe.");
        }
        salaExistente.setNombre(datosNuevos.getNombre());
        salaExistente.setCapacidad(datosNuevos.getCapacidad());
        salaExistente.setEstadoSala(datosNuevos.isEstadoSala());
    }

    public void deshabilitarSala(String codigoBuscado) throws Exception {
        Sala salaExistente = buscarSala(codigoBuscado);
        if (salaExistente == null) {
            throw new Exception("La sala con código " + codigoBuscado + " no existe.");
        }
        salaExistente.setEstadoSala(false);
    }



    public void programarFuncion(Funcion nuevaFuncion) throws SalaNoDisponibleException {
        if (!nuevaFuncion.getSala().isEstadoSala()) {
            throw new SalaNoDisponibleException("No se puede programar la función. La sala se encuentra fuera de servicio.");
        }
        listaFunciones.add(nuevaFuncion);
    }

    public List<Funcion> consultarFunciones() {
        return listaFunciones;
    }

    public Funcion buscarFuncion(String codigoBuscado) {
        Funcion funcionEncontrada = null;
        for (Funcion funcionIterada : listaFunciones) {
            if (funcionIterada.getCodigo().equalsIgnoreCase(codigoBuscado)) {
                funcionEncontrada = funcionIterada;
                break;
            }
        }
        return funcionEncontrada;
    }



    public Venta venderEntrada(String codigoFuncion, Cliente clienteComprador, String codigoAsientoSeleccionado)
            throws FuncionNoEncontradaException, AsientoNoDisponibleException, AsientoOcupadoException {

        Funcion funcionSeleccionada = buscarFuncion(codigoFuncion);
        if (funcionSeleccionada == null) {
            throw new FuncionNoEncontradaException("La función con código " + codigoFuncion + " no existe.");
        }

        if (funcionSeleccionada.contarAsientosDisponibles() <= 0) {
            throw new AsientoNoDisponibleException("No existen asientos disponibles para la función seleccionada.");
        }

        Asiento asientoEncontrado = funcionSeleccionada.buscarAsiento(codigoAsientoSeleccionado);
        if (asientoEncontrado == null) {
            throw new AsientoNoDisponibleException("El asiento " + codigoAsientoSeleccionado + " no se encuentra en esta función.");
        }

        if (!asientoEncontrado.isDisponible()) {
            throw new AsientoOcupadoException("El asiento " + codigoAsientoSeleccionado + " ya se encuentra ocupado.");
        }

        funcionSeleccionada.ocuparAsiento(codigoAsientoSeleccionado);


        double valorPagadoPorEntrada = clienteComprador.getTipoCliente().getTarifa(funcionSeleccionada.getValorBase());


        int totalVentasRegistradas = 0;
        for (Venta ventaIterada : listaVentas) {
            if (ventaIterada != null) {
                totalVentasRegistradas++;
            }
        }

        String codigoVentaGenerado = "VENTA-" + (totalVentasRegistradas + 1);
        Venta nuevaVenta = new Venta(codigoVentaGenerado, clienteComprador, funcionSeleccionada, asientoEncontrado, LocalDateTime.now(), valorPagadoPorEntrada, true);        listaVentas.add(nuevaVenta);

        return nuevaVenta;
    }

    public void cancelarVenta(String codigoVentaBuscada) throws VentaNoEncontradaException {
        Venta ventaEncontrada = buscarVenta(codigoVentaBuscada);
        if (ventaEncontrada == null || !ventaEncontrada.isActiva()) {
            throw new VentaNoEncontradaException("La venta con código " + codigoVentaBuscada + " no existe o ya ha sido cancelada.");
        }
        ventaEncontrada.cancelar();
    }

    public Venta buscarVenta(String codigoBuscado) {
        Venta ventaEncontrada = null;
        for (Venta ventaIterada : listaVentas) {
            if (ventaIterada.getCodigo().equalsIgnoreCase(codigoBuscado)) {
                ventaEncontrada = ventaIterada;
                break;
            }
        }
        return ventaEncontrada;
    }

    public int totalEntradasVendidas() {
        int contadorEntradasActivas = 0;
        for (Venta ventaIterada : listaVentas) {
            if (ventaIterada.isActiva()) {
                contadorEntradasActivas++;
            }
        }
        return contadorEntradasActivas;
    }

    public double totalIngresos() {
        double acumuladorIngresosTotales = 0.0;
        for (Venta ventaIterada : listaVentas) {
            if (ventaIterada.isActiva()) {
                acumuladorIngresosTotales += ventaIterada.getValorPagado();
            }
        }
        return acumuladorIngresosTotales;
    }

    public Pelicula peliculaMasVendida() {
        if (listaPeliculas.isEmpty()) {
            return null;
        }

        Pelicula peliculaConMayorVenta = null;
        int maximoVentasEncontradas = -1;

        for (Pelicula peliculaIterada : listaPeliculas) {
            int contadorVentasPorPelicula = 0;
            for (Venta ventaIterada : listaVentas) {
                if (ventaIterada.isActiva() &&
                        ventaIterada.getFuncion().getPelicula().getCodigo().equalsIgnoreCase(peliculaIterada.getCodigo())) {
                    contadorVentasPorPelicula++;
                }
            }
            if (contadorVentasPorPelicula > maximoVentasEncontradas) {
                maximoVentasEncontradas = contadorVentasPorPelicula;
                peliculaConMayorVenta = peliculaIterada;
            }
        }
        return peliculaConMayorVenta;
    }

    public String ocupacionFuncion(String codigoFuncion) throws FuncionNoEncontradaException {
        Funcion funcionEncontrada = buscarFuncion(codigoFuncion);
        if (funcionEncontrada == null) {
            throw new FuncionNoEncontradaException("La función " + codigoFuncion + " no existe.");
        }
        int asientosDisponibles = funcionEncontrada.contarAsientosDisponibles();
        int asientosOcupados = funcionEncontrada.contarAsientosNoDisponibles();
        return "Asientos Disponibles: " + asientosDisponibles + " | Asientos Ocupados: " + asientosOcupados;
    }

    public List<Funcion> funcionesProgramadas(LocalDate fechaBuscada) {
        List<Funcion> funcionesFiltradas = new ArrayList<>();
        for (Funcion funcionIterada : listaFunciones) {
            if (funcionIterada.getFecha().equals(fechaBuscada) && funcionIterada.getEstadoFuncion() == EstadoFuncion.PROGRAMADA) {
                funcionesFiltradas.add(funcionIterada);
            }
        }
        return funcionesFiltradas;
    }


}