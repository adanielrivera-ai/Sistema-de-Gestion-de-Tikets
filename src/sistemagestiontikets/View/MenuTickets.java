/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

import sistemagestiontikets.model.Ticket;
import sistemagestiontikets.service.TicketService;
import java.util.List;
/**
 *
 * @author alexr
 */
public class MenuTickets {

    private final TicketService ticketService;

 
    public MenuTickets(TicketService ticketService) {
        this.ticketService = ticketService;
    }
 
    public void mostrar() {
        int opcion;
        do {
            Consolautil.mostrarTitulo("Gestión de Tickets");
            System.out.println("  1. Vender ticket");
            System.out.println("  2. Listar todos los tickets");
            System.out.println("  0. Volver al menú principal");
            Consolautil.mostrarLinea();
 
            opcion = Consolautil.leerEntero("Seleccione una opción");
 
            switch (opcion) {
                case 1 -> venderTicket();
                case 2 -> listarTickets();
                case 0 -> Consolautil.mostrarInfo("Volviendo al menú principal...");
                default -> Consolautil.mostrarError("Opción no válida.");
            }
 
            if (opcion != 0) Consolautil.pausar();
 
        } while (opcion != 0);
    }
 
    private void venderTicket() {
        Consolautil.mostrarSubtitulo("Vender ticket");

        String cedula  = Consolautil.leerTexto("Cédula del pasajero");
        String placa   = Consolautil.leerTexto("Placa del vehículo");
        String origen  = Consolautil.leerTexto("Ciudad de origen");
        String destino = Consolautil.leerTexto("Ciudad de destino");
 
        // fechaCompra = LocalDate.now() — se asigna en el service
        String resultado = ticketService.venderTicket(cedula, placa, origen, destino);
        if (resultado.startsWith("OK")) Consolautil.mostrarExito(resultado);
        else                            Consolautil.mostrarError(resultado);
    }
 
    private void listarTickets() {
        Consolautil.mostrarSubtitulo("Todos los tickets vendidos");
        List<Ticket> lista = ticketService.listarTodos();

        if (lista.isEmpty()) {
            Consolautil.mostrarInfo("No hay tickets registrados.");
            return;
        }
        for (Ticket t : lista) {
            t.imprimirDetalle();
            Consolautil.mostrarLinea();
        }

    }
}
