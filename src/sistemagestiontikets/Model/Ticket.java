/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

import java.time.LocalDate;

/**
 * Clase que representa un ticket de viaje en el sistema.
 * @author JAVIER FERNANDEZ
 */
public class Ticket implements Imprimible {

    private String cedulaPasajero;
    private String nombrePasajero;
    private String placaVehiculo;
    private String origen;
    private String destino;
    private LocalDate fechaCompra;
    private double valorFinal;

    public Ticket(String cedulaPasajero, String nombrePasajero,
                  String placaVehiculo, String origen, String destino,
                  LocalDate fechaCompra, double valorFinal) {
        this.cedulaPasajero = cedulaPasajero;
        this.nombrePasajero = nombrePasajero;
        this.placaVehiculo = placaVehiculo;
        this.origen = origen;
        this.destino = destino;
        this.fechaCompra = fechaCompra;
        this.valorFinal = valorFinal;
    }

    public String getCedulaPasajero()  { return cedulaPasajero; }
    public String getNombrePasajero()  { return nombrePasajero; }
    public String getPlacaVehiculo()   { return placaVehiculo; }
    public String getOrigen()          { return origen; }
    public String getDestino()         { return destino; }
    public LocalDate getFechaCompra()  { return fechaCompra; }
    public double getValorFinal()      { return valorFinal; }

    @Override
    public String toString() {
        return cedulaPasajero + ";" + nombrePasajero + ";" +
               placaVehiculo + ";" + origen + ";" + destino + ";" +
               fechaCompra + ";" + valorFinal;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== Ticket ===");
        System.out.println("Pasajero  : " + nombrePasajero + " (" + cedulaPasajero + ")");
        System.out.println("Vehiculo  : " + placaVehiculo);
        System.out.println("Origen    : " + origen);
        System.out.println("Destino   : " + destino);
        System.out.println("Fecha     : " + fechaCompra);
        System.out.println("Valor     : $" + valorFinal);
    }
}