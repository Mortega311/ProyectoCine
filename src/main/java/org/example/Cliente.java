package org.example;

import java.util.List;

public class Cliente extends Persona {

   private TipoCliente tipoCliente;

   //RELACIONES
   private List<Venta> listaVentas;


   public Cliente(TipoCliente tipoCliente,String identificacion,
                  String nombre) {
      super(identificacion, nombre);
      this.tipoCliente = tipoCliente;
   }

   public TipoCliente getTipoCliente() {
      return tipoCliente;
   }

   public void setTipoCliente(TipoCliente tipoCliente) {
      this.tipoCliente = tipoCliente;
   }
}
