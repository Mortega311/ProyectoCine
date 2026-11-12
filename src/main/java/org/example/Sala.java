package org.example;

import java.util.List;

public class Sala {
    private String codigo;
    private String nombre;
    private int capacidad;
    private boolean estadoSala;

    //RELACIONES

    private List<Asiento> listaAsientos;


    public Sala(String codigo, String nombre, int capacidad, boolean estadoSala,
                List<Asiento> listaAsientos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.estadoSala = estadoSala;
        this.listaAsientos = listaAsientos;
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

    public boolean isEstadoSala() {
        return estadoSala;
    }

    public void setEstadoSala(boolean estadoSala) {
        this.estadoSala = estadoSala;
    }

    public List<Asiento> getListaAsientos() {
        return listaAsientos;
    }

    public void setListaAsientos(List<Asiento> listaAsientos) {
        this.listaAsientos = listaAsientos;
    }


    public void generarAsientos() {
        Asiento[][] asientos = new Asiento[0][];
        int filas = asientos.length;
        int columnas = asientos[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                char letraFila = (char) ('A' + i);
                String codigoAsiento = letraFila + String.valueOf(j + 1);

                // Se instancia cada asiento en la posición [i][j] de la matriz
                asientos[i][j] = new Asiento(codigoAsiento, i + 1, j + 1, true);
            }
        }
    }
}

