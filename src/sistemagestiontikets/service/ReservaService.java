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

public class ReservaService {

    private ReservaDAO reservaDAO = new ReservaDAO();

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

    public void listarReservas() {
        List<String> reservas = reservaDAO.listarReservas();
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            reservas.forEach(System.out::println);
        }
    }
}
