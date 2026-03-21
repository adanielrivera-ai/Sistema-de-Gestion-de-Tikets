/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import sistemagestiontikets.model.Vehiculo;
/**
 * Dev 2. esto para: Clase que representa una reserva en el sistema.
 * @author JAVIER FERNANDEZ
 */
public class Reserva implements Imprimible {

    private static int contadorCodigo = 1;

    private String codigo;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private LocalDateTime fechaCreacion;
    private LocalDate fechaViaje;
    private String estado; // Activa, Convertida, Cancelada

    public Reserva(Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaViaje) {
        this.codigo = String.format("RES-%03d", contadorCodigo++);
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaViaje = fechaViaje;
        this.estado = "Activa";
    }

    public String getCodigo()              { return codigo; }
    public Pasajero getPasajero()          { return pasajero; }
    public Vehiculo getVehiculo()          { return vehiculo; }
    public LocalDateTime getFechaCreacion(){ return fechaCreacion; }
    public LocalDate getFechaViaje()       { return fechaViaje; }
    public String getEstado()              { return estado; }
    public void setEstado(String estado)   { this.estado = estado; }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== Reserva ===");
        System.out.println("Codigo    : " + codigo);
        System.out.println("Pasajero  : " + pasajero.getNombre() + " (" + pasajero.getCedula() + ")");
        System.out.println("Vehiculo  : " + vehiculo.getPlaca());
        System.out.println("Creacion  : " + fechaCreacion);
        System.out.println("Fecha Viaje: " + fechaViaje);
        System.out.println("Estado    : " + estado);
    }
}