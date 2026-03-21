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
import java.time.LocalDate;
import java.util.List;

public class TicketService {

    private TicketDAO ticketDAO = new TicketDAO();

    private int contadorRegular = 0;
    private int contadorEstudiante = 0;
    private int contadorAdultoMayor = 0;
    private double totalRecaudado = 0;

    public void venderTicket(Pasajero pasajero, String placa,
                              String origen, String destino,
                              double tarifaBase, int capacidadMaxima,
                              int contadorPasajeros) {
        // Validar cupos disponibles
        if (contadorPasajeros >= capacidadMaxima) {
            System.out.println("No hay cupos disponibles en este vehiculo.");
            return;
        }

        // Validar maximo 3 tickets por pasajero por dia
        long ticketsHoy = ticketDAO.listarTickets().stream()
                .filter(t -> t.contains(pasajero.getCedula()))
                .filter(t -> t.contains(LocalDate.now().toString()))
                .count();

        if (ticketsHoy >= 3) {
            System.out.println("El pasajero ya tiene 3 tickets comprados hoy.");
            return;
        }

        // Calcular valor final con descuento
        double descuento = pasajero.calcularDescuento();
        double valorFinal = tarifaBase * (1 - descuento);

        // Registrar ticket
        String lineaTicket = pasajero.getCedula() + ";" +
                             pasajero.getNombre() + ";" +
                             placa + ";" +
                             origen + ";" +
                             destino + ";" +
                             LocalDate.now() + ";" +
                             valorFinal;

        ticketDAO.guardarTicket(lineaTicket);

        // Actualizar estadisticas
        totalRecaudado += valorFinal;
        if (pasajero instanceof PasajeroRegular) contadorRegular++;
        else if (pasajero instanceof PasajeroEstudiante) contadorEstudiante++;
        else if (pasajero instanceof PasajeroAdultoMayor) contadorAdultoMayor++;

        System.out.println("Ticket vendido exitosamente. Valor: $" + valorFinal);
    }

    public void mostrarEstadisticas() {
        System.out.println("=== Estadisticas de Tickets ===");
        System.out.println("Total recaudado     : $" + totalRecaudado);
        System.out.println("Pasajeros Regular   : " + contadorRegular);
        System.out.println("Pasajeros Estudiante: " + contadorEstudiante);
        System.out.println("Pasajeros Adulto Mayor: " + contadorAdultoMayor);
    }

    public void listarTickets() {
        ticketDAO.listarTickets().forEach(System.out::println);
    }
}
