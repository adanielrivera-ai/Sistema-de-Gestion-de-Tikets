/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.service;

import sistemagestiontikets.dao.TicketDAO;
import sistemagestiontikets.model.Pasajero;
import sistemagestiontikets.model.Vehiculo;
import java.time.LocalDate;

public class TicketService {

    private TicketDAO ticketDAO = new TicketDAO();

    public void venderTicket(Pasajero pasajero, Vehiculo vehiculo,
                              String origen, String destino) {
        // Validar cupos disponibles
        if (vehiculo.getContadorPasajeros() >= vehiculo.getCapacidadMaxima()) {
            System.out.println("No hay cupos disponibles en este vehiculo.");
            return;
        }

        // Calcular valor final con descuento
        double tarifaBase = vehiculo.getTarifaBase();
        double descuento = pasajero.calcularDescuento();
        double valorFinal = tarifaBase * (1 - descuento);

        // Registrar ticket
        String lineaTicket = pasajero.getCedula() + ";" +
                             pasajero.getNombre() + ";" +
                             vehiculo.getPlaca() + ";" +
                             origen + ";" +
                             destino + ";" +
                             LocalDate.now() + ";" +
                             valorFinal;

        ticketDAO.guardarTicket(lineaTicket);
        vehiculo.setContadorPasajeros(vehiculo.getContadorPasajeros() + 1);
        System.out.println("Ticket vendido exitosamente. Valor: $" + valorFinal);
    }

    public void listarTickets() {
        ticketDAO.listarTickets().forEach(System.out::println);
    }
}
