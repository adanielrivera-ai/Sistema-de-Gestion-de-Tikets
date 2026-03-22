/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.View;

import sistemagestiontikets.model.Ticket;
import sistemagestiontikets.model.Pasajero;
import sistemagestiontikets.model.Vehiculo;
import sistemagestiontikets.service.PersonaService;
import sistemagestiontikets.service.VehiculoService;
import sistemagestiontikets.service.TicketService;
import java.util.List;
/**
 *
 * @author alexr
 */
public class MenuTickets {

    private final TicketService ticketService;
    private VehiculoService vehiculoService;
    private PersonaService  personaService;

 
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

    // 1️⃣ Buscar pasajero
    Pasajero pasajero = personaService.BuscarPorCedula(cedula);
    if (pasajero == null) {
        Consolautil.mostrarError("No existe un pasajero con esa cédula.");
        return;
    }

    // 2️⃣ Buscar vehículo
    Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);
    if (vehiculo == null) {
        Consolautil.mostrarError("No existe un vehículo con esa placa.");
        return;
    }

    // 3️⃣ Llamar al service correctamente
    String resultado = ticketService.venderTicket(
        pasajero,
        placa,
        origen,
        destino,
        vehiculo.getTarifaBase(),
        vehiculo.getCapacidadMaxima(),
        vehiculo.getContadorPasajeros()
    );

    // 4️⃣ Mostrar resultado
    if (resultado.startsWith("OK")) {
        Consolautil.mostrarExito(resultado);
        vehiculo.agregarPasajero(); // si aplica
    } else {
        Consolautil.mostrarError(resultado);
    }
}
 
    private void listarTickets() {
        Consolautil.mostrarSubtitulo("Todos los tickets vendidos");
        List<Ticket> lista;
        lista = ticketService.listarTickets();

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
