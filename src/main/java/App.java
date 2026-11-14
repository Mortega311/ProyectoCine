import org.example.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class App{
    private Cinema cinema;

    public static void main(String[] args) {
    App app = new App();

       app.cinema= new Cinema("CinemAUQ Centro", "900123456-1", "Calle 21 # 15-45",new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Pelicula pelicula1 = new Pelicula("P001", "Interestelar", "Ciencia Ficción", LocalTime.of(1,45), ClasificacionPelicula.MAYORES_12, true);
        Pelicula pelicula2 = new Pelicula("P002", "El Padrino", "Drama", LocalTime.of(2,05), ClasificacionPelicula.MAYORES_18, true);
        app.cinema.registrarPelicula(pelicula1);
        app.cinema.registrarPelicula(pelicula2);

        Sala sala1 = new Sala("S01", "Sala 1 VIP", 50, true,new ArrayList<>());
        Sala sala2 = new Sala("S02", "Sala 2 Macro XE", 80, true,new ArrayList<>());
        sala1.generarAsientos();
        sala2.generarAsientos();
        app.cinema.registrarSala(sala1);
        app.cinema.registrarSala(sala2);


        Asiento asiento1 = new Asiento("A1", 1, 1, false);
        Asiento asiento2 = new Asiento("B5", 2, 5, true);


        Cliente cliente1 = new Cliente("1094888123", "Carlos Gómez", TipoCliente.ESTUDIANTE);
        Cliente cliente2 = new Cliente("94321789", "María Fernanda", TipoCliente.GENERAL);


        Funcion funcion1 = new Funcion("F001", pelicula1, sala1, LocalDate.of(2026, 6, 15), LocalTime.of(18, 30), 15000.0, EstadoFuncion.PROGRAMADA);
        Funcion funcion2 = new Funcion("F002", pelicula2, sala2, LocalDate.of(2026, 6, 15), LocalTime.of(21, 0), 18000.0, EstadoFuncion.PROGRAMADA);
        app.cinema.programarFuncion(funcion1);
        app.cinema.programarFuncion(funcion2);

        Venta venta1 = new Venta("V01", LocalDateTime.now(), 12000.0, true);
        Venta venta2= new Venta("V02",LocalDateTime.now(2026,11,3,04,30), 15000,true);

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
        String codigoVenta = JOptionPane.showInputDialog("Código de la venta:");
        if (codigoVenta == null) return;

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

        String codigoFuncion = JOptionPane.showInputDialog("Código de la función:");
        if (codigoFuncion == null) return;

        String codigoAsiento = JOptionPane.showInputDialog("Código del asiento (ej. A1):");
        if (codigoAsiento == null) return;

        try {
            Venta venta = cinema.venderEntrada(codigoFuncion, cliente, tipoCliente, codigoAsiento, codigoVenta);
            JOptionPane.showMessageDialog(null, "¡Venta registrada con éxito!\n"
                    + "Código Venta: " + venta.getCodigo() + "\n"
                    + "Valor Pagado: $" + venta.getValorPagado() + "\n"
                    + "Fecha y Hora: " + venta.getFechaHora());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la venta: " + e.getMessage());
        }
    }

    private void cancelarVenta() {
        String codigoVenta = JOptionPane.showInputDialog("Ingrese el código de la venta a cancelar:");
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
        if (peliculas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay películas registradas.");
            return;
        }

        String texto = "--- PELÍCULAS DISPONIBLES ---\n";
        for (Pelicula p : peliculas) {
            texto += "Código: " + p.getCodigo() + " | " + p.getNombre() + " | Género: " + p.getGenero()
                    + " | Duración: " + p.getDuracion() + " min | Clasificación: " + p.getClasificacion() + "\n";
        }
        JOptionPane.showMessageDialog(null, texto);
    }

    private void consultarFunciones() {
        List<Funcion> funciones = cinema.consultarFunciones();
        if (funciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay funciones programadas.");
            return;
        }

        String texto = "--- FUNCIONES PROGRAMADAS ---\n";
        for (Funcion f : funciones) {
            texto += "Función: " + f.getCodigo() + " | Película: " + f.getPelicula().getNombre()
                    + " | Sala: " + f.getSala().getNombre() + " | Hora: " + f.getHora()
                    + " | Valor Base: $" + f.getValorBase() + "\n";
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
                String estado = a.isEstadoAsiento() ? "Ocupado" : "Disponible";
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

        if (subOpcion.equals("1")) {
            String codigo = JOptionPane.showInputDialog("Código:");
            String nombre = JOptionPane.showInputDialog("Nombre:");
            String genero = JOptionPane.showInputDialog("Género:");
            int duracion = Integer.parseInt(JOptionPane.showInputDialog("Duración en minutos:"));
            Pelicula p = new Pelicula(codigo, nombre, genero, duracion, ClasificacionPelicula.TODOS, true);
            cinema.registrarPelicula(p);
            JOptionPane.showMessageDialog(null, "Película registrada con éxito.");
        } else if (subOpcion.equals("2")) {
            String codigo = JOptionPane.showInputDialog("Código de la película a modificar:");
            String nombre = JOptionPane.showInputDialog("Nuevo nombre:");
            String genero = JOptionPane.showInputDialog("Nuevo género:");
            int duracion = Integer.parseInt(JOptionPane.showInputDialog("Nueva duración:"));
            Pelicula datosMod = new Pelicula(codigo, nombre, genero, duracion, ClasificacionPelicula.TODOS, true);
            cinema.modificarPelicula(codigo, datosMod);
            JOptionPane.showMessageDialog(null, "Película modificada.");
        } else if (subOpcion.equals("3")) {
            String codigo = JOptionPane.showInputDialog("Código de la película a deshabilitar:");
            cinema.deshabilitarPelicula(codigo);
            JOptionPane.showMessageDialog(null, "Película deshabilitada.");
        }
    }

    private void administrarFunciones() {
        String codigo = JOptionPane.showInputDialog("Código de función:");
        String codPelicula = JOptionPane.showInputDialog("Código de la película:");
        String codSala = JOptionPane.showInputDialog("Código de la sala:");
        double valorBase = Double.parseDouble(JOptionPane.showInputDialog("Valor base de la entrada:"));

        Pelicula p = cinema.buscarPelicula(codPelicula);
        Sala s = cinema.buscarSala(codSala);

        if (p != null && s != null) {
            Funcion f = new Funcion(codigo, p, s, LocalDate.now(), LocalTime.of(20, 0), valorBase, EstadoFuncion.PROGRAMADA);
            try {
                cinema.programarFuncion(f);
                JOptionPane.showMessageDialog(null, "Función programada con éxito.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Película o Sala no encontrada.");
        }
    }

    private void administrarSalas() {
        String subOpcion = JOptionPane.showInputDialog(
                "GESTIÓN DE SALAS\n1. Registrar\n2. Modificar\n3. Deshabilitar");
        if (subOpcion == null) return;

        if (subOpcion.equals("1")) {
            String codigo = JOptionPane.showInputDialog("Código de sala:");
            String nombre = JOptionPane.showInputDialog("Nombre o número de sala:");
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Capacidad:"));
            Sala sala = new Sala(codigo, nombre, capacidad, true, new ArrayList<>());
            sala.generarAsientos();
            cinema.registrarSala(sala);
            JOptionPane.showMessageDialog(null, "Sala registrada con éxito.");
        } else if (subOpcion.equals("2")) {
            String codigo = JOptionPane.showInputDialog("Código de la sala a modificar:");
            String nombre = JOptionPane.showInputDialog("Nuevo nombre:");
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Nueva capacidad:"));
            Sala datosMod = new Sala(codigo, nombre, capacidad, true, new ArrayList<>());
            cinema.modificarSala(codigo, datosMod);
            JOptionPane.showMessageDialog(null, "Sala modificada.");
        } else if (subOpcion.equals("3")) {
            String codigo = JOptionPane.showInputDialog("Código de la sala a deshabilitar:");
            cinema.deshabilitarSala(codigo);
            JOptionPane.showMessageDialog(null, "Sala deshabilitada / fuera de servicio.");
        }
    }

    private void generarReportes() {
        int totalEntradas = cinema.totalEntradasVendidas();
        double totalIngresos = cinema.totalIngresos();
        Pelicula masVendida = cinema.peliculaMasVendida();
        String nombrePeli = (masVendida != null) ? masVendida.getNombre() : "N/A";

        JOptionPane.showMessageDialog(null,
                "=== REPORTE GENERAL DEL CINEMA ===\n"
                        + "• Total de entradas vendidas: " + totalEntradas + "\n"
                        + "• Total de ingresos: $" + totalIngresos + "\n"
                        + "• Película con mayor cantidad de ventas: " + nombrePeli);
    }
}
