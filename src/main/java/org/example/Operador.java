package org.example;

public class Operador extends Persona {
    private String codigoEmpleado;
    private String turno;

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Operador(String identificacion, String nombre, String codigoEmpleado, String turno) {
        super(identificacion, nombre);
        this.codigoEmpleado = codigoEmpleado;
        this.turno = turno;


    }
}
