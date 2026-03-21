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

/**
 * Servicio que gestiona la venta de tickets en el sistema.
 * Contiene las reglas de negocio para vender tickets y generar estadisticas.
 * @author JAVIER FERNANDEZ
 */
public class TicketService {

    /** DAO para persistencia de tickets */
    private TicketDAO ticketDAO = new TicketDAO();

    /** Contador de pasajeros regulares */
    private int contadorRegular = 0;
    
    /** Contador de pasajeros estudiantes */
    private int contadorEstudiante = 0;
    
    /** Contador de pasajeros adulto mayor */
    private int contadorAdultoMayor = 0;
    
    /** Total recaudado por ventas */
    private double totalRecaudado = 0;

    /**
     * Vende un ticket validando cupos y limite de tickets por dia
     * @param pasajero pasajero que compra el ticket
     * @param placa placa del vehiculo
     * @param origen ciudad de origen
     * @param destino ciudad de destino
     * @param tarifaBase tarifa base del vehiculo
     * @param capacidadMaxima capacidad maxima del vehiculo
     * @param contadorPasajeros pasajeros actuales en el vehiculo
     */
    public void venderTicket(Pasajero pasajero, String placa,
                              String origen, String destino,
                              double tarifaBase, int capacidadMaxima,
                              int contadorPasajeros) {
        if (contadorPasajeros >= capacidadMaxima) {
            System.out.println("No hay cupos disponibles en este vehiculo.");
            return;
        }

        long ticketsHoy = ticketDAO.listarTickets().stream()
                .filter(t -> t.contains(pasajero.getCedula()))
                .filter(t -> t.contains(LocalDate.now().toString()))
                .count();

        if (ticketsHoy >= 3) {
            System.out.println("El pasajero ya tiene 3 tickets comprados hoy.");
            return;
        }

        double descuento = pasajero.calcularDescuento();
        double valorFinal = tarifaBase * (1 - descuento);

        String lineaTicket = pasajero.getCedula() + ";" +
                             pasajero.getNombre() + ";" +
                             placa + ";" +
                             origen + ";" +
                             destino + ";" +
                             LocalDate.now() + ";" +
                             valorFinal;

        ticketDAO.guardarTicket(lineaTicket);

        totalRecaudado += valorFinal;
        if (pasajero instanceof PasajeroRegular) contadorRegular++;
        else if (pasajero instanceof PasajeroEstudiante) contadorEstudiante++;
        else if (pasajero instanceof PasajeroAdultoMayor) contadorAdultoMayor++;

        System.out.println("Ticket vendido exitosamente. Valor: $" + valorFinal);
    }

    /**
     * Muestra las estadisticas de tickets vendidos
     */
    public void mostrarEstadisticas() {
        System.out.println("=== Estadisticas de Tickets ===");
        System.out.println("Total recaudado     : $" + totalRecaudado);
        System.out.println("Pasajeros Regular   : " + contadorRegular);
        System.out.println("Pasajeros Estudiante: " + contadorEstudiante);
        System.out.println("Pasajeros Adulto Mayor: " + contadorAdultoMayor);
    }

    /**
     * Lista todos los tickets registrados en el sistema
     */
    public void listarTickets() {
        ticketDAO.listarTickets().forEach(System.out::println);
    }
}
