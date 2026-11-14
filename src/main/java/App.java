import org.example.*;
import org.example.excepcions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class App {
    private Cinema cinema;

    public static void main(String[] args) {
        App app = new App();

        // 1. Inicializar la clase principal Cinema (3 parámetros)
        app.cinema = new Cinema("CINEMAUQ", "900123456-7", "Universidad del Quindío");

        // 2. Crear datos iniciales de prueba
        try {
            Pelicula pelicula1 = new Pelicula("P01", "Interestelar", "Ciencia Ficción",
                    LocalTime.of(2, 49), true, ClasificacionPelicula.MAYORES_12, new ArrayList<>());
            Pelicula pelicula2 = new Pelicula("P02", "El Padrino", "Drama",
                    LocalTime.of(2, 55), true, ClasificacionPelicula.MAYORES_18, new ArrayList<>());
            app.cinema.registrarPelicula(pelicula1);
            app.cinema.registrarPelicula(pelicula2);

            Sala sala1 = new Sala("S01", "Sala 1", 10, true, new ArrayList<>());
            sala1.generarAsientos();
            app.cinema.registrarSala(sala1);

            Funcion funcion1 = new Funcion("F01", LocalDate.now(), LocalTime.of(18, 30),
                    15000.0, EstadoFuncion.PROGRAMADA, pelicula1);
            funcion1.setSala(sala1);
            funcion1.setPelicula(pelicula1);
            app.cinema.programarFuncion(funcion1);

            Cliente cliente1 = new Cliente("1001234567", "Carlos Gómez", TipoCliente.ESTUDIANTE);
            app.cinema.venderEntrada("F01", cliente1, "A1");

        } catch (Exception e) {
            System.out.println("Error al inicializar datos de prueba: " + e.getMessage());
        }

        app.iniciar();
    }

    private void iniciar() {
        int opcion = -1;

        while (opcion != 10) {
            String texto = JOptionPane.showInputDialog(
                    "=========================\n"
                            + "        CINEMAUQ\n"
                            + "=========================\n"
                            + "1. Venta de entrada\n"
                            + "2. Cancelar venta\n"
                            + "3. Consultar películas\n"
                            + "4. Consultar funciones\n"
                            + "5. Consultar disponibilidad\n"
                            + "6. Administrar películas\n"
                            + "7. Administrar funciones\n"
                            + "8. Administrar salas\n"
                            + "9. Generar reportes\n"
                            + "10. Salir");

            if (texto == null) {
                break;
            }

            try {
                opcion = Integer.parseInt(texto);
                if (opcion == 1) registrarVenta();
                else if (opcion == 2) cancelarVenta();
                else if (opcion == 3) consultarPeliculas();
                else if (opcion == 4) consultarFunciones();
                else if (opcion == 5) consultarDisponibilidadFuncion();
                else if (opcion == 6) administrarPeliculas();
                else if (opcion == 7) administrarFunciones();
                else if (opcion == 8) administrarSalas();
                else if (opcion == 9) generarReportes();
                else if (opcion == 10) JOptionPane.showMessageDialog(null, "Programa terminado.");
                else JOptionPane.showMessageDialog(null, "Opción no válida.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Escribe un número válido.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    private void registrarVenta() {
        String idCliente = JOptionPane.showInputDialog("Identificación del cliente:");
        if (idCliente == null) return;
        String nombreCliente = JOptionPane.showInputDialog("Nombre del cliente:");
        if (nombreCliente == null) return;

        String tipoClienteTexto = JOptionPane.showInputDialog(
                "Tipo de cliente:\n1. GENERAL\n2. ESTUDIANTE\n3. NIÑO\n4. ADULTO_MAYOR");
        if (tipoClienteTexto == null) return;

        TipoCliente tipoCliente = TipoCliente.GENERAL;
        int tipoC = Integer.parseInt(tipoClienteTexto);
        if (tipoC == 2) tipoCliente = TipoCliente.ESTUDIANTE;
        else if (tipoC == 3) tipoCliente = TipoCliente.NINO;
        else if (tipoC == 4) tipoCliente = TipoCliente.ADULTO_MAYOR;

        Cliente cliente = new Cliente(idCliente, nombreCliente, tipoCliente);

        String codigoFuncion = JOptionPane.showInputDialog("Código de la función (ej. F01):");
        if (codigoFuncion == null) return;

        String codigoAsiento = JOptionPane.showInputDialog("Código del asiento (ej. A1):");
        if (codigoAsiento == null) return;

        try {
            Venta venta = cinema.venderEntrada(codigoFuncion, cliente, codigoAsiento);
            JOptionPane.showMessageDialog(null, "¡Venta registrada con éxito!\n"
                    + "Código Venta: " + venta.getCodigo() + "\n"
                    + "Valor Pagado: $" + venta.getValorPagado() + "\n"
                    + "Fecha y Hora: " + venta.getFechaHora());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la venta: " + e.getMessage());
        }
    }

    private void cancelarVenta() {
        String codigoVenta = JOptionPane.showInputDialog("Ingrese el código de la venta a cancelar (ej. VENTA-1):");
        if (codigoVenta == null) return;

        try {
            cinema.cancelarVenta(codigoVenta);
            JOptionPane.showMessageDialog(null, "Venta cancelada exitosamente y asiento liberado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo cancelar: " + e.getMessage());
        }
    }

    private void consultarPeliculas() {
        List<Pelicula> peliculas = cinema.consultarPeliculas();
        if (peliculas == null || peliculas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay películas registradas.");
            return;
        }

        String texto = "--- PELÍCULAS REGISTRADAS ---\n";
        for (Pelicula p : peliculas) {
            String estado = p.isEstadoPelicula() ? "DISPONIBLE" : "NO DISPONIBLE";
            texto += "Código: " + p.getCodigo() + " | " + p.getNombre() + " | Género: " + p.getGenero()
                    + " | Duración: " + p.getDuracion() + " | Clasificación: " + p.getClasificacionPelicula()
                    + " | Estado: " + estado + "\n";
        }
        JOptionPane.showMessageDialog(null, texto);
    }

    private void consultarFunciones() {
        List<Funcion> funciones = cinema.consultarFunciones();
        if (funciones == null || funciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay funciones programadas.");
            return;
        }

        String texto = "--- FUNCIONES PROGRAMADAS ---\n";
        for (Funcion f : funciones) {
            String nombrePeli = (f.getPelicula() != null) ? f.getPelicula().getNombre() : "Sin película";
            String nombreSala = (f.getSala() != null) ? f.getSala().getNombre() : "Sin sala";
            texto += "Función: " + f.getCodigo() + " | Película: " + nombrePeli
                    + " | Sala: " + nombreSala + " | Fecha: " + f.getFecha() + " | Hora: " + f.getHora()
                    + " | Valor Base: $" + f.getValorBase()
                    + " | Disponibles: " + f.contarAsientosDisponibles() + "\n";
        }
        JOptionPane.showMessageDialog(null, texto);
    }

    private void consultarDisponibilidadFuncion() {
        String codigoFuncion = JOptionPane.showInputDialog("Ingrese el código de la función:");
        if (codigoFuncion == null) return;

        try {
            String ocupacion = cinema.ocupacionFuncion(codigoFuncion);
            List<Asiento> asientos = cinema.consultarDisponibilidad(codigoFuncion);

            String texto = "--- DISPONIBILIDAD ---\n" + ocupacion + "\nAsientos:\n";
            for (Asiento a : asientos) {
                String estado = a.isDisponible() ? "Disponible" : "Ocupado";
                texto += a.getCodigo() + " " + estado + " | ";
            }
            JOptionPane.showMessageDialog(null, texto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private void administrarPeliculas() {
        String subOpcion = JOptionPane.showInputDialog(
                "GESTIÓN DE PELÍCULAS\n1. Registrar\n2. Modificar\n3. Deshabilitar");
        if (subOpcion == null) return;

        try {
            if (subOpcion.equals("1")) {
                String codigo = JOptionPane.showInputDialog("Código:");
                if (codigo == null) return;
                String nombre = JOptionPane.showInputDialog("Nombre:");
                if (nombre == null) return;
                String genero = JOptionPane.showInputDialog("Género:");
                if (genero == null) return;
                int duracionMin = Integer.parseInt(JOptionPane.showInputDialog("Duración en minutos:"));
                LocalTime duracion = LocalTime.of(duracionMin / 60, duracionMin % 60);

                Pelicula p = new Pelicula(codigo, nombre, genero, duracion, true, ClasificacionPelicula.TODOS, new ArrayList<>());
                cinema.registrarPelicula(p);
                JOptionPane.showMessageDialog(null, "Película registrada con éxito.");

            } else if (subOpcion.equals("2")) {
                String codigo = JOptionPane.showInputDialog("Código de la película a modificar:");
                if (codigo == null) return;
                String nombre = JOptionPane.showInputDialog("Nuevo nombre:");
                if (nombre == null) return;
                String genero = JOptionPane.showInputDialog("Nuevo género:");
                if (genero == null) return;
                int duracionMin = Integer.parseInt(JOptionPane.showInputDialog("Nueva duración en minutos:"));
                LocalTime duracion = LocalTime.of(duracionMin / 60, duracionMin % 60);

                Pelicula datosMod = new Pelicula(codigo, nombre, genero, duracion, true, ClasificacionPelicula.TODOS, new ArrayList<>());
                cinema.modificarPelicula(codigo, datosMod);
                JOptionPane.showMessageDialog(null, "Película modificada.");

            } else if (subOpcion.equals("3")) {
                String codigo = JOptionPane.showInputDialog("Código de la película a deshabilitar:");
                if (codigo == null) return;
                cinema.deshabilitarPelicula(codigo);
                JOptionPane.showMessageDialog(null, "Película deshabilitada.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en gestión de películas: " + e.getMessage());
        }
    }

    private void administrarFunciones() {
        try {
            String codigo = JOptionPane.showInputDialog("Código de función:");
            if (codigo == null) return;
            String codPelicula = JOptionPane.showInputDialog("Código de la película:");
            if (codPelicula == null) return;
            String codSala = JOptionPane.showInputDialog("Código de la sala:");
            if (codSala == null) return;
            String horaTexto = JOptionPane.showInputDialog("Hora de la función (HH:mm, ej. 20:00):");
            if (horaTexto == null) return;
            LocalTime hora = LocalTime.parse(horaTexto);
            double valorBase = Double.parseDouble(JOptionPane.showInputDialog("Valor base de la entrada:"));

            Pelicula p = cinema.buscarPelicula(codPelicula);
            Sala s = cinema.buscarSala(codSala);

            if (p != null && s != null) {
                Funcion f = new Funcion(codigo, LocalDate.now(), hora, valorBase, EstadoFuncion.PROGRAMADA, p);
                f.setSala(s);
                f.setPelicula(p);
                cinema.programarFuncion(f);
                JOptionPane.showMessageDialog(null, "Función programada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Película o Sala no encontrada.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al programar función: " + e.getMessage());
        }
    }

    private void administrarSalas() {
        String subOpcion = JOptionPane.showInputDialog(
                "GESTIÓN DE SALAS\n1. Registrar\n2. Modificar\n3. Deshabilitar");
        if (subOpcion == null) return;

        try {
            if (subOpcion.equals("1")) {
                String codigo = JOptionPane.showInputDialog("Código de sala:");
                if (codigo == null) return;
                String nombre = JOptionPane.showInputDialog("Nombre o número de sala:");
                if (nombre == null) return;
                int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Capacidad:"));

                Sala sala = new Sala(codigo, nombre, capacidad, true, new ArrayList<>());
                sala.generarAsientos();
                cinema.registrarSala(sala);
                JOptionPane.showMessageDialog(null, "Sala registrada con éxito.");

            } else if (subOpcion.equals("2")) {
                String codigo = JOptionPane.showInputDialog("Código de la sala a modificar:");
                if (codigo == null) return;
                String nombre = JOptionPane.showInputDialog("Nuevo nombre:");
                if (nombre == null) return;
                int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Nueva capacidad:"));

                Sala datosMod = new Sala(codigo, nombre, capacidad, true, new ArrayList<>());
                cinema.modificarSala(codigo, datosMod);
                JOptionPane.showMessageDialog(null, "Sala modificada.");

            } else if (subOpcion.equals("3")) {
                String codigo = JOptionPane.showInputDialog("Código de la sala a deshabilitar:");
                if (codigo == null) return;
                cinema.deshabilitarSala(codigo);
                JOptionPane.showMessageDialog(null, "Sala deshabilitada / fuera de servicio.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en gestión de salas: " + e.getMessage());
        }
    }

    private void generarReportes() {
        int totalEntradas = cinema.totalEntradasVendidas();
        double totalIngresos = cinema.totalIngresos();
        Pelicula masVendida = cinema.peliculaMasVendida();
        String nombrePeli = (masVendida != null) ? masVendida.getNombre() : "N/A";
        int funcionesHoy = cinema.funcionesProgramadas(LocalDate.now()).size();

        JOptionPane.showMessageDialog(null,
                "=== REPORTE GENERAL DEL CINEMA ===\n"
                        + "• Total de entradas vendidas: " + totalEntradas + "\n"
                        + "• Total de ingresos: $" + totalIngresos + "\n"
                        + "• Película con mayor cantidad de ventas: " + nombrePeli + "\n"
                        + "• Funciones programadas para hoy: " + funcionesHoy);
    }
}
