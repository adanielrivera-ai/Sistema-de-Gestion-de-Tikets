/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestiontikets.model;

import java.time.LocalDate;

/**
 * Clase que representa un pasajero regular sin descuento.
 * @author JAVIER FERNANDEZ
 */
public class PasajeroRegular extends Pasajero {

    /**
     * Constructor de PasajeroRegular
     * @param cedula cedula de identidad
     * @param nombre nombre completo
     * @param fechaNacimiento fecha de nacimiento
     */
    public PasajeroRegular(String cedula, String nombre, LocalDate fechaNacimiento) {
        super(cedula, nombre, fechaNacimiento);
    }

    @Override
    public double calcularDescuento() {
        return 0.0;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== Pasajero Regular ===");
        System.out.println("Cedula   : " + getCedula());
        System.out.println("Nombre   : " + getNombre());
        System.out.println("Edad     : " + getEdad());
        System.out.println("Descuento: 0%");
    }
}