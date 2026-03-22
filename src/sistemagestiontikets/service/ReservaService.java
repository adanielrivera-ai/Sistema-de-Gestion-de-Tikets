/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.service;

import sistemagestiontikets.dao.ReservaDAO;
import sistemagestiontikets.dao.TicketDAO;
import sistemagestiontikets.model.Reserva;
import sistemagestiontikets.model.Pasajero;
import sistemagestiontikets.model.Vehiculo;
import sistemagestiontikets.model.Ticket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Dev 2. esto para: Servicio que gestiona las reservas en el sistema.
 * @author JAVIER FERNANDEZ
 */
public class ReservaService {

    private ReservaDAO reservaDAO = new ReservaDAO();
    private TicketDAO ticketDAO = new TicketDAO();
    private TicketService ticketService = new TicketService();

    public void crearReserva(Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaViaje) {
        // Dev 2. esto para: validar 1 reserva activa por pasajero/vehiculo/fecha
        for (Reserva r : reservaDAO.cargarReservas()) {
            if (r.getPasajero().getCedula().equals(pasajero.getCedula()) &&
                r.getVehiculo().getPlaca().equals(vehiculo.getPlaca()) &&
                r.getFechaViaje().equals(fechaViaje) &&
                r.getEstado().equals("Activa")) {
                System.out.println("El pasajero ya tiene una reserva activa para este vehiculo en esa fecha.");
                return;
            }
        }

        // Dev 2. esto para: validar capacidad (tickets + reservas activas <= capacidadMax)
        long reservasActivas = reservaDAO.cargarReservas().stream()
                .filter(r -> r.getVehiculo().getPlaca().equals(vehiculo.getPlaca()))
                .filter(r -> r.getEstado().equals("Activa"))
                .count();

        long ticketsVendidos = ticketDAO.cargarTickets().stream()
                .filter(t -> t.getPlacaVehiculo().equals(vehiculo.getPlaca()))
                .count();

        if (reservasActivas + ticketsVendidos >= vehiculo.getCapacidadMaxima()) {
            System.out.println("No hay cupos disponibles en este vehiculo.");
            return;
        }

        Reserva reserva = new Reserva(pasajero, vehiculo, fechaViaje);
        reservaDAO.guardarReserva(reserva);
        System.out.println("Reserva creada exitosamente.");
        reserva.imprimirDetalle();
    }

    public void cancelarReserva(String codigo) {
        // Dev 2. esto para: cambia estado a Cancelada y libera cupo
        for (Reserva r : reservaDAO.cargarReservas()) {
            if (r.getCodigo().equals(codigo)) {
                if (r.getEstado().equals("Cancelada") || r.getEstado().equals("Convertida")) {
                    System.out.println("La reserva ya fue " + r.getEstado() + ".");
                    return;
                }
                reservaDAO.actualizarEstado(codigo, "Cancelada");
                System.out.println("Reserva " + codigo + " cancelada exitosamente.");
                return;
            }
        }
        System.out.println("No se encontro la reserva con codigo: " + codigo);
    }

    public int verificarVencidas() {
        // Dev 2. esto para: cancela reservas activas de +24h y retorna cantidad cancelada
        int canceladas = 0;
        for (Reserva r : reservaDAO.cargarReservas()) {
            if (r.getEstado().equals("Activa") &&
                r.getFechaCreacion().isBefore(LocalDateTime.now().minusHours(24))) {
                reservaDAO.actualizarEstado(r.getCodigo(), "Cancelada");
                canceladas++;
            }
        }
        System.out.println("Reservas vencidas canceladas: " + canceladas);
        return canceladas;
    }

    public void convertirATicket(String codigo, String origen, String destino, double tarifaBase) {
        // Dev 2. esto para: aplica descuento + festivo, crea Ticket, cambia estado a Convertida
        for (Reserva r : reservaDAO.cargarReservas()) {
            if (r.getCodigo().equals(codigo)) {
                if (!r.getEstado().equals("Activa")) {
                    System.out.println("Solo se pueden convertir reservas Activas.");
                    return;
                }

                double descuento = r.getPasajero().calcularDescuento();
                double valorFinal = tarifaBase * (1 - descuento);

                if (ticketService.esFestivo(LocalDate.now())) {
                    valorFinal *= 1.20;
                    System.out.println("Dia festivo detectado. Se aplica recargo del 20%.");
                }

                Ticket ticket = new Ticket(
                    r.getPasajero().getCedula(),
                    r.getPasajero().getNombre(),
                    r.getVehiculo().getPlaca(),
                    origen,
                    destino,
                    LocalDate.now(),
                    valorFinal
                );

                ticketDAO.guardarTicket(ticket);
                reservaDAO.actualizarEstado(codigo, "Convertida");
                System.out.println("Reserva convertida a ticket exitosamente.");
                ticket.imprimirDetalle();
                return;
            }
        }
        System.out.println("No se encontro la reserva con codigo: " + codigo);
    }

    public List<Reserva> listarActivas() {
        // Dev 2. esto para: filtra reservas con estado Activa
        List<Reserva> activas = new ArrayList<>();
        for (Reserva r : reservaDAO.cargarReservas()) {
            if (r.getEstado().equals("Activa")) {
                activas.add(r);
            }
        }
        return activas;
    }

    public List<Reserva> historialPasajero(String cedula) {
        // Dev 2. esto para: retorna todas las reservas de un pasajero
        List<Reserva> historial = new ArrayList<>();
        for (Reserva r : reservaDAO.cargarReservas()) {
            if (r.getPasajero().getCedula().equals(cedula)) {
                historial.add(r);
            }
        }
        return historial;
    }
}