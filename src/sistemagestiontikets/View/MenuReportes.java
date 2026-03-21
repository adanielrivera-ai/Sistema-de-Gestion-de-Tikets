/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

import java.time.LocalDate;

/**
 *
 * @author alexr
 */
public class MenuReportes {
    
        public MenuReportes() {
        // TODO: inicializar servicio
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
        LocalDate fecha = Consolautil.leerFecha("Ingrese la fecha a consultar");
        // TODO: ticketService.listarPorFecha(fecha)
        // Llamar ticket.imprimirDetalle() por cada resultado
        Consolautil.mostrarInfo("Fecha consultada: " + fecha + ". [pendiente implementación]");
    }
 
    private void reportePorTipoVehiculo() {
        Consolautil.mostrarSubtitulo("Tickets por tipo de vehículo");
        System.out.println("  1. Buseta   2. MicroBus   3. Bus");
        int op   = Consolautil.leerEntero("Seleccione tipo");
        String tipo = switch (op) {
            case 1 -> "Buseta"; case 2 -> "MicroBus"; case 3 -> "Bus";
            default -> "Buseta";
        };
        // TODO: ticketService.listarPorTipoVehiculo(tipo)
        Consolautil.mostrarInfo("Tipo consultado: " + tipo + ". [pendiente implementación]");
    }
 
    private void reportePorTipoPasajero() {
        Consolautil.mostrarSubtitulo("Tickets por tipo de pasajero");
        System.out.println("  1. Regular   2. Estudiante   3. Adulto Mayor");
        int op   = Consolautil.leerEntero("Seleccione tipo");
        String tipo = switch (op) {
            case 1 -> "REGULAR"; case 2 -> "ESTUDIANTE"; case 3 -> "ADULTO_MAYOR";
            default -> "REGULAR";
        };
        // TODO: ticketService.listarPorTipoPasajero(tipo)
        Consolautil.mostrarInfo("Tipo consultado: " + tipo + ". [pendiente implementación]");
    }
 
    private void resumenDiaActual() {
        Consolautil.mostrarSubtitulo("Resumen del día — " + LocalDate.now());
        // TODO: ticketService.listarPorFecha(LocalDate.now())
        // Mostrar total de tickets y total recaudado del día
        Consolautil.mostrarInfo("Resumen del día actual. [pendiente implementación]");
    }
 
    private void totalRecaudado() {
        Consolautil.mostrarSubtitulo("Total recaudado general");
        // TODO: ticketService.calcularTotal()
        Consolautil.mostrarInfo("Total recaudado: $0. [pendiente implementación]");
    }
    
}
