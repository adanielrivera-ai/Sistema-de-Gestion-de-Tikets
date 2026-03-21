/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.service;

import sistemagestiontikets.dao.TicketDAO;
import sistemagestiontikets.model.Pasajero;
import sistemagestiontikets.model.PasajeroRegular;
import sistemagestiontikets.model.PasajeroEstudiante;
import sistemagestiontikets.model.PasajeroAdultoMayor;
import sistemagestiontikets.model.Ticket;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Dev 2. esto para: Servicio que gestiona la venta de tickets en el sistema.
 * @author JAVIER FERNANDEZ
 */
public class TicketService {

    private TicketDAO ticketDAO = new TicketDAO();

    private int contadorRegular = 0;
    private int contadorEstudiante = 0;
    private int contadorAdultoMayor = 0;
    private double totalRecaudado = 0;

    // Dev 2. esto para: lista de festivos colombianos
    private static final List<LocalDate> FESTIVOS = Arrays.asList(
        LocalDate.of(LocalDate.now().getYear(), 1, 1),   // Año Nuevo
        LocalDate.of(LocalDate.now().getYear(), 1, 6),   // Reyes Magos
        LocalDate.of(LocalDate.now().getYear(), 3, 24),  // San Jose
        LocalDate.of(LocalDate.now().getYear(), 5, 1),   // Dia del Trabajo
        LocalDate.of(LocalDate.now().getYear(), 7, 20),  // Independencia
        LocalDate.of(LocalDate.now().getYear(), 8, 7),   // Batalla de Boyaca
        LocalDate.of(LocalDate.now().getYear(), 8, 18),  // Asuncion
        LocalDate.of(LocalDate.now().getYear(), 10, 13), // Dia de la Raza
        LocalDate.of(LocalDate.now().getYear(), 11, 3),  // Todos los Santos
        LocalDate.of(LocalDate.now().getYear(), 11, 17), // Independencia Cartagena
        LocalDate.of(LocalDate.now().getYear(), 12, 8),  // Inmaculada Concepcion
        LocalDate.of(LocalDate.now().getYear(), 12, 25)  // Navidad
    );

    public boolean esFestivo(LocalDate fecha) {
        return FESTIVOS.contains(fecha);
    }

    public void venderTicket(Pasajero pasajero, String placa,
                              String origen, String destino,
                              double tarifaBase, int capacidadMaxima,
                              int contadorPasajeros) {
        if (contadorPasajeros >= capacidadMaxima) {
            System.out.println("No hay cupos disponibles en este vehiculo.");
            return;
        }

        long ticketsHoy = ticketDAO.cargarTickets().stream()
                .filter(t -> t.getCedulaPasajero().equals(pasajero.getCedula()))
                .filter(t -> t.getFechaCompra().equals(LocalDate.now()))
                .count();

        if (ticketsHoy >= 3) {
            System.out.println("El pasajero ya tiene " + ticketsHoy + " tickets comprados hoy. No puede comprar mas.");
            return;
        }

        double descuento = pasajero.calcularDescuento();
        double valorFinal = tarifaBase * (1 - descuento);

        if (esFestivo(LocalDate.now())) {
            valorFinal *= 1.20;
            System.out.println("Dia festivo detectado. Se aplica recargo del 20%.");
        }

        Ticket ticket = new Ticket(
            pasajero.getCedula(),
            pasajero.getNombre(),
            placa,
            origen,
            destino,
            LocalDate.now(),
            valorFinal
        );

        ticketDAO.guardarTicket(ticket);

        totalRecaudado += valorFinal;
        if (pasajero instanceof PasajeroRegular) contadorRegular++;
        else if (pasajero instanceof PasajeroEstudiante) contadorEstudiante++;
        else if (pasajero instanceof PasajeroAdultoMayor) contadorAdultoMayor++;

        System.out.println("Ticket vendido exitosamente. Valor: $" + valorFinal);
        ticket.imprimirDetalle();
    }

    public void mostrarEstadisticas() {
        System.out.println("=== Estadisticas de Tickets ===");
        System.out.println("Total recaudado       : $" + totalRecaudado);
        System.out.println("Pasajeros Regular     : " + contadorRegular);
        System.out.println("Pasajeros Estudiante  : " + contadorEstudiante);
        System.out.println("Pasajeros Adulto Mayor: " + contadorAdultoMayor);
    }

    public void listarTickets() {
        ticketDAO.cargarTickets().forEach(t -> t.imprimirDetalle());
    }
}