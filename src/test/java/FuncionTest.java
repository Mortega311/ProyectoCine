import org.example.ClasificacionPelicula;
import org.example.EstadoFuncion;
import org.example.Funcion;
import org.example.Pelicula;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionTest {
    Pelicula pelicula = new Pelicula("01", "Tom y jerry", "Comedia", LocalTime.now(), true,  ClasificacionPelicula.MAYORES_7, null );
    Funcion funcion = new Funcion("01", LocalDate.now(), LocalTime.now(), 10000, EstadoFuncion.PROGRAMADA, pelicula);
    @Test
    void verificarFuncion(){
        assertEquals("01", funcion.getCodigo());
        assertEquals(10000, funcion.getValorBase());
        assertEquals(EstadoFuncion.PROGRAMADA, funcion.getEstadoFuncion());
    }

}
