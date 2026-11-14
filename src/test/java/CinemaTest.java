import org.example.*;
import org.example.excepcions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CinemaTest {

    // Método auxiliar para crear una sala con 2 asientos listos para las pruebas
    private Sala crearSalaDePrueba(boolean disponible) {
        List<Asiento> asientos = new ArrayList<>();
        asientos.add(new Asiento("A1", 1, 1, true));
        asientos.add(new Asiento("A2", 1, 2, true));
        return new Sala("S01", "Sala 1", 2, disponible, asientos);
    }

    @Test
    void registrarPeliculaExitoso() throws PeliculaDuplicadaException {
        Cinema cinema = new Cinema("CINEMAUQ", "900123", "Armenia");
        Pelicula pelicula = new Pelicula("P01", "Interestelar", "Ciencia Ficción",
                LocalTime.of(2, 49), true, ClasificacionPelicula.MAYORES_12, new ArrayList<>());

        cinema.registrarPelicula(pelicula);

        assertEquals(1, cinema.consultarPeliculas().size());
        assertTrue(cinema.buscarPelicula("P01").isDisponible());
    }

    @Test
    void registrarPeliculaDuplicadaLanzaExcepcion() throws PeliculaDuplicadaException {
        Cinema cinema = new Cinema("CINEMAUQ", "900123", "Armenia");
        Pelicula pelicula1 = new Pelicula("P01", "Interestelar", "Ciencia Ficción",
                LocalTime.of(2, 49), true, ClasificacionPelicula.MAYORES_12, new ArrayList<>());
        Pelicula peliculaRepetida = new Pelicula("P01", "Otra Película", "Drama",
                LocalTime.of(1, 30), true, ClasificacionPelicula.TODOS, new ArrayList<>());

        cinema.registrarPelicula(pelicula1);

        assertThrows(PeliculaDuplicadaException.class, () -> {
            cinema.registrarPelicula(peliculaRepetida);
        });
    }

    @Test
    void registrarSalaDuplicadaLanzaExcepcion() throws SalaDuplicadaException {
        Cinema cinema = new Cinema("CINEMAUQ", "900123", "Armenia");
        Sala sala1 = crearSalaDePrueba(true);
        Sala salaRepetida = crearSalaDePrueba(true);

        cinema.registrarSala(sala1);

        assertThrows(SalaDuplicadaException.class, () -> {
            cinema.registrarSala(salaRepetida);
        });
    }

    @Test
    void programarFuncionEnSalaFueraDeServicioLanzaExcepcion() {
        Cinema cinema = new Cinema("CINEMAUQ", "900123", "Armenia");
        Sala salaFueraDeServicio = crearSalaDePrueba(false); // false = fuera de servicio
        Pelicula pelicula = new Pelicula("P01", "Interestelar", "Ciencia Ficción",
                LocalTime.of(2, 49), true, ClasificacionPelicula.MAYORES_12, new ArrayList<>());

        Funcion funcion = new Funcion("F01", LocalDate.now(), LocalTime.of(18, 0),
                10000.0, EstadoFuncion.PROGRAMADA, pelicula);
        funcion.setSala(salaFueraDeServicio);

        assertThrows(SalaNoDisponibleException.class, () -> {
            cinema.programarFuncion(funcion);
        });
    }

    @Test
    void venderEntradaExitosoYReportes() throws Exception {
        Cinema cinema = new Cinema("CINEMAUQ", "900123", "Armenia");
        Sala sala = crearSalaDePrueba(true);
        Pelicula pelicula = new Pelicula("P01", "Interestelar", "Ciencia Ficción",
                LocalTime.of(2, 49), true, ClasificacionPelicula.MAYORES_12, new ArrayList<>());

        cinema.registrarPelicula(pelicula);
        cinema.registrarSala(sala);

        Funcion funcion = new Funcion("F01", LocalDate.now(), LocalTime.of(18, 0),
                10000.0, EstadoFuncion.PROGRAMADA, pelicula);
        funcion.setSala(sala);
        funcion.setPelicula(pelicula);
        cinema.programarFuncion(funcion);

        // Cliente estudiante tiene 20% de descuento sobre 10000 -> paga 8000
        Cliente cliente = new Cliente("1001", "Juan Pérez", TipoCliente.ESTUDIANTE);
        Venta venta = cinema.venderEntrada("F01", cliente, "A1");

        assertTrue(venta.isActiva());
        assertEquals(8000.0, venta.getValorPagado());
        assertEquals(1, cinema.totalEntradasVendidas());
        assertEquals(8000.0, cinema.totalIngresos());
    }

    @Test
    void venderEntradaEnAsientoOcupadoLanzaExcepcion() throws Exception {
        Cinema cinema = new Cinema("CINEMAUQ", "900123", "Armenia");
        Sala sala = crearSalaDePrueba(true);
        Pelicula pelicula = new Pelicula("P01", "Interestelar", "Ciencia Ficción",
                LocalTime.of(2, 49), true, ClasificacionPelicula.MAYORES_12, new ArrayList<>());

        Funcion funcion = new Funcion("F01", LocalDate.now(), LocalTime.of(18, 0),
                10000.0, EstadoFuncion.PROGRAMADA, pelicula);
        funcion.setSala(sala);
        cinema.programarFuncion(funcion);

        Cliente cliente1 = new Cliente("1001", "Juan", TipoCliente.GENERAL);
        Cliente cliente2 = new Cliente("1002", "Ana", TipoCliente.GENERAL);

        // Primera compra ocupa el asiento A1
        cinema.venderEntrada("F01", cliente1, "A1");

        // Segunda compra en el mismo asiento A1 debe lanzar AsientoOcupadoException
        assertThrows(AsientoOcupadoException.class, () -> {
            cinema.venderEntrada("F01", cliente2, "A1");
        });
    }

    @Test
    void venderEntradaParaFuncionInexistenteLanzaExcepcion() {
        Cinema cinema = new Cinema("CINEMAUQ", "900123", "Armenia");
        Cliente cliente = new Cliente("1001", "Juan", TipoCliente.GENERAL);

        assertThrows(FuncionNoEncontradaException.class, () -> {
            cinema.venderEntrada("FUNCION_FALSA", cliente, "A1");
        });
    }

    @Test
    void cancelarVentaInexistenteLanzaExcepcion() {
        Cinema cinema = new Cinema("CINEMAUQ", "900123", "Armenia");

        assertThrows(VentaNoEncontradaException.class, () -> {
            cinema.cancelarVenta("VENTA-999");
        });
    }
}