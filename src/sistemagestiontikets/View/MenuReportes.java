/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

import sistemagestiontikets.model.Ticket;
import sistemagestiontikets.service.TicketService;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author alexr
 */
public class MenuReportes {
    
private final TicketService ticketService;
 
    public MenuReportes(TicketService ticketService) {
        this.ticketService = ticketService;
    }
 
    public void mostrar() {
        int opcion;
        do {
            Consolautil.mostrarTitulo("Módulo de Reportes");
            System.out.println("  1. Tickets por fecha específica");
            System.out.println("  2. Tickets por tipo de vehículo");
            System.out.println("  3. Tickets por tipo de pasajero");
            System.out.println("  4. Resumen del día actual");
            System.out.println("  5. Total recaudado general");
            System.out.println("  0. Volver al menú principal");
            Consolautil.mostrarLinea();
 
            opcion = Consolautil.leerEntero("Seleccione una opción");
 
            switch (opcion) {
                case 1 -> reportePorFecha();
                case 2 -> reportePorTipoVehiculo();
                case 3 -> reportePorTipoPasajero();
                case 4 -> resumenDiaActual();
                case 5 -> totalRecaudado();
                case 0 -> Consolautil.mostrarInfo("Volviendo al menú principal...");
                default -> Consolautil.mostrarError("Opción no válida.");
            }
 
            if (opcion != 0) Consolautil.pausar();
 
        } while (opcion != 0);
    }
 
    private void reportePorFecha() {
        Consolautil.mostrarSubtitulo("Tickets vendidos por fecha");
        LocalDate fecha = Consolautil.leerFecha("Fecha a consultar");
        List<Ticket> lista = ticketService.listarPorFecha(fecha);
        if (lista.isEmpty()) {
            Consolautil.mostrarInfo("No hay tickets para la fecha " + fecha + ".");
            return;
        }
        Consolautil.mostrarInfo("Tickets encontrados: " + lista.size());
        Consolautil.mostrarLinea();
        for (Ticket t : lista) { t.imprimirDetalle(); Consolautil.mostrarLinea(); }
    }
 
    private void reportePorTipoVehiculo() {
        Consolautil.mostrarSubtitulo("Tickets por tipo de vehículo");
        System.out.println("  1. Buseta   2. MicroBus   3. Bus");
        int op = Consolautil.leerEntero("Seleccione tipo");
        String tipo = switch (op) {
            case 1 -> "Buseta"; case 2 -> "MicroBus"; case 3 -> "Bus";
            default -> "Buseta";
        };
        List<Ticket> lista = ticketService.listarPorTipoVehiculo(tipo);
        if (lista.isEmpty()) {
            Consolautil.mostrarInfo("No hay tickets para el tipo " + tipo + ".");
            return;
        }
        Consolautil.mostrarInfo("Tickets encontrados: " + lista.size());
        Consolautil.mostrarLinea();
        for (Ticket t : lista) { t.imprimirDetalle(); Consolautil.mostrarLinea(); }
    }
 
    private void reportePorTipoPasajero() {
        Consolautil.mostrarSubtitulo("Tickets por tipo de pasajero");
        System.out.println("  1. Regular   2. Estudiante   3. Adulto Mayor");
        int op = Consolautil.leerEntero("Seleccione tipo");
        String tipo = switch (op) {
            case 1 -> "REGULAR"; case 2 -> "ESTUDIANTE"; case 3 -> "ADULTO_MAYOR";
            default -> "REGULAR";
        };
        List<Ticket> lista = ticketService.listarPorTipoPasajero(tipo);
        if (lista.isEmpty()) {
            Consolautil.mostrarInfo("No hay tickets para pasajeros tipo " + tipo + ".");
            return;
        }
        Consolautil.mostrarInfo("Tickets encontrados: " + lista.size());
        Consolautil.mostrarLinea();
        for (Ticket t : lista) { t.imprimirDetalle(); Consolautil.mostrarLinea(); }
    }
 
    private void resumenDiaActual() {
        LocalDate hoy = LocalDate.now();
        Consolautil.mostrarSubtitulo("Resumen del día — " + hoy);
        List<Ticket> lista = ticketService.listarPorFecha(hoy);
        double totalHoy = lista.stream().mapToDouble(Ticket::getValorFinal).sum();
        Consolautil.mostrarInfo("Tickets vendidos hoy : " + lista.size());
        Consolautil.mostrarInfo("Total recaudado hoy  : $" + String.format("%,.0f", totalHoy));
    }
 
    private void totalRecaudado() {
        Consolautil.mostrarSubtitulo("Total recaudado general");
        double total = ticketService.calcularTotalRecaudado();
        Consolautil.mostrarInfo("Total recaudado: $" + String.format("%,.0f", total));
    }
    
}
