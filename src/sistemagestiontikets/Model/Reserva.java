/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

import java.time.LocalDate;

public class Reserva implements Imprimible {

    private Pasajero pasajero;
    private String placaVehiculo;
    private LocalDate fechaViaje;
    private String estado; // PENDIENTE, CONFIRMADA, CANCELADA

    public Reserva(Pasajero pasajero, String placaVehiculo,
                   LocalDate fechaViaje, String estado) {
        this.pasajero = pasajero;
        this.placaVehiculo = placaVehiculo;
        this.fechaViaje = fechaViaje;
        this.estado = estado;
    }

    public Pasajero getPasajero()       { return pasajero; }
    public String getPlacaVehiculo()    { return placaVehiculo; }
    public LocalDate getFechaViaje()    { return fechaViaje; }
    public String getEstado()           { return estado; }
    public void setEstado(String estado){ this.estado = estado; }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== Reserva ===");
        System.out.println("Pasajero  : " + pasajero.getNombre());
        System.out.println("Vehiculo  : " + placaVehiculo);
        System.out.println("Fecha     : " + fechaViaje);
        System.out.println("Estado    : " + estado);
    }
}
