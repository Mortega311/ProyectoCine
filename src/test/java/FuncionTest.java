import org.example.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionTest {

    private Funcion crearFuncionConAsientos() {
        List<Asiento> asientos = new ArrayList<>();
        asientos.add(new Asiento("A1", 1, 1, true)); // Disponible
        asientos.add(new Asiento("A2", 1, 2, true)); // Disponible

        Sala sala = new Sala("S01", "Sala 1", 2, true, asientos);
        Pelicula pelicula = new Pelicula("01", "Tom y Jerry", "Comedia",
                LocalTime.of(1, 30), true, ClasificacionPelicula.MAYORES_7, new ArrayList<>());

        Funcion funcion = new Funcion("F01", LocalDate.now(), LocalTime.now(),
                10000.0, EstadoFuncion.PROGRAMADA, pelicula);
        funcion.setSala(sala);
        funcion.setPelicula(pelicula);
        return funcion;
    }

    @Test
    void verificarDatosBasicosFuncion() {
        Funcion funcion = crearFuncionConAsientos();

        assertEquals("F01", funcion.getCodigo());
        assertEquals(10000.0, funcion.getValorBase());
        assertEquals(EstadoFuncion.PROGRAMADA, funcion.getEstadoFuncion());
    }

    @Test
    void ocuparAsientoDisponibleRetornaTrue() {
        Funcion funcion = crearFuncionConAsientos();

        boolean resultado = funcion.ocuparAsiento("A1");

        assertTrue(resultado);
        assertEquals(1, funcion.contarAsientosDisponibles());
        assertEquals(1, funcion.contarAsientosNoDisponibles());
    }

    @Test
    void liberarAsientoOcupadoRetornaTrue() {
        Funcion funcion = crearFuncionConAsientos();
        funcion.ocuparAsiento("A1"); // Primero lo ocupamos

        boolean liberado = funcion.liberarAsiento("A1");

        assertTrue(liberado);
        assertTrue(funcion.buscarAsiento("A1").isDisponible());
        assertEquals(2, funcion.contarAsientosDisponibles());
    }
}