/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.service;

import sistemagestiontikets.dao.ReservaDAO;
import sistemagestiontikets.model.Pasajero;
import sistemagestiontikets.model.Reserva;
import java.time.LocalDate;
import java.util.List;

/**
 * Servicio que gestiona las reservas en el sistema.
 * Contiene las reglas de negocio para crear y listar reservas.
 * @author JAVIER FERNANDEZ
 */
public class ReservaService {

    /** DAO para persistencia de reservas */
    private ReservaDAO reservaDAO = new ReservaDAO();

    /**
     * Crea una nueva reserva para un pasajero
     * @param pasajero pasajero que realiza la reserva
     * @param placaVehiculo placa del vehiculo a reservar
     * @param fechaViaje fecha del viaje
     */
    public void crearReserva(Pasajero pasajero, String placaVehiculo,
                              LocalDate fechaViaje) {
        Reserva reserva = new Reserva(pasajero, placaVehiculo,
                                      fechaViaje, "PENDIENTE");
        String lineaReserva = pasajero.getCedula() + ";" +
                              pasajero.getNombre() + ";" +
                              placaVehiculo + ";" +
                              fechaViaje + ";" +
                              "PENDIENTE";
        reservaDAO.guardarReserva(lineaReserva);
        System.out.println("Reserva creada exitosamente.");
        reserva.imprimirDetalle();
    }

    /**
     * Lista todas las reservas registradas en el sistema
     */
    public void listarReservas() {
        List<String> reservas = reservaDAO.listarReservas();
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            reservas.forEach(System.out::println);
        }
    }
}