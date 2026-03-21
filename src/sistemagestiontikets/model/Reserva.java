/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

import java.time.LocalDate;

/**
 * Clase que representa una reserva en el sistema.
 * Permite reservar un cupo en un vehiculo para una fecha determinada.
 * @author JAVIER FERNANDEZ
 */
public class Reserva implements Imprimible {

    /** Pasajero que realiza la reserva */
    private Pasajero pasajero;
    
    /** Placa del vehiculo reservado */
    private String placaVehiculo;
    
    /** Fecha del viaje reservado */
    private LocalDate fechaViaje;
    
    /** Estado de la reserva: PENDIENTE, CONFIRMADA, CANCELADA */
    private String estado;

    /**
     * Constructor de Reserva
     * @param pasajero pasajero que reserva
     * @param placaVehiculo placa del vehiculo
     * @param fechaViaje fecha del viaje
     * @param estado estado inicial de la reserva
     */
    public Reserva(Pasajero pasajero, String placaVehiculo,
                   LocalDate fechaViaje, String estado) {
        this.pasajero = pasajero;
        this.placaVehiculo = placaVehiculo;
        this.fechaViaje = fechaViaje;
        this.estado = estado;
    }

    /**
     * Retorna el pasajero de la reserva
     * @return pasajero
     */
    public Pasajero getPasajero() { return pasajero; }

    /**
     * Retorna la placa del vehiculo
     * @return placaVehiculo
     */
    public String getPlacaVehiculo() { return placaVehiculo; }

    /**
     * Retorna la fecha del viaje
     * @return fechaViaje
     */
    public LocalDate getFechaViaje() { return fechaViaje; }

    /**
     * Retorna el estado de la reserva
     * @return estado
     */
    public String getEstado() { return estado; }

    /**
     * Actualiza el estado de la reserva
     * @param estado nuevo estado
     */
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== Reserva ===");
        System.out.println("Pasajero  : " + pasajero.getNombre());
        System.out.println("Vehiculo  : " + placaVehiculo);
        System.out.println("Fecha     : " + fechaViaje);
        System.out.println("Estado    : " + estado);
    }
}
