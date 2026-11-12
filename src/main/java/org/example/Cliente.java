package org.example;

import java.util.List;

public class Cliente {

   private String identificacion;
   private String nombre;

   //RELACIONES
   private List<Venta> listaVentas;
   private TipoCliente tipoCliente;


   public Cliente(String identificacion,String nombre,
                  List<Venta> listaVentas,TipoCliente tipoCliente) {
      this.tipoCliente = tipoCliente;
      this.identificacion=identificacion;
      this.nombre= nombre;
      this.listaVentas= listaVentas;
   }

   public TipoCliente getTipoCliente() {
      return tipoCliente;
   }

   public void setTipoCliente(TipoCliente tipoCliente) {
      this.tipoCliente = tipoCliente;
   }

   public String getIdentificacion() {
      return identificacion;
   }

   public void setIdentificacion(String identificacion) {
      this.identificacion = identificacion;
   }

   public String getNombre() {
      return nombre;
   }

   public void setNombre(String nombre) {
      this.nombre = nombre;
   }

   public List<Venta> getListaVentas() {
      return listaVentas;
   }

   public void setListaVentas(List<Venta> listaVentas) {
      this.listaVentas = listaVentas;
   }
}
