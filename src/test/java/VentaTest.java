import org.example.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VentaTest{

    @Test
    void calcularTarifasSegunTipoCliente() {
        double valorBase = 10000.0;

        // General: 0% descuento -> 10000.0
        assertEquals(10000.0, TipoCliente.GENERAL.getTarifa(valorBase));

        // Estudiante: 20% descuento -> 8000.0
        assertEquals(8000.0, TipoCliente.ESTUDIANTE.getTarifa(valorBase));

        // Niño: 33% descuento -> 6700.0
        assertEquals(6700.0, TipoCliente.NINO.getTarifa(valorBase));

        // Adulto Mayor: 27% descuento -> 7300.0
        assertEquals(7300.0, TipoCliente.ADULTO_MAYOR.getTarifa(valorBase));
    }

    @Test
    void cancelarVentaCambiaEstadoAInactiva() {
        Cliente cliente = new Cliente("1001", "Laura", TipoCliente.GENERAL);
        Asiento asiento = new Asiento("A1", 1, 1, false);
        Venta venta = new Venta("VENTA-1", cliente, null, asiento, LocalDateTime.now(), 10000.0, true);

        assertTrue(venta.isActiva());

        venta.cancelar();

        assertEquals(false, venta.isActiva());
    }
}