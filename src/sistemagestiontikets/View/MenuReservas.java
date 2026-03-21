/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

import sistemagestiontikets.model.Reserva;
import sistemagestiontikets.service.ReservaService;
import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author alexr
 */
public class MenuReservas {
    private final ReservaService reservaService;
 
    public MenuReservas(ReservaService reservaService) {
        this.reservaService = reservaService;
    }
 
    public void mostrar() {
        int opcion;
        do {
            Consolautil.mostrarTitulo("Gestión de Reservas");
            System.out.println("  1. Crear nueva reserva");
            System.out.println("  2. Cancelar reserva por código");
            System.out.println("  3. Listar reservas activas");
            System.out.println("  4. Historial de reservas de un pasajero");
            System.out.println("  5. Convertir reserva en ticket");
            System.out.println("  6. Verificar reservas vencidas");
            System.out.println("  0. Volver al menú principal");
            Consolautil.mostrarLinea();
 
            opcion = Consolautil.leerEntero("Seleccione una opción");
 
            switch (opcion) {
                case 1 -> crearReserva();
                case 2 -> cancelarReserva();
                case 3 -> listarActivas();
                case 4 -> historialPasajero();
                case 5 -> convertirEnTicket();
                case 6 -> verificarVencidas();
                case 0 -> Consolautil.mostrarInfo("Volviendo al menú principal...");
                default -> Consolautil.mostrarError("Opción no válida.");
            }
 
            if (opcion != 0) Consolautil.pausar();
 
        } while (opcion != 0);
    }
 
    private void crearReserva() {
        Consolautil.mostrarSubtitulo("Crear nueva reserva");
        String cedula        = Consolautil.leerTexto("Cédula del pasajero");
        String placa         = Consolautil.leerTexto("Placa del vehículo");
        LocalDate fechaViaje = Consolautil.leerFecha("Fecha del viaje");
 
        String resultado = reservaService.crearReserva(cedula, placa, fechaViaje);
        if (resultado.startsWith("OK")) Consolautil.mostrarExito(resultado);
        else                            Consolautil.mostrarError(resultado);
    }
 
    private void cancelarReserva() {
        Consolautil.mostrarSubtitulo("Cancelar reserva");
        String codigo = Consolautil.leerTexto("Código de la reserva");
        String resultado = reservaService.cancelarReserva(codigo);
        if (resultado.startsWith("OK")) Consolautil.mostrarExito(resultado);
        else                            Consolautil.mostrarError(resultado);
    }
 
    private void listarActivas() {
        Consolautil.mostrarSubtitulo("Reservas activas");
        List<Reserva> lista = reservaService.listarReservasActivas();
        if (lista.isEmpty()) {
            Consolautil.mostrarInfo("No hay reservas activas en este momento.");
            return;
        }
        Consolautil.mostrarInfo("Reservas activas: " + lista.size());
        Consolautil.mostrarLinea();
        for (Reserva r : lista) { r.imprimirDetalle(); Consolautil.mostrarLinea(); }
    }
 
    private void historialPasajero() {
        Consolautil.mostrarSubtitulo("Historial de reservas");
        String cedula = Consolautil.leerTexto("Cédula del pasajero");
        List<Reserva> lista = reservaService.listarHistorialPasajero(cedula);
        if (lista.isEmpty()) {
            Consolautil.mostrarInfo("El pasajero no tiene reservas registradas.");
            return;
        }
        Consolautil.mostrarInfo("Total reservas: " + lista.size());
        Consolautil.mostrarLinea();
        for (Reserva r : lista) { r.imprimirDetalle(); Consolautil.mostrarLinea(); }
    }
 
    private void convertirEnTicket() {
        Consolautil.mostrarSubtitulo("Convertir reserva en ticket");
        String codigo  = Consolautil.leerTexto("Código de la reserva");
        String origen  = Consolautil.leerTexto("Ciudad de origen");
        String destino = Consolautil.leerTexto("Ciudad de destino");
 
        String resultado = reservaService.convertirEnTicket(codigo, origen, destino);
        if (resultado.startsWith("OK")) Consolautil.mostrarExito(resultado);
        else                            Consolautil.mostrarError(resultado);
    }
 
    private void verificarVencidas() {
        Consolautil.mostrarSubtitulo("Verificar reservas vencidas");
        int canceladas = reservaService.verificarReservasVencidas();
        if (canceladas == 0)
            Consolautil.mostrarInfo("No hay reservas vencidas. Todo en orden.");
        else
            Consolautil.mostrarExito("Se cancelaron " + canceladas
                + " reserva(s) vencidas. Cupos liberados.");
    }
}
